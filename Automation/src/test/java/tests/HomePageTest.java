package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.DriverFactory;

public class HomePageTest {

    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver("chrome");
        DriverFactory.setDriver(driver); // Register driver for listener
        driver.get("https://demo.nopcommerce.com");
        homePage = new HomePage(driver);
    }

    @Test
    public void verifyHomePageTitle() {
        String title = homePage.getPageTitle();
        Assert.assertTrue(title.contains("nopCommerce demo store"),
                "❌ Title does not match! Expected 'nopCommerce demo store' but got: " + title);
        System.out.println("✅ Home page title verified: " + title);
    }

    @Test
    public void verifySearchWorks() {
        homePage.searchProduct("computer");
        Assert.assertTrue(driver.getCurrentUrl().contains("computer"),
                "❌ Search did not navigate to results page!");
        System.out.println("✅ Search works correctly");
    }

    @Test
    public void verifyLoginLinkClickable() {
        homePage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                "❌ Login page not opened!");
        System.out.println("✅ Login link clickable");
    }
    @Test
    public void verifyTwitterLink() throws InterruptedException {
        // Click on the Twitter icon
        homePage.clickTwitterIcon();

        // Get current window handle (main window)
        String mainWindow = driver.getWindowHandle();

        // Switch to the new tab/window
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Verify the URL of the new tab
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://x.com/nopCommerce", "❌ Twitter link is incorrect!");

        System.out.println("✅ Twitter link verified: " + currentUrl);

        // Close the Twitter tab and switch back to main window
        driver.close();
        driver.switchTo().window(mainWindow);
    }


    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
