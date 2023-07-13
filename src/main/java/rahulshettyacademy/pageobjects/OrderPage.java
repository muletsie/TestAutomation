package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy (css="tbody tr td:nth-child(3)")List<WebElement> orderProduct;
	
	public Boolean verifyOrder(String productName) {
		Boolean match = orderProduct.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}

}
