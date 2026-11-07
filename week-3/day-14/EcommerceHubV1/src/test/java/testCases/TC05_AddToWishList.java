package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import testBase.BaseClass;



public class TC05_AddToWishList extends BaseClass{
	
	@Test
	void testAddToWishList() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.goToLogin();
		LoginPage lp = new LoginPage(driver);
		lp.setEmail("uzmashxikh@hotmail.com");
		lp.setPwd("uzmauzma");
		lp.clickLogin();
		
		CategoryPage cp = new CategoryPage(driver);
        cp.clickLaptopsAndNotebooks();
        cp.clickShowAll();
        Thread.sleep(500);
        cp.selectHPProduct();

        ProductPage pp = new ProductPage(driver);
        pp.addToWishlist();

        Assert.assertTrue(pp.isSuccessMessageDisplayed(), "Wishlist message not shown.");
		

	}

}