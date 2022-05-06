package com.example.foodtracker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "MealPlan_table")
public class MealPlanD {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "name")
    public String name;

    public MealPlanD(long id, String date, String type, String name){
        this.id = id;
        this.date = date;
        this.type = type;
        this.name = name;
    }

    @Ignore
    public MealPlanD(String date, String type, String name){
        this.date = date;
        this.type = type;
        this.name = name;
    }

    //Getters and setters
    public void setId(long id){
        this.id = id;
    }

    public String getMeal_name(){
        return name;
    }
    public void setMeal_name(String name){
        this.name = name;
    }

    public String getMeal_date(String date){
        return date;
    }
    public void setMeal_date(String date){
        this.date = date;
    }

}
