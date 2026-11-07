package day13;

import org.testng.Assert;
import org.testng.annotations.Test;



public class TC03_AddToCart extends BaseClass {

    @Test
    public void testAddToCart() throws InterruptedException {
        CategoryPage cp = new CategoryPage(driver);
        cp.clickLaptopsAndNotebooks();
        cp.clickShowAll();
        Thread.sleep(500);
        cp.selectHPProduct();

        ProductPage pp = new ProductPage(driver);
        pp.setDeliveryDate();
        pp.clickAddToCart();

        Assert.assertTrue(pp.isSuccessMessageDisplayed(), "Add to Cart Failed!");
    }
}