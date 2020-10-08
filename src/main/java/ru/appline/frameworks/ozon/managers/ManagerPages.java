package ru.appline.frameworks.ozon.managers;

import ru.appline.frameworks.ozon.pages.*;

import java.util.ArrayList;
import java.util.List;

public class ManagerPages {

    private static ManagerPages managerPages;
    private static List<BasePage> listPages = new ArrayList<>();

    private static StartPage startPage;
    private static SearchPage searchPage;
//    private static ContributionsPage contributionsPage;
//    private static CardsPage cardsPage;
//    private static LoansPage loansPage;

    private ManagerPages() {
    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
            listPages.add(startPage);

        }
        return startPage;
    }

    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
            listPages.add(searchPage);

        }
        return searchPage;
    }

//    public ContributionsPage getContributionsPage() {
//        if (contributionsPage == null) {
//            contributionsPage = new ContributionsPage();
//            listPages.add(contributionsPage);
//        }
//        return contributionsPage;
//    }
//
//    public CardsPage getCardsPage() {
//        if (cardsPage == null) {
//            cardsPage = new CardsPage();
//            listPages.add(cardsPage);
//        }
//        return cardsPage;
//    }
//
//    public LoansPage getLoansPage() {
//        if (loansPage == null) {
//            loansPage = new LoansPage();
//            listPages.add(loansPage);
//        }
//        return loansPage;
//    }


    public static void deletePages(){
        for (int i = 0; i < listPages.size(); i++){
            listPages.remove(i);
        }
    }

}
