package com.example.foodtracker.model;

public class BuyList {

    Long id;
    String name;
    Integer number;

    public BuyList(Long id, String name, Integer number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Long getId(){return id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


}
