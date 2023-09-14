package com.tutorialsninja.qa.base;

import java.time.Duration;
import java.util.*;
import java.io.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	
	WebDriver driver;
    public Properties prop;
    public Properties dataProp;
	
	public Base() {
		
		prop = new Properties();
		File propfile = new File("C:\\Users\\sravan kumar\\SeleniumProjectPractice\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		dataProp = new Properties();
		File dataPropFile = new File("C:\\Users\\sravan kumar\\SeleniumProjectPractice\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try {
			FileInputStream dataFis = new FileInputStream(dataPropFile);
			dataProp.load(dataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		try {
		FileInputStream fis = new FileInputStream(propfile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver InitializeBrowserAndOpenApplicationURL() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sravan kumar\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
