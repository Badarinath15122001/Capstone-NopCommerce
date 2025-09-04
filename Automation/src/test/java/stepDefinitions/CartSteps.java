package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class CartSteps {
    HomePage home = new HomePage(DriverManager.getDriver());
    CartPage cart = new CartPage(DriverManager.getDriver());

    @When("I go to cart page")
    public void goToCart() {
        home.clickCart();
    }

    @Then("cart should contain that product")
    public void cartContains() {
        Assert.assertTrue(cart.getCartItemsCount() > 0);
    }

    @When("I update quantity to {string}")
    public void updateQty(String qty) {
        cart.changeQuantity(qty);
    }

    @Then("cart quantity updates")
    public void qtyUpdated() {
        Assert.assertTrue(DriverManager.getDriver().findElements(org.openqa.selenium.By.cssSelector("input.qty-input")).size() > 0);
    }

    @Given("I have product in cart")
    public void haveProductInCart() {
        // if none, try to add a product
        home.search("Apple MacBook Pro 13-inch");
        if (cart.getCartItemsCount() == 0) {
            DriverManager.getDriver().findElement(org.openqa.selenium.By.linkText("Apple MacBook Pro 13-inch")).click();
            new ProductPage(DriverManager.getDriver()).addToCart();
            home.clickCart();
        }
    }

    @When("I remove all items from cart")
    public void removeAll() {
        cart.removeAll();
    }

    @Then("cart shows empty message")
    public void emptyMsg() {
        Assert.assertTrue(cart.isEmpty());
    }
}
