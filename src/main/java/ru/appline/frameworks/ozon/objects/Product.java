package ru.appline.frameworks.ozon.objects;

public class Product {

    private String productName;
    private String price;
//    private String guarant;

    public Product(String productName, String price) {
        this.productName = productName;
        this.price = price;
//        this.guarant = "-------------------";
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

//    public String getGuarant() {
//        return guarant;
//    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

//    public void setGuarant(String guarant) {
//        this.guarant = guarant;
//    }
}
