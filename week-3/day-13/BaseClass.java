package day13;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseClass 
{
   
	WebDriver driver; //open the chrome
    
	@BeforeClass
    void openApp()
    {
    driver = new ChromeDriver();
	//1) Launch Chrome browser
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//2) Open URL: https://cloudberrystore.services/
	driver.get("https://cloudberrystore.services/");
	driver.manage().window().maximize();
    }
    
    @AfterClass
	void closeApp()
	{
	driver.quit();
	}
}