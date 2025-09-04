package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.ComparePage;
import pages.HomePage;

public class CompareSteps {
    HomePage home = new HomePage(DriverManager.getDriver());
    ComparePage compare = new ComparePage(DriverManager.getDriver());

    @When("I go to compare page")
    public void goCompare() {
        home.clickCompare();
    }

    @Then("compare should show both products")
    public void compareShows() {
        Assert.assertTrue(DriverManager.getDriver().getPageSource().contains("Apple MacBook Pro 13-inch") &&
                          DriverManager.getDriver().getPageSource().contains("HTC One M8 Android"));
    }
}
