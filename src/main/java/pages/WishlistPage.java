package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage {

    private WebDriver driver;

    public By clickElectronics = By.linkText("Electronics");
    public By clickCellPhones = By.linkText("Cell phones");
    public By selectProduct = By.linkText("Samsung Galaxy S24 256GB");
    public By addToWishlistButton = By.xpath("//button[@id='add-to-wishlist-button-22']");
    private By successMessage = By.cssSelector(".bar-notification.success");
    private By footerWishlistLink = By.xpath("//a[@href='/wishlist' and text()='Wishlist']");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProductToWishlist() {
        driver.findElement(addToWishlistButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public void openWishlistFromFooter() {
        driver.findElement(footerWishlistLink).click();
    }
}
