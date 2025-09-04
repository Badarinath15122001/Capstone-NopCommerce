package utils;

import base.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(String name) {
        try {
            WebDriver driver = DriverManager.getDriver();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = System.getProperty("user.dir") + "/reports/screenshots/" + name + "_" + ts + ".png";
            File dest = new File(path);
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
