package ru.appline.frameworks.ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.frameworks.ozon.objects.Product;
import static ru.appline.frameworks.ozon.objects.Cart.*;
import static ru.appline.frameworks.ozon.utils.MyAllureListener.addInformationCart;
import static ru.appline.frameworks.ozon.utils.NumbersUtil.cleanNumber;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//input[contains(@role, 'combobox')]/../../../../../../../..")
    List<WebElement> listProductsInCart;

    @FindBy(xpath = "//span[contains(text(), 'Удалить выбранные')]")
    WebElement deleteAllChecked;

    @FindBy(xpath = "//div[@select_button_name = 'Выбрать все']//input")
    WebElement checkAll;

    @FindBy(xpath = "//div[contains(text(), 'Удалить')]/ancestor::button")
    WebElement confirmDelete;

    @FindBy(xpath = "//a[@data-widget = 'cart']/span[1]")
    private WebElement counterCart;

    public CartPage clearCart(){
        String beforeValue = counterCart.getText();
        setCheckbox(checkAll, true);
        action.click(deleteAllChecked).build().perform();
        action.click(confirmDelete).build().perform();
        elementToBeChanged(counterCart, "textContent", beforeValue);
        Assert.assertEquals("Корзина не отчистилась", "0", counterCart.getText().trim());
        return this;
    }

    private void setCheckbox(WebElement checkbox, boolean checked){
        if (checked != Boolean.valueOf(checkbox.getAttribute("checked"))){
            action.click(checkbox).build().perform();
            elementToBeChanged(checkbox, "checked", String.valueOf(!checked));
        }
    }

    public CartPage checkCartProducts(){
        WebElement productTitle, productPrice;
        for (WebElement productBlock : listProductsInCart){
            productTitle = productBlock.findElement(By.xpath(".//a/span"));
            boolean finded = false;
            productPrice = productBlock.findElement(By.xpath(".//div[contains(@style, 'bold')]/span"));
            for (Product product : getCart().getListPoducts()){
                if (productTitle.getText().trim().equals(product.getProductName()) && cleanNumber(product.getPrice()).equals(cleanNumber(productPrice.getText()))){
                    finded = true;
                    break;
                }
            }
            if (finded){
                continue;
            }
            Assert.fail("Добавленный товар в корзину не найден в списке записаных товаров: " + productTitle.getText().trim() + " с ценой: " + cleanNumber(productPrice.getText()));
        }
        return this;
    }

}
