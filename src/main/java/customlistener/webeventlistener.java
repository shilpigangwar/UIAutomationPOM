package customlistener;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;
import org.testng.internal.TestResult;
import testbase.testbase;
import com.relevantcodes.extentreports.LogStatus;


public class webeventlistener extends testbase implements WebDriverEventListener {

    public static final Logger log = Logger.getLogger(WebDriverEventListener.class.getName());

    public void beforeAlertAccept(WebDriver webDriver) {
        log("Accepting the alert"+webDriver);
    }

    public void afterAlertAccept(WebDriver webDriver) {
        log("Accepted the alert"+webDriver);
    }

    public void afterAlertDismiss(WebDriver webDriver) {
        log("Alert is dismissed");
    }

    public void beforeAlertDismiss(WebDriver webDriver) {
        log("Dismissing the Alert");
    }

    public void beforeNavigateTo(String url, WebDriver webDriver) {
        log(webDriver+" navigating to "+url);
    }

    public void afterNavigateTo(String url, WebDriver webDriver) {
        log(webDriver+" navigated to "+url);
    }

    public void beforeNavigateBack(WebDriver webDriver) {
        log("Navigating back to previous page");
    }

    public void afterNavigateBack(WebDriver webDriver) {
        log("Navigated back to previous page");
    }

    public void beforeNavigateForward(WebDriver webDriver) {
        log("Navigating forward to next page");
    }

    public void afterNavigateForward(WebDriver webDriver) {
        log("Navigated forward to next page");
    }

    public void beforeNavigateRefresh(WebDriver webDriver) {
        log("Page is refreshing");
    }

    public void afterNavigateRefresh(WebDriver webDriver) {
        log("Page is refreshed");
    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        log("Trying to find Element By : " + by.toString());
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        log("Found Element By : " + by.toString());
    }

    public void beforeClickOn(WebElement element, WebDriver webDriver) {
        log("Trying to click on: " + element.toString());
    }

    public void afterClickOn(WebElement element, WebDriver webDriver) {
        log("Clicked on: " + element.toString());
    }

    public void beforeChangeValueOf(WebElement element, WebDriver webDriver, CharSequence[] charSequences) {
        log("Value of the:" + element.toString()
                + " before any changes made");
    }

    public void afterChangeValueOf(WebElement element, WebDriver webDriver, CharSequence[] charSequences) {
        log("Element value changed to: " + element.toString());
    }

    public void beforeScript(String s, WebDriver driver) {
        System.out.println("Just before beforeScript " + driver);
    }

    public void afterScript(String s, WebDriver driver) {
        System.out.println("Just after afterScript " + driver);
    }

    public void onException(Throwable throwable, WebDriver webDriver) {
        log("Exception occured: " + throwable);
        Reporter.log("Exception occured:" , false);
    }

    public void log(String data){
        log.info(data);
        Reporter.log(data);
       test.log(LogStatus.INFO, data);

    }

}
