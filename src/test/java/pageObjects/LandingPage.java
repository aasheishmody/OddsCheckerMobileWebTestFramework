package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends Page {

    @FindBy(css = "a[title='Log In']")
    private WebElement LoginLink;

    public void open() {
        getDriver().get(getBaseURL());
    }

    public void clickLoginLink() {
        click(LoginLink, getMediumTimeout());
    }
}
