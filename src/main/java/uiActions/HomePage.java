package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public static final Logger log = Logger.getLogger(HomePage.class.getName());

    WebDriver driver;

    @FindBy(id="sign_in")
    WebElement signInLink;

    @FindBy(id="username")
    WebElement uname;

    @FindBy(id="password")
    WebElement pword;

    @FindBy(xpath=".//*[@id='formSignIn']/p[5]/a")
    WebElement signInBtn;

    @FindBy(xpath=".//*[@id='invalidCredentials']")
    WebElement authenticationFailMessage;

    @FindBy(id="explore_benefits")
    WebElement join;

    @FindBy(id="firstNameJoin")
    WebElement firstnameJoin;

    @FindBy(id="lastNameJoin")
    WebElement lastnameJoin;

    @FindBy(id="phoneJoin")
    WebElement phoneJoin;

    @FindBy(id="emailJoin")
    WebElement emailJoin;

    @FindBy(id="street1")
    WebElement address;

    @FindBy(id="street2")
    WebElement additionaladdress;

    @FindBy(id="postalCode")
    WebElement zip;




    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void loginToApplication(String username, String password){
        signInLink.click();
        log.info("sign in link clicked and the object is "+signInLink.toString());
        uname.sendKeys(username);
        log.info("username "+username+" entered and the object is "+uname.toString());
        pword.sendKeys(password);
        log.info("password "+password+" entered and the object is "+pword.toString());
        signInBtn.click();
        log.info("sign in button clicked and the object is "+signInBtn.toString());
    }

    public String getInvalidLoginMessage(){
        log.info("authentication fail message displayed is "+authenticationFailMessage.getText());
        return authenticationFailMessage.getText();
    }

}
