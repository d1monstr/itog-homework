package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import ru.appline.frameworks.ozon.managers.InitManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"ru.appline.frameworks.ozon.steps"},
        features = {"src/test/resources/"},
        tags = {"@all"}
)
public class CucumberRunner {
    @AfterClass
    public static void after() {
        InitManager.quitFramework();
    }
}
