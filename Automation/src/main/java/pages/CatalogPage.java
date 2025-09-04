package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage {
    private WebDriver driver;
    public CatalogPage(WebDriver d){ this.driver = d; }

    private By productItems = By.cssSelector(".product-item");

    public int getProductsCount() { return driver.findElements(productItems).size(); }
}
