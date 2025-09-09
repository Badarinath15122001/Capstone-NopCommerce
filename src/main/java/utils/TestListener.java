package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        test.get().log(Status.INFO, "🟢 Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        test.get().log(Status.PASS, "✅ Test Passed")
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        test.get().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        test.get().log(Status.SKIP, "⚠️ Test Skipped")
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
