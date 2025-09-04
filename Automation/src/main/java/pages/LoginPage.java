package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver d){ this.driver = d; }

    private By email = By.id("Email");
    private By password = By.id("Password");
    private By loginBtn = By.cssSelector("button.login-button");
    private By logoutLink = By.linkText("Log out");
    private By myAccountLink = By.linkText("My account");
    private By errorMsg = By.cssSelector(".message-error");

    public void login(String user, String pass) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElements(errorMsg).size() > 0;
    }

    public boolean isLoggedIn() {
        return driver.findElements(myAccountLink).size() > 0 || driver.findElements(logoutLink).size() > 0;
    }
}
