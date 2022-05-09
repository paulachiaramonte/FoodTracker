package com.example.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme_Foodtracker);
        } else{
            setTheme(R.style.Theme_Foodtracker);
        }

        setContentView(R.layout.activity_main);




        // Bottom nav
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Intent intentFragment = getIntent();

        if (intentFragment.hasExtra("intFragment")){
            int intFragment = intentFragment.getExtras().getInt("intFragment");
            Fragment fragment = new FoodFragment();
            switch (intFragment){
                case 1: // Food Fragment
                    fragment = new FoodFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_food);
                    break;
                case 2: // Shopping List
                    fragment = new ShoppingFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_shopping);
                    break;
                case 3: // Meal Plan
                    fragment = new MealPlanFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_meal_plan);
                    break;
                case 4: // Settings
                    fragment = new SettingsFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_settings);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new FoodFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.navigation_food);
        }


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_food:
                        fragment = new FoodFragment();
                        break;
                    case R.id.navigation_shopping:
                        fragment = new ShoppingFragment();
                        break;
                    case R.id.navigation_meal_plan:
                        fragment = new MealPlanFragment();
                        break;
                    case R.id.navigation_settings:
                        fragment = new SettingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

                return true;
            }
        });

    }



    public void add_food(View v){
        Intent intent = new Intent(MainActivity.this, com.example.foodtracker.AddFoodActivity.class);
        startActivity(intent);
    }

    public void add_shop(View v){
        Intent intent = new Intent(MainActivity.this, com.example.foodtracker.AddShoppingList.class);
        startActivity(intent);
    }

    public void go_shelf_life(View v){
        Intent intent = new Intent(MainActivity.this, CheckShelfLife.class);
        startActivity(intent);
    }







}

