
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Load config if not already loaded
            try {
                ConfigReader.loadConfig();
            } catch (Exception e) {
                System.out.println("⚠️ Warning: ConfigReader not loaded, default values will be used.");
            }

            // Generate timestamped report path
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = System.getProperty("user.dir") + "/reports/ExtentReports/ExtentReport_" + timestamp + ".html";

            // Initialize ExtentSparkReporter
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setReportName("nopCommerce Automation Report");
            spark.config().setDocumentTitle("Automation Test Results");

            // Initialize ExtentReports and attach reporter
            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Set system info
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser") != null ? ConfigReader.getProperty("browser") : "chrome");
            extent.setSystemInfo("URL", ConfigReader.getProperty("url") != null ? ConfigReader.getProperty("url") : "https://demo.nopcommerce.com");
        }
        return extent;
    }
}
