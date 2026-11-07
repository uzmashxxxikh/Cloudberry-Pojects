package day7;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_06AddtoAffiliate {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();
		
		WebElement element=driver.findElement(By.xpath("//a[normalize-space()='Affiliate']"));

		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		Thread.sleep(1000);
		element.click();
		
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("uzmashxikh@hotmail.com");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("uzmauzma");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		
		driver.findElement(By.xpath("//input[@id='input-company']")).sendKeys("CLoudberry");
		driver.findElement(By.xpath("//input[@id='input-website']")).sendKeys("Your sotre of fun");
		
		driver.findElement(By.xpath("//input[@id='input-tax']")).sendKeys("101");
		driver.findElement(By.xpath("//input[@id='input-cheque']")).sendKeys("Owais");
		
	
		WebElement element1=driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
		js.executeScript( "arguments[0].scrollIntoView(true);",element1);
		Thread.sleep(1000);
		element1.click();
		boolean status=driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).isDisplayed();
	
		if(status==true) {
			System.out.println("Test passed");
		}
		else {
			System.out.println("Test failed");
		}
		
		driver.quit();
	}

}