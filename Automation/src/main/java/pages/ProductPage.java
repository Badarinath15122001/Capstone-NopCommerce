package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;
    public ProductPage(WebDriver d){ this.driver = d; }

    private By addToCartBtn = By.cssSelector("button[id^='add-to-cart-button']");
    private By addToWishlistBtn = By.cssSelector("button[id^='add-to-wishlist-button']");
    private By addToCompareBtn = By.cssSelector("button[id^='add-to-compare-list-button']");
    private By productTitle = By.cssSelector("div.product-name h1");

    public String getTitle() { return driver.findElement(productTitle).getText(); }

    public void addToCart() { driver.findElement(addToCartBtn).click(); }
    public void addToWishlist() { driver.findElement(addToWishlistBtn).click(); }
    public void addToCompare() { driver.findElement(addToCompareBtn).click(); }
}
