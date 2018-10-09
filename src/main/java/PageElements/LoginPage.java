package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This is a Library for Elements on Page to be used as reference for Tests to improve readability.
public class LoginPage {
    public static void waitForLoginPage(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button-1 w-100 sc-bwzfXH jPmxNo']")));
    }

    public static WebElement createAccountLink(WebDriver driver){
        return driver.findElement(By.xpath("//a[@class='curacao semibold link small']"));
    }

    public static WebElement usernameInput(WebDriver driver){
        return driver.findElement(By.xpath("//input[@name='username']"));
    }

    public static WebElement passwordInput(WebDriver driver){
        return driver.findElement(By.xpath("//input[@name='password']"));
    }

    public static WebElement loginButton(WebDriver driver){
        return driver.findElement(By.xpath("//button[@class='button-1 w-100 sc-bwzfXH jPmxNo']"));
    }

    public static WebElement errorMessage(WebDriver driver){
        return driver.findElement(By.xpath("//div[@class='LoginForm-error tomato mb1']"));
    }

    public static WebElement shiptLogoLink(WebDriver driver){
        return driver.findElement(By.xpath("//header[@class='container pv3 ph4 ph0-m']//a"));
    }

    public static WebElement loginTitle(WebDriver driver){
        return driver.findElement(By.xpath("//h1[@class='title-3 near-black']"));
    }

}
