package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector("button[type='submit'][class*='search-box-button']");
    private By categoryMenu = By.cssSelector("ul.top-menu.notmobile > li > a");
    private By sortByDropdown = By.id("products-orderby");
    private By compareButton = By.cssSelector("button[class*='add-to-compare-list-button']");
    private By comparePageLink = By.linkText("product comparison");
    private By comparePageLinkFooter = By.linkText("Compare products list");
    private By successNotification = By.cssSelector("div.bar-notification.success");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 1. Search for product
    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchInput.clear();
        searchInput.sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    // 2. Navigate to category (main menu)
    public void navigateToCategory(String categoryName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText(categoryName))).click();
    }

    // 3. Sort products by given option (Price: Low to High, High to Low, etc.)
    public void sortBy(String option) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortByDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }

    // 4. Add product to compare list
    public void addProductToCompare(String productName) {
        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.linkText(productName))
        );

        // Click compare button for that product
        WebElement compareBtn = product.findElement(
                By.xpath("../../..//button[contains(@class,'add-to-compare-list-button')]")
        );
        compareBtn.click();

        // ✅ Wait for success notification to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification));

        // ✅ Wait for notification to disappear (so next click doesn’t overlap)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(successNotification));
    }

    // Open compare page from footer
    public void openComparePage() {
        wait.until(ExpectedConditions.elementToBeClickable(comparePageLinkFooter)).click();
    }

    // Count products in compare list page
    public int getCompareProductsCount() {
        return driver.findElements(By.cssSelector("tr.product-name td a")).size();
    }
}

