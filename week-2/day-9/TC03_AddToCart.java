package day9;

import java.time.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

/*
Test case - Add to Cart
-------
1. Launch Browser (chrome)
2. Open URL  https://cloudberrystore.services/
3. Click on Laptops and NoteBooks
4. Click on Show all Laptops and NoteBooks
5. Select an item "HP LP3065"
6. Set Delivery Date
7. Click on Add to Cart
8. Validate Item added to the cart successfully
9. Close WebDriver
*/
public class TC03_AddToCart {

	WebDriver driver = new ChromeDriver();

	@Test(priority = 1)
	void openApp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
		// 2. Open URL https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();
	}

	@Test(priority = 2, dependsOnMethods = { "openApp" })
	void testAddToCart() throws InterruptedException {
		// 3. Click on Laptops and NoteBooks - //a[normalize-space()='Laptops &
		// Notebooks']
		driver.findElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']")).click();

		// 4. Click on Show all Laptops and NoteBooks -//a[normalize-space()='Show All
		// Laptops & Notebooks']
		driver.findElement(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']")).click();

		// 5. Select an item "HP LP3065" - //a[normalize-space()='HP LP3065']
		// Find the element
		WebElement element = driver.findElement(By.xpath("//a[normalize-space()='HP LP3065']"));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now click
		element.click();

		// 6. Set Delivery Date - //input[@id='input-option-225']
		// Find the element
		WebElement element1 = driver.findElement(By.xpath("//input[@id='input-option-225']"));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now clear
		element1.clear();

		// Set the date
		LocalDate today = LocalDate.now(); // Got today's date
		// System.out.println(today);

		// Add 5 days to today's date
		LocalDate deliveryDate = today.plusDays(5);
		// System.out.println(deliveryDate);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDeliveryDate = deliveryDate.format(formatter);
		// System.out.println(formattedDeliveryDate);

		// driver.findElement(By.xpath("//input[@id='input-option-225']")).sendKeys(formattedDeliveryDate);
		// //set delivery date
		element1.sendKeys(formattedDeliveryDate); // set delivery date

		// 7. Click on Add to Cart - //button[@id='button-cart']
		driver.findElement(By.xpath("//button[@id='button-cart']")).click();

		// 8. Validate Item added to the cart successfully - //div[@class='alert
		// alert-success alert-dismissible']
		String actualMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"))
				.getText();
		// System.out.println(actualMessage);
		if (actualMessage.contains("Success")) {
			// System.out.println("Test Passed");
			Assert.assertTrue(true);
		} else {
			// System.out.println("Test Failed");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 3)
	void closeApp() {
		// Close the webdriver
		driver.quit();
	}

}