package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    public RegisterPage(WebDriver d){ this.driver = d; }

    private By genderMale = By.id("gender-male");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By email = By.id("Email");
    private By password = By.id("Password");
    private By confirmPassword = By.id("ConfirmPassword");
    private By registerBtn = By.id("register-button");
    private By result = By.cssSelector(".result");

    public void register(String fName, String lName, String emailAddr, String pwd, boolean male) {
        if (male) driver.findElement(genderMale).click();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(this.email).sendKeys(emailAddr);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(confirmPassword).sendKeys(pwd);
        driver.findElement(registerBtn).click();
    }

    public boolean isRegistrationSuccess() {
        return driver.findElements(result).size() > 0 && driver.findElement(result).getText().contains("Your registration completed");
    }
}
