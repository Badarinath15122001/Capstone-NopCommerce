package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.DriverFactory;

import static org.testng.Assert.assertTrue;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    String testEmail;
    String password = "Test@123";

    @Given("User opens the application")
    public void user_opens_the_application() {
        driver = DriverFactory.initDriver("chrome");
        loginPage = new LoginPage(driver);
    }

    @When("User registers with {string}, {string}, {string}, and {string}")
    public void user_registers_with_and(String firstName, String lastName, String email, String pwd) {
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        // dynamically generate email
        testEmail = email.replace("<email>", "testuser" + System.currentTimeMillis() + "@demo.com");
        loginPage.register(firstName, lastName, testEmail, pwd);

        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @When("User logs out")
    public void user_logs_out() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        loginPage.logout();
    }

    @When("User logs in with registered credentials")
    public void user_logs_in_with_registered_credentials() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        loginPage.login(testEmail, password);
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        assertTrue(loginPage.isMyAccountVisible(), "❌ Login failed for registered user!");
        System.out.println("✅ Register → Logout → Login successful!");
        DriverFactory.quitDriver();
    }

    @When("User enters invalid credentials {string} and {string}")
    public void user_enters_invalid_credentials_and(String email, String pwd) {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        loginPage.login(email, pwd);
    }

    @Then("An error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String expectedMessage) {
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        String actualMessage = loginPage.getLoginErrorMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "❌ Expected: " + expectedMessage + " but got: " + actualMessage);
        System.out.println("✅ Error message displayed: " + actualMessage);
        DriverFactory.quitDriver();
    }
}
