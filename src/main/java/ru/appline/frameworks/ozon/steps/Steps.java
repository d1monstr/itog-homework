package ru.appline.frameworks.ozon.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import ru.appline.frameworks.ozon.managers.InitManager;
import ru.appline.frameworks.ozon.managers.ManagerPages;
import ru.appline.frameworks.ozon.objects.Product;

import static ru.appline.frameworks.ozon.objects.Cart.getCart;
import static ru.appline.frameworks.ozon.utils.MyAllureListener.addInformationCart;

public class Steps {


    @Когда("^Загрузка стартовой страницы$")
    public void getInitialPage(){
        ManagerPages.getManagerPages().getStartPage();
    }

    @Когда("^Поиск по запросу '(.*)'$")
    public void search(String searchRequest){
        ManagerPages.getManagerPages().getStartPage().search(searchRequest);
    }

    @Когда("^Установка фильтра '(.*)' от (.*) и до (.*)$")
    public void setFilterFromTo(String nameFilter, String from, String to){
        ManagerPages.getManagerPages().getSearchPage().setFilterFromTo(nameFilter, from, to);
    }

    @Когда("^Установка фильтра '(.*)' до (.*)$")
    public void setFilterTo(String nameFilter, String to){
        ManagerPages.getManagerPages().getSearchPage().setFilterTo(nameFilter, to);
    }

    @Когда("^Установка фильтра '(.*)' от (.*)$")
    public void setFilterFrom(String nameFilter, String from){
        ManagerPages.getManagerPages().getSearchPage().setFilterFrom(nameFilter, from);
    }

    @Когда("^Переключение чекбокса$")
    public void checkboxIs(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    ManagerPages.getManagerPages().getSearchPage().checkboxIs(raw.get(0), Boolean.valueOf(raw.get(1)));
                }
        );
    }

    @Когда("^Выбор чекбоксов в группе '(.*)'$")
    public void checkboxesInBlock(String nameBlock, DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    ManagerPages.getManagerPages().getSearchPage().checkboxesInBlock(nameBlock, raw.get(0), Boolean.valueOf(raw.get(1)));
                }
        );
    }

    @Когда("^Добавить (\\d+|all) (even|odd|all) товар/ов$")
    public void addToCart(String amount, String parity){
        switch (parity){
            case "even":
                ManagerPages.getManagerPages().getSearchPage().addToCart(amount, true);
                break;
            case "odd":
                ManagerPages.getManagerPages().getSearchPage().addToCart(amount, false);
                break;
            case "all":
                ManagerPages.getManagerPages().getSearchPage().addToCart(amount);
                break;
        }
    }

//    @И("BE элемент \"([^\"]*)\" (?:в json строке \"([^\"]*)\" )*(не )*(равен|соответствует|содержит) ((?:значени(?:е|ю))|json|(?:элемент(?:у)* json)) \"(.*)\"$")


//    @Когда("^Установка фильтра '(.*)' (?:от|до (.*)) (?:и до (.*)) $")

    @Когда("^Переход в корзину$")
    public void goToCart(){
        ManagerPages.getManagerPages().getSearchPage().goToCart();
    }

    @Когда("^Проверка продуктов в корзине$")
    public void checkCart(){
        String infosString = "", mostPriceProduct = "", productString = "";
        int maxPrice = 0;
        for (Product product : getCart().getListPoducts()){
            productString = product.getProductName() + " - " + product.getPrice() + "\n";
            infosString += productString;
            if (Integer.parseInt(product.getPrice()) > maxPrice){
                maxPrice = Integer.parseInt(product.getPrice());
                mostPriceProduct = "Самый дорогой продукт в корзине:\n" + productString;
            }
        }
        infosString += mostPriceProduct;
        addInformationCart(infosString);
        ManagerPages.getManagerPages().getCartPage().checkCartProducts();
    }

    @Когда("^Отчистка корзины$")
    public void clearCart(){
        ManagerPages.getManagerPages().getCartPage().clearCart();
    }

    @Когда("^Переход на домашнюю страницу$")
    public void url(){
        InitManager.initUrl();
    }

}
