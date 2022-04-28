package com.example.foodtracker.model;

public class MealDate {
    private String date;
    private String date_of_week;

    public MealDate(String date, String date_of_week){
        this.date = date;
        this.date_of_week = date_of_week;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDayOfWeek(){
        return date_of_week;
    }

    public void setDayOfWeek(String date_of_week){
        this.date_of_week = date_of_week;
    }
}