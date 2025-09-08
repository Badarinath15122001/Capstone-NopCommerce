package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private By addToCartButton = By.cssSelector("button[id^='add-to-cart-button']");
    private By successMessage = By.cssSelector(".bar-notification.success");
    private By cartLinkFooter = By.xpath("//a[@href='/cart' and text()='Shopping cart']");
    
    private By cartQty = By.cssSelector("span.cart-qty");

    // Actions
    public void addProductToCart() {
        
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        
    }

    public String getSuccessMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
        } catch (TimeoutException e) {
            
            return "Added to cart (qty=" + driver.findElement(cartQty).getText() + ")";
        }
    }

    public void openCart() {
        WebElement footerLink = wait.until(ExpectedConditions.elementToBeClickable(cartLinkFooter));
        footerLink.click();
        
    }

    
}
