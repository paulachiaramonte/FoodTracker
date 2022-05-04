package com.example.foodtracker.model;

public class FoodItem {
    public int id;
    public String name;
    public String url;

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

    public String getURL_food() { return url; }

    public void setURL_food(String url) {
        this.url = url;
    }

}