package day10;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC05_AddToWishList {

	
	

	WebDriver driver;   
	  @Test (priority = 1)
	  @Parameters ("browser")
	  void openApp(String br)
		{
		switch (br)
		{
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
		    break;
		default: System.out.println("Invalid Browser");
		return;
		}
		// 1. Launch Browser (chrome)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait\

		// 2) Open URL: https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services");
		driver.manage().window().maximize(); // Maximize the window
	}

	@Test(priority = 2, dependsOnMethods = { "openApp" })
	void testAddToWishList() throws InterruptedException

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		// 3. Click on 'My Account' Dropmenu
		driver.findElement(By.xpath("//i[@class='fa-solid fa-user']")).click();

		// 4. Click on 'Login' option - //button[normalize-space()='Login']

		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		// 5. Enter valid email address into the 'E-Mail Address' field

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sid@cloudberry.services");

		// 6. Enter valid password into the 'Password' field

		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test123");

		// 7. Click on 'Login' button - //button[normalize-space()='Login']

		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		// 3. Click on Laptops and NoteBooks

		Thread.sleep(500); // Explicit wait - //a[normalize-space()='Laptops & Notebooks']
							// //a[normalize-space()='Laptops & Notebooks']
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Laptops & Notebooks']")))
				.click();

		// driver.findElement(By.xpath("//a[normalize-space()='Laptops &
		// Notebooks']")).click();
		Thread.sleep(500);
		// 4. Click on Show all Laptops and NoteBooks -//a[normalize-space()='Show All
		// Laptops & Notebooks']
		driver.findElement(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']")).click();
		// Find the element
		WebElement element = driver.findElement(By.xpath("//a[normalize-space()='HP LP3065']"));

		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		// Optional: Add a small wait after scrolling to let it stabilize
		Thread.sleep(500);

		// Now click
		element.click();

		// Click on Add on heart - add to wishlist -
		// ---relative xpath -> button[@aria-label='Add to Wish
		// List']//i[@class='fa-solid fa-heart']
		// --absolute xpath ->
		// /html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/button[1]/i[1]
		// from xpath using google inspector ->
		// //*[@id="content"]/div[1]/div[2]/form/div/button[1]
		// from Geeta -> //div[@class="btn-group"]/button[1]
		// My own x-path - //div//button//i[@class='fa-solid fa-heart']

		driver.findElement(By.xpath("//div//button//i[@class='fa-solid fa-heart']")).click();

		// Confirm Success - //div[@class='alert alert-success alert-dismissible']

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