package ru.appline.frameworks.ozon.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.frameworks.ozon.managers.DriverManager;

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
    @Attachment(value = "Информация о добавленных товарах", type = "text/plain", fileExtension = ".txt")
    public static byte[] addInformationCart(String contentFile){
        return contentFile.getBytes();
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenshot(){
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }


}
