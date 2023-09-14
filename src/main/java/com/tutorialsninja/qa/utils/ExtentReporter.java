package com.tutorialsninja.qa.utils;

import java.io.*;
import java.util.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File("C:\\Users\\sravan kumar\\SeleniumProjectPractice\\TutorialsNinjaProj\\test-output\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Sravan Test Automation");
		sparkReporter.config().setDocumentTitle("Sravan Automation report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File("C:\\Users\\sravan kumar\\SeleniumProjectPractice\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configProp.load(fisConfigProp);
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application url",configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser name",configProp.getProperty("browser"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validpassword"));
		
		return extentReport;
	}

}
