package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "ru.appline.frameworks.ozon.utils.MyAllureListener"},
        glue = {"steps"},
        features = {"src/test/resources/"},
        tags = "@all"
)
public class CucumberRunner {
}
