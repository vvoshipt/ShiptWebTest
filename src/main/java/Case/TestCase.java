package Case;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCase {
    private WebDriver browser;
    private String url;
    public ExtentTest test;
    public SoftAssert softAssertion = new SoftAssert();


    public TestCase(String url) {
        this.browser = getLocalDriver();
        this.url = url;
    }

    public void startTest(ExtentReports reports, String name) {
        test = reports.startTest(name + "_" + getCurrentTimeStamp());
        logInfo("Starting test: " + name);
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public void endTest(){
        browser.close();
        browser.quit();
        softAssertion.assertAll();
    }


    private WebDriver getLocalDriver() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        return new ChromeDriver();
    }

    public void logInfo(String message){
        syso(message);
        test.log(LogStatus.INFO, message);
    }

    public void logFatal(String message){
        syso(message);
        test.log(LogStatus.FATAL, message);
        softAssertion.fail("FATAL ERROR OCCURED: \n " + message);
    }

    public void assertEquals(Object first, Object second, String message){
        String details = "Asserting {" + first.toString() + "} is equal to {" + second.toString() + "}";

        if(first.equals(second)){
            logPass("PASS: " + details);
        }else{
            logError("FAIL: " + details);
        }
        softAssertion.assertEquals(first, second, message);
    }

    public void logPass(String message){
        syso(message);
        test.log(LogStatus.PASS, message);
    }

    public void logError(String message){
        syso(message);
        test.log(LogStatus.ERROR, message);
    }

    private void syso(String message){
        System.out.println(message);
    }

    public WebDriver getBrowser() {
        return browser;
    }

    public String getUrl() {
        return url;
    }
}
