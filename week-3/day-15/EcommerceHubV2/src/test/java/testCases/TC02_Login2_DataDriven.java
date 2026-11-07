package testCases;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TC02_Login2_DataDriven extends BaseClass 
{
		
		@Test (dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	    void testLogin(String email, String pwd)
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.goToLogin();
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPwd(pwd);
			lp.clickLogin();
			AccountPage ap = new AccountPage(driver);
			boolean status = ap.getMyAccountConfirmation().isDisplayed();
			//Assert.assertTrue(status);
			if (status == true) {
				// logout steps
				ap.clickMyAccountDropDown();
				ap.clickLogout();

				// Assert.assertTrue(lp.getMyAccountConfirmation().isDisplayed());
				Assert.assertTrue(status);

			} else

			{
				Assert.assertTrue(false);
			}
			
		}

		public static void main(String[] args) {
			clickMyAccountDropDown
		}
		
	}

