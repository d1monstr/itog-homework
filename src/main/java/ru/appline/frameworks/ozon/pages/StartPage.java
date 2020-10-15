package ru.appline.frameworks.ozon.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//input[contains(@placeholder, 'Искать')]")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@data-widget = 'megaPaginator']")
    private WebElement blockSearchResult;

    public SearchPage search(String searchRequest){
        searchInput.sendKeys(searchRequest);
        WebElement searchButton = searchInput.findElement(By.xpath("./../..//button"));
        elementToBeClickable(searchButton);
        searchButton.click();
        elementToBeVisible(blockSearchResult);
        return app.getSearchPage();

    }

}
