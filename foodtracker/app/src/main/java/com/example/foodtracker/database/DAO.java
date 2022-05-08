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
    @Query("SELECT * FROM Food_table WHERE id=:id")
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

    @Query("SELECT id FROM Food_table ORDER BY id DESC LIMIT 1")
    Long getLastIdFoodTable();

    @Query("SELECT food FROM Food_table WHERE id = :idx")
    String getFoodFromId(Long idx);

    @Query("SELECT expire FROM Food_table WHERE id = :idx")
    String getDateFromId(Long idx);

    //Insert new food
    @Insert
    void insertFoodD(FoodD food);

    // Delete all rows from Food_table
    @Query("DELETE FROM Food_table;")
    public void DeleteAllFoodTable();


    //SHOPPING TABLE
    //Get expire date and food name from all instances
    @Query("SELECT food FROM Shopping_table")
    List<String> getFood_shop();

    //Get quantity
    @Query("SELECT quantity FROM Shopping_table")
    List<Integer> getQuantity();

    //Get ID
    @Query("SELECT id FROM Shopping_table")
    List<Long> getID_shops();

    @Query("DELETE FROM Shopping_table WHERE id = :foodId")
    public void deleteShopFoodbyID(long foodId);

    //Insert new food
    @Insert
    public void insertShopping(ShoppingD food);

    // Delete all from Shopping_table
    @Query("DELETE FROM Shopping_table")
    public void deleteAllShoppingTable();


    //MEAL PLAN
    //Get name
    @Query("SELECT name FROM MealPlan_table")
    List<String> getMeal_name();


    //Get date
    @Query("SELECT date FROM MealPlan_table")
    List<String> getMeal_date();

    //Get type
    @Query("SELECT type FROM MealPlan_table")
    List<String> getMeal_type();


    @Query("SELECT name FROM MealPlan_table WHERE date= :date AND type = :type")
    List<String> getName_Meal(String date , String type);

    @Query("SELECT id FROM MealPlan_table WHERE date= :date AND type = :type")
    List<Long> getId_Meal(String date , String type);

    @Query("SELECT name FROM MealPlan_table WHERE date= :date")
    List<String> getMeals_date(String date);

    @Query("DELETE FROM MealPlan_table WHERE id = :id")
    public void DeleteMealById(Long id);

    //Insert new food
    @Insert
    void insertMealPlan(MealPlanD meal);


    //Delete food
    @Delete
    void delete(FoodD food);



    //Delete all Meal Plan
    @Query("DELETE FROM MealPlan_table")
    public void DeleteAllMealPlan();




}
