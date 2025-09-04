package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.HomePage;
import pages.ProductPage;

public class ProductSteps {
    HomePage home = new HomePage(DriverManager.getDriver());

    @When("I search for {string}")
    public void searchFor(String q) {
        home.search(q);
    }

    @Then("search results are displayed")
    public void searchResults() {
        Assert.assertTrue(DriverManager.getDriver().getPageSource().toLowerCase().contains("product") ||
                          DriverManager.getDriver().findElements(By.cssSelector(".product-item")).size() > 0);
    }

    @When("I click category {string}")
    public void clickCategory(String cat) {
        DriverManager.getDriver().findElement(By.linkText(cat)).click();
    }

    @Then("list of products should appear")
    public void listProducts() {
        Assert.assertTrue(DriverManager.getDriver().findElements(By.cssSelector(".product-item")).size() > 0);
    }

    @When("I add product {string} to cart")
    public void addProductToCart(String productName) {
        DriverManager.getDriver().findElement(By.linkText(productName)).click();
        new ProductPage(DriverManager.getDriver()).addToCart();
    }

    @When("I add product {string} to wishlist")
    public void addToWishlist(String productName) {
        DriverManager.getDriver().findElement(By.linkText(productName)).click();
        new ProductPage(DriverManager.getDriver()).addToWishlist();
    }

    @When("I add product {string} to compare")
    public void addToCompare(String productName) {
        DriverManager.getDriver().findElement(By.linkText(productName)).click();
        new ProductPage(DriverManager.getDriver()).addToCompare();
    }
}
