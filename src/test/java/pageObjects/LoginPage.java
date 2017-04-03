package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page {

    @FindBy(id = "login-email")
    private WebElement EmailAddressTextBox;
    @FindBy(id = "login-password")
    private WebElement PasswordAddressTextBox;
    @FindBy(css = "input[value='Log In']")
    private WebElement LoginButton;

    public void enterEmailAddress(String userName) {
        sendKeys(EmailAddressTextBox, userName, getMediumTimeout());
    }

    public void enterPassword(String password) {
        sendKeys(PasswordAddressTextBox, password, getMediumTimeout());
    }

    public void clickLoginButton() {
        click(LoginButton, getMediumTimeout());
    }





    /*public void enterValidUserDetails() {
        waitForElementToBePresent(EmailTextBox);
        EmailTextBox.sendKeys("eqtestload-002@mailinator.com");
        PasswordTextBox.sendKeys("Test1234");
    }

    public boolean checkUserIsLoggedin() {
        waitForElementToBePresent(MyAccountButton);
        if (MyAccountButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserIsOnLoginPage() {
        waitForElementToBePresent(EmailTextBox);
        return EmailTextBox.isDisplayed();
    }

    public void clickSubmitButton() {
        SubmitButton.click();
    }

    public void enterInvalidUserDetails() {
        waitForElementToBePresent(EmailTextBox);
        int ran;
        ran = 100 + (int)(Math.random() * ((10000 - 100) + 1));
        EmailTextBox.sendKeys("test"+ran+"@mailinator.com");
        PasswordTextBox.sendKeys("Test123");
    }

    public boolean checkUserIsNotLoggedin() {
        waitForElementToBePresent(UnsuccessfulLoginNotification);
        if (UnsuccessfulLoginNotification.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void loginWithInvalidUserCredentials() {
        enterInvalidUserDetails();
        clickSubmitButton();
    }

    public void dismissUnsuccessfulLoginNotification() {
        waitForElementToBePresent(UnsuccessfulLoginNotification);
        DismissUnsuccessfulLoginNotification.click();
    }

    public boolean checkUnsuccessfulLoginNotificationIsDisplayed() {
        waitForElementToDisappear(UnsuccessfulLoginNotification);
        return elementIsDisplayedOrNot(UnsuccessfulLoginNotification);
    }*/
}