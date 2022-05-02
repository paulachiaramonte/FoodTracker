package com.example.foodtracker.model;

public class FoodItem {
    public int id;
    public String name;
    public String url_food;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL_food() { return url_food; }

    public void setURL_food(String url_food) {
        this.url_food = url_food;
    }

}