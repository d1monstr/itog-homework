package ru.appline.frameworks.ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ru.appline.frameworks.ozon.utils.NumbersUtil.numberWithThinSpace;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[@unit = '[object Object]']/..")
    private List<WebElement> listFilterFromTo;

    @FindBy(xpath = "//div[@data-widget = 'searchResultsFiltersActive']/div/div")
    private List<WebElement> listFilterActive;

    @FindBy(xpath = "//div[contains(@class, 'filter-block')]//div[@value]")
    private List<WebElement> listFilterCheckboxes;

    public SearchPage setFilterCheckboxes(String nameCheckbox, boolean checked){
        return app.getSearchPage();
    }

    public SearchPage setFilterFromTo(String nameFilter, String from, String to){
        WebElement titleFilter, fromInput, toInput;
        String filterString;
        for (WebElement filterFromTo : listFilterFromTo){
            titleFilter = filterFromTo.findElement(By.xpath("./div[1]"));
            if (titleFilter.getText().toLowerCase().contains(nameFilter.toLowerCase())){
                fromInput = filterFromTo.findElement(By.xpath(".//input[@qa-id = 'range-from']"));
                toInput = filterFromTo.findElement(By.xpath(".//input[@qa-id = 'range-to']"));
                action.doubleClick(fromInput).build().perform();
                js.executeScript("arguments[0].value='';", fromInput);
                fromInput.sendKeys(from);
                action.doubleClick(toInput).build().perform();
                js.executeScript("arguments[0].value='';", toInput);
                toInput.sendKeys(to);
                action.sendKeys(Keys.ENTER).build().perform();
                filterString = titleFilter.getText().trim() + ": от " + numberWithThinSpace(fromInput.getAttribute("value")) + " до " + numberWithThinSpace(toInput.getAttribute("value"));
                checkConfiguredFilter(filterString);
                return app.getSearchPage();
            }
        }
        Assert.fail("Фильтра 'от/до' под названием " + nameFilter + "не существует");
        return app.getSearchPage();
    }

    public SearchPage setFilterTo(String nameFilter, String to){
        WebElement titleFilter,fromInput, toInput;
        String filterString;
        for (WebElement filterFromTo : listFilterFromTo){
            titleFilter = filterFromTo.findElement(By.xpath("./div[1]"));
            if (titleFilter.getText().toLowerCase().contains(nameFilter.toLowerCase())){
                fromInput = filterFromTo.findElement(By.xpath(".//input[@qa-id = 'range-from']"));
                toInput = filterFromTo.findElement(By.xpath(".//input[@qa-id = 'range-to']"));
                action.doubleClick(toInput).build().perform();
                js.executeScript("arguments[0].value='';", toInput);
                toInput.sendKeys(to);
                action.sendKeys(Keys.ENTER).build().perform();
                filterString = titleFilter.getText().trim() + ": от " + numberWithThinSpace(fromInput.getAttribute("value")) + " до " + numberWithThinSpace(toInput.getAttribute("value"));
                checkConfiguredFilter(filterString);
                return app.getSearchPage();
            }
        }
        Assert.fail("Фильтра 'от/до' под названием " + nameFilter + " не существует");
        return app.getSearchPage();
    }

    public SearchPage setFilterFrom(String nameFilter, String from){
        WebElement titleFilter, fromInput, toInput;
        String filterString;
        for (WebElement filterFromTo : listFilterFromTo){
            titleFilter = filterFromTo.findElement(By.xpath("./div[1]"));
            if (titleFilter.getText().toLowerCase().contains(nameFilter.toLowerCase())){
                fromInput = filterFromTo.findElement(By.xpath(".//input[@qa-id = 'range-from']"));
                toInput = filterFromTo.findElement(By.xpath(".//input[@qa-id = 'range-to']"));
                action.doubleClick(fromInput).build().perform();
                js.executeScript("arguments[0].value='';", fromInput);
                fromInput.sendKeys(from);
                action.sendKeys(Keys.CONTROL + "A").build().perform();
                action.sendKeys(Keys.ENTER).build().perform();
                filterString = titleFilter.getText().trim() + ": от " + numberWithThinSpace(fromInput.getAttribute("value")) + " до " + numberWithThinSpace(toInput.getAttribute("value"));
                checkConfiguredFilter(filterString);
                return app.getSearchPage();
            }
        }
        Assert.fail("Фильтра 'от/до' под названием " + nameFilter + "не существует");
        return app.getSearchPage();
    }

    public SearchPage checkboxIs(String nameCheckbox, boolean checked){
        WebElement inputCheckbox = null;
        for (WebElement checkbox : listFilterCheckboxes){
            if (checkbox.findElement(By.xpath(".//span")).getText().contains(nameCheckbox)){
                String filterString = checkbox.findElement(By.xpath(".//span")).getText().trim();
                inputCheckbox = checkbox.findElement(By.xpath(".//input"));
                setCheckbox(inputCheckbox, checked);
                checkConfiguredFilter(filterString);
                break;
            }

        }
//        scrollToElementJs(inputCheckbox);
//        elementToBeVisible(inputCheckbox);
//        Assert.assertEquals("Чекбокс '" + nameCheckbox + "' был установлен некорректно",
//                String.valueOf(checked), inputCheckbox.getAttribute("checked"));
        return this;
    }

    private void setCheckbox(WebElement checkbox, boolean checked){
        if (checked != Boolean.valueOf(checkbox.getAttribute("checked"))){
            action.click(checkbox).build().perform();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private SearchPage checkConfiguredFilter(String filterString){
        WebElement activeFilterName;
        for (WebElement activeFilter : listFilterActive){
            activeFilterName = activeFilter.findElement(By.xpath(".//span"));
            if (activeFilterName.getText().equals(filterString)){
                return app.getSearchPage();
            }
        }
        Assert.fail("Фильтр " + filterString + " не применился");
        return app.getSearchPage();
    }
}
