import objects.FacebookObjects;
import objects.WallethubObjects;
import utils.Propertie;
import org.testng.annotations.Test;
import setup.Setup;
import utils.RetryTest;
import utils.Utils;

public class AssignmentTests extends Setup {

    @Test(groups = "regression", description = "Facebook Posting", retryAnalyzer = RetryTest.class)
    public void testAssignment_01() {
        FacebookObjects facebook = new FacebookObjects(getDriver());
        facebook.login(Propertie.getValue("fbLogin"), Propertie.getValue("fbPwd"));

        facebook.post("Hello World");


    }

    @Test(groups = "regression", description = "WalletHub test insurance company flow", retryAnalyzer = RetryTest.class)
    public void testAssignment_02() {
        String product = "Health Insurance";
        String review = Utils.randomText();
        int stars = 4;

        WallethubObjects wallethub = new WallethubObjects(getDriver());
        wallethub.login(Propertie.getValue("whLogin"), Propertie.getValue("whPwd"));
        wallethub.setReview(stars);
        wallethub.setOption(product);
        wallethub.submitReview(Utils.randomText());
        wallethub.reviewConfirmation(review, product);

    }

}
