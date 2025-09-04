//package runners;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//@CucumberOptions(
//    features = "src/test/resources/features",
//    glue = {"stepDefinitions"},
//    plugin = {"pretty", "html:reports/cucumber-report.html", "json:reports/cucumber.json"},
//    monochrome = true
//)
//public class TestRunner extends AbstractTestNGCucumberTests {}
package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/resources/features"},   // path to feature files
    glue = {"stepDefinitions"},                   // package where step defs + hooks live
    plugin = {
        "pretty",
        "html:reports/cucumber-report.html",
        "json:reports/cucumber.json"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No extra code needed here
}
