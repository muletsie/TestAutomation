package rahulshettyacademy.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

@SuppressWarnings("unused")
public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups={"Purchase"}, retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalog = landingPage.applicationLogin(input.get("email"), input.get("password"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProduct(input.get("product"));
		CartPage cartPage = productCatalog.goToCart();
		Boolean match = cartPage.verifyProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkPage = cartPage.goToCheckout();
		checkPage.selectCountry("India");
		ConfirmPage conPage = checkPage.placeOrder();
		String orderFinish1 = conPage.verifyText();
		Assert.assertTrue(orderFinish1.equalsIgnoreCase("Thankyou for the order."));
		// driver.close();
	}
	
	//@Parameters("browser")
	@Test(dependsOnMethods= {"submitOrder"}, retryAnalyzer=Retry.class)
	public void checkHistoryPage() {
		ProductCatalogue productCatalog = landingPage.applicationLogin("mrmulaudzi@yahoo.co.za", "Test1234");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		OrderPage ordersPage = productCatalog.goToOrders();
		Assert.assertTrue(ordersPage.verifyOrder(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getDataFromJasonFile(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrderData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}

/*
 * @DataProvider public Object[][] getData() {
 * 
 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
 * "mrmulaudzi@yahoo.co.za"); map.put("password", "Test1234");
 * map.put("product", "ADIDAS ORIGINAL");
 * 
 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
 * "mrmulaudzi@yahoo.co.za"); map1.put("password", "Test1234");
 * map1.put("product", "IPHONE 13 PRO"); return new Object[][] {{map},{map1}}; }
 */

/*
 * @DataProvider public Object[][] getData() { return new Object[][]
 * {{"mrmulaudzi@yahoo.co.za","Test1234","ADIDAS ORIGINAL"},{
 * "mrmulaudzi@yahoo.co.za","Test1234","IPHONE 13 PRO"}}; }
 */
