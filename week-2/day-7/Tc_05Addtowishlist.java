package day7;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tc_05Addtowishlist {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();

		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("uzmashxikh@hotmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("uzmauzma");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		driver.findElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']")).click();
		WebElement element = driver
				.findElement(By.xpath("//div[@class='description']//a[contains(text(),'HP LP3065')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);
		element.click();

		driver.findElement(By.xpath("//button[@aria-label='Add to Wish List']//i[@class='fa-solid fa-heart']")).click();
		boolean status = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"))
				.isDisplayed();

		if (status == true) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed");
		}
		driver.quit();
	}

}