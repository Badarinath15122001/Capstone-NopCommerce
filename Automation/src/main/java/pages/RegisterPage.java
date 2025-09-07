package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By genderMale = By.id("gender-male");
    private By genderFemale = By.id("gender-female");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By email = By.id("Email");
    private By password = By.id("Password");
    private By confirmPassword = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By successMessage = By.cssSelector("div.result");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            wait.until(ExpectedConditions.elementToBeClickable(genderMale)).click();
        } else if (gender.equalsIgnoreCase("female")) {
            wait.until(ExpectedConditions.elementToBeClickable(genderFemale)).click();
        }
    }

    public void enterFirstName(String fName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lName);
    }

    public void enterEmail(String userEmail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(userEmail);
    }

    public void enterPassword(String userPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(userPassword);
    }

    public void enterConfirmPassword(String userPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(userPassword);
    }

    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}
