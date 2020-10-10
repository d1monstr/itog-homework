package ru.appline.frameworks.ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.frameworks.ozon.objects.Product;

import java.util.List;
import java.util.NoSuchElementException;

import static ru.appline.frameworks.ozon.objects.Cart.*;
import static ru.appline.frameworks.ozon.objects.Product.*;
import static ru.appline.frameworks.ozon.utils.NumbersUtil.cleanNumber;
import static ru.appline.frameworks.ozon.utils.NumbersUtil.numberWithThinSpace;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'filter-block')]//div[@unit = '[object Object]']/..")
    private List<WebElement> listFilterFromTo;

    @FindBy(xpath = "//div[@data-widget = 'searchResultsFiltersActive']/div/div")
    private List<WebElement> listFilterActive;

    @FindBy(xpath = "//div[contains(@class, 'filter-block')]//div[@value]")
    private List<WebElement> listFilterCheckboxes;

    @FindBy(xpath = "//div[contains(@class, 'filter-block')]/div/div//label/div/input[@type = 'checkbox']/ancestor::div[contains(@class, 'filter-block')]")
    private List<WebElement> listFilterBlockWithCheckboxes;

    @FindBy(xpath = "//div[contains(@data-widget, 'searchResultsV2')]/div/div")
    private List<WebElement> listSearchedProduct;

    @FindBy(xpath = "//a[@data-widget = 'cart']/span[1]")
    private WebElement counterCart;

    @FindBy(xpath = "//a[@href = '/cart']")
    private WebElement cartButton;

    @FindBy(xpath = "//div[contains(@data-widget, 'header')]/div/div/div[1]")
    private WebElement titlePage;

    public CartPage goToCart(){
        action.click(cartButton).build().perform();
        Assert.assertEquals("Страница 'Корзина' не загрузилась", "Корзина", titlePage.getText().trim());
        return app.getCartPage();
    }

    public SearchPage addToCart(String amount, boolean even){
        int count = 0;
        for (WebElement productBlock : listSearchedProduct){
            count++;
            if (amount.equals("all")){
                if (even && (count % 2 == 0)){
                    buy(productBlock);
                }
                else if (!even && (count % 2 == 1)){
                    buy(productBlock);
                }
            }
            else {
                int amountInt = Integer.parseInt(amount);
                if (even && (count % 2 == 0) && (getCart().getListPoducts().size() < amountInt)){
                    buy(productBlock);
                }
                else if (!even && (count % 2 == 1) && (getCart().getListPoducts().size() < amountInt)){
                    buy(productBlock);
                }
            }
        }
        Assert.assertTrue("Количество товаров в корзине не совпадает с добавленным количеством", getCart().getListPoducts().size() == Integer.parseInt(counterCart.getText()));
        return this;
    }

    private void buy(WebElement productBlock){
        String beforeValue = counterCart.getText();
        WebElement buyButton = productBlock.findElement(By.xpath(".//div[contains(text(), 'В корзину')]/ancestor::button"));
        elementToBeClickable(buyButton);
        buyButton.click();
        getCart().add(new Product(productBlock.findElement(By.xpath("./div/div/div[2]/div/a")).getText().trim(), cleanNumber(productBlock.findElement(By.xpath(".//a/div/span")).getText())));
        elementToBeChanged(counterCart, "textContent", beforeValue);
        Assert.assertEquals("Продукт " + productBlock.findElement(By.xpath("./div/div/div[2]/div/a")).getText().trim() + " не добавился в корзину", String.valueOf(getCart().getListPoducts().size()), counterCart.getText());
    }

    public SearchPage addToCart(String amount){
        WebElement buyButton;
        int amountInt = -1;
        if (!amount.equals("all")) {
            amountInt = Integer.parseInt(amount);
        }
        for (WebElement productBlock : listSearchedProduct) {
            if (getCart().getListPoducts().size() == amountInt) {
                break;
            }
            try {
                String beforeValue = counterCart.getText();
                buyButton = productBlock.findElement(By.xpath(".//div[contains(text(), 'В корзину')]/ancestor::button"));
                elementToBeClickable(buyButton);
                buyButton.click();
                getCart().add(new Product(productBlock.findElement(By.xpath("./div/div/div[2]/div/a")).getText().trim(), cleanNumber(productBlock.findElement(By.xpath(".//a/div/span")).getText())));
                elementToBeChanged(counterCart, "textContent", beforeValue);
                Assert.assertEquals("Продукт " + productBlock.findElement(By.xpath("./div/div/div[2]/div/a")).getText().trim() + " не добавился в корзину", String.valueOf(getCart().getListPoducts().size()), counterCart.getText());
            } catch (NoSuchElementException e) {
            }
        }
        return this;
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

    public SearchPage checkboxesInBlock(String nameBlock, String nameCheckbox, boolean checked){
        List<WebElement> listCheckboxes;
        WebElement inputCheckbox, searchInputBlock;
        for (WebElement block : listFilterBlockWithCheckboxes){
            if (block.findElement(By.xpath("./div[1]")).getText().trim().equals(nameBlock)){
                //в случае если в блоке отображаются не все доступные чекбоксы
                if (isElementPresentIn(block, ".//span[contains(text(), 'Посмотреть все')]")){
                    action.click(block.findElement(By.xpath(".//span[contains(text(), 'Посмотреть все')]"))).build().perform();
                    wait.until(ExpectedConditions.attributeToBe(block.findElement(By.xpath(".//span[@class = 'show']/span")), "textContent", "Свернуть"));
                }
                searchInputBlock = block.findElement(By.xpath(".//p[contains(text(), 'Найти')]/../input"));
                searchInputBlock.sendKeys(nameCheckbox);
                listCheckboxes = block.findElements(By.xpath(".//label"));
                for (WebElement checkbox : listCheckboxes){
                    if (checkbox.findElement(By.xpath("./div[2]/span")).getText().contains(nameCheckbox)){
                        String filterString = nameBlock + ": " + checkbox.getText().trim();
                        inputCheckbox = checkbox.findElement(By.xpath(".//input"));
                        setCheckbox(inputCheckbox, checked);
                        checkConfiguredFilter(filterString);
                        return this;
                    }
                }
//                listCheckboxes = block.findElements(By.xpath(".//label"));
//                for (WebElement checkbox : listCheckboxes){
//                    if (checkbox.findElement(By.xpath("./div[2]/span")).getText().contains(nameCheckbox)){
//                        String filterString = nameBlock + ": " + checkbox.getText().trim();
//                        inputCheckbox = checkbox.findElement(By.xpath(".//input"));
//                        setCheckbox(inputCheckbox, checked);
//                        checkConfiguredFilter(filterString);
//                        return this;
//                    }
//                }

            }
        }
        Assert.fail("Чекбокса '" + nameCheckbox + "' нет на странице");
        return this;
    }

    public SearchPage checkboxIs(String nameCheckbox, boolean checked){
        WebElement inputCheckbox;
        for (WebElement checkbox : listFilterCheckboxes){
            if (checkbox.findElement(By.xpath(".//span")).getText().contains(nameCheckbox)){
                String filterString = checkbox.findElement(By.xpath(".//span")).getText().trim();
                inputCheckbox = checkbox.findElement(By.xpath(".//input"));
                setCheckbox(inputCheckbox, checked);
                checkConfiguredFilter(filterString);
                return this;
            }

        }
        Assert.fail("Чекбокса '" + nameCheckbox + "' нет на странице");
        return this;
    }

    private void setCheckbox(WebElement checkbox, boolean checked){
        if (checked != Boolean.valueOf(checkbox.getAttribute("checked"))){
            int listSizeBefore = listFilterActive.size();
            action.click(checkbox).build().perform();
            //ждем пока количество элементов в блоке с активными фильтрами будет отличаться от размера листа до клика
            wait.until(ExpectedConditions.not(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@data-widget = 'searchResultsFiltersActive']/div/div"), listSizeBefore)));
        }
    }

    private SearchPage checkConfiguredFilter(String filterString){
        WebElement activeFilterName;
        for (WebElement activeFilter : listFilterActive){
            activeFilterName = activeFilter.findElement(By.xpath(".//span"));
            elementToBeChanged(activeFilter, "textContent", activeFilterName.getText().trim());
            if (activeFilterName.getText().equals(filterString)){
                return app.getSearchPage();
            }
        }
        Assert.fail("Фильтр " + filterString + " не применился");
        return app.getSearchPage();
    }
}
