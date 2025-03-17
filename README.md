# AutomationTask

This project is an automated testing suite for validating various functionalities on a web application using Java 11 Selenium WebDriver, TestNG, and Cucumber.

## Project Setup

### Prerequisites

- Java 11
- Maven
- Chrome Browser
- ChromeDriver
- Machine location is Lithuania (make sure you are not running a VPN in another country)

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/AutomationTask.git
    cd AutomationTask
    ```

2. Install the dependencies:
    ```sh
    mvn clean install
    ```

## Running Tests

### Running TestNG Tests

To run the TestNG tests, use the following command:
```sh
mvn test
```

### Running Cucumber Tests

To run the Cucumber tests, use the following command:
```sh
mvn test -Dcucumber.options="--tags @Regression"
```

### Running Specific Cucumber Feature

To run a specific Cucumber feature file, use the following command:
```sh
mvn test -Dcucumber.options="src/test/java/cucumber/features/TestValidationsOnPurchaseScreen.feature"
```

## Project Structure

- `src/main/java/pages`: Contains page object classes representing different pages of the web application.
- `src/main/java/pages/abstractpage`: Contains abstract base page class.
- `src/test/java/testcomponents`: Contains base test class for setting up and tearing down WebDriver.
- `src/test/java/stepdefinitions`: Contains step definition class and hooks for Cucumber.
- `src/test/java/cucumber`: Contains Cucumber test runner.
- `src/test/java/e2e`: Contains end-to-end test class using TestNG.
- `src/test/resources`: Contains test resources such as properties files.
