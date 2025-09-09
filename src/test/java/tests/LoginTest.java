//this is LoginTest.java
package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    String firstName = "John";
    String lastName = "Doe";
    String email = "testuser" + System.currentTimeMillis() + "@demo.com";
    String password = "Test@123";

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver("chrome");
        DriverFactory.setDriver(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void registerLogoutLoginTest() {
        // Register
        loginPage.register(firstName, lastName, email, password);

        // Logout
        loginPage.logout();

        // Login with correct credentials
        loginPage.login(email, password);

        Assert.assertTrue(loginPage.isMyAccountVisible(),
                "❌ Login failed for registered user!");
        System.out.println("✅ Register → Logout → Login successful!");
    }

    @Test
    public void loginWithWrongCredentialsTest() {
        // Login with wrong credentials (no registration)
        String wrongEmail = "wronguser@demo.com";
        String wrongPassword = "WrongPassword123";

        loginPage.login(wrongEmail, wrongPassword);

        String errorMessage = loginPage.getLoginErrorMessage();
        Assert.assertTrue(errorMessage.contains("Login was unsuccessful"),
                "❌ Error message not displayed for wrong credentials!");
        System.out.println("✅ Login failed as expected with wrong credentials: " + errorMessage);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
