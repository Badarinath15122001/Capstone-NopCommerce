package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class ExtentListener implements ITestListener {
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String path = ScreenshotUtil.takeScreenshot(result.getMethod().getMethodName());
        testThread.get().fail(result.getThrowable());
        if (path != null) {
            try {
                testThread.get().addScreenCaptureFromPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
