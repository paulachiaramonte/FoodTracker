package com.example.foodtracker.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity(tableName = "Food_table")
public class FoodD {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "food")
    public String food;

    @ColumnInfo(name = "place")
    public String place;

    @ColumnInfo(name = "expire")
    public String expire;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name="expDate")
    @TypeConverters(Converters.class)
    public Date expDate;

    public FoodD(long id, String food, String place, String expire, int quantity){
        this.id = id;
        this.food = food;
        this.place = place;
        this.expire = expire;
        this.quantity = quantity;

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.expDate = (Date) format.parse(expire);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Ignore
    public FoodD(String food, String place, String expire, int quantity){
        this.food = food;
        this.place = place;
        this.expire = expire;
        this.quantity = quantity;

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.expDate = (Date) format.parse(expire);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Getters and setters
    public void setId(long id){
        this.id = id;
    }

    public String getFood(){
        return food;
    }
    public void setFood(String food){
        this.food = food;
    }

    public String getPlace(){
        return place;
    }
    public void setPlace(String place){
        this.place = place;
    }

    public String getExpire(){
        return expire;
    }
    public void setExpire(String expire){
        this.expire = expire;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public Date getExpDate(){
        return expDate;
    }

}

