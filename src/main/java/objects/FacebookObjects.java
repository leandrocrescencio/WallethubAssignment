package objects;

import elements.FacebookElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FacebookObjects extends FacebookElements {

    public static final Logger log = LogManager.getLogger(FacebookObjects.class);
    private WebDriver driver;

    private WebDriver getDriver() {
        return driver;
    }
    private void setDriver(WebDriver page) {
        driver = page;
    }

    public FacebookObjects(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void login(String usr, String pwd) {
            Utils.waitForPageLoadComplete(getDriver());
            Utils.waitElementToBeVisible(email);
            Utils.waitElementToBeVisible(pass);
            email.sendKeys(usr);
            pass.sendKeys(pwd);

            if(Utils.elementIsPresent(By.xpath("//input[@type='submit']"))) {
                Utils.waitElementToBeClickable(loginButton);
                loginButton.click();
            } else {
                Utils.waitElementToBeClickable(bigButton);
                bigButton.click();
            }


    }

    public void post(String msg) {
        Utils.waitForPageLoadComplete(getDriver());
        Utils.waitTitle("Facebook");
        Utils.waitElementToBeVisible(messagePost);
        Utils.waitElementToBeClickable(messagePost);
        messagePost.sendKeys(msg);

        Utils.waitElementToBeClickable(messagePostButton);
        messagePostButton.click();

    }

}
