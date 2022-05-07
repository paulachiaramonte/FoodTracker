package com.example.foodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Bottom nav
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Intent intentFragment = getIntent();

        if (intentFragment.hasExtra("intFragment")){
            int intFragment = intentFragment.getExtras().getInt("intFragment");
            Fragment fragment = new com.example.foodtracker.FoodFragment();
            switch (intFragment){
                case 1: // Food Fragment
                    fragment = new com.example.foodtracker.FoodFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_food);
                    break;
                case 2: // Shopping List
                    fragment = new com.example.foodtracker.ShoppingFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_shopping);
                    break;
                case 3: // Meal Plan
                    fragment = new com.example.foodtracker.MealPlanFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_meal_plan);
                    break;
                case 4: // Settings
                    fragment = new com.example.foodtracker.SettingsFragment();
                    bottomNavigationView.setSelectedItemId(R.id.navigation_settings);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new com.example.foodtracker.FoodFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.navigation_food);
        }


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_food:
                        fragment = new com.example.foodtracker.FoodFragment();
                        break;
                    case R.id.navigation_shopping:
                        fragment = new com.example.foodtracker.ShoppingFragment();
                        break;
                    case R.id.navigation_meal_plan:
                        fragment = new com.example.foodtracker.MealPlanFragment();
                        break;
                    case R.id.navigation_settings:
                        fragment = new com.example.foodtracker.SettingsFragment();
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







}

