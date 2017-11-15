package homePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import uiActions.HomePage;
import testbase.testbase;

import java.io.IOException;


public class tc_001 extends testbase{

    public static final Logger log = Logger.getLogger(tc_001.class.getName());

    HomePage homepage;

    @BeforeClass
    public void setUp() throws IOException{
        init();
        homepage = new HomePage(driver);
    }

    @Test
    public void verifyLoginWithInvalidUseridPassword(){
        log.info("========================Starting Test case 1 =====================");

        homepage.loginToApplication("qaz@gmail.com","Password@1");
        Assert.assertEquals(homepage.getTitle(),"My account - My Store");
        log.info("===================================Finished executing Test case 1====================");
    }

}
