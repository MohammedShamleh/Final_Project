package com.codeinger.Final.car;

public class Car {
    int img;
    String Name;
    String Model;
    Double Price;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Car() {
    }

    public Car(int img, String name, String model, Double price) {
        this.img = img;
        this.Name = name;
        this.Model = model;
        this.Price = price;
    }
}
