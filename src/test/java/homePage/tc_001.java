package homePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uiActions.HomePage;
import testbase.testbase;

import java.util.concurrent.TimeUnit;

public class tc_001 extends testbase{

    public static final Logger log = Logger.getLogger(tc_001.class.getName());

    HomePage homepage;

    @BeforeTest
    public void setUp(){
    init();
    }

    @Test
    public void verifyLoginWithInvalidUseridPassword(){
        log.info("========================Starting Test case 1 =====================");
        homepage = new HomePage(driver);
        homepage.loginToApplication("username@123.com","password123");
        Assert.assertEquals(homepage.getInvalidLoginMessage(),"Hmm, your login details don't seem right. Check that your caps lock is off and try again.\n" +
                "Careful! After five tries, you'll get locked out.\n" +
                "Forgot your password? Get a new one.");
        log.info("===================================Finished executing Test case 1====================");
    }

    @AfterTest
    public void endTest(){
    driver.close();
    }
}
