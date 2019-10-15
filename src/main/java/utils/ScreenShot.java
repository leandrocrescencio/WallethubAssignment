package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import setup.Setup;

public class ScreenShot {

    public static final Logger log = LogManager.getLogger(ScreenShot.class);
    private static String base64Encoded = "";

    private ScreenShot() {
        throw new IllegalStateException("Utility class");
    }

    /**
     *  Take a screenshot from the driver screen
     *
     * @return
     */

    public static String capture() {
        WebDriver webDriver = Setup.getDriver();
        base64Encoded = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        return base64Encoded;
    }

}