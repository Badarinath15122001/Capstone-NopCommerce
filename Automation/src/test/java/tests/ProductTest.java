package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductPage;
import utils.DriverFactory;

public class ProductTest {
    WebDriver driver;
    ProductPage productPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver("chrome");
        DriverFactory.setDriver(driver);
        driver.get("https://demo.nopcommerce.com/");
        productPage = new ProductPage(driver);
    }

    @Test
    public void searchProductTest() {
        productPage.searchProduct("Samsung Galaxy S24");
        Assert.assertTrue(driver.getPageSource().contains("Samsung Galaxy"),
                "❌ Product search failed!");
        System.out.println("✅ Search for Samsung Galaxy successful!");
    }

    @Test
    public void navigateCategoryTest() {
        productPage.navigateToCategory("Electronics");
        Assert.assertTrue(driver.getTitle().contains("Electronics"),
                "❌ Failed to navigate to Electronics category!");
        System.out.println("✅ Navigated to Electronics category!");
    }

    @Test
    public void sortByPriceTest() {
        productPage.navigateToCategory("Electronics");
        productPage.navigateToCategory("Cell phones");
        productPage.sortBy("Price: Low to High");
        Assert.assertTrue(driver.getPageSource().contains("Cell phones"),
                "❌ Failed to sort by price!");
        System.out.println("✅ Products sorted by Price: Low to High!");
    }

    @Test
    public void compareTwoProductsTest() {
        // Navigate to Cell Phones page
        driver.get("https://demo.nopcommerce.com/cell-phones");

        // Add Samsung Galaxy to compare
        productPage.addProductToCompare("Samsung Galaxy S24 256GB");

        // Add Apple iPhone to compare
        productPage.addProductToCompare("Apple iPhone 16 128GB");

        // Open compare page
        productPage.openComparePage();

        // Verify two products exist
        int count = productPage.getCompareProductsCount();
        Assert.assertEquals(count, 2, "❌ Expected 2 products in compare list but found " + count);

        System.out.println("✅ Both Samsung Galaxy and Apple iPhone are present in compare list!");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
