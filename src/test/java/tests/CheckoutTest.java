package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CheckoutPage;
import utils.DriverFactory;

public class CheckoutTest {
    WebDriver driver;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
    	
        driver = DriverFactory.initDriver("chrome");
        DriverFactory.setDriver(driver);
        driver.get("https://demo.nopcommerce.com/samsung-galaxy-s24-256gb");
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void verifyCheckoutPageNavigation() {
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.addSamsungGalaxyToCart();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.goToCheckout();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(checkoutPage.isOnCheckoutPage(), "❌ Not on Checkout Page!");
        System.out.println("✅ Redirected to Checkout Page successfully!");
    }

    @Test
    public void checkoutAsGuestWithMoneyOrder() {
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.addSamsungGalaxyToCart();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.goToCheckout();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.checkoutAsGuest();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.fillBillingDetails("John", "Doe", "johndoe@testmail.com", "India","Telangana");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.continueShipping();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.selectPaymentMethod("Money Order");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.continuePaymentInfo();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.confirmOrder();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(checkoutPage.isOrderConfirmed(), "❌ Order not confirmed!");
        System.out.println("✅ Order placed successfully with Money Order!");
    }

    @Test
    public void checkoutAsGuestWithCreditCard() {
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.addSamsungGalaxyToCart();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.goToCheckout();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.checkoutAsGuest();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.fillBillingDetails("Jane", "Smith", "janesmith@testmail.com", "India","Telangana");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.continueShipping();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.selectPaymentMethod("Credit Card");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.enterCreditCardDetails();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.confirmOrder();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(checkoutPage.isOrderConfirmed(), "❌ Order not confirmed!");
        System.out.println("✅ Order placed successfully with Credit Card!");
    }

    @Test
    public void downloadInvoiceAfterCheckout() {
        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.addSamsungGalaxyToCart();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.goToCheckout();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.checkoutAsGuest();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.fillBillingDetails("Alice", "Brown", "alice@testmail.com", "India","Telangana");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.continueShipping();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.selectPaymentMethod("Money Order");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.continuePaymentInfo();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.confirmOrder();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue(checkoutPage.isOrderConfirmed(), "❌ Order not confirmed!");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.openOrderDetails();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        checkoutPage.downloadInvoicePDF();

        System.out.println("✅ Invoice PDF downloaded successfully!");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
