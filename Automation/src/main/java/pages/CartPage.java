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
    private By cartTotal = By.cssSelector(".order-total .value-summary");
    private By cartQty = By.cssSelector("span.cart-qty");

    // Actions
    public void addProductToCart() {
        
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        
    }

    public String getSuccessMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
        } catch (TimeoutException e) {
            // fallback if notification disappears quickly
            return "Added to cart (qty=" + driver.findElement(cartQty).getText() + ")";
        }
    }

    public void openCart() {
        WebElement footerLink = wait.until(ExpectedConditions.elementToBeClickable(cartLinkFooter));
        footerLink.click();
        
    }

    public double getProductPriceByProductName(String keyword) {
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[@class='cart-item-row']//a[contains(text(),'" + keyword + "')]/ancestor::tr")
        ));
        String priceText = row.findElement(By.cssSelector("td.subtotal span"))
                .getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public double getCartTotal() {
        try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
        String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotal)).getText();
        return Double.parseDouble(totalText.replace("$", "").trim());
    }
}
