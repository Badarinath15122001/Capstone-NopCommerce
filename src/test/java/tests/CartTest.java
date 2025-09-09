package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import utils.DriverFactory;

public class CartTest {
    WebDriver driver;
    CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver("chrome");
        DriverFactory.setDriver(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void addAppleMacBookToCartTest() {
        driver.get("https://demo.nopcommerce.com/apple-macbook-pro");
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

        cartPage.addProductToCart();
        

        Assert.assertTrue(cartPage.getSuccessMessage().contains("added"),
                "❌ Product not added to cart!");
        System.out.println("✅ Apple MacBook Pro added to cart successfully!");
    }

    @Test
    public void removeProductFromCartTest() {
        driver.get("https://demo.nopcommerce.com/apple-macbook-pro");
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

        cartPage.addProductToCart();
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

        cartPage.openCart();
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

        driver.findElement(By.cssSelector("button.remove-btn")).click();
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }

        String emptyMessage = driver.findElement(By.cssSelector(".order-summary-content")).getText();
        Assert.assertTrue(emptyMessage.contains("Your Shopping Cart is empty!"),
                "❌ Cart is not empty after removing product!");
        System.out.println("🗑️ Product removed successfully, cart is empty!");
    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
