package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AffiliatePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;



public class TC06_AddAffiliate extends BaseClass{
	
	@Test
	void testAddAffiliate() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.goToLogin();
		LoginPage lp = new LoginPage(driver);
		lp.setEmail("uzmashxikh@hotmail.com");
		lp.setPwd("uzmauzma");
		lp.clickLogin();
		 AffiliatePage ap = new AffiliatePage(driver);
	        ap.navigateToAffiliateForm();
	        ap.fillAffiliateDetails("CloudBerry", "cloudberry.services", "123456", "Uzma Shaikh");

	        Assert.assertTrue(ap.isAffiliateAdded(), "Affiliate details not added successfully.");
}
}