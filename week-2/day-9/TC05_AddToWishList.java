package day9;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC05_AddToWishList {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	@Test(priority = 1)
	void openApp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://cloudberrystore.services");
		driver.manage().window().maximize();
	}

	@Test(priority = 2, dependsOnMethods = { "openApp" })
	void testAddToWishList() throws InterruptedException {
		// 1. Navigate to Login page and authenticate
		driver.findElement(By.xpath("//i[@class='fa-solid fa-user']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("uzmashxikh@hotmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("uzmauzma");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		// 2. Navigate to Laptops & Notebooks category
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Laptops & Notebooks']")))
				.click();

		Thread.sleep(500);
		driver.findElement(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']")).click();

		// 3. Locate, center-scroll, and click the product safely
		WebElement element = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='HP LP3065']"))
		);

		// Scroll the element into the center of the view window to avoid sticky headers
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element
		);
		
		Thread.sleep(1000); // Allow scroll animation to settle

		// Click using JavaScript Executor to bypass any UI interception or viewport boundaries
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		// 4. Add to wishlist and verify success message
		driver.findElement(By.xpath("//div//button//i[@class='fa-solid fa-heart']")).click();

		String actualMessage = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"))
				.getText();
				
		if (actualMessage.contains("Success")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3)
	void closeApp() {
		driver.quit();
	}
}

/* =====================================================================
 * TROUBLESHOOTING & FIX SUMMARY:
 * 
 * 1. What was the issue?
 *    - When using your personal credentials, the test threw a TimeoutException 
 *      or ElementNotInteractableException on the "HP LP3065" laptop link.
 *    - The root cause was that the product was located "below the fold" 
 *      (scrolled out of view). Standard visibility and clickability waits 
 *      require elements to be actively visible on the screen immediately, 
 *      causing Selenium to time out before scrolling occurred. Additionally, 
 *      sticky top navigation headers can intercept standard mouse clicks.
 * 
 * 2. How did we solve it?
 *    - Switched from visibility-based waits to ExpectedConditions.presenceOfElementLocated, 
 *      which finds the element in the HTML DOM structure even if it is not currently 
 *      visible on screen.
 *    - Used JavaScript's `scrollIntoView({block: 'center'})` to bring the laptop 
 *      safely into the middle of the browser window, avoiding sticky header blocks.
 *    - Used a JavaScript click execution (`arguments[0].click()`) to trigger the 
 *      click directly via the browser engine, ensuring the action succeeds reliably 
 *      without interception issues.
 * ===================================================================== */