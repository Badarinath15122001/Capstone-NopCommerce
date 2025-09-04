package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.ProductPage;

public class CheckoutSteps {
    HomePage home = new HomePage(DriverManager.getDriver());

    @When("I proceed to checkout as guest")
    public void proceedGuest() {
        // minimal implementation: assume checkout page appears after navigating to cart/checkout
        home.clickCart();
        DriverManager.getDriver().findElements(org.openqa.selenium.By.linkText("Checkout")).forEach(el -> el.click());
    }

    @Then("I should reach billing address page")
    public void billingPage() {
        Assert.assertTrue(DriverManager.getDriver().getPageSource().toLowerCase().contains("billing address") ||
                          DriverManager.getDriver().getCurrentUrl().toLowerCase().contains("checkout"));
    }
}
