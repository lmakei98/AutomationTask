package testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.io.IOException;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected HomePage homePage;
    protected Actions actions;

    public WebDriver initializeDriver() throws IOException {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            driver.set(new ChromeDriver(options));
            driver.get().manage().window().maximize();

            actions = new Actions(driver.get());
        }
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public HomePage launchApplication() throws IOException {
        WebDriver webDriver = initializeDriver();

        homePage = new HomePage(webDriver);
        homePage.open();
        return homePage;
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}