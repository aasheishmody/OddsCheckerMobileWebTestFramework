package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Retrier;
import util.WebConnector;

public class Page extends WebConnector {


    protected void click(WebElement element, int timeout) {
        Retrier.retry("click " + element, () -> singleClick(element, timeout), 5, 250L);
    }

    public void singleClick(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
        getLogger().info("Waiting for element to be clickable - " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        getLogger().info("Clicking " + element);
        element.click();
        getLogger().info("Clicked " + element);
    }

    private WebDriverWait buidWebDriverWait(int timeout) {
        long sleepInMillis = 250L;
        return new WebDriverWait(getDriver(), timeout, sleepInMillis);
    }

    protected void dragAndSelect(WebElement firstElement, WebElement lastElement, int timeOut) {
       Retrier.retry( "Drag And Select " ,()-> {
           waitForElementToBeDisplayed(firstElement, timeOut);
           waitForElementToBeDisplayed(lastElement, timeOut);
        Actions builder = new Actions(getDriver());
        Action dragAndDrop = builder.clickAndHold(firstElement).moveToElement(lastElement).release(lastElement).keyDown(Keys.SHIFT).build();
        dragAndDrop.perform();
        Action undoShift = builder.keyUp(Keys.SHIFT).build();
        undoShift.perform();},5, 1000l);
    }

    protected void waitForElementToBeDisplayed(WebElement element, int timeout) {
        Retrier.retry("waitForElementToBeDisplayed " + element,
                () -> singleWaitForElementToBeDisplayed(element, timeout),
                4, 250l);
    }

    private void singleWaitForElementToBeDisplayed(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
    }

    protected String getText(WebElement element, int timeout) {
        Retrier.retry("getText " + element,
                () -> singleGetText(element, timeout),
                4
        );
        return singleGetText(element, timeout);
    }

    private String singleGetText(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Getting text from element - " + element);
        getLogger().info("Got text - " + element.getText() + " from element - " + element);
        return element.getText();
    }

    protected void waitForTextInElement(WebElement element, String text, int timeout) {
        getLogger().info("Waiting for text - " + text + " in element " + element);
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        getLogger().info("Text - " + text + " found after waiting for it to be displayed in " + element);
    }

    protected void refreshPage() {
        getLogger().info("Refreshing Page");
        getDriver().navigate().refresh();
        getLogger().info("Page Refreshed");
    }

    protected boolean isNotDisplayed(WebElement element) {
        getLogger().info("Checking if " + element + " is not displayed");
        boolean result;
        try {
            result = !element.isDisplayed();
        } catch (NoSuchElementException e) {
            result = true;
        }
        getLogger().info(element + " is displayed - " + result);
        return result;
    }

    protected Boolean elementIsDisplayedOrNot(WebElement element) {
        getLogger().info("Checking if " + element + " is not displayed or not");
        Boolean displayed = true;
        try {
            if (element.isDisplayed())
                displayed = true;
        } catch (Throwable t) {
            displayed = false;
        }
        getLogger().info(element + " is displayed - " + displayed);
        return displayed;
    }

    protected void waitForElementToDisappear(WebElement element) {
        getLogger().info("Waiting for " + element + " to disappear");
        try {
            while (true) {
                element.isDisplayed();
            }
        } catch (Exception e) {
            System.out.println("Element has disappeared now");
        }
        getLogger().info("Waited for " + element + " to disappear");
    }

    public void waitForURLtoContain(String text, int timeout) {
        getLogger().info("Waiting for URL to contain text - " + text);
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.urlContains(text));
        getLogger().info("Waited for URL to contain text - " + text);
    }

    protected void waitForURL(String url, int timeout) {
        Retrier.retry("wait for url " + url,
                () -> singleWaitForUrl(url, timeout),
                4);
    }

    private void singleWaitForUrl(String url, int timeout) {
        getLogger().info("Waiting for URL to be - " + url);
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.urlToBe(url));
        getLogger().info("Waited for URL to be - " + url);
    }

    protected void waitForURLtoContainRegex(String regex, int timeout) {
        Retrier.retry("wait for regex" + regex,
                () -> singleWaitForUrlToContainRegex(regex, timeout),
                4);
    }

    private void singleWaitForUrlToContainRegex(String regex, int timeout) {
        getLogger().info("Waiting for URL to contain regex - " + regex);
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.urlMatches(regex));
        getLogger().info("Waited for URL to contain regex - " + regex);
    }

    protected void javascriptClick(WebElement element, int timeout) {
        Retrier.retry("javascriptClick " + element, () -> singleJavascriptClick(element, timeout), 4);
    }

    private void singleJavascriptClick(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Clicking element - " + element + " using javascript click");
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
        getLogger().info("Clicked element - " + element + " using javascript click");
    }

    protected String getAttribute(WebElement element, String attribute, int timeout) {
        Retrier.retry("getAttribute " + element,
                () -> singleGetAttribute(element, attribute, timeout),
                4, 100l);
        return singleGetAttribute(element, attribute, timeout);
    }

    private String singleGetAttribute(WebElement element, String attribute, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Getting attribute - " + attribute + " from element - " + element);
        getLogger().info("Attribute - " + attribute + " of the element - " + element + " is" + element.getAttribute(attribute));
        return element.getAttribute(attribute);
    }

    protected boolean isDisplayed(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        return element.isDisplayed();
    }

    protected void sendKeys(WebElement element, String text, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + text + " to element - " + element);
        element.sendKeys(text);
        getLogger().info("Sent text - " + text + " to element - " + element);
    }

    protected void sendKeyBoardKeys(WebElement element, Keys key, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + key + " to element - " + element);
        element.sendKeys(key);
        getLogger().info("Sent text - " + key + " to element - " + element);
    }

    protected void switchWindow() {
        // Store the current window handle
        String winHandleBefore = getDriver().getWindowHandle();
        System.out.println("Parent window name is " + winHandleBefore);
        // Switch to new window opened
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
    }

    protected void switchToOriginalWindow() {
        for (String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
    }

    protected void closeDriver() {
        getDriver().close();
    }

}
