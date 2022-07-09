package com.example.mad1;

public class CarData {
    private String price;
    private String name;
    private Integer imageview;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageview() {
        return imageview;
    }

    public void setImageview(Integer imageview) {
        this.imageview = imageview;
    }

    public CarData(String price, String name, Integer imageview){
        this.price = price;
        this.name = name;
        this.imageview = imageview;


    }
}
