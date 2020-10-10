package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.sl.In;
import ru.appline.frameworks.ozon.managers.ManagerPages;
import ru.appline.frameworks.ozon.objects.Product;

import java.util.ArrayList;
import java.util.List;

import static ru.appline.frameworks.ozon.objects.Cart.getCart;
import static ru.appline.frameworks.ozon.utils.MyAllureListener.addInformationCart;

public class Steps {

    private ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Загрузка стартовой страницы$")
    public void getInitialPage(){
        app.getStartPage();
    }

    @Когда("^Поиск по запросу '(.*)'$")
    public void search(String searchRequest){
        app.getStartPage().search(searchRequest);
    }

    @Когда("^Установка фильтра '(.*)' от (.*) и до (.*)$")
    public void setFilterFromTo(String nameFilter, String from, String to){
        app.getSearchPage().setFilterFromTo(nameFilter, from, to);
    }

    @Когда("^Установка фильтра '(.*)' до (.*)$")
    public void setFilterTo(String nameFilter, String to){
        app.getSearchPage().setFilterTo(nameFilter, to);
    }

    @Когда("^Установка фильтра '(.*)' от (.*)$")
    public void setFilterFrom(String nameFilter, String from){
        app.getSearchPage().setFilterFrom(nameFilter, from);
    }

    @Когда("^Переключение чекбокса$")
    public void checkboxIs(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getSearchPage().checkboxIs(raw.get(0), Boolean.valueOf(raw.get(1)));
                }
        );
    }

    @Когда("^Выбор чекбоксов в группе '(.*)'$")
    public void checkboxesInBlock(String nameBlock, DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getSearchPage().checkboxesInBlock(nameBlock, raw.get(0), Boolean.valueOf(raw.get(1)));
                }
        );
    }

    @Когда("^Добавить (\\d+|all) (even|odd|all) товар/ов$")
    public void addToCart(String amount, String parity){
        switch (parity){
            case "even":
                app.getSearchPage().addToCart(amount, true);
                break;
            case "odd":
                app.getSearchPage().addToCart(amount, false);
                break;
            case "all":
                app.getSearchPage().addToCart(amount);
                break;
        }
    }

    @Когда("^Переход в корзину$")
    public void goToCart(){
        app.getSearchPage().goToCart();
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
        app.getCartPage().checkCartProducts();
    }

    @Когда("^Отчистка корзины$")
    public void clearCart(){
        app.getCartPage().clearCart();
    }

}
