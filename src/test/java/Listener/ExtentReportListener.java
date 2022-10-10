package Listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.Utility;

public class ExtentReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";
	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;
	
	private static ExtentReports init()
	{
		Path path = Paths.get(OUTPUT_FOLDER);
		
		if(!Files.exists(path))
		{
			try {
				Files.createDirectories(path);
				
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("open cart automation test results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "window 10");
		extentReports.setSystemInfo("Author", "Mustaq");
		extentReports.setSystemInfo("Build", "1.1");
		extentReports.setSystemInfo("Team", "OMS");
		extentReports.setSystemInfo("Customer_Name", "NAL");
		
		return extentReports;
		
	}
	
	@Override
	public synchronized void onStart(ITestContext context)
	{
		System.out.println("Test Suite Started!");
	}
	
	@Override 
	public synchronized void onTestStart(ITestResult result)
	{
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid+1, last);
		
		System.out.println(methodName + "srarted");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}
	
	
	public synchronized void onTestSuccess(ITestResult result)
	{
		System.out.println(result.getMethod().getMethodName() + "Passed!");
		test.get().pass("Test Passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		
	}
	
	public synchronized void onTestFailiure(ITestResult result)
	{
		System.out.println(result.getMethod().getMethodName() + "Failed!");
		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(Utility.getScreenShot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	public synchronized void onTestSkipped(ITestResult result)
	{
		System.out.println(result.getMethod().getMethodName() + "Skipped!");
		test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(Utility.getScreenShot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
	
	public synchronized void onTestFailedButWithSuccessPercentage(ITestResult result)
	{
		System.out.println("onTestFailedButWithinSuccessPercentage for" + result.getMethod().getMethodName());
	}
	private Date getTime(long millis)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
}
