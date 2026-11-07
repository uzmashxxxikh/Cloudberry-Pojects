package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import testBase.BaseClass;



public class TC04_CompletePurchase extends BaseClass {

    @Test
    public void testCompletePurchase() throws InterruptedException {
        CategoryPage cp = new CategoryPage(driver);
        cp.clickLaptopsAndNotebooks();
        cp.clickShowAll();
        Thread.sleep(500);
        cp.selectHPProduct();

        ProductPage pp = new ProductPage(driver);
        pp.setDeliveryDate();
        pp.clickAddToCart();
        pp.clickCheckout();
        
        CheckoutPage cop = new CheckoutPage(driver);
        cop.clickLogin();
        
        LoginPage lp = new LoginPage(driver);
		lp.setEmail("uzmashxikh@hotmail.com");
		lp.setPwd("uzmauzma");
		lp.clickLogin();
		
		cop.completeCheckout();
		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertTrue(confirmationPage.isOrderPlaced(), "Order placement failed!");
        

       
    }
}