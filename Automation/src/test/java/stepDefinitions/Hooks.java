package stepDefinitions;

import base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;

public class Hooks {
    @Before
    public void beforeScenario() {
        String browser = ConfigReader.get("browser"); 
        DriverManager.initDriver(browser);
    }

    @After
    public void afterScenario() {
        DriverManager.quitDriver();
    }
}
