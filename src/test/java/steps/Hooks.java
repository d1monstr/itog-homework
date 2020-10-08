package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.appline.frameworks.ozon.managers.InitManager;


public class Hooks {

    @Before
    public void beforeEach(){
        InitManager.initFramework();
        InitManager.initUrl();
    }


    @After
    public void afterEach() {
        InitManager.quitFramework();
    }

}