package stepDefinitions.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import util.WebConnector;

import java.net.MalformedURLException;

public class Hooks extends WebConnector {

    @Before
    public void beforeTest() throws MalformedURLException {
        initialize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String driverName = getDriver().toString();
            System.out.println("The driver name is "+driverName);
            if (driverName.contains("iOS")) {
                WebDriver augmentedDriver = new Augmenter().augment(getDriver());
                byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/jpeg");
            } else if (driverName.contains("ANDROID"))  {
                String contextName = getDriver().getContext();
                getDriver().context("NATIVE_APP");
                WebDriver augmentedDriver = new Augmenter().augment(getDriver());
                byte[] screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/jpeg");
                getDriver().context(contextName);
            }
        }
        getDriver().manage().deleteAllCookies();
    }
}
