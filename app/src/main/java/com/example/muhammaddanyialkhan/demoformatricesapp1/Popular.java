package com.example.muhammaddanyialkhan.demoformatricesapp1;

public class Popular {

    private String name, price, link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_image() {
        return link;
    }

    public void setProduct_image(String link) {
        this.link = link;
    }

    public Popular(String product_title, String product_price, String product_image) {

        this.name = product_title;
        this.price = product_price;
        this.link = product_image;
    }

    public Popular() {

    }
}
