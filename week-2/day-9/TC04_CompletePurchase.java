package day9;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
Test case - Complete Purchase
-------
1. Launch Browser (chrome)
2. Open URL  https://cloudberrystore.services/
3. Click on Laptops and NoteBooks
4. Click on Show all Laptops and NoteBooks
5. Select an item "HP LP3065"
6. Set Delivery Date
7. Click on Add to Cart
9. Go to Checkout
10. Login
11. Complete Checkout forms
12. Submit order
13. Validate Order Confirmation
9. Close WebDriver
*/
public class TC04_CompletePurchase {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	@Test(priority = 1)
	void openApp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Implicit wait

		// 2. Open URL https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();
	}

	@Test(priority = 2, dependsOnMethods = { "openApp" })
	void addToCart() throws InterruptedException {

		// 3. Click on Laptops and NoteBooks
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Laptops & Notebooks']"))).click();

		// 4. Click on Show all Laptops and NoteBooks
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']"))).click();

		// 5. Select an item "HP LP3065"
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='HP LP3065']")));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now click safely via fresh reference
		WebElement freshElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='HP LP3065']")));
		freshElement.click();

		// 6. Set Delivery Date
		WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='input-option-225']")));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now clear
		element1.clear();

		LocalDate today = LocalDate.now(); // Got today's date

		// Add 5 days to today's date
		LocalDate deliveryDate = today.plusDays(5);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDeliveryDate = deliveryDate.format(formatter);
		element1.sendKeys(formattedDeliveryDate); // set delivery date

		// 7. Click on Add to Cart
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='button-cart']"))).click();
	}

	@Test(priority = 3, dependsOnMethods = { "openApp", "addToCart" })
	void checkout() throws InterruptedException {
		// 9. Go to Checkout

		// Find the element with wait
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Checkout']//i[@class='fa-solid fa-share']")));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now click
		element2.click();
	}

	@Test(priority = 4, dependsOnMethods = { "openApp", "addToCart", "checkout" })
	void login() {
		// Click on Login Page
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[normalize-space()='login page']"))).click();

		// 10. Login
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='input-email']"))).sendKeys("uzmashxikh@hotmail.com");

		// Enter valid password into the 'Password' field
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("uzmauzma");

		// Click on 'Login' button
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	}

	@Test(priority = 5, dependsOnMethods = { "openApp", "addToCart", "checkout", "login" })
	void testCompletePurchase() throws InterruptedException {
		// 11. Complete Checkout forms
		// Select Shipping address
		WebElement dropDown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='input-shipping-address']")));

		Select select = new Select(dropDown);
		int optionCount = select.getOptions().size();
		
		if (optionCount > 1) {
			select.selectByIndex(1); // Select the saved address if available
		} else if (optionCount == 1) {
			select.selectByIndex(0); // Select the default option if only one exists
		}

		// Choose shipping method
		wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-methods"))).click();

		// Select default flat shipping
		wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method"))).click();

		// Choose Payment Method
		wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-methods"))).click();

		// Select the default - Cash on Delivery
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='button-payment-method']"))).click();

		// Click on Confirm
		WebElement confirmOrderButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='text-end']//button[contains(text(),'Confirm')]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmOrderButton);

		// Wait for a moment in case there are animations or overlays
		Thread.sleep(1000);

		// Re-locate to avoid stale references
		WebElement confirmButtonAgain = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='text-end']//button[contains(text(),'Confirm')]")));
		
		// Bypass overlay issue and click using JS
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmButtonAgain);

		// Validate the order has been placed successfully
		boolean status = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h1[normalize-space()='Your order has been placed!']"))).isDisplayed();
		
		if (status == true) {
			System.out.println("Test Passed");
			Assert.assertTrue(true);
		} else {
			System.out.println("Test Failed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 6)
	void closeApp() {
		// Close the webdriver
		driver.quit();
	}
}