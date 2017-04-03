package stepDefinitions.navigation;

import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePage;
import util.WebConnector;

public class Navigation extends WebConnector {

    public HomePage homePage;

    public Navigation() {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
    }
}