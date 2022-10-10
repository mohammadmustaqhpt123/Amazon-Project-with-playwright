package Tests;


import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



import Base.Utility;

public class BrowserTest extends Utility {
	FileInputStream fis;
	
	@Parameters({"BrowserName", "URL"})
	@Test(enabled = true)
	public void ChromeBrowserTest(@Optional("chrome") String browserName, @Optional("https://www.amazon.in") String url) 
	{
		prop = new Properties();
		
		try {
			 fis = new FileInputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\Amazon\\src\\test\\java\\Base\\Amazon.properties");
			 prop.load(fis);
			 fis.close();
		}
		catch(Exception e)
		{
			System.out.println("File path is invalid please currect it");
		}
	    String browser = prop.getProperty("browserName");
	    String Url = prop.getProperty("url");
	    Utility.launchBrowser(browser);
	   

		
	}

}
