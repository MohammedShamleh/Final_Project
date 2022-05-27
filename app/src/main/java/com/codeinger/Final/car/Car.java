package com.codeinger.Final.car;

public class Car {

    private String id;
    private String img;
    private String Name;
    private String Transmission;



    public Car(String id, String name, String transmission ,String img) {
        this.id = id;
        this.img = img;
        Name = name;
        Transmission = transmission;

    }

    public Car() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
