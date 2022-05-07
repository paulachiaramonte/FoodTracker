package com.example.foodtracker.model;

public class Meal {
    private String name;

    public Meal(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}