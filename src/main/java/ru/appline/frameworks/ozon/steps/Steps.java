package ru.appline.frameworks.ozon.steps;

import io.cucumber.java.ru.Когда;
import ru.appline.frameworks.ozon.managers.ManagerPages;

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
    public void checkboxIs(String nameCheckbox, boolean checked){
        app.getSearchPage().checkboxIs(nameCheckbox, checked);
    }

}
