package tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WishlistPage;
import utils.DriverFactory;

public class WishlistTest {
    WebDriver driver;
    WishlistPage addToWishlistPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver("chrome");
        DriverFactory.setDriver(driver);
        driver.get("https://demo.nopcommerce.com/samsung-galaxy-s24-256gb");
        addToWishlistPage = new WishlistPage(driver);
    }

    @Test
    public void addSamsungGalaxyToWishlistTest() {
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        addToWishlistPage.addProductToWishlist();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        String message = addToWishlistPage.getSuccessMessage();
        Assert.assertTrue(message.contains("The product has been added to your wishlist"),
                "❌ Product not added to wishlist! Message: " + message);

        System.out.println("✅ Samsung Galaxy S24 added to wishlist successfully!");
    }

    @Test
    public void moveWishlistItemToCartTest() {
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        addToWishlistPage.addProductToWishlist();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        addToWishlistPage.openWishlistFromFooter();

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("addtocart")));

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        WebElement checkbox = driver.findElement(By.name("addtocart"));
        if (!checkbox.isSelected()) checkbox.click();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        driver.findElement(By.name("addtocartbutton")).click();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        driver.findElement(By.xpath("//a[@href='/cart' and text()='Shopping cart']")).click();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr.cart-item-row, tr")));

        String productName = driver.findElement(By.cssSelector("td.product a.product-name")).getText();
        System.out.println("Product in cart: " + productName);

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(productName.contains("Galaxy") || productName.contains("Samsung"),
                "❌ Product not found in cart!");

        System.out.println("✅ Verified product is present in shopping cart!");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
