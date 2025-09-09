package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Login locators
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton = By.cssSelector("button.login-button");
    private By myAccount = By.linkText("My account");
    private By loginErrorMessage = By.cssSelector("div.message-error"); // Error message locator

    // Registration locators
    private By firstNameField = By.id("FirstName");
    private By lastNameField = By.id("LastName");
    private By registerEmailField = By.id("Email");
    private By registerPasswordField = By.id("Password");
    private By confirmPasswordField = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By logoutLink = By.linkText("Log out");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ====== Registration ======
    public void register(String firstName, String lastName, String email, String password) {
        driver.get("https://demo.nopcommerce.com/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(registerEmailField).sendKeys(email);
        driver.findElement(registerPasswordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    // ====== Logout ======
    public void logout() {
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logout.click();
    }

    // ====== Login ======
    public void login(String email, String password) {
        driver.get("https://demo.nopcommerce.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isMyAccountVisible() {
        try {
            WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(myAccount));
            return account.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMessage));
            return error.getText();
        } catch (Exception e) {
            return "";
        }
    }
}
