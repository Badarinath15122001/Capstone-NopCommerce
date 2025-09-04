package pages;
//accountpage
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private WebDriver driver;
    public AccountPage(WebDriver d){ this.driver = d; }

    private By accountSections = By.cssSelector(".customer-account .listbox");

    public boolean isMyAccountVisible() {
        return driver.findElements(accountSections).size() > 0;
    }
}
