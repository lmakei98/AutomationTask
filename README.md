# AutomationTask

This project is an automated testing suite for validating various functionalities on a web application using Java 11, Selenium WebDriver, TestNG and Cucumber.

## Project Setup

### Prerequisites

- Java 11
- Maven
- Chrome Browser
- IDE plugin providing Gherkin language support (for IntelliJ - Gherkin)
- Machine location is Lithuania (make sure you are not running a VPN in another country)

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/lmakei98/AutomationTask
    cd AutomationTask
    ```
2. 

3. Install the dependencies:
    ```sh
    mvn clean install -DskipTests
    ```

## Running Tests

### Setup Instructions

#### 1. Clone the Repository
Clone the repository to your local machine using the following command:
```sh
git clone https://github.com/lmakei98/AutomationTask
```

#### 2. Open the Project in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Click on `File` > `Open`.
3. Navigate to the cloned repository directory and select the `pom.xml` file.
4. Click `Open as Project`.

#### 3. Import Maven Dependencies
IntelliJ IDEA should automatically detect the `pom.xml` file and import the Maven dependencies. If not, follow these steps:
1. Open the `Maven` tool window (View > Tool Windows > Maven).
2. Click on the `Reload All Maven Projects` button.

#### 4. Run Automation Tests

##### Run E2E which verifies positive scenario
1. Open the `src/test/java/e2e/TestSuccessfulPurchaseFlow.java` file.
2. Right-click on the class name `TestSuccessfulPurchaseFlow`.
3. Select `Run 'TestSuccessfulPurchaseFlow'`.

##### Run seperate 8 tests which verify the proper validatons are added
1. Open the `src/test/java/cucumber/TestNGTestRunner.java` file.
2. Right-click on the feature file.
3. Select `Run 'TestNGTestRunner'`.


## Project Structure

- `src/main/java/pages`: Contains page object classes representing different pages of the web application.
- `src/main/java/pages/abstractpage`: Contains abstract base page class.
- `src/test/java/testcomponents`: Contains base test class for setting up and tearing down WebDriver.
- `src/test/java/stepdefinitions`: Contains step definition class and hooks for Cucumber.
- `src/test/java/cucumber`: Contains Cucumber test runner.
- `src/test/java/e2e`: Contains end-to-end test class using TestNG.
- `src/test/resources`: Contains test resources such as properties files.
