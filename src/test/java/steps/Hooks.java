package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.appline.frameworks.ozon.managers.InitManager;

import static ru.appline.frameworks.ozon.utils.MyAllureListener.addScreenshot;


public class Hooks {

    @Before
    public void beforeEach(){

        InitManager.initFramework();
        InitManager.initUrl();
    }


    @After
    public void afterEach(Scenario scenario){
        if (scenario.isFailed()){
            addScreenshot();
        }
        InitManager.quitFramework();
    }

//    @BeforeClass
//    public static void before() {
//        InitManager.initFramework();
//    }
//
//    @Before
//    public void beforeEach(){
//        InitManager.initUrl();
//    }
//
//    @AfterClass
//    public static void after() {
//        InitManager.quitFramework();
//    }


}