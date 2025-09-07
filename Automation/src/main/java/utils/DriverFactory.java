package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    // ThreadLocal to store driver for each test thread
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Initialize driver
    public static WebDriver initDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("❌ Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        setDriver(driver);  // register driver immediately for listener
        return driver;
    }

    // Register driver in ThreadLocal
    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    // Get driver from ThreadLocal
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // Quit driver safely
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
