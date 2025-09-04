package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    public HomePage(WebDriver driver) { this.driver = driver; }

    private By loginLink = By.linkText("Log in");
    private By registerLink = By.linkText("Register");
    private By wishlistLink = By.linkText("Wishlist");
    private By cartLink = By.linkText("Shopping cart");
    private By compareLink = By.linkText("Compare products list");
    private By searchBox = By.id("small-searchterms");
    private By searchBtn = By.cssSelector("button[type='submit'][class*='search-box-button']");
    private By newsletter = By.id("newsletter-email");
    private By newsletterBtn = By.id("newsletter-subscribe-button");

    public void goTo(String url) { driver.get(url); }
    public void clickLogin() { driver.findElement(loginLink).click(); }
    public void clickRegister() { driver.findElement(registerLink).click(); }
    public void clickWishlist() { driver.findElement(wishlistLink).click(); }
    public void clickCart() { driver.findElement(cartLink).click(); }
    public void clickCompare() { driver.findElement(compareLink).click(); }
    public void search(String text) { driver.findElement(searchBox).clear(); driver.findElement(searchBox).sendKeys(text); driver.findElement(searchBtn).click(); }
    public void subscribe(String email) { driver.findElement(newsletter).clear(); driver.findElement(newsletter).sendKeys(email); driver.findElement(newsletterBtn).click(); }
}
