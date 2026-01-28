# Allure Report Integration Guide

## Overview
Allure Report has been integrated into this UI Automation project to provide comprehensive test execution reports with detailed information about test results, steps, attachments, and trends.

## What's Been Added

### 1. Dependencies
- `allure-testng`: Allure adapter for TestNG framework
- `allure-core`: Core Allure functionality

### 2. Maven Plugin
- `allure-maven`: Plugin to generate Allure reports from test results

### 3. Test Configuration
- **testng.xml**: TestNG configuration with Allure listener enabled
- **allure.properties**: Allure configuration properties in `src/test/resources/`

### 4. Annotations
- **@Feature**: Groups tests by feature
- **@Severity**: Marks test severity level (CRITICAL, MAJOR, NORMAL, MINOR, TRIVIAL)
- **@Description**: Provides detailed test descriptions

### 5. Base Test Updates
- Added `Allure.step()` calls in setUp() and tearDown() methods for tracking

## How to Run Tests with Allure

### Option 1: Run All Tests and Generate Report
```bash
mvn clean test
mvn allure:report
```

### Option 2: Run Specific Test Class
```bash
mvn clean test -Dtest=CookiesTest
mvn allure:report
```

### Option 3: Run Tests with TestNG Suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
mvn allure:report
```

## Viewing Reports

### View in Browser (Recommended)
After generating the report, open it in your default browser:
```bash
mvn allure:serve
```
This will start a local server and automatically open the Allure report in your browser.

### Manual Viewing
The generated report is located at: `target/allure-report/index.html`
Open this file in any web browser.

## Report Locations
- **Test Results**: `target/allure-results/` (raw JSON files)
- **Report HTML**: `target/allure-report/` (generated report)

## Allure Report Features

The Allure report includes:

1. **Overview**: Test execution summary with pass/fail rates
2. **Suites**: Detailed test suite information
3. **Graphs**: Various charts showing test distribution and trends
4. **Timeline**: Execution timeline of tests
5. **Behaviors**: Tests grouped by feature and story
6. **Steps**: Detailed steps executed in each test
7. **Attachments**: Screenshots, logs, and other attachments (when added)
8. **History**: Historical trend of test execution

## Adding Allure Steps to Your Tests

To add more detailed steps in your test methods:

```java
import io.qameta.allure.Allure;

@Test
public void testExample() {
    Allure.step("Step description here");
    // Your test code
    
    Allure.step("Another step");
    // More test code
}
```

## Adding Attachments (Screenshots)

To add screenshots or other files to your report:

```java
import io.qameta.allure.Allure;
import java.nio.file.Files;
import java.nio.file.Paths;

@Test
public void testWithScreenshot() {
    // Test code...
    
    // Add screenshot
    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAsFile()...;
    Allure.addAttachment("Screenshot", "image/png", new FileInputStream(screenshotPath), "png");
}
```

## Adding More Allure Features

### Test Cases/Requirements Linking
```java
@Test
@Link("https://github.com/your-repo/issues/123")
@Issue("BUG-123")
@TmsLink("TEST-456")
public void testExample() {
    // Test code
}
```

### Test Severity
```java
@Test
@Severity(SeverityLevel.CRITICAL)  // CRITICAL, MAJOR, NORMAL, MINOR, TRIVIAL
public void testCriticalFeature() {
    // Test code
}
```

## Troubleshooting

### Reports Not Generating
1. Ensure `target/allure-results/` directory exists after test execution
2. Check Maven build output for errors
3. Verify Allure plugin is configured in pom.xml

### Java Heap Space Error
```bash
mvn clean test -Xmx1024m
```

### Clear Previous Results
```bash
mvn clean
```

## Environment Setup Checklist

- [x] Java 17+ installed
- [x] Maven 3.6+ installed
- [x] Allure Maven plugin configured
- [x] TestNG configured with Allure listener
- [x] Test classes annotated with @Feature and @Severity
- [x] testng.xml created with Allure listener

## References

- [Allure Framework Documentation](https://docs.qameta.io/allure/)
- [Allure TestNG Adapter](https://docs.qameta.io/allure/#_testng)
- [Allure Maven Plugin](https://github.com/allure-framework/allure-maven)

## CI/CD Integration

For GitHub Actions or other CI/CD tools, add to your workflow:

```yaml
- name: Run tests
  run: mvn clean test

- name: Generate Allure Report
  run: mvn allure:report

- name: Deploy report (optional)
  run: mvn allure:serve
```