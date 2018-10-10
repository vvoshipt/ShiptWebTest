package Case;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TestCase {
    private RemoteWebDriver browser;
    private String url;
    public ExtentTest test;
    public SoftAssert softAssertion = new SoftAssert();


    public TestCase(String url) {
        this.browser = getLocalDriver();
        browser.manage().window().maximize();
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


    private RemoteWebDriver getLocalDriver() {
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

    public void assertEquals(Object first, Object second, String message) throws IOException {
        String details =  "<br />"+message + "<br />Asserting {" + first.toString() + "} is equal to {" + second.toString() + "}";

        if(first.equals(second)){
            logPass("PASS: " + details);
        }else{
            logError("FAIL: " + details);
        }
        softAssertion.assertEquals(first, second, message);
    }

    private void logPass(String message){
        syso(message);
        test.log(LogStatus.PASS, message);
    }

    private void logError(String message) throws IOException {
        syso(message);
        String screenShotFilePath = takeScreenShot(getScreenshotName());
        test.log(LogStatus.ERROR, message + test.addScreenCapture(screenShotFilePath));
    }

    public String getScreenshotName(){
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+"_"+UUID.randomUUID()+".png";
    }

    public String takeScreenShot(String screenShotName) throws IOException {
        screenShotName = screenShotName.replaceAll("/", "_");
        syso("OutputType.FILE: " + OutputType.FILE.toString());
        File scrFile = browser.getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        String filePath = System.getProperty("user.dir") + "/ExtentReport/"+ screenShotName;
        FileUtils.copyFile(scrFile, new File(filePath));
        return filePath;
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
