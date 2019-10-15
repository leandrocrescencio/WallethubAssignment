package objects;


import elements.WallethubElements;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utils;

public class WallethubObjects extends WallethubElements {

    public static final Logger log = LogManager.getLogger(WallethubObjects.class);
    private WebDriver driver;

    private WebDriver getDriver() {
        return driver;
    }
    private void setDriver(WebDriver page) {
        driver = page;
    }

    public WallethubObjects(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }


    public void login(String usr, String pwd) {
            Utils.waitForPageLoadComplete(getDriver());
            Utils.waitElementToBeVisible(login);
            Utils.waitElementToBeVisible(login);

            login.click();

            Utils.waitElementToBeVisible(email);
            Utils.waitElementToBeVisible(pass);
            email.sendKeys(usr);
            pass.sendKeys(pwd);

            Utils.waitElementToBeClickable(loginButton);
            loginButton.click();

    }

    public void setReview(int n) {
        Utils.waitElementToBeVisible(fiveStars.get(0));
        Utils.scrollUntilElement(fiveStars.get(0));

        Actions action = new Actions(getDriver());

        for (int i = 0; i < n; i++) {
            action.moveToElement(fiveStars.get(i)).build().perform();
            if (i==n-1) {
                action.click(fiveStars.get(i)).build().perform();
            }

        }
    }

    public void setOption (String n) {

        Utils.waitElementToBeVisible(dropdown);
        Utils.mouseClickByLocator(dropdown);

        Utils.waitElementToBeClickable(dropdownList.get(0));

        for (int i = 0; i < dropdownList.size(); i++) {
            if (Utils.containsIgnoreCase(dropdownList.get(i).getText(),n)) {
                Utils.mouseClickByLocator(dropdownList.get(i));
            }
        }
    }

    public void submitReview(String words) {
        Utils.waitElementToBeClickable(messageReview);
        messageReview.click();
        messageReview.clear();
        messageReview.sendKeys(words);

        submitButton.click();

    }

    public void reviewConfirmation(String text, String product) {
       Utils.waitTitle("WalletHub - Review Confirmation");

        Utils.waitElementToBeVisible(confirmation);
        Utils.waitElementToBeClickable(continueButton);
        continueButton.click();

        Utils.waitForPageLoadComplete(getDriver());

        Utils.waitElementToBeVisible(username);
        Utils.mouseClickByLocator(username);

        Utils.waitElementToBeClickable(usernameList.get(0));

        for (int i = 0; i < usernameList.size(); i++) {
            System.out.println(usernameList.get(i).getText());
            if (Utils.containsIgnoreCase(usernameList.get(i).getText(),"Profile")) {
                usernameList.get(i).click();
            }
        }

        Assert.assertTrue(profileReviewlist.get(0).isDisplayed());
        profileReviewlist.get(0).click();

        Utils.waitForPageLoadComplete(getDriver());

        Assert.assertTrue(reviewlist.get(0).getText().equalsIgnoreCase(text));
        Assert.assertTrue(reviewProductlist.get(0).getText().equalsIgnoreCase(product));


    }
}
