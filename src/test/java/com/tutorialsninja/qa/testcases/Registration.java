package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.*;

import com.tutorialsninja.qa.utils.Utilities;

public class Registration extends Base{
	
public WebDriver driver;

	public Registration() {
		super();
	}

	@BeforeMethod
	public void beforeRegistration() {
		
		driver = InitializeBrowserAndOpenApplicationURL();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@AfterMethod
	public void ClosingRegistration(){
		driver.quit();
	}
	
	@Test(priority=1)
	public void Register() throws InterruptedException {

		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.name("telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.name("password")).sendKeys(dataProp.getProperty("password"));
		driver.findElement(By.name("confirm")).sendKeys(dataProp.getProperty("confirmPassword"));
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualmessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedmessage = dataProp.getProperty("accountcreatedSuccessMessage");
		Assert.assertEquals(actualmessage,expectedmessage);
		
	}
	
	@Test(priority=2)
	public void verifyRegistrationByProvidingAllTheFileds() {
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.name("telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.name("password")).sendKeys(dataProp.getProperty("password"));
		driver.findElement(By.name("confirm")).sendKeys(dataProp.getProperty("confirmPassword"));
		driver.findElement(By.xpath("//label[text()='Yes']")).click();
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualmessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		String expectedmessage = dataProp.getProperty("accountcreatedSuccessMessage");
		Assert.assertEquals(actualmessage,expectedmessage);
		
	}
	

}
