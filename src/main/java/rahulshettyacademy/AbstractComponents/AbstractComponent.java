package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css="[routerlink*='cart']")
	WebElement clickCart;
	@FindBy (css="[routerlink*='myorders']")
	WebElement myOrders;

	public void waitForElementAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementDisAppear() throws InterruptedException {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(1000);
	}

	public CartPage goToCart() {
		clickCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	
	public OrderPage goToOrders() {
		myOrders.click();
		OrderPage ordersPage = new OrderPage(driver);
		return ordersPage;
		
	}
}
