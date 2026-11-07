package day9;

import java.time.Duration;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TC02_Login {

		//1) Launch Chrome browser
		WebDriver driver = new ChromeDriver(); 
		
		
		@Test (priority=1)
		void openApp() {
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    	//2) Open URL: https://cloudberrystore.services/
    	driver.get("https://cloudberrystore.services/");
    	driver.manage().window().maximize();
		}
		
		@Test (priority=2, dependsOnMethods = {"openApp"})
		void testLogin() {
    	//3) Click on 'My Account' 
    	//i[@class='fa-solid fa-user']

    	driver.findElement(By.xpath("//i[@class='fa-solid fa-user']")).click();
        
 
    	//4) Click 'Login'
    	driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

    	//5) Enter valid Email and Password
    	driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("uzmashxikh@hotmail.com");
    	
    	//6)
    	driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("uzmauzma"); 
    	driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    	
    	
    	//7) Verify Successful login
    	//Thread.sleep(5000);
    	boolean status = driver.findElement(By.xpath("//h1[normalize-space()='My Account']")).isDisplayed();
    	Assert.assertTrue(true);
    	
		/* System.out.println(status); */
		/*
		 * if (status == true) { System.out.println("Test Passed"); }else {
		 * System.out.println("Test Failed"); }
		 */
    	
		}
		
		@Test (priority=3)
		void closeApp() {
    	driver.quit();
    	
		}
        
        }
