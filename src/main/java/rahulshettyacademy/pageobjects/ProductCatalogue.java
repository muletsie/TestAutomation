package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement toaster;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".btn.w-10.rounded");
	By toastContainer = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementAppear(productsBy);
		return products;
	}

	public WebElement getProductName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProduct(String productName) throws InterruptedException {
		WebElement prod = getProductName( productName);
		prod.findElement(addToCart).click();
		waitForElementAppear(toastContainer);
		waitForElementDisAppear();
	}
}
