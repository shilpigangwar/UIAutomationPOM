package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class joinHonorConfirmationPage {
    public static final Logger log = Logger.getLogger(HomePage.class.getName());

    WebDriver driver;

    @FindBy(id="welcome")
    WebElement welcomeheader;


}
