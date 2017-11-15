package testbase;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import customlistener.webeventlistener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class testbase {

    public static final Logger log = Logger.getLogger(testbase.class.getName());

    public WebDriver driver;
   // String url = "http://www3.hilton.com/en/index.html";
  //  String browser = "chrome";
    public static ExtentTest test;
    public static ExtentReports extentReports;
    public webeventlistener eventListener;
    public Properties OR = new Properties();
    public ITestResult iTestResult;


    public WebDriver getDriver(){
        return driver;
    }

    static {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/src/main/java/report/test" + formater.format(calendar.getTime()) + ".html", false);
            }

    public void loadData() throws IOException{
        File file = new File(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
        FileInputStream f = new FileInputStream(file);
        OR.load(f);
    }

    public void setDriver(EventFiringWebDriver driver) {
        this.driver = driver;
    }


    public void init() throws IOException{
        try {
            loadData();

            String log4jConfPath = "log4j.properties";
            PropertyConfigurator.configure(log4jConfPath);
            System.out.println(OR.getProperty("browser"));
            selectBrowser(OR.getProperty("browser"));
            geturl(OR.getProperty("url"));
        } catch (Exception e){
            getScreenShot("initIssue");}
        }

    public void selectBrowser(String browser){
        System.out.println(System.getProperty("os.name"));

        if (System.getProperty("os.name").contains("Window")) {
            if (browser.equals("chrome")) {
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                // driver = new EventFiringWebDriver(dr);
                // eventListener = new WebEventListener();
                // driver.register(eventListener);
            } else if (browser.equals("firefox")) {
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                // driver = new EventFiringWebDriver(dr);
                eventListener = new webeventlistener();
                // driver.register(eventListener);
                // setDriver(driver);
            }
        } else if (System.getProperty("os.name").contains("Mac")) {
            if (browser.equals("chrome")) {
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
                driver = new ChromeDriver();
                // driver = new EventFiringWebDriver(dr);
                // eventListener = new WebEventListener();
                // driver.register(eventListener);
            } else if (browser.equals("firefox")) {
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
                driver = new FirefoxDriver();
                // driver = new EventFiringWebDriver(dr);
                eventListener = new webeventlistener();
                // driver.register(eventListener);
                // setDriver(driver);
            }
        }
    }

    public void geturl(String url){
        log.info("navigating to :-" + url);
        driver.get(url);
        driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //public String[][] getData(String excelName, String sheetName) {
    //    String path = System.getProperty("user.dir") + "/src/main/java/com/test/automation/uiAutomation/data/" + excelName;
    //    excel = new Excel_Reader(path);
     //   String[][] data = excel.getDataFromSheet(sheetName, excelName);
     //   return data;
   // }

    public void waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element){
        WebDriverWait wait= new WebDriverWait(driver,timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void getScreenShot(String name){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/screenshots/";
            File destFile = new File((String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(srcFile, destFile);
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void highlightMe(WebDriver driver, WebElement element) throws InterruptedException {
        // Creating JavaScriptExecuter Interface
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Execute javascript
        js.executeScript("arguments[0].style.border='4px solid yellow'", element);
        Thread.sleep(3000);
        js.executeScript("arguments[0].style.border=''", element);
    }

    public Iterator<String> getAllWindows() {
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();
        return itr;
    }

    public void getScreenShot(WebDriver driver, ITestResult result, String folderName) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        String methodName = result.getName();

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/";
            File destFile = new File((String) reportDirectory + "/" + folderName + "/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");

            FileUtils.copyFile(scrFile, destFile);

            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getScreenShotOnSucess(WebDriver driver, ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        String methodName = result.getName();

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/";
            File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");

            FileUtils.copyFile(scrFile, destFile);

            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String captureScreen(String fileName) {
        if (fileName == "") {
            fileName = "blank";
        }
        File destFile = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/test/automation/uiAutomation/screenshot/";
            destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, destFile);
            // This will help us to link the screen shot in testNG report
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile.toString();
    }

    public void log(String data) {
        log.info(data);
        Reporter.log(data);
        test.log(LogStatus.INFO, data);
    }

    public void getresult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, result.getName() + " test is pass");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
            String screen = captureScreen("");
            test.log(LogStatus.FAIL, test.addScreenCapture(screen));
        } else if (result.getStatus() == ITestResult.STARTED) {
            test.log(LogStatus.INFO, result.getName() + " test is started");
        }
    }

    @AfterMethod()
    public void afterMethod(ITestResult result) {
        getresult(result);
    }

    @BeforeMethod()
    public void beforeMethod(Method result) {
        test = extentReports.startTest(result.getName());
        test.log(LogStatus.INFO, result.getName() + " test Started");
    }

    @AfterClass(alwaysRun = true)
    public void endTest() {
        closeBrowser();
    }

    public void closeBrowser() {
        //driver.quit();
        log.info("browser closed");
        extentReports.endTest(test);
        extentReports.flush();
    }

    public WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void launchapp(String browser) throws IOException {

        if (System.getProperty("os.name").contains("Mac")) {
            if (browser.equals("chrome")) {
                //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
                System.out.println(" Executing on CHROME");
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                String Node = "http://localhost:5001/wd/hub";
                driver = new RemoteWebDriver(new URL(Node), cap);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                // Launch website
                loadData();
                geturl(OR.getProperty("url"));
            } else if (browser.equals("firefox")) {
                //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
                System.out.println(" Executing on FireFox");
                String Node = "http://172.16.123.130:5000/wd/hub";
                DesiredCapabilities cap = DesiredCapabilities.firefox();
                cap.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(Node), cap);
                loadData();
                geturl(OR.getProperty("url"));
            } else if (browser.equalsIgnoreCase("ie")) {
                System.out.println(" Executing on IE");
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("ie");
                String Node = "http://192.168.0.101:5555/wd/hub";
                driver = new RemoteWebDriver(new URL(Node), cap);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                // Launch website
                loadData();
                geturl(OR.getProperty("url"));
            } else {
                throw new IllegalArgumentException("The Browser Type is Undefined");
            }
        }
        if (System.getProperty("os.name").contains("Window")) {
            if (browser.equals("chrome")) {
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                System.out.println(" Executing on CHROME");
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                String Node = "http://localhost:5555/wd/hub";
                driver = new RemoteWebDriver(new URL(Node), cap);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                // Launch website
                loadData();
                geturl(OR.getProperty("url"));
            } else if (browser.equals("firefox")) {
                System.out.println(System.getProperty("user.dir"));
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
                System.out.println(" Executing on FireFox");
                String Node = "http://172.16.123.130:5554/wd/hub";
                DesiredCapabilities cap = DesiredCapabilities.firefox();
                cap.setBrowserName("firefox");
                driver = new RemoteWebDriver(new URL(Node), cap);
                loadData();
                geturl(OR.getProperty("url"));
            } else if (browser.equalsIgnoreCase("ie")) {
                System.out.println(" Executing on IE");
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("ie");
                String Node = "http://192.168.0.101:5555/wd/hub";
                driver = new RemoteWebDriver(new URL(Node), cap);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                // Launch website
                geturl(OR.getProperty("url"));
            } else {
                throw new IllegalArgumentException("The Browser Type is Undefined");
            }
        }
    }




}
