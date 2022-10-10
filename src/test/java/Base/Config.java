package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;
import java.util.*;

import Pages.HomePage;


public class Config {
	public static Playwright       		playwright;
	public static Browser          		browser;
	public static BrowserContext   		context;
	public static Page             		page;
	public static Page             		page1;
	public static Page            		page2;
	public static HomePage         		homePage;
	public static Properties       		prop;
	public static ExtentSparkReporter	spark;
	public static ExtentReports 		extent;
	public static ExtentTest 			logger;
	
	
	
	
	
}
