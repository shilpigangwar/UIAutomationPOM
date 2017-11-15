package customlistener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import testbase.testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class listener extends testbase implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        if(iTestResult.isSuccess()){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

            String methodName = iTestResult.getName();

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

    }


    public void onTestFailure(ITestResult iTestResult) {
        if(!iTestResult.isSuccess()){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

            String methodName = iTestResult.getName();

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

    }

    public void onTestSkipped(ITestResult iTestResult) {
        Reporter.log("Test is skipped:" + iTestResult.getMethod().getMethodName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        Reporter.log("Test is started:" + iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        Reporter.log("Test is finished:" + iTestContext.getName());
    }
}
