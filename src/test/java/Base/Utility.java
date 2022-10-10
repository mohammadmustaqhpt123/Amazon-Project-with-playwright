package Base;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;

import Pages.HomePage;




public class Utility extends Config{
	
	
	public static LaunchOptions launchOption() {
		LaunchOptions lp = new LaunchOptions();
		lp.setChannel("chrome");
		return lp;
	}
	
	
	public static Dimension getScreenSize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}
	
	
	public static NewContextOptions contextOption()
	{
		NewContextOptions contextOption = new Browser.NewContextOptions();
//		contextOption.setViewportSize(getScreenSize().width, getScreenSize().width);
		contextOption.setStorageStatePath(Paths.get(System.getProperty("user.dir")+"/jsonFiles/amazonLogin.json"));
//		contextOption.setRecordVideoDir(Paths.get("record"));
		return contextOption;
	}
	
	public static void launchBrowser(String browserName)
	{

		if(browserName.toLowerCase().equals("chrome") )
		{
			Config.playwright = Playwright.create();
			browser = playwright.chromium().launch(launchOption());
			context = browser.newContext(contextOption());
			page = context.newPage();
			homePage = new HomePage(page);

		}
		else if(browserName.toLowerCase().equals("firefox")) {
			Config.playwright = Playwright.create();
			browser = playwright.firefox().launch(new LaunchOptions().setHeadless(false));
			context = browser.newContext(contextOption());
			page = context.newPage();
			homePage = new HomePage(page);
		}
	}
	
	public static void launchUrl(String url)
	{
		page.navigate(url);
	}
	
	public static void startTracing() {
		Utility.context.tracing().start(new Tracing.StartOptions()
				  .setScreenshots(true)
				  .setSnapshots(true)
				  );
	}
	
	
	public static String getScreenShot()
	{
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;
	}
	
	public static void properties()
	{
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\HusqvqarnaPlaywright\\src\\test\\java\\Base\\Husqvqarna.properties");
			prop = new Properties();
			prop.load(file);
		}catch(IOException e) {
			System.out.println(e);
			System.out.println("Properties file is not currect");
		}
	}
	
	
	
	public static void stopTracing(String saveAsName)
	{
		Utility.context.tracing().stop(new Tracing.StopOptions()
				  .setPath(Paths.get("tracing/"+ saveAsName +".zip")));
	}
	
	
	
	public static void saveVideoRecording(String recordingName) 
	{
		FileOutputStream fos;
		FileInputStream fis;
		Properties product;
		try {

		if(page1 == null && page != null && page2 == null) {
			product= new Properties();
			fis = new FileInputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\HusqvqarnaPlaywright\\src\\test\\java\\Base\\videos.properties");
			product.load(fis);
			 fos= new FileOutputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\HusqvqarnaPlaywright\\src\\test\\java\\Base\\videos.properties");
			
			Path path = page.video().path();
			product.setProperty(recordingName, path.toString());
		}
		else if(page2 == null && page1 != null && page != null )
		{
			product = new Properties();
			fis = new FileInputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\HusqvqarnaPlaywright\\src\\test\\java\\Base\\videos.properties");
			product.load(fis);
			fos= new FileOutputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\HusqvqarnaPlaywright\\src\\test\\java\\Base\\videos.properties");
			
			Path path = page.video().path();
			Path path1 = page1.video().path();
			product.setProperty(recordingName, path.toString()+"   "+path1.toString());
		}
		else {
			product = new Properties();
			fis = new FileInputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\HusqvqarnaPlaywright\\src\\test\\java\\Base\\videos.properties");
			product.load(fis);
			 fos= new FileOutputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\HusqvqarnaPlaywright\\src\\test\\java\\Base\\videos.properties");
			
			Path path = page.video().path();
			Path path1 = page1.video().path();
			Path path2 = page2.video().path();
			product.setProperty(recordingName, path.toString()+"   "+path1.toString()+"   "+path2.toString());
		}
		
		product.store(fos, null);
		fos.close();
		fis.close();
		
		}catch(IOException e) {
			
			System.out.println(e);
			System.out.println("video Properties is not proper set");
		}
		
	}

}
