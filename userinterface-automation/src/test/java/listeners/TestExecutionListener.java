package listeners;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Test Execution Listener for comprehensive test result management
 * Provides real-time test tracking, status management, and detailed reporting
 */
public class TestExecutionListener implements ITestListener {
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== TEST SUITE START ===");
        System.out.println("Suite: " + context.getSuite().getName());
        System.out.println("Total tests: " + context.getAllTestMethods().length);
        System.out.println("Start time: " + new java.util.Date(context.getStartDate().getTime()));
        System.out.println("========================");
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        String className = result.getTestClass().getName();
        
        System.out.println("STARTING: " + testName + " (" + className + ")");
        
        // Log test metadata
        System.out.println("  Groups: " + java.util.Arrays.toString(result.getMethod().getGroups()));
        System.out.println("  Priority: " + getTestPriority(result));
        System.out.println("  Description: " + getTestDescription(result));
        System.out.println("  Thread: " + Thread.currentThread().getId() + " (" + Thread.currentThread().getName() + ")");
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        String testName = result.getName();
        
        System.out.println("PASSED: " + testName + " (" + duration + "ms)");
        
        // Add Allure attachment with success details
        String successReport = String.format(
            "Test: %s\nClass: %s\nStatus: PASSED\nDuration: %d ms\nThread: %d\nEnd Time: %s",
            testName, 
            result.getTestClass().getName(), 
            duration,
            Thread.currentThread().getId(),
            new java.util.Date(result.getEndMillis())
        );
        
        Allure.addAttachment("Test Success Report", "text/plain", successReport);
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        String testName = result.getName();
        
        System.out.println("FAILED: " + testName + " (" + duration + "ms)");
        System.out.println("  Error: " + result.getThrowable().getMessage());
        System.out.println("  Thread: " + Thread.currentThread().getId());
        
        // Add Allure attachment with failure details
        String failureReport = String.format(
            "Test: %s\nClass: %s\nStatus: FAILED\nDuration: %d ms\nThread: %d\nError: %s\nStack Trace: %s\nEnd Time: %s",
            testName, 
            result.getTestClass().getName(), 
            duration,
            Thread.currentThread().getId(),
            result.getThrowable().getMessage(),
            getStackTrace(result.getThrowable()),
            new java.util.Date(result.getEndMillis())
        );
        
        Allure.addAttachment("Test Failure Report", "text/plain", failureReport);
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        
        System.out.println("SKIPPED: " + testName);
        System.out.println("  Thread: " + Thread.currentThread().getId());
        
        String skipReason = result.getThrowable() != null ? 
            result.getThrowable().getMessage() : "Unknown reason";
        System.out.println("  Reason: " + skipReason);
        
        String skipReport = String.format(
            "Test: %s\nClass: %s\nStatus: SKIPPED\nThread: %d\nReason: %s\nEnd Time: %s",
            testName, 
            result.getTestClass().getName(), 
            Thread.currentThread().getId(),
            skipReason,
            new java.util.Date(result.getEndMillis())
        );
        
        Allure.addAttachment("Test Skip Report", "text/plain", skipReport);
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getName();
        
        System.out.println("PARTIAL FAILURE: " + testName);
        System.out.println("  Success Percentage: " + result.getMethod().getSuccessPercentage() + "%");
        System.out.println("  Thread: " + Thread.currentThread().getId());
        
        String partialReport = String.format(
            "Test: %s\nClass: %s\nStatus: PARTIAL FAILURE\nSuccess Percentage: %d%%\nThread: %d\nEnd Time: %s",
            testName, 
            result.getTestClass().getName(), 
            result.getMethod().getSuccessPercentage(),
            Thread.currentThread().getId(),
            new java.util.Date(result.getEndMillis())
        );
        
