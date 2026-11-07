package day11;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
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
9. Go to Checkount
10. Login
11. Complete Checkout forms
12. Submit order
13. Validate Order COnfirmation
9. Close WebDriver
*/
public class TC04_CompletePurchase {

	WebDriver driver;

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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); // Page load timeout

		// 2. Open URL https://cloudberrystore.services/ with retry logic
		int maxRetries = 3;
		for (int i = 0; i < maxRetries; i++) {
			try {
				driver.get("https://cloudberrystore.services/");
				driver.manage().window().maximize();
				break; // Success, exit loop
			} catch (org.openqa.selenium.TimeoutException e) {
				System.out.println("Attempt " + (i + 1) + " failed to load page. Retrying...");
				if (i == maxRetries - 1) {
					throw e; // Rethrow on last attempt
				}
			}
		}
	}

	private void clickWhenReady(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

		try {
			// Wait a moment after scroll
			Thread.sleep(300);
			element.click();
		} catch (org.openqa.selenium.ElementClickInterceptedException | InterruptedException e) {
			// If regular click fails, use JavaScript click
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	@Test(priority = 2, dependsOnMethods = { "openApp" })
	void addToCart() {

		clickWhenReady(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
		clickWhenReady(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']"));
		clickWhenReady(By.xpath("//a[normalize-space()='HP LP3065']"));

		By deliveryDateField = By.xpath("//input[@id='input-option-225']");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(deliveryDateField));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element1);

		element1.clear();

		LocalDate deliveryDate = LocalDate.now().plusDays(5);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		element1.sendKeys(deliveryDate.format(formatter));

		clickWhenReady(By.xpath("//button[@id='button-cart']"));
	}

	@Test(priority = 3, dependsOnMethods = { "openApp", "addToCart" })
	void checkout() throws InterruptedException {
		// 9. Go to Checkout - //a[@title='Checkout']//i[@class='fa-solid fa-share']

		// Find the element
		WebElement element2 = driver.findElement(By.xpath("//a[@title='Checkout']//i[@class='fa-solid fa-share']"));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now clear
		element2.click();
	}

	@Test(priority = 4, dependsOnMethods = { "openApp", "addToCart", "checkout" })
	void login()

	{
		// Click on Login Page - //strong[normalize-space()='login page']

		driver.findElement(By.xpath("//strong[normalize-space()='login page']")).click();

		// 10. Login

		// 5. Enter valid email address into the 'E-Mail Address' field

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sid@cloudberry.services");

		// 6. Enter valid password into the 'Password' field

		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test123");

		// 7. Click on 'Login' button -
		// driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
	}

	@Test(priority = 5, dependsOnMethods = { "openApp", "addToCart", "checkout", "login" })
	void testCompletePurchase() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// 11. Complete Checkout forms
		// Select Shipping address - - //select[@id='input-shipping-address']

		WebElement dropDown = driver.findElement(By.xpath("//select[@id='input-shipping-address']"));

		Select select = new Select(dropDown);
		select.selectByIndex(1);

		// Choose shipping method - //button[@id='button-shipping-methods']
		WebElement shippingMethodsButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-methods")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", shippingMethodsButton);

		// Wait for modal to appear and select default flat shipping - //button[@id='button-shipping-method']
		Thread.sleep(500); // Brief wait for modal animation
		WebElement shippingMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", shippingMethodButton);

		// Wait for shipping modal to close
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-shipping")));
		Thread.sleep(500); // Additional wait for modal close animation

		// Choose Payment Method - //button[@id='button-payment-methods']
		WebElement paymentMethodsButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-methods")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentMethodsButton);

		// Wait for payment modal to appear and select the default - Cash on Delivery
		Thread.sleep(500); // Brief wait for modal animation
		WebElement paymentMethodButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-method")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentMethodButton);

		// Wait for payment modal to close
		Thread.sleep(1000);

		// Click on Confirm - //div[@class='text-end']//button[contains(text(),'Confirm')]
		WebElement confirmOrderButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='text-end']//button[contains(text(),'Confirm')]")));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmOrderButton);
		Thread.sleep(500);

		// Use JavaScript click to bypass any overlay issues
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmOrderButton);

		// Validate the order has been placed successfully
		boolean status = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//h1[normalize-space()='Your order has been placed!']"))).isDisplayed();
		
		Assert.assertTrue(status, "Order confirmation message not displayed");
	}

	@Test(priority = 6)
	void closeApp() {

		// Close the webdriver
		driver.quit();
	}

}