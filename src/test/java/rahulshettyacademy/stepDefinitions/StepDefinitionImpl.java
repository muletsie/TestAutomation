package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

@SuppressWarnings("unused")
public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalog;
	public CartPage cartPage;
	public ConfirmPage conPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void I_logged_in_with_username_and_password(String username, String password) {
		productCatalog = landingPage.applicationLogin(username, password);
	}

	@When("^I add producname (.+) to cart$")
	public void When_I_add_producname_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProduct(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		cartPage = productCatalog.goToCart();
		Boolean match = cartPage.verifyProduct(productName);
		Assert.assertTrue(match);
		CheckoutPage checkPage = cartPage.goToCheckout();
		checkPage.selectCountry("India");
		conPage = checkPage.placeOrder();
	}

	@Then("^I verify the message (.+) is displayed on ConfirmationPage$")
	public void I_verify_the_message_is_displayed_on_ConfirmationPage(String status) {
		String orderFinish1 = conPage.verifyText();
		Assert.assertTrue(orderFinish1.equalsIgnoreCase(status));
		driver.close();
	}
	
	@Then("I verify the error message {string} is displayed")
	public void I_verify_the_error_message_is_displayed(String string) {
		Assert.assertEquals(landingPage.getValidationError(), string);
		driver.close();
	}
}
