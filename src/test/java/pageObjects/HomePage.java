package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(css = "a[title='My Account']")
    private WebElement MyAccountLink;

    public boolean homePageIsDisplayed() {
        if(isDisplayed(MyAccountLink, getMediumTimeout()))
            return true;
        else
            return false;
    }
}
