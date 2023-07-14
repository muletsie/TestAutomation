package seleium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class openBrowser {
	@Test
	public void goTo() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Khuthadzo Mulaudzi\\eclipse-workspace\\SeleniumGrid\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://investecezval.uat.lightstoneproperty.co.za/Account/SignIn?ReturnUrl=%2fDashboard");
		driver.findElement(By.id("Username")).sendKeys("amanda.investeclive@lsmail.co.za");
		driver.findElement(By.id("Password")).sendKeys("Test!1234");
		driver.findElement(By.cssSelector("button")).click();
		String loggedUser = driver.findElement(By.cssSelector("a[class='dropdown-toggle']")).getText();
		Assert.assertEquals("Hi,Amanda Dovey - LS Business", loggedUser);
		driver.close();
	}

}
