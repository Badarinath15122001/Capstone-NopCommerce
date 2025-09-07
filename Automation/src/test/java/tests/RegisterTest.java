//package tests;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import pages.RegisterPage;
//import utils.DriverFactory;
//
//import java.util.UUID;
//
//public class RegisterTest {
//
//    WebDriver driver;
//    RegisterPage registerPage;
//
//    @BeforeMethod
//    public void setUp() {
//        driver = DriverFactory.initDriver("chrome");
//        DriverFactory.setDriver(driver); // Register driver for listener
//        driver.get("https://demo.nopcommerce.com/register");
//        registerPage = new RegisterPage(driver);
//    }
//
//    @Test
//    public void validRegistrationTest() {
//        String randomEmail = "user" + UUID.randomUUID().toString().substring(0, 5) + "@demo.com";
//        String password = "Test@123";
//
//        registerPage.selectGender("male");
//        registerPage.enterFirstName("John");
//        registerPage.enterLastName("Doe");
//        registerPage.enterEmail(randomEmail);
//        registerPage.enterPassword(password);
//        registerPage.enterConfirmPassword(password);
//        registerPage.clickRegister();
//
//        String success = registerPage.getSuccessMessage();
//        Assert.assertTrue(success.contains("Your registration completed"),
//                "❌ Registration failed! Expected success message but got: " + success);
//
//        System.out.println("✅ Registration successful with email: " + randomEmail);
//    }
//    @Test
//    public void registrationWithMismatchedPasswordsTest() {
//        String randomEmail = "user" + UUID.randomUUID().toString().substring(0, 5) + "@demo.com";
//
//        registerPage.selectGender("male");
//        registerPage.enterFirstName("Alice");
//        registerPage.enterLastName("Smith");
//        registerPage.enterEmail(randomEmail);
//        registerPage.enterPassword("Password123!");
//        registerPage.enterConfirmPassword("DifferentPass456!");
//        registerPage.clickRegister();
//
//        // Locator for error message under Confirm Password field
//        String errorMessage = driver.findElement(
//                org.openqa.selenium.By.id("ConfirmPassword-error")
//        ).getText();
//
//        Assert.assertTrue(errorMessage.contains("The password and confirmation password do not match."),
//                "❌ Expected mismatch error but got: " + errorMessage);
//
//        System.out.println("⚠️ Registration failed as expected due to mismatched passwords.");
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        DriverFactory.quitDriver();
//    }
//}
package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.DriverFactory;

import java.util.UUID;

public class RegisterTest {

    WebDriver driver;
    RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver("chrome");
        DriverFactory.setDriver(driver);
        driver.get("https://demo.nopcommerce.com/register");
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void validRegistrationTest() {
        String randomEmail = "user" + UUID.randomUUID().toString().substring(0, 5) + "@demo.com";
        String password = "Test@123";

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.selectGender("male");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterFirstName("John");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterLastName("Doe");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterEmail(randomEmail);

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterPassword(password);

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterConfirmPassword(password);

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.clickRegister();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        String success = registerPage.getSuccessMessage();
        Assert.assertTrue(success.contains("Your registration completed"),
                "❌ Registration failed! Expected success message but got: " + success);

        System.out.println("✅ Registration successful with email: " + randomEmail);
    }

    @Test
    public void registrationWithMismatchedPasswordsTest() {
        String randomEmail = "user" + UUID.randomUUID().toString().substring(0, 5) + "@demo.com";

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.selectGender("male");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterFirstName("Alice");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterLastName("Smith");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterEmail(randomEmail);

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterPassword("Password123!");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.enterConfirmPassword("DifferentPass456!");

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        registerPage.clickRegister();

        try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        String errorMessage = driver.findElement(
                org.openqa.selenium.By.id("ConfirmPassword-error")
        ).getText();

        Assert.assertTrue(errorMessage.contains("The password and confirmation password do not match."),
                "❌ Expected mismatch error but got: " + errorMessage);

        System.out.println("⚠️ Registration failed as expected due to mismatched passwords.");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
