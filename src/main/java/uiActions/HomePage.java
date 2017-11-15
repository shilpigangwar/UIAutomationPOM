package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Reporter.log;

public class HomePage {

    public static final Logger log = Logger.getLogger(HomePage.class.getName());

    WebDriver driver;

    @FindBy(className="login")
    WebElement signInLink;

    @FindBy(id="email")
    WebElement uname;

    @FindBy(id="passwd")
    WebElement pword;

    @FindBy(id="SubmitLogin")
    WebElement signInBtn;

    @FindBy(xpath=".//*[contains(Text(),'Already registered?')]")
    WebElement alreadyRegistered;

    @FindBy(xpath=".//*[contains(Text(),'Forgot your password')]")
    WebElement forgotYourPasswordLink;

    @FindBy(id="email_create")
    WebElement emailCreate;

    @FindBy(id="SubmitCreate")
    WebElement createAccount;

    @FindBy(xpath=".//*[@id='create_account_error']/ol/li")
    WebElement emailerror;

    @FindBy(xpath=".//*[@id='create-account_form']/div/p")
    WebElement createAccountMessage;

    @FindBy(id = "newsletter-input")
    WebElement newsletterEmail;

    @FindBy(id="newsletter_block_left")
    WebElement newsletterBlock;

    @FindBy(id="social_block")
    WebElement socialblock;

    @FindBy(xpath=".//*[@class='facebook']")
    WebElement facebook;

    @FindBy(xpath=".//*[@class='twitter']")
    WebElement twitter;

    @FindBy(xpath = ".//*[@class='youtube']")
    WebElement youtube;

    @FindBy(xpath = ".//*[@class='google-plus']")
    WebElement googleplus;

    @FindBy(xpath=".//*[@class='alert alert-danger']/ol/li")
    WebElement errormessage;




    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void loginToApplication(String username, String password){
        signInLink.click();
        log("cliked on sign in and object is:-"+signInLink.toString());
        uname.sendKeys(username);
        log("entered email address:-"+uname+" and object is "+uname.toString());
        pword.sendKeys(password);
        log("entered password:-"+password+" and object is "+pword.toString());
        signInBtn.click();
        log("clicked on sublit button and object is:-"+signInBtn.toString());
    }

    public String getTitle(){
      return  driver.getTitle();
    }




}
