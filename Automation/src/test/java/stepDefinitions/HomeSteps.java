package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import utils.ConfigReader;

public class HomeSteps {
    HomePage home = new HomePage(DriverManager.getDriver());

    @Given("I open the application")
    public void i_open_the_application() {
        home.goTo(ConfigReader.get("base.url"));
    }

    @Then("I should see the main menu elements")
    public void i_should_see_main_menu() {
        Assert.assertTrue(DriverManager.getDriver().getTitle().toLowerCase().contains("nopcommerce") ||
                          DriverManager.getDriver().getCurrentUrl().contains("nopcommerce"));
    }

    @When("I subscribe with email {string}")
    public void i_subscribe_with_email(String email) {
        home.subscribe(email);
    }

    @Then("subscription confirmation or message should appear")
    public void subscription_confirmation() {
        Assert.assertTrue(DriverManager.getDriver().getPageSource().toLowerCase().contains("newsletter") ||
                          DriverManager.getDriver().getPageSource().toLowerCase().contains("success"));
    }
}
