package ru.appline.frameworks.ozon.utils;

import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.frameworks.ozon.managers.DriverManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyAllureListener extends AllureCucumber5Jvm {

//    @Override
//    public void setEventPublisher(EventPublisher publisher) {
//        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished -> {
//            if (!testStepFinished.getResult().getStatus().is(Status.FAILED)){
//                byte[] screenShot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//                Allure.getLifecycle().addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", screenShot);
//            }
//        });
//        super.setEventPublisher(publisher);
//    }
//
//    @Attachment(value = "Информация о добавленных товарах", type = "text/txt")
//    public static byte addInformationCart(){
//
//    }


}
