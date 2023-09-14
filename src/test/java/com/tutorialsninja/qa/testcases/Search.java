package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends Base{
	
	public WebDriver driver;
	
	public Search() {
		super();
	}
	
	@BeforeMethod
	public void BeforeSearch() {
		driver = InitializeBrowserAndOpenApplicationURL();
	}
	
	@Test(priority=1)
	public void verifyTheSearchForValidProduct() {
		driver.findElement(By.xpath("//div[@id='search']/input[@name='search']")).sendKeys("iphone");
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button[@type='button']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("iPhone")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifyTheSearchWithInvalidProduct() {
		driver.findElement(By.xpath("//div[@id='search']/input[@name='search']")).sendKeys("Honda");
		driver.findElement(By.xpath("//span[@class='input-group-btn']/button[@type='button']")).click();
		
		String actualstring = driver.findElement(By.xpath("//div[@id='content']/p[2]")).getText();
		Assert.assertEquals(actualstring, "There is no product that matches the search criteria.");
		
	}
	
	@AfterMethod
	public void afterSearch() {
		driver.quit();
	}

}
