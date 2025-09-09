package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By loginLink = By.cssSelector(".ico-login");
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By twitterLink = By.cssSelector("a[href='https://twitter.com/nopCommerce']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickLogin() {
        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        login.click();
    }

    public void searchProduct(String product) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(product);
        driver.findElement(searchButton).click();
    }
 // In HomePage.java
    public void clickTwitterIcon() {
        WebElement twitterIcon = driver.findElement(By.cssSelector("a[href*='twitter.com/nopCommerce']"));
        twitterIcon.click();
    }

}
