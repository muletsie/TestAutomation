package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	String orderFinish;
	@FindBy (css="[placeholder='Select Country']")
	WebElement country;
	@FindBy (css="button:nth-child(2) span:nth-child(1)")
	WebElement addCounrty;
	@FindBy (css=".action__submit")
	WebElement submitOrder;
	@FindBy (css="[class*='hero'] ")
	WebElement finishText;
	
	By pageResults = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementAppear(pageResults);
		addCounrty.click();
	}
	
	public ConfirmPage placeOrder() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		submitOrder.click();
		return new ConfirmPage(driver);
	}

}
