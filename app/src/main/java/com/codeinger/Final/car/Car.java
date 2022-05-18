package com.codeinger.Final.car;

public class Car {
    int img;
    String Name;
    String Transmission;
    String Nav ;
    String Minimum  ;

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public String getNav() {
        return Nav;
    }

    public void setNav(String nav) {
        Nav = nav;
    }

    public String getMinimum() {
        return Minimum;
    }

    public void setMinimum(String minimum) {
        Minimum = minimum;
    }

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

    public Car(int img, String name, String transmission, String nav, String minimum) {
        this.img = img;
        Name = name;
        Transmission = transmission;
        Nav = nav;
        Minimum = minimum;
    }

    public Car() {
    }


}
