package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import utils.DriverFactory;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize ExtentReports once
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser, Method method) {
        // Initialize driver using DriverFactory
        driver = DriverFactory.initDriver(browser);

        // Register driver in DriverFactory for listener
        DriverFactory.setDriver(driver);

        // Create ExtentTest instance for reporting
        test = extent.createTest(method.getName());

        System.out.println("🚀 Starting test: " + method.getName() + " on browser: " + browser);
        System.out.println("✅ Driver initialized for test: " + method.getName());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
            System.out.println("🛑 Driver quit after test");
        }
    }

    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            extent.flush();
        }
    }
}