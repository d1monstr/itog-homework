package ru.appline.frameworks.ozon.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ru.appline.frameworks.ozon.managers.InitManager;

import static ru.appline.frameworks.ozon.utils.MyAllureListener.addScreenshot;


public class Hooks {

    private static boolean init = false;

    @Before
    public void beforeEach(){
        if (!init){
            InitManager.initFramework();
            init = true;
        }
        InitManager.initUrl();
    }

    @After
    public void afterEach(Scenario scenario){
        if (scenario.isFailed()){
            addScreenshot();
        }
    }


}