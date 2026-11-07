package testCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class TC01_LaunchApplication extends BaseClass 
{
		@Test
		void testLaunchApplication()
		{
		HomePage hp = new HomePage(driver);
		String title = driver.getTitle();
		Assert.assertEquals(title, "Your store of fun");
		}
		
}

