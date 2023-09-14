package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.*;
import com.tutorialsninja.qa.utils.Utilities;


public class Login extends Base{

	public WebDriver driver;
	
	public Login() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		driver = InitializeBrowserAndOpenApplicationURL();
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name="validCredentialsSupplyer")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
	}
	
	
	@Test(priority=1,dataProvider = "validCredentialsSupplyer")
	public void verifyLoginWithValidCredentials(String email,String password) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sravan kumar\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64");
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {
		
		driver.findElement(By.name("email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(dataProp.getProperty("invalidpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualmessage = driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).getText();
		String expectedmessage = dataProp.getProperty("emailPasswordNoMatch");
		Assert.assertEquals(actualmessage, expectedmessage, "Too many attempts");
	}

}
