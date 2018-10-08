import Case.TestCase;
import PageElements.HomePage;
import PageElements.LoginPage;
import TestNG.TestRunnerBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class LoginTest extends TestRunnerBase {
    @Test(dataProvider = "GenerateTestCase")
    public void siteHeaderIsOnHomePage(TestCase testCase) {
        //set up any environments and go to start of the page
        testCase.startTest(reports, this.getClass().getSimpleName() );
        try {
            WebDriver browser = testCase.getBrowser();
            browser.get(testCase.getUrl());
            HomePage.loginButton(browser).click();
            testCase.assertEquals(LoginPage.loginTitle(browser).getText(), "Log In", "Asserting if we are on the Log In Page");


            testCase.endTest();
        }catch (Exception e) {
            testCase.logFatal(e.getMessage());
            testCase.endTest();
        }

    }
}
