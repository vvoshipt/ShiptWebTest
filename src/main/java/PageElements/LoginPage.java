package PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//This is a Library for Elements on Page to be used as reference for Tests
public class LoginPage {
    public static WebElement shiptLogoLink(WebDriver driver){
        return driver.findElement(By.xpath("//header[@class='container pv3 ph4 ph0-m']//a"));
    }

    public static WebElement loginTitle(WebDriver driver){
        return driver.findElement(By.xpath("//h1[@class='title-3 near-black']"));
    }

}
