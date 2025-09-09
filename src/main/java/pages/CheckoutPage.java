package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By addToCartButton = By.id("add-to-cart-button-22");
    private By successNotification = By.cssSelector("div.bar-notification.success");
    private By termsOfServiceCheckbox = By.id("termsofservice");
    private By checkoutButton = By.id("checkout");
    private By checkoutAsGuestButton = By.cssSelector("button.checkout-as-guest-button");

    // Billing info
    private By firstName = By.id("BillingNewAddress_FirstName");
    private By lastName = By.id("BillingNewAddress_LastName");
    private By email = By.id("BillingNewAddress_Email");
    private By countryDropdown = By.id("BillingNewAddress_CountryId");
    private By stateDropdown = By.id("BillingNewAddress_StateProvinceId");
    private By city = By.id("BillingNewAddress_City");
    private By address1 = By.id("BillingNewAddress_Address1");
    private By zip = By.id("BillingNewAddress_ZipPostalCode");
    private By phone = By.id("BillingNewAddress_PhoneNumber");
    private By continueBillingButton = By.cssSelector(".new-address-next-step-button");

    // Continue buttons
    private By shippingContinue = By.cssSelector(".shipping-method-next-step-button");
    private By paymentContinue = By.cssSelector(".payment-method-next-step-button");
    private By paymentInfoContinue = By.cssSelector(".payment-info-next-step-button");
    private By confirmOrderButton = By.cssSelector(".confirm-order-next-step-button");
    private By orderConfirmationMessage = By.cssSelector("div.section.order-completed");

    // Payment methods
    private By creditCardOption = By.id("paymentmethod_1");
    private By moneyOrderOption = By.id("paymentmethod_0");
    private By continuePaymentButton = By.cssSelector(".payment-method-next-step-button");

    // Credit card fields
    private By cardType = By.id("CreditCardType");
    private By cardholderName = By.id("CardholderName");
    private By cardNumber = By.id("CardNumber");
    private By expireMonth = By.id("ExpireMonth");
    private By expireYear = By.id("ExpireYear");
    private By cardCode = By.id("CardCode");

    // Order details
    private By orderDetailsLink = By.linkText("Click here for order details.");
    private By pdfInvoiceButton = By.cssSelector(".pdf-invoice-button");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Wait for full page load
    private void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    // Wait until element is visible
    private WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Click safely after element is fully loaded
    private void clickElement(By locator) {
        WebElement element = waitForElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Type safely after element is fully loaded
    private void typeText(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Add product to cart
    public void addSamsungGalaxyToCart() {
        waitForPageToLoad();
        WebElement addButton = waitForElement(addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
        waitForElement(successNotification);

        By footerCartLink = By.xpath("//a[@href='/cart' and text()='Shopping cart']");
        WebElement cartLinkFooter = wait.until(ExpectedConditions.elementToBeClickable(footerCartLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartLinkFooter);
        cartLinkFooter.click();
    }

    // Proceed to checkout
    public void goToCheckout() {
        waitForPageToLoad();
        WebElement termsCheckbox = waitForElement(termsOfServiceCheckbox);
        if (!termsCheckbox.isSelected()) termsCheckbox.click();
        clickElement(checkoutButton);
    }

    public boolean isOnCheckoutPage() {
        waitForPageToLoad();
        return wait.until(ExpectedConditions.urlContains("checkout")) || driver.getTitle().contains("Checkout");
    }

    public void checkoutAsGuest() {
        clickElement(checkoutAsGuestButton);
    }

    // ===== Updated billing details with country/state selection =====
    public void fillBillingDetails(String fName, String lName, String emailId, String country, String state) {
        typeText(firstName, fName);
        typeText(lastName, lName);
        typeText(email, emailId);
        typeText(city, "Hyderabad");           
        typeText(address1, "123 Main Street"); 
        typeText(zip, "500001");               
        typeText(phone, "9876543210");         

        // Country selection
        try {
            WebElement countryElement = waitForElement(countryDropdown);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryElement);
            new Select(countryElement).selectByVisibleText(country);
            Thread.sleep(2000); // wait for states to load
        } catch (Exception e) {
            System.out.println("❌ Country selection failed: " + e.getMessage());
        }

        // State selection
        try {
            WebElement stateElement = waitForElement(stateDropdown);
            new Select(stateElement).selectByVisibleText(state);
        } catch (Exception e) {
            System.out.println("❌ State selection failed: " + e.getMessage());
        }

        clickElement(continueBillingButton);
    }

    public void continueShipping() { clickElement(shippingContinue); }

    public void selectPaymentMethod(String method) {
        if (method.equalsIgnoreCase("Credit Card")) clickElement(creditCardOption);
        else if (method.equalsIgnoreCase("Money Order")) clickElement(moneyOrderOption);
        clickElement(continuePaymentButton);
    }

    public void enterCreditCardDetails() {
        WebElement typeElement = waitForElement(cardType);
        new Select(typeElement).selectByVisibleText("Visa");
        typeText(cardholderName, "John Doe");
        typeText(cardNumber, "4111111111111111");
        new Select(waitForElement(expireMonth)).selectByVisibleText("12");
        new Select(waitForElement(expireYear)).selectByVisibleText("2030");
        typeText(cardCode, "123");
        clickElement(paymentInfoContinue);
    }

    public void continuePaymentInfo() { clickElement(paymentInfoContinue); }

    public void confirmOrder() { clickElement(confirmOrderButton); }

    public boolean isOrderConfirmed() { return waitForElement(orderConfirmationMessage).isDisplayed(); }

    public void openOrderDetails() { clickElement(orderDetailsLink); }

    public void downloadInvoicePDF() { clickElement(pdfInvoiceButton); }
}
