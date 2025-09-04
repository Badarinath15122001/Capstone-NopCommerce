package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage {
    private WebDriver driver;
    public WishlistPage(WebDriver d){ this.driver = d; }

    private By wishlistRows = By.cssSelector("table.wishlist-table tbody tr");
    private By addToCartBtns = By.cssSelector("button[name^='addtocartbutton']");

    public int getWishlistCount() {
        return driver.findElements(wishlistRows).size();
    }

    public void addFirstToCart() {
        if (driver.findElements(addToCartBtns).size() > 0) {
            driver.findElements(addToCartBtns).get(0).click();
        }
    }
}
