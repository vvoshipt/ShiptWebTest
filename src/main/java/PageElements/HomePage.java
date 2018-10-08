package PageElements;
//This is a Library for Elements on Page to be used as reference for Tests

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    //Being forced to use xpath is bad practice but there isnt anything labeled as a unique ID for the Login Element
    public static WebElement loginButton(WebDriver driver){
        return driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/nav[1]/ul[2]/li[1]/a[1]"));
    }
}
