package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".cartSection h3")
	List<WebElement> cartProduct;
	
	@FindBy (css="[class*='subtotal'] button")
	WebElement checkout;
	
	By submitButton = By.cssSelector("[class*='submit']");
	
	public Boolean verifyProduct(String productName) {
		Boolean match = cartProduct.stream().anyMatch(cartProd -> cartProd.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		waitForElementAppear(submitButton);
		
		CheckoutPage checkPage = new CheckoutPage(driver);
		return checkPage;
	}

}
