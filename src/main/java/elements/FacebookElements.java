package elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookElements {

    @FindBy(name = "email")
    protected WebElement email;

    @FindBy(name = "pass")
    protected WebElement pass;

    @FindBy(xpath = "//input[@type='submit']")
    protected WebElement loginButton;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement bigButton;

    @FindBy(xpath = "//textarea[contains(@name,'message')]")
    protected WebElement messagePost;

    @FindBy(xpath = "//button[contains(@class,'selected')]/span")
    protected WebElement messagePostButton;

}
