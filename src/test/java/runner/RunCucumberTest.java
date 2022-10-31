package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber-report.json",
                "html:target/cucumber-report.html",
                "pretty",
                "de.monochromata.cucumber.report.PrettyReports:target/cucumber"
        },
        features = {"src/test/resources/features"},
        glue = {"stepDefs"},
        strict = true,
        monochrome = true
)

/**
 * Entry point for tests
 *
 */
public class RunCucumberTest {

}
