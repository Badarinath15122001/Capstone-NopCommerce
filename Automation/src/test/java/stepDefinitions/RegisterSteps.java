package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.RegisterPage;

import java.util.UUID;

public class RegisterSteps {
    HomePage home = new HomePage(DriverManager.getDriver());
    RegisterPage register = new RegisterPage(DriverManager.getDriver());

    @When("I navigate to register page")
    public void navigateToRegister() {
        home.clickRegister();
    }

    @When("I register new user with random email")
    public void registerNewUser() {
        String random = UUID.randomUUID().toString().substring(0,6);
        String email = "auto+" + random + "@example.com";
        register.register("Auto", "User", email, "Password123", true);
    }

    @Then("registration should complete")
    public void registrationComplete() {
        Assert.assertTrue(register.isRegistrationSuccess());
    }
}
