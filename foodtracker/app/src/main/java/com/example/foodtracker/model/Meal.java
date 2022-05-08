package com.example.foodtracker.model;

public class Meal {
    private Long id;
    private String name;

    public Meal(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId(){return id;}

    public void setName(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}