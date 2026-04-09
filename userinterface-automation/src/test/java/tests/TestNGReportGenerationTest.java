package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Simple test to verify TestNG report generation
 */
public class TestNGReportGenerationTest {
    @Test(groups = {"smoke"}, priority = 1, description = "Verify simple TestNG functionality")
    public void testSimplePass() {
        System.out.println("Running simple passing test");
        Assert.assertTrue(true, "This test should always pass");
    }

    /*@DataProvider(name = "numberProvider", parallel = true)
    public Object[][] numberProvider() {
        Object[] data1 = {1, 2, 3};
        Object[] data2 = {2, 4, 7};
        return new Object[][]{
                data1, data2
        };
    }*/

    @DataProvider(name = "numberProvider")
    public Object[][] numberProvider() {
        return new Object[][]{
                {1, 2, 3},
                {2, 4, 7}
        };
    }

    @Test(groups = {"smoke"}, priority = 2, description = "Verify TestNG assertion", dataProvider = "numberProvider")
    public void testSimpleAssertion(int num1, int num2, int sum) throws InterruptedException {
        System.out.println("Running assertion test");
        Thread.sleep(2000);
        Assert.assertEquals(num1 + num2, sum, "Math should work correctly");
    }

    private void getResult(String status) {
        System.out.println("Our test " + status);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        int status = result.getStatus();
        switch (status) {
            case ITestResult.SUCCESS:
                getResult("PASSED");
                break;
            case ITestResult.FAILURE:
                getResult("FAILED");
                break;
            case ITestResult.SKIP:
                getResult("SKIPPED");
                break;
            default:
                getResult("UNKNOWN");
                break;
        }
    }
}
