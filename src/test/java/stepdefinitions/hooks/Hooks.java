package stepdefinitions.hooks;

import io.cucumber.java.After;
import testcomponents.BaseTest;

public class Hooks extends BaseTest {
    @After
    public void tearDown() {
        quitDriver();
    }
}