        Allure.addAttachment("Partial Failure Report", "text/plain", partialReport);
    }
    
    @Override
    public void onFinish(ITestContext context) {
        long totalDuration = context.getEndDate().getTime() - context.getStartDate().getTime();
        
        System.out.println("=== TEST SUITE SUMMARY ===");
        System.out.println("Suite: " + context.getSuite().getName());
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("Total: " + (context.getPassedTests().size() + 
                         context.getFailedTests().size() + 
                         context.getSkippedTests().size()));
        System.out.println("Duration: " + totalDuration + "ms (" + (totalDuration/1000) + "s)");
        System.out.println("End time: " + new java.util.Date(context.getEndDate().getTime()));
        System.out.println("========================");
        
        // Create comprehensive summary report
        String summary = String.format(
            "Test Suite: %s\n" +
            "========================\n" +
            "Execution Summary:\n" +
            "- Passed: %d\n" +
            "- Failed: %d\n" +
            "- Skipped: %d\n" +
            "- Total: %d\n" +
            "- Duration: %d ms (%.2f minutes)\n" +
            "- Start Time: %s\n" +
            "- End Time: %s\n" +
            "- Parallel Execution: %s\n\n" +
            "Performance Metrics:\n" +
            "- Average Test Duration: %.2f ms\n" +
            "- Fastest Test: %d ms\n" +
            "- Slowest Test: %d ms\n\n" +
            "Thread Information:\n" +
            "- Total Threads Used: %d\n" +
            "- Parallel Execution: Enabled\n\n" +
            "Test Groups Executed:\n" +
            "- Smoke Tests: %d\n" +
            "- Registration Tests: %d\n" +
            "- Cookie Tests: %d\n" +
            "- Regression Tests: %d\n" +
            "- Performance Tests: %d",
            context.getSuite().getName(),
            context.getPassedTests().size(),
            context.getFailedTests().size(),
            context.getSkippedTests().size(),
            context.getPassedTests().size() + context.getFailedTests().size() + context.getSkippedTests().size(),
            totalDuration,
            totalDuration / 60000.0,
            new java.util.Date(context.getStartDate().getTime()),
            new java.util.Date(context.getEndDate().getTime()),
            context.getSuite().getParallel() != null ? context.getSuite().getParallel().toString() : "Disabled",
            calculateAverageTestDuration(context),
            getFastestTest(context),
            getSlowestTest(context),
            countTestsByGroup(context, "smoke"),
            countTestsByGroup(context, "registration"),
            countTestsByGroup(context, "cookies"),
            countTestsByGroup(context, "regression"),
            countTestsByGroup(context, "performance")
        );
        
        Allure.addAttachment("Test Suite Summary Report", "text/plain", summary);
        
        // Generate test results for external systems
        generateTestResultsForExternalSystems(context);
    }
    
    private String getTestDescription(ITestResult result) {
        try {
            org.testng.annotations.Test testAnnotation = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(org.testng.annotations.Test.class);
            return testAnnotation != null ? testAnnotation.description() : "No description";
        } catch (Exception e) {
            return "No description";
        }
    }
    
    private int getTestPriority(ITestResult result) {
        try {
            org.testng.annotations.Test testAnnotation = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(org.testng.annotations.Test.class);
            return testAnnotation != null ? testAnnotation.priority() : 0;
        } catch (Exception e) {
            return 0;
        }
    }
    
    private String getStackTrace(Throwable throwable) {
        if (throwable == null) return "No stack trace available";
        
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
    
    private double calculateAverageTestDuration(ITestContext context) {
        long totalDuration = 0;
        int testCount = 0;
        
        for (ITestResult result : context.getPassedTests().getAllResults()) {
            totalDuration += (result.getEndMillis() - result.getStartMillis());
            testCount++;
        }
        
        return testCount > 0 ? (double) totalDuration / testCount : 0.0;
    }
    
    private long getFastestTest(ITestContext context) {
        long fastest = Long.MAX_VALUE;
        
        for (ITestResult result : context.getPassedTests().getAllResults()) {
            long duration = result.getEndMillis() - result.getStartMillis();
            if (duration < fastest) {
                fastest = duration;
            }
        }
        
        return fastest == Long.MAX_VALUE ? 0 : fastest;
    }
    
    private long getSlowestTest(ITestContext context) {
        long slowest = 0;
        
        for (ITestResult result : context.getPassedTests().getAllResults()) {
            long duration = result.getEndMillis() - result.getStartMillis();
            if (duration > slowest) {
                slowest = duration;
            }
        }
        
        return slowest;
    }
    
    private int countTestsByGroup(ITestContext context, String groupName) {
        int count = 0;
        for (org.testng.ITestNGMethod method : context.getAllTestMethods()) {
            for (String group : method.getGroups()) {
                if (groupName.equals(group)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    
    private void generateTestResultsForExternalSystems(ITestContext context) {
        // This method can be used to generate results for TestRail, Jira, or other systems
        System.out.println("Generating test results for external integration systems...");
        
        // Example: Create results map for external systems
        java.util.Map<String, Object> externalResults = new java.util.HashMap<>();
        externalResults.put("suiteName", context.getSuite().getName());
        externalResults.put("passedTests", context.getPassedTests().size());
        externalResults.put("failedTests", context.getFailedTests().size());
        externalResults.put("skippedTests", context.getSkippedTests().size());
        externalResults.put("totalDuration", context.getEndDate().getTime() - context.getStartDate().getTime());
        externalResults.put("executionTime", new java.util.Date());
        externalResults.put("parallelExecution", context.getSuite().getParallel());
        
        // Log external results
        System.out.println("External results generated: " + externalResults);
        
        // This data can be sent to TestRail, Jira, or other systems
        Allure.addAttachment("External System Results", "text/plain", externalResults.toString());
    }
}
