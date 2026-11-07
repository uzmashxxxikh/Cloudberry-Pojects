package day13;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TC02_Login extends BaseClass 
{	
		@Test
	    void testLogin()
		{
         HomePage hp = new HomePage(driver); 
         hp.clickMyAccount();
         hp.goToLogin();
         LoginPage lp = new LoginPage(driver);
         lp.setEmail("Uzmashxikh@hotmail.com");
         lp.setPwd("uzmauzma");
         lp.clickLogin();
         AccountPage ap = new AccountPage(driver);
         boolean status = ap.getMyAccountConfirmation().isDisplayed();
         Assert.assertTrue(status);
		}
		
		
	}

