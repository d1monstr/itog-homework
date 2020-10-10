package ru.appline.frameworks.ozon.managers;

import java.util.concurrent.TimeUnit;

import static ru.appline.frameworks.ozon.managers.DriverManager.*;
import static ru.appline.frameworks.ozon.utils.PropConst.*;

public class InitManager {

    public static TestPropManager props = TestPropManager.getTestPropManager();
//    private static boolean initialized = false;

    public static void initFramework() {
//        if (!initialized) {
            getDriver().manage().window().maximize();
            getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
            getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
//            initialized = true;
//        }
//        initUrl();
    }


    public static void quitFramework() {
        quitDriver();
    }

    public static void initUrl(){
        getDriver().get(TestPropManager.getTestPropManager().getProperty(APP_URL));
    }
}
