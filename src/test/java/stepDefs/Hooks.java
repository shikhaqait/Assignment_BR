package stepDefs;

import logger.MainLogger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import variables.Variable;
import webDriver.DriverManager;

public class Hooks {

    @Before
    public void LaunchBrowser(Scenario scenario) throws Exception {
        Variable.setScenario(scenario);
        MainLogger.logger().info("Started scenario " + scenario.getName());
    }

    public final void TakeScreenshotAfterStep(Scenario scenario) {
        final byte[] imgBytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(imgBytes, "image/png", "Image");
    }

    @After
    public void CloseBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            TakeScreenshotAfterStep(scenario);
        }
        DriverManager.closeDriver();
        MainLogger.logger().info("Ended scenario " + scenario.getName());
    }

}
