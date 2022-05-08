package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodtracker.adapter.MealsAdapter;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class EditDateMealPlan extends AppCompatActivity {

    AppDatabase db;

    //Intent intent = getIntent();
    //String date = intent.getStringExtra("Date");
    //String dayWeek = intent.getStringExtra("DayWeek");

    //TextView tvDate = (TextView)findViewById(R.id.DateEditView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_date_meal_plan);

        TextView tvDate = (TextView)findViewById(R.id.DateEditView);
        Bundle bundle = getIntent().getExtras();

        String dateString = bundle.getString("Date");
        String dayWeekString = bundle.getString("DayWeek");
        String DatePlusDayWeek = dayWeekString + " " + dateString;
        tvDate.setText(DatePlusDayWeek);


        Button buttonBreakfast = (Button) findViewById(R.id.ButtonAddBreakfast);
        Button buttonLunch = (Button) findViewById(R.id.ButtonAddLunch);
        Button buttonDinner = (Button) findViewById(R.id.ButtonAddDinner);



        db = AppDatabase.getInstance(getApplicationContext());
        //por hacer

        View view = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_edit_date_meal_plan, null);
        //Loop

        String breakfast = "Breakfast";
        List<String> breakfast_meals_q = db.DAO().getName_Meal(dateString, breakfast);

        ArrayList<Meal> breakfast_meals = new ArrayList<>();
        for (int i = 0; i < breakfast_meals_q.size(); i++)
        {
            Meal mealName = new Meal(breakfast_meals_q.get(i));
            breakfast_meals.add(mealName);
        }

        ListView list_breakfast = (ListView) findViewById(R.id.ListViewBreakfast);

        MealsAdapter breakfast_adapter = new MealsAdapter(getApplicationContext(),
                R.layout.food_list_item, breakfast_meals);
        list_breakfast.setAdapter(breakfast_adapter);
        //

        TextView emptyFoodBreakfast = findViewById(R.id.TextNoBreakfast);
        if(breakfast_meals_q.size() >0){
            emptyFoodBreakfast.setVisibility(View.GONE);
        }

        String lunch = "Lunch";
        List<String> lunch_meals_q = db.DAO().getName_Meal(dateString, lunch);

        ArrayList<Meal> lunch_meals = new ArrayList<>();
        for (int i = 0; i < lunch_meals_q.size(); i++)
        {
            Meal mealName = new Meal(lunch_meals_q.get(i));
            lunch_meals.add(mealName);
        }

        ListView list_lunch = (ListView) findViewById(R.id.ListViewLunch);

        MealsAdapter lunch_adapter = new MealsAdapter(getApplicationContext(),
                R.layout.food_list_item, lunch_meals);
        list_lunch.setAdapter(lunch_adapter);
        //

        TextView emptyFoodLunch = findViewById(R.id.TextNoLunch);
        if(lunch_meals_q.size() >0){
            emptyFoodLunch.setVisibility(View.GONE);
        }

        String dinner = "Dinner";
        List<String> dinner_meals_q = db.DAO().getName_Meal(dateString, dinner);

        ArrayList<Meal> dinner_meals = new ArrayList<>();
        for (int i = 0; i < dinner_meals_q.size(); i++)
        {
            Meal mealName = new Meal(dinner_meals_q.get(i));
            dinner_meals.add(mealName);
        }

        ListView list_dinner = (ListView) findViewById(R.id.ListViewDinner);

        MealsAdapter dinner_adapter = new MealsAdapter(getApplicationContext(),
                R.layout.food_list_item, dinner_meals);
        list_dinner.setAdapter(dinner_adapter);
        //

        TextView emptyFoodDinner = findViewById(R.id.TextNoDinner);

        if(dinner_meals_q.size() >0){
            emptyFoodDinner.setVisibility(View.GONE);
        }

        buttonBreakfast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goActivityMeal("Breakfast", dateString);
            }
        });

        buttonLunch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goActivityMeal("Lunch", dateString);
            }
        });

        buttonDinner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goActivityMeal("Dinner", dateString);
            }
        });
    }

    public void goActivityMeal(String meal, String date){

        Intent intent = new Intent(this, AddMeal.class);
        intent.putExtra("Date", date);
        intent.putExtra("Meal", meal);
        startActivity(intent);
    }

}
