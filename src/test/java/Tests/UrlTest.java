package Tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.Test;

import Base.Utility;

public class UrlTest extends Utility {
	
	@Test
	public void UrlTest() {
		prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\MUSTMOHA\\eclipse-workspace\\Amazon\\src\\test\\java\\Base\\Amazon.properties");
			 prop.load(fis);
			 fis.close();
		}
		catch(Exception e)
		{
			System.out.println("File path is invalid please currect it");
		}
	    String browser = prop.getProperty("browserName");
	    String url = prop.getProperty("url");
	    Utility.launchBrowser(browser);
	    Utility.launchUrl("");
	   
	}

}
