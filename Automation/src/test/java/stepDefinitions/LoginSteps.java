package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps {
    HomePage home = new HomePage(DriverManager.getDriver());
    LoginPage login = new LoginPage(DriverManager.getDriver());

    @When("I navigate to login page")
    public void navigateToLogin() {
        home.goTo(System.getProperty("user.home")); // placeholder - Hooks opens base URL
        home.clickLogin();
    }

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        login.login(username, password);
    }

    @Then("I should see My account link")
    public void shouldSeeMyAccount() {
        Assert.assertTrue(login.isLoggedIn());
    }

    @Then("login error should be displayed")
    public void loginError() {
        Assert.assertTrue(login.isErrorDisplayed());
    }
}
