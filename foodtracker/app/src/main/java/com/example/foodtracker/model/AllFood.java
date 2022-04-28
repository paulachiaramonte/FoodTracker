package com.example.foodtracker.model;

public class AllFood {

    String name;
    String exp;
    //Integer imageUrl;


    public AllFood(String name, String exp /*Integer imageUrl*/) {
        this.name = name;
        this.exp = exp;
        //this.imageUrl = imageUrl;         not for now
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

    /* public Integer getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }*/
}
