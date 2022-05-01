package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddMeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        Bundle bundle = getIntent().getExtras();

        TextView tvDate = findViewById(R.id.DateMealTextView);
        TextView tvMeal = findViewById(R.id.MealTextView);

        tvDate.setText(bundle.getString("Date"));
        tvMeal.setText(bundle.getString("Meal"));
    }
}