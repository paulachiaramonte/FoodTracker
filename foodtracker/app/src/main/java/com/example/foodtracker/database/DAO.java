package com.example.foodtracker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;
@Dao
public interface DAO {

    //Get all foods from the database
    @Query("SELECT * FROM Food_table ORDER BY expDate")
    List<FoodD> getAllFoodD();

    //Get a single food from its id
    @Query("SELECT * FROM food_table WHERE id=:id")
    FoodD findById(long id);

    //Get list of foods from list of ids
    @Query("SELECT * FROM Food_table WHERE id IN (:foodsIds)")
    List<FoodD> loadAllByIds(long[] foodsIds);

    //Get expire date and food name from all instances
    @Query("SELECT food FROM Food_table ORDER BY expDate")
    List<String> getFoodN();

    //Get expire date from all instances
    @Query("SELECT expire FROM Food_table ORDER BY expDate")
    List<String> getExp();

    @Query("SELECT place FROM Food_table ORDER BY expDate")
    List<String> getPlace();

    @Query("SELECT quantity FROM Food_table ORDER BY expDate")
    List<Integer> getQuant();

    //Get expire date and food name from a specific place(pantry, freezer, fridge)
    @Query("SELECT food FROM Food_table WHERE place= :place ORDER BY expDate")
    List<String> getFood_place(String place);

    //Get expire date and food name from a specific place(pantry, freezer, fridge)
    @Query("SELECT expire FROM Food_table WHERE place= :place ORDER BY expDate")
    List<String> getExp_place(String place);

    @Query("SELECT place FROM Food_table WHERE place= :place ORDER BY expDate")
    List<String> getPlace_place(String place);

    @Query("SELECT quantity FROM Food_table WHERE place= :place ORDER BY expDate")
    List<Integer> getQuantity_place(String place);

    //Insert new food
    @Insert
    void insertFoodD(FoodD food);


    //SHOPPING TABLE
    //Get expire date and food name from all instances
    @Query("SELECT food FROM Shopping_table")
    List<String> getFood_shop();

    //Get quantity
    @Query("SELECT quantity FROM Shopping_table")
    List<Integer> getQuantity();

    //Insert new food
    @Insert
    void insertShopping(ShoppingD food);

    //Delete food
    @Delete
    void delete(FoodD food);

}
