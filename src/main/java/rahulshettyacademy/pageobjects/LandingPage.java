package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

@SuppressWarnings("unused")
public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")WebElement userName;
	@FindBy(id="userPassword")WebElement userPassword;
	@FindBy(id="login")WebElement submit;
	@FindBy(css="div[aria-label='Incorrect email or password.']")WebElement errorMessage;
	
	public ProductCatalogue applicationLogin(String username, String password) {
		userName.sendKeys(username);
		userPassword.sendKeys(password);
		submit.click();
		
		ProductCatalogue productCatalog = new ProductCatalogue(driver);
		return productCatalog;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getValidationError() {
		waitForWebElementAppear(errorMessage);
		return errorMessage.getText();
		
	}
}
