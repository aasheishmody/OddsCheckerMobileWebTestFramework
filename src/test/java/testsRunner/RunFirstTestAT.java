package testsRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import util.WebConnector;

import java.io.IOException;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-html-report/1.json"},
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        tags = {"@login"},
        monochrome = true
)

public class RunFirstTestAT extends WebConnector {

    @AfterClass
    public static void closeBrowser() throws IOException, InterruptedException {
        getDriver().quit();
    }
}





