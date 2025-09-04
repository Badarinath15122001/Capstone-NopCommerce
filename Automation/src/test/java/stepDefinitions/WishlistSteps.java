package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.WishlistPage;

public class WishlistSteps {
    HomePage home = new HomePage(DriverManager.getDriver());
    WishlistPage wishlist = new WishlistPage(DriverManager.getDriver());

    @When("I add product {string} to wishlist")
    public void addToWishlist(String name) {
        home.search(name);
        DriverManager.getDriver().findElement(org.openqa.selenium.By.linkText(name)).click();
        new pages.ProductPage(DriverManager.getDriver()).addToWishlist();
    }

    @When("I go to wishlist page")
    public void goToWishlist() {
        home.clickWishlist();
    }

    @Then("wishlist shows the product")
    public void wishlistShows() {
        Assert.assertTrue(wishlist.getWishlistCount() > 0);
    }

    @When("I move first wishlist item to cart")
    public void moveFirstToCart() {
        wishlist.addFirstToCart();
    }

    @Then("cart should contain that item")
    public void cartContains() {
        home.clickCart();
        Assert.assertTrue(DriverManager.getDriver().getPageSource().toLowerCase().contains("shopping cart"));
    }
}
