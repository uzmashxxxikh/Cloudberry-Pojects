package day9;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC06AddtoAffiliate {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	@Test(priority = 1)
	void openApp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait

		// 2) Open URL: https://cloudberrystore.services/

		driver.get("https://cloudberrystore.services");
		driver.manage().window().maximize(); // Maximize the window
	}

	@Test(priority = 2, dependsOnMethods = { "openApp" })
	void login() {
		// 3. Click on 'My Account' Dropmenu

		driver.findElement(By.xpath("//i[@class='fa-solid fa-user']")).click();

		// 4. Click on 'Login' option - //button[normalize-space()='Login']

		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

		// 5. Enter valid email address into the 'E-Mail Address' field

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("uzmashxikh@hotmail.com");

		// 6. Enter valid password into the 'Password' field

		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("uzmauzma");

		// 7. Click on 'Login' button - //button[normalize-space()='Login']

		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

	}

	@Test(priority = 3, dependsOnMethods = { "openApp", "login" })
	void testAddAffiliate() throws InterruptedException {
		// Click on Affiliate link - //a[normalize-space()='Affiliate']

		// Find the element
		WebElement element = driver.findElement(By.xpath("//a[normalize-space()='Affiliate']"));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		WebElement element2 = driver.findElement(By.xpath("//a[normalize-space()='Affiliate']"));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now click
		element2.click();

		driver.findElement(By.xpath("//input[@id='input-company']")).clear();
		driver.findElement(By.xpath("//input[@id='input-company']")).sendKeys("CloudBerry");
		driver.findElement(By.xpath("//input[@id='input-website']")).clear();
		driver.findElement(By.xpath("//input[@id='input-website']")).sendKeys("cloudberry.services");
		driver.findElement(By.xpath("//input[@id='input-tax']")).clear();
		driver.findElement(By.xpath("//input[@id='input-tax']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='input-cheque']")).clear();
		driver.findElement(By.xpath("//input[@id='input-cheque']")).sendKeys("Uzma Shaikh");
		WebElement element3 = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element3);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now click
		element3.click();
		boolean status = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"))
				.isDisplayed();
		if (status == true) {
			//System.out.println("Test Passed");
			Assert.assertTrue(true);
		} else {
			//System.out.println("Test Failed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 4)
	void closeApp() {

		// Close the webdriver
		driver.quit();

	}

}