package day10;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TC02_Login {

	  
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
		//1) Launch Chrome browser
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//2) Open URL: https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();
        }
		
		@Test (priority =2, dependsOnMethods = {"openApp"})
	    void testLogin()
		{
        //3) Click on 'My Account' dropdown - xpath - //i[@class='fa-solid fa-user']
		//i[@class='fa-solid fa-user']
		driver.findElement(By.xpath("//i[@class='fa-solid fa-user']")).click();
		
		//4) Click 'Login'
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		
		//5) Enter valid Email and Password
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("sid@cloudberry.services");
		
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Test123");
		
		//4) Click 'Login'
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		//7) Verify successful login
		//Thread.sleep(15000); //putting wait on the code for 15 secs
		boolean status = driver.findElement(By.xpath("//h1[normalize-space()='My Account']")).isDisplayed();
		//System.out.println(status);
		Assert.assertTrue(status);
		
		/*
		 * if (status==true) { System.out.println("Test Passed");
		 * 
		 * } else { System.out.println("Test Failed"); }
		 */
		
		}
		
		@Test (priority =3)
		void closeApp()
		{
		driver.quit();
		}
	}

