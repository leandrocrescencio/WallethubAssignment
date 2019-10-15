package setup;

import utils.Static;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Setup {
    public static final Logger log = LogManager.getLogger(Setup.class);
    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver page) {
        driver = page;
    }

    public static final String BROWSER = "webdriver.chrome.driver";

    @BeforeSuite(alwaysRun = true)
    public void extentSetup(ITestContext context) {
        if (!context.getName().contains("Default") && !context.getName().contains("Surefire") && !context.getName().contains("Gradle")) {
            Static.setTestName(context.getCurrentXmlTest().getSuite().getName());
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeScenario(Method method) {
        Test test = method.getAnnotation(Test.class);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("test-type");
        options.addArguments("--verbose");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-gpu");
        options.addArguments("--dns-prefetch-disable");

        String[] chromium = {"Windows", "Mac", "Linux"};
        int i;
        for (i = 0; i < chromium.length; i++)
            if (Utils.containsIgnoreCase(Static.OS,chromium[i])) break;

        switch (i) {
            case 0: //Windows
                System.setProperty(BROWSER, Static.PATH_PROJECT + Propertie.getValue("driverWin"));
                break;
            case 1: //Mac
                System.setProperty(BROWSER, Static.PATH_PROJECT + Propertie.getValue("driverMac"));
                break;
            case 2: //Linux
                System.setProperty(BROWSER, Static.PATH_PROJECT + Propertie.getValue("driverLinux"));
                break;
            default:
                log.error("No specification for the driver");
                break;
        }
        setDriver(new ChromeDriver(options));
        getDriver().manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);

        String[] url = {"Facebook", "Wallethub"};
        for (i = 0; i < url.length; i++)
            if (Utils.containsIgnoreCase(test.description(),url[i])) break;

        switch (i) {
            case 0: // Facebook
                getDriver().get(Propertie.getValue("url1"));
                break;
            case 1: // Wallethub
                getDriver().get(Propertie.getValue("url2"));
                break;
            default:
                log.error("No specification for the app url");
                break;
        }

        log.info("Starting scenario: {}", test.description());

        if (Static.getTestName().contains("Default") && Static.getTestName().contains("Suite")) {
            Static.setTestName(this.getClass().getSimpleName());
        }
        Utils.waitForPageLoadComplete(getDriver());

        ExtentTestManager.startTest(method.getName(), test.description());
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult result) {
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group); // new
        }
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed", ExtentTestManager.getTest().addBase64ScreenShot(ScreenShot.capture()));
                ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
                break;
            case ITestResult.SKIP:
                ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped", ExtentTestManager.getTest().addBase64ScreenShot(ScreenShot.capture()));
                ExtentTestManager.getTest().log(LogStatus.SKIP, getStackTrace(result.getThrowable()));
                break;
            case ITestResult.SUCCESS:
                ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
                break;
            default:
                if (ExtentTestManager.getTest().getRunStatus().equals(LogStatus.UNKNOWN)) {
                    ExtentTestManager.getTest().log(LogStatus.SKIP, "This test method is skipped");
                } else {
                    ExtentTestManager.endTest();
                }
        }
        ExtentManager.getReporter(Static.getTestName()).endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter(Static.getTestName()).flush();

        getDriver().quit();
        log.info("Execution finished\n+++++++++++++++++++++++++++++++++++++++++++++");

    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

}
