package com.example.foodtracker.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

@Database(entities = {
        FoodD.class,
        ShoppingD.class,
        MealPlanD.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static final String Database_name = "FoodTrackDB";
    private static volatile AppDatabase instance;

    public abstract DAO DAO();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null) instance = create(context);
        return instance;
    }

    private static AppDatabase create(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, Database_name)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
