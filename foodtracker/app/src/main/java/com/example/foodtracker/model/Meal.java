package com.example.foodtracker.model;

public class Meal {
    private String date;
    private String type;
    private String name;

    public Meal(String date, String type, String name){
        this.date = date;
        this.type = type;
        this.name = name;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}