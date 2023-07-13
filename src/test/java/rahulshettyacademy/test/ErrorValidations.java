package rahulshettyacademy.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidations extends BaseTest {
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void runValidation() {
		
		landingPage.applicationLogin("mrmulaudzi@yahoo.co.za", "Test1235");
		//landingPage.getValidationError();
		Assert.assertEquals(landingPage.getValidationError(), "Incorrect email or password.");
		
	}

}
