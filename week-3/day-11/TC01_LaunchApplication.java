package day11;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class TC01_LaunchApplication {

	WebDriver driver; // Launch Browser
	WebDriverWait wait;
	// 1)) Launch Chrome Browser

	@Test(priority = 1)
	@Parameters("browser")
	void openApp(String br) {
		switch (br) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			return;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Open URL: https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services/"); // open your application
		driver.manage().window().maximize(); // maximize the window
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// 3) Validate page title = "Your store of fun"

	@Test(priority = 2, dependsOnMethods = { "openApp" })
	public void testLaunchApplication() {
		// 3) Validate page title = "Your store of fun"
		String title = driver.getTitle();
		Assert.assertEquals(title, "Your store of fun");

		/*
		 * if (title.equals("Your store of fun")) {
		 * 
		 * Assert.assertTrue(true); } else {
		 * 
		 * Assert.assertTrue(false); }
		 */

	}

	@Test(priority = 3)
	public void closeApp() {
		// 4) Close browser
		driver.quit();
	}

}
