package com.example.foodtracker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Shopping_table")
public class ShoppingD {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "food")
    public String food;

    @ColumnInfo(name = "quantity")
    public int quantity;

    public ShoppingD(long id, String food, int quantity){
        this.id = id;
        this.food = food;
        this.quantity = quantity;
    }

    @Ignore
    public ShoppingD(String food, int quantity){
        this.food = food;
        this.quantity = quantity;
    }

    //Getters and setters
    public void setId(long id){
        this.id = id;
    }

    public String getFood_shop(){
        return food;
    }
    public void setFood(String food){
        this.food = food;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

}
