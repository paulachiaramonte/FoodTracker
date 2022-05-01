package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class EditDateMealPlan extends AppCompatActivity {

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
