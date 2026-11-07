package day7;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC04_CompletePurchase {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * 4th Test case - TC04_CompletePurchase ------- 1. Launch Browser (chrome) 2.
		 * Open URL https://cloudberrystore.services/ 3. Click on Laptops and NoteBooks
		 * 4. Click on Show all Laptops and NoteBooks 5. Select an item "HP LP3065" 6.
		 * Set Delivery Date 7. Click on Add to Cart 9. Go to Checkount 10. Login 11.
		 * Complete Checkout forms 12. Submit order 13. Validate Order COnfirmation 9.
		 * Close WebDriver
		 */

		// 1) Launch Chrome browser

		WebDriver driver = new ChromeDriver(); // open the chrome
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait

		// 2) Open URL: https://cloudberrystore.services/
		driver.get("https://www.cloudberrystore.services/");
		driver.manage().window().maximize();

		// 3. Click on Laptops and NoteBooks

		driver.findElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']")).click();

		// 4. Click on Show all Laptops and NoteBooks
		driver.findElement(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']")).click();
		Thread.sleep(5000);

		// 5. Select an item "HP LP3065"
		WebElement element = driver
				.findElement(By.xpath("//div[@class='description']//a[contains(text(),'HP LP3065')]"));

		// Scroll into view

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", element);

		// ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",
		// element); Same as above 2 lines

		// Add a small wait
		Thread.sleep(1000);

		// Now click
		element.click();

		// 6. Set Delivery Date
		LocalDate currentDate = LocalDate.now();
		// System.out.println("Current Date: " + currentDate);

		LocalDate deliveryDate = currentDate.plusDays(5);
		// System.out.println(deliveryDate);

		// Convert to format MM/dd/yyyy
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String formattedDeliveryDate = deliveryDate.format(formatter);
		// System.out.println(formattedDeliveryDate);

		driver.findElement(By.xpath("//input[@id='input-option-225']")).sendKeys(formattedDeliveryDate);

		// 7. Click on Add to Cart
		// System.out.println("Going to addtocart");
		driver.findElement(By.xpath("//button[@id='button-cart']")).click();
		// System.out.println("Add to cart clicked");

		// 9. Go to Checkount
		// System.out.println("Going to checkout");

		WebElement element1 = driver.findElement(By.xpath("//a[@title='Checkout']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		// Add a small wait
		Thread.sleep(1000);

		// Now click
		element1.click();

		// 10. Login
		driver.findElement(By.xpath("//strong[normalize-space()='login page']")).click();

		// Enter valid Email and Password

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sid@cloudberry.services");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test123");

		// Click 'Login' button
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		// 11. Complete Checkout forms
		// select Shipping address - //select[@id='input-shipping-address']
		WebElement dropdown = driver.findElement(By.xpath("//select[@id='input-shipping-address']"));

		Select select = new Select(dropdown);
		select.selectByIndex(1);

		// select Shipping Method - //button[@id='button-shipping-methods']

		driver.findElement(By.xpath("//button[@id='button-shipping-methods']")).click();

		// click continue
		driver.findElement(By.xpath("//button[@id='button-shipping-method']")).click();

		// Select Payment method - //button[@id='button-payment-methods']

		driver.findElement(By.xpath("//button[@id='button-payment-methods']")).click();

		// click continue - //button[@id='button-payment-method']

		driver.findElement(By.xpath("//button[@id='button-payment-method']")).click();

		// confirm order - //button[@id='button-confirm']

		// driver.findElement(By.xpath("//button[@id='button-confirm']")).click();

		WebElement element2 = driver.findElement(By.xpath("//button[@id='button-confirm']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
		// Add a small wait
		Thread.sleep(1000);

		// Now click
		element2.click();

		// 13. Validate Order COnfirmation

		boolean status = driver.findElement(By.xpath("//h1[normalize-space()='Your order has been placed!']"))
				.isDisplayed();
		if (status == true) {
			System.out.println("Test Passed");
		} else
			System.out.println("Test Failed");

		// 9. Close WebDriver
		driver.quit();

	}

}