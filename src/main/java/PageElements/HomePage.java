package PageElements;
//This is a Library for Elements on Page to be used as reference for Tests to improve readability.

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    public static void waitForHomePage(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='button-primary']")));
    }

    //Being forced to use xpath is bad practice but there isnt anything labeled as a unique ID for the Login Element
    public static WebElement loginButton(WebDriver driver){
        return driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/nav[1]/ul[2]/li[1]/a[1]"));
    }
}
