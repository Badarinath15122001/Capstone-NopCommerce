package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComparePage {
    private WebDriver driver;
    public ComparePage(WebDriver d){ this.driver = d; }

    private By compareTable = By.cssSelector("table.compare-products-table");
    private By clearListLink = By.linkText("Clear list");

    public boolean isProductPresent(String productName) {
        return driver.getPageSource().contains(productName);
    }

    public void clearCompareList() {
        if (driver.findElements(clearListLink).size() > 0) {
            driver.findElement(clearListLink).click();
        }
    }
}
