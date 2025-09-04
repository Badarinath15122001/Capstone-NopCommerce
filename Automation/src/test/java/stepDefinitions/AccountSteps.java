package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.AccountPage;

public class AccountSteps {
    HomePage home = new HomePage(DriverManager.getDriver());
    LoginPage login = new LoginPage(DriverManager.getDriver());
    AccountPage account = new AccountPage(DriverManager.getDriver());

    @When("I open My account page")
    public void openAccount() {
        DriverManager.getDriver().findElement(org.openqa.selenium.By.linkText("My account")).click();
    }

    @Then("account sections should be visible")
    public void accountSections() {
        Assert.assertTrue(account.isMyAccountVisible());
    }
}
