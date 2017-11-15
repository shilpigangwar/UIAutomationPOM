package customlistener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class retry implements IRetryAnalyzer {

    public static final Logger log = Logger.getLogger(retry.class.getName());
    private int retryCount = 0;
    private  int maxRetryCount = 3;


    public boolean retry(ITestResult iTestResult) {
           if (retryCount < maxRetryCount) {
            log("Retrying test " + iTestResult.getName() + " with status " + getResultStatusName(iTestResult.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            retryCount++;
            return true;
           }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

    public void log(String data){
        log.info(data);
        Reporter.log(data);
    }
}
