package Pages;

import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class ItemPage {
	
	
	private Page   page;
	
	
	private String expectedTitle        = "Buy ASIAN Men's Wonder-13 Sports Running Shoes at Amazon.in";
	private String quantity             = ".a-native-dropdown.a-declarative#quantity";
	private String addToCart            = "input[name='submit.add-to-cart']";
	private String expectedAddedToCart  = "Added to Cart";
	private String actualAddedToCart    = "//*[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold']";
	private String cartCount            = "//*[@id='nav-cart-count']";	
	private String productImg           = ".imgTagWrapper img";
	private String price				= "span.a-price.a-text-price.a-size-medium.apexPriceToPay[data-a-color='price'] span.a-offscreen";
	private String customerReview       = "h2:has-text('Customer reviews')";
	private String productDetails       = "h3:has-text('Product Details')";
	private String stock                = "#availability span";
	private String address				= "#contextualIngressPtLabel_deliveryShortLine span";
	private String paymentMode			= ".a-section.a-spacing-none.icon-content";
	private String relatedProduct		= "//*[@class='a-section sp_offerVertical p13n-asin sp_ltr_offer']//img";
	private String cart					= "//*[@id='nav-cart-count']";
	private String gotoSelectedItem 	= "#add-to-cart-confirmation-image .a-link-normal.sc-product-link";
	private String singOut				= "#nav-item-signout";
	private String login      			= "#nav-link-accountList";
	
	public ItemPage(Page page) {
		this.page = page;
	}
	
	
	
	public boolean verifyProductImg()
	{
		return page.isVisible(productImg);
	}
	
	public boolean verifyStock()
	{
		System.out.println(page.innerText(stock));
		return page.isVisible(stock);
	}
	
	public boolean verifyPaymentMode()
	{
		System.out.println(page.locator(paymentMode).first().innerText());
		return page.locator(paymentMode).first().isVisible();
	}
	
	
	
	public boolean verifyPrice()
	{
		System.out.println(page.innerText(price));
		return page.isVisible(price);
	}

	
	public boolean verifyCustomerReview()
	{
		return page.isVisible(customerReview);
	}
	
	public boolean verifyProductDetails()
	{
		return page.locator(productDetails).first().isVisible();
	}
	
	public boolean verifyProductDeliveryAddress()
	{
		String address = page.locator(this.address).last().innerText();
		System.out.println(address);
		return page.locator(this.address).last().isVisible();
	}
	
	public boolean verifyRelatedProduct()
	{
		return page.locator(relatedProduct).first().isVisible();
	}
	
	public String getTitle() {
		page.waitForLoadState();
		return page.title();
	}
	
	public String getExpectedTitle() {
		return expectedTitle;
		
	}
	
	
	public void selectQuantity(String visibleText)
	{
		Locator quantitieSelecter = page.locator(quantity).first();
		quantitieSelecter.selectOption(new SelectOption().setValue(""+visibleText+""));
		
	}
	
	public void selectLastQuantity()
	{
		Locator quantitieSelecter = page.locator(quantity);
		int last = page.locator("select[name='quantity'] option").count();
		quantitieSelecter.selectOption(new SelectOption().setValue(""+last+""));
		
	}
	
	public void clickOnAddToCart()
	{
		page.click(addToCart);
	}
	
	public void  verifyCartAdded()
	{
		String actual =  page.innerText(actualAddedToCart).trim();
		Assert.assertEquals(actual, expectedAddedToCart);
		
	}
	
	public int getCartCount() {
		int actualCount = Integer.parseInt(page.innerText(cartCount));
		return actualCount;
	}
	
	public CartPage clickOnCart()
	{
		page.click(cart);
		page.waitForLoadState();
		return new CartPage(page);
	}
	
	
	public void goToItmePage()
	{
		page.click(gotoSelectedItem);
		
	}
	
	public LoginPage singOut()
	{
		page.hover(login);
		page.click(singOut);
		return new LoginPage(page);
	}
	
}
