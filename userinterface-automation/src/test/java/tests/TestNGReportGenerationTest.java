package tests;

import org.testng.Assert;
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

    @Test(groups = {"smoke"}, priority = 2, description = "Verify TestNG assertion")
    public void testSimpleAssertion() {
        System.out.println("Running assertion test");
        Assert.assertEquals(2 + 2, 4, "Math should work correctly");
    }
}
