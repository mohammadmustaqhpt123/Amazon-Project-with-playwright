package Tests;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserContext.StorageStateOptions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing.StartOptions;

import Base.Utility;
import Pages.HomePage;
import Pages.LoginPage;

public class LoginTest  {
//	
//	@Test
//	public void loginTest() throws InterruptedException {
//		LaunchOptions lo = new LaunchOptions();
//		lo.setHeadless(false);
//		lo.setChannel("chrome");
//		Playwright pw = Playwright.create();
//		Browser browser = pw.chromium().launch(lo);
//		BrowserContext context = browser.newContext(new NewContextOptions());
//		context.tracing().start(new StartOptions().setScreenshots(true).setSnapshots(true));
//		Page page = context.newPage();
//		page.navigate("https://www.amazon.in/");
//		HomePage homePage = new HomePage(page);
//		LoginPage lp = homePage.clickOnLogin();
//		lp.setEmail("mohammadmustaq227@gmail.com");
//		lp.clickOnContinueBtn();
//		lp.setPassword("Mustaq@123");
//		lp.clickOnLoginBtn();
//		Thread.sleep(40000);
//		context.storageState(new StorageStateOptions().setPath(Paths.get(System.getProperty("user.dir")+"./jsonFiles/amazonLogin.json")));
//		
//		
//		
//		
//	}
//	
	@Test(groups = {"Smoke"})
	public void loginTest2() throws InterruptedException {
		Utility.launchBrowser("chrome");
		Utility.launchUrl("https://www.amazon.in/");
		LoginPage lp = Utility.homePage.clickOnLogin();
		lp.setEmail("mohammadmustaq227@gmail.com");
		lp.clickOnContinueBtn();
		lp.setPassword("Mustaq@123");
		lp.clickOnLoginBtn();
		Utility.context.storageState(new StorageStateOptions().setPath(Paths.get(System.getProperty("user.dir")+"/jsonFiles/amazonLogin.json")));
		
	
	}
	
	
	
	

}
