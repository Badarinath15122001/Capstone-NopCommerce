package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    public CartPage(WebDriver d){ this.driver = d; }

    private By cartRows = By.cssSelector("table.cart tbody tr");
    private By qtyInput = By.cssSelector("input.qty-input");
    private By updateBtn = By.name("updatecart");
    private By removeCheckbox = By.cssSelector("input.remove-from-cart");

    public int getCartItemsCount() {
        return driver.findElements(cartRows).size();
    }

    public void changeQuantity(String qty) {
        driver.findElement(qtyInput).clear();
        driver.findElement(qtyInput).sendKeys(qty);
        driver.findElement(updateBtn).click();
    }

    public void removeAll() {
        driver.findElements(removeCheckbox).forEach(cb -> { if (!cb.isSelected()) cb.click(); });
        driver.findElement(updateBtn).click();
    }

    public boolean isEmpty() {
        return driver.getPageSource().contains("Your Shopping Cart is empty");
    }
}
