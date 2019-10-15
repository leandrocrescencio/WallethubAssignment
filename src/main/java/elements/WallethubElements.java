package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WallethubElements {


    @FindBy(xpath = "//span[contains(@class,'brgm-button')]")
    protected WebElement login;

    @FindBy(name = "em")
    protected WebElement email;

    @FindBy(xpath = "//input[@type='password']")
    protected WebElement pass;

    @FindBy(xpath = "//button[contains(@class,'btn blue')]")
    protected WebElement loginButton;

    @FindBy(xpath = "//*[@id='reviews-section']/div[1]/div[3]/review-star/div//*[name()='svg']/*[name()='g']")
    protected List<WebElement> fiveStars;

    @FindBy(xpath = "//*[@class='wrev-drp']//span[contains(@class,'dropdown')]")
    protected WebElement dropdown;

    @FindBy(xpath = "//*[@class='wrev-drp']//li[contains(@class,'dropdown')]")
    protected List<WebElement> dropdownList;

    @FindBy(xpath = "//*[@class='android']/textarea")
    protected WebElement messageReview;

    @FindBy(xpath = "//div[text()='Submit']")
    protected WebElement submitButton;

    @FindBy(xpath = "//h2[text()='Awesome!']")
    protected WebElement confirmation;

    @FindBy(xpath = "//div[text()='Continue']")
    protected WebElement continueButton;

    @FindBy(xpath = "//div[contains(@class,'brgm-user')]//span[contains(@class,'brgm-list-title')]")
    protected WebElement username;

    @FindBy(xpath = "//div[contains(@class,'brgm-user-list')]/a")
    protected List<WebElement> usernameList;

    @FindBy(xpath = "//div[contains(@class,'brgm-user-list')]/span")
    protected WebElement logout;

    @FindBy(xpath = "//div[contains(@class,'pr-rec-texts-container')]/a")
    protected List<WebElement> profileReviewlist;

    @FindBy(xpath = "//div[contains(@class,'rvtab-ci-content with-links text-select')]")
    protected List<WebElement> reviewlist;

    @FindBy(xpath = "//div[contains(@class,'rvtab-ci-category')]/span")
    protected List<WebElement> reviewProductlist;



}
