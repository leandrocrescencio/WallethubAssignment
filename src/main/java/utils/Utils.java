package utils;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Setup;

public class Utils  extends Setup {
    // Extends the Setup to access drivers declarations

    public static void waitElementToBeClickable (WebElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), Static.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean elementIsPresent(By selector) {
        try {
            getDriver().findElement(selector);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public static void waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Static.TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitSelectorToBeVisible(By selector) {
        return (new WebDriverWait(getDriver(), Static.TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static WebElement waitSelectorToBeClickable(By selector) {
        return (new WebDriverWait(getDriver(), Static.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(selector));
    }

    public static WebElement waitPresenceOfElement(By selector) {
        return (new WebDriverWait(getDriver(), Static.TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(selector));
    }

    public static void waitTitle(String title) {
        (new WebDriverWait(getDriver(), Static.TIMEOUT)).until(ExpectedConditions.titleIs(title));
    }

    public static void scrollUntilElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void mouseClickByLocator( WebElement element) {
        Actions action = new Actions(getDriver());
        action.moveToElement( element ).build().perform();
        action.click( element ).build().perform();
    }

    public static void returnPreviousPage(){
        getDriver().navigate().back();
    }

    public static void waitForPageLoadComplete(WebDriver driver) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Static.TIMEOUT);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    private static String alpha = "aaaaaaAbBcCdDeeeeeeeEfFgGh .HiiiiiiiiIjJkKlLmMn NoooooooooOpP. qQrRsStTuuuuuuuuuuuUvVxXyYwWzZ. ! 1234567890 WalletHub Hire Me :)";

    public static String randomText() {
        return RandomStringUtils.random(200, alpha);
    }

}
