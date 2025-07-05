# Modern Selenium Java BDD Framework

## 🚀 Features
- **Selenium WebDriver 4.21.0** for modern browser automation
- **Page Object Model (POM)** with enhanced BasePage
- **JUnit 4.13.2** as the test runner with parallel execution
- **Cucumber 7.16.1** for BDD (Gherkin feature files)
- **Multi-browser support** (Chrome, Firefox, Edge, Safari)
- **Headless mode** support for CI/CD
- **Parallel execution** with configurable thread count
- **Configuration management** with properties file
- **Screenshot capture** on test failures
- **Explicit waits** and smart element handling
- **Thread-safe WebDriver management**
- **ExtentReports integration** with detailed step-by-step reporting

## 📁 Project Structure
```
selenium-java-bdd/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── config/          # Configuration management
│   │   │   ├── core/            # Core framework components
│   │   │   └── pages/           # Page Objects
│   │   └── resources/
│   │       └── config.properties # Test configuration
│   └── test/
│       ├── java/com/example/
│       │   ├── runner/          # Cucumber runner
│       │   └── stepdefs/        # Step Definitions
│       └── resources/
│           ├── features/        # Gherkin feature files
│           ├── extent.properties # ExtentReports configuration
│           └── spark-config.xml # ExtentReports spark config
├── pom.xml                     # Maven configuration
└── README.md
```

## 🛠️ Setup & Configuration

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser

### Installation
1. Clone the repository
2. Run `mvn clean install` to download dependencies
3. Configure `src/main/resources/config.properties` for your environment

### Configuration Options
Edit `src/main/resources/config.properties`:
```properties
# Browser Configuration
browser=chrome
headless=false

# Wait Timeouts
implicit.wait=10
explicit.wait=20
page.load.timeout=30

# Parallel Execution
parallel.threads=3
parallel.mode=methods

# Test Data
valid.username=student
valid.password=Password123

# ExtentReports Configuration
extent.reports.dir=target/extent-reports/
extent.reports.name=ExtentReport.html
```

## 🚀 Running Tests

### Run all tests
```bash
mvn test
```

### Run with specific browser
```bash
mvn test -Dbrowser=firefox
```

### Run in headless mode
```bash
mvn test -Dheadless=true
```

### Run with custom thread count
```bash
mvn test -Dparallel.threads=5
```

## 📊 Test Reports
- **Console output**: Real-time test execution logs
- **ExtentReports**: Comprehensive HTML reports in `target/extent-reports/`
- **Screenshots**: Captured in `target/screenshots/` on failures
- **Step-by-step reporting**: Detailed Gherkin step execution with screenshots

## 🔧 Framework Components

### DriverManager
- Thread-safe WebDriver management
- Multi-browser support
- Automatic driver cleanup

### BasePage
- Common utilities and wait strategies
- Screenshot capabilities
- Element interaction methods

### ConfigManager
- Centralized configuration management
- Environment-specific settings
- Type-safe property access

## 📝 Sample Feature
```gherkin
Feature: Login functionality

  Scenario Outline: Login with valid credentials
    Given I am on the login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see the homepage

    Examples:
      | username | password     |
      | student  | Password123 |

  Scenario Outline: Login with invalid credentials
    Given I am on the login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see an error message

    Examples:
      | username | password |
      | wrong    | wrong    |
```

## 🎯 Best Practices Implemented
- **Page Object Model**: Clean separation of concerns
- **Explicit waits**: Reliable element interactions
- **Configuration**: Environment-specific settings
- **Parallel execution**: Faster test execution
- **Error handling**: Robust failure management
- **Screenshots**: Visual debugging support
- **JUnit integration**: Standard testing framework
- **ExtentReports**: Professional test reporting
- **Cucumber BDD**: Behavior-driven development

## 🔄 CI/CD Integration
The framework is designed for CI/CD integration with:
- Headless mode support
- Parallel execution
- Screenshot capture
- Configurable timeouts

---
**Built with modern Java and Selenium best practices!** 🚀 