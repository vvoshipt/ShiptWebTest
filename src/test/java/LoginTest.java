import Case.TestCase;
import PageElements.HomePage;
import PageElements.LoginPage;
import TestNG.TestRunnerBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class LoginTest extends TestRunnerBase {
    @Test(dataProvider = "GenerateTestCase")
    public void LoginFunctions(TestCase testCase) {
        //set up any environments and go to start of the page
        testCase.startTest(reports, this.getClass().getSimpleName() );
        try {
            //Got to Home Page
            WebDriver browser = testCase.getBrowser();
            browser.get(testCase.getUrl());
            HomePage.waitForHomePage(browser);

            //Go to Login
            HomePage.loginButton(browser).click();
            LoginPage.waitForLoginPage(browser);
            testCase.assertEquals(LoginPage.loginTitle(browser).getText(), "Log In", "Asserting if we are on the Log In Page");
            testCase.assertEquals(LoginPage.createAccountLink(browser).getText(), "Create account", "Asserting Create Account Link Exists and is Labeled Correctly");

            //Attempt Login with invalid user and Password
            attemptLogin("badUser@gmail.com", "badPassword", browser);
            testCase.assertEquals(LoginPage.errorMessage(browser).getText(), "Invalid Username or Password", "Assert Login Error when Invalid User or Invalid Password");
            attemptLogin("ValidUser@gmail.com", "badPassword", browser);
            testCase.assertEquals(LoginPage.errorMessage(browser).getText(), "Invalid Username or Password", "Assert Login Error when Valid User or InvalidPassword");
            attemptLogin("badUser@gmail.com", "ValidPassword", browser);
            testCase.assertEquals(LoginPage.errorMessage(browser).getText(), "Invalid Username or Password", "Assert Login Error when Invalid User or  Valid Password");

            //Attempt Login with valid User and then Logout and return to login page
            //TODO add logic for this if valid user is available.
            //attemptLogin("validUser@gmail.com", "validPassword", browser);
            //waitForLoggedInPageToLoad
            //Assert element on page to show we logged in
            //logout
            //HomePage.loginButton(browser).click();
            //LoginPage.waitForLoginPage(browser);

            //Test workflow of Shipt logo to go to home page and come back to login page
            LoginPage.shiptLogoLink(browser).click();
            HomePage.waitForHomePage(browser);
            testCase.assertEquals(LoginPage.loginTitle(browser).getText(), "Log In", "Asserting Ship Logo in Log In takes us back to ");
            HomePage.loginButton(browser).click();
            LoginPage.waitForLoginPage(browser);


            testCase.endTest();
        }catch (Exception e) {
            testCase.logFatal(e.getMessage());
            testCase.endTest();
        }

    }

    private void attemptLogin(String user, String password, WebDriver browser) throws InterruptedException {
        LoginPage.usernameInput(browser).clear();
        LoginPage.usernameInput(browser).sendKeys(user);
        LoginPage.passwordInput(browser).clear();
        LoginPage.passwordInput(browser).sendKeys(password);
        LoginPage.loginButton(browser).click();
        //Selenium moves to fast after click to assert anything: must use a type of wait
        TimeUnit.SECONDS.sleep(1);
    }
}
