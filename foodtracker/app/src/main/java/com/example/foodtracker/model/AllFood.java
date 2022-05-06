package com.example.foodtracker.model;

import android.content.Intent;

public class AllFood {

    String name;
    String exp;
    String place;
    Integer quantity;

    public AllFood(String name, String exp, String place, Integer quantity) {
        this.name = name;
        this.exp = exp;
        this.place = place;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
