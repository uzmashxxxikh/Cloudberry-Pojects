package day13;

import org.testng.Assert;
import org.testng.annotations.Test;



public class TC05_AddToWishList extends BaseClass{
	
	@Test
	void testAddToWishList() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.goToLogin();
		LoginPage lp = new LoginPage(driver);
		lp.setEmail("sid@cloudberry.services");
		lp.setPwd("Test123");
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