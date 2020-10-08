package ru.appline.frameworks.ozon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.frameworks.ozon.managers.ManagerPages;
import ru.appline.frameworks.ozon.managers.TestPropManager;

import java.util.concurrent.TimeUnit;

import static ru.appline.frameworks.ozon.managers.DriverManager.getDriver;
import static ru.appline.frameworks.ozon.utils.PropConst.IMPLICITLY_WAIT;

public class BasePage {

    protected ManagerPages app = ManagerPages.getManagerPages();
    protected Actions action = new Actions(getDriver());
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);
    public static TestPropManager props = TestPropManager.getTestPropManager();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement elementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean elementToBeChanged(WebElement element, String attribute, String beforeValue) {
        return wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(element, attribute, beforeValue)));
    }

    public boolean isElementPresent(String xpath) {
        getDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        try {
            getDriver().findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        }
    }

}
