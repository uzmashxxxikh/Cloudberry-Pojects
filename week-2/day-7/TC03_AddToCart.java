package day7;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC03_AddToCart {

	public static void main(String[] args) throws InterruptedException 
	{
		/*
		3rd  Test case - TC03_AddToCart - Continue
		-------
		1. Launch Browser (chrome)
		2. Open URL https://cloudberrystore.services/
		3. Click on Laptops and NoteBooks
		4. Click on Show all Laptops and NoteBooks
		5. Select an item "HP LP3065"
		6. Set Delivery Date
		7. Click on Add to Cart
		8. Validate Item added to the cart successfully
		9. Close WebDriver
		*/


		//1) Launch Chrome browser
		
		WebDriver driver = new ChromeDriver(); //open the chrome
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit wait
		
		//2) Open URL: https://cloudberrystore.services/
		driver.get("https://www.cloudberrystore.services/");
		driver.manage().window().maximize();
		
        //3. Click on Laptops and NoteBooks
		
		driver.findElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']")).click();
		
		//4. Click on Show all Laptops and NoteBooks
		driver.findElement(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']")).click();
		Thread.sleep(5000);
		
		//5. Select an item "HP LP3065"
		WebElement element = driver.findElement(By.xpath("//div[@class='description']//a[contains(text(),'HP LP3065')]"));
		
		// Scroll into view
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element); Same as above 2 lines
		
		//Add a small wait
		Thread.sleep(1000);
		
		//Now click
		element.click();
		
		//	6. Set Delivery Date
		 LocalDate currentDate = LocalDate.now();
	   //  System.out.println("Current Date: " + currentDate);
	    
	     LocalDate deliveryDate = currentDate.plusDays(5);
	   //  System.out.println(deliveryDate);
	     
	     // Convert to format MM/dd/yyyy
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	     String formattedDeliveryDate = deliveryDate.format(formatter);
	  //   System.out.println(formattedDeliveryDate);
	     
	     driver.findElement(By.xpath("//input[@id='input-option-225']")).sendKeys(formattedDeliveryDate);
	     
	     //	7. Click on Add to Cart
	     
	     driver.findElement(By.xpath("//button[@id='button-cart']")).click();
	     
	     //8. Validate Item added to the cart successfully - //div[@class='alert alert-success alert-dismissible']
	     
	    String actualMessage =  driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
		
	    if(actualMessage.contains("Success"))
	    {
	    	System.out.println("Test Passed");
	    }
	    else
	    {
	    	System.out.println("Test Failed");
	    }
	    
	    //	9. Close WebDriver
	    driver.quit();
	}

}