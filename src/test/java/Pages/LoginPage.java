package Pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;
	
	private String email         = "input[name='email']";
	private String continueBtn   = "input:below(input[name='email'])";
	private String password      = "input[name='password']";
	private String loginBtn      = "input#signInSubmit:below(input[name='password'])";
	
	
	public LoginPage(Page page) {
		this.page = page;
	}
	
	public void setEmail(String email) {
		page.fill(this.email, email);
	}
	
	public void clickOnContinueBtn() {
		page.click(continueBtn);
	}
	
	public void setPassword(String password) {
		page.fill(this.password, password);
	}
	public HomePage clickOnLoginBtn() {
		page.click(loginBtn);
		return new HomePage(page);
		
	}
}
