package stepDefinitions.login;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import util.WebConnector;

import java.util.List;
import java.util.Map;

import static util.LoggerHelper.asserting;
import static util.LoggerHelper.clicking;
import static util.LoggerHelper.navigating;

public class Login extends WebConnector {
	
	public LandingPage landingPage;
    public LoginPage loginPage;
    public HomePage homePage;

    public Login(){
        landingPage = PageFactory.initElements(getDriver(), LandingPage.class);
        loginPage = PageFactory.initElements(getDriver(), LoginPage.class);
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
    }

    @Given("^I am on the 'Landing' page$")
    public void iAmOnTheLandingPage() throws Throwable {
        navigating("to the Landing page", () -> landingPage.open());
    }

    @And("^I click on the 'Login' link$")
    public void iClickOnTheLoginLink() throws Throwable {
        clicking("Login link", () -> landingPage.clickLoginLink());
    }

    @And("^I enter the following registered email in the 'Email' textbox$")
    public void iEnterTheFollowingRegisteredEmailInTheEmailTextbox(DataTable table) throws Throwable {
        List<Map<String, String>> aux = table.asMaps(String.class, String.class);
        String userName;
        for (int i = 0; i < aux.size(); i++) {
            Map<String, String> usrdetails = aux.get(i);
            userName = usrdetails.get("UserName");
            if (userName != null) {
                loginPage.enterEmailAddress(userName);
            }
        }
    }

    @And("^I enter the following valid password in the 'Password' textbox$")
    public void iEnterTheFollowingValidPasswordInTheEmailTextbox(DataTable table) throws Throwable {
        List<Map<String, String>> aux = table.asMaps(String.class, String.class);
        String password;
        for (int i = 0; i < aux.size(); i++) {
            Map<String, String> usrdetails = aux.get(i);
            password = usrdetails.get("Password");
            if (password != null) {
                loginPage.enterPassword(password);
            }
        }
    }

    @When("^I click on the 'Login' button$")
    public void iClickOnTheLoginButton() throws Throwable {
                        clicking("Login Button", () -> loginPage.clickLoginButton());
    }

    @Then("^the 'Home' page is displayed$")
    public void theHomePageIsDisplayed() throws Throwable {
                         asserting("that the 'Home' page is displayed", () -> Assert.assertTrue(homePage.homePageIsDisplayed()));
    }
}
