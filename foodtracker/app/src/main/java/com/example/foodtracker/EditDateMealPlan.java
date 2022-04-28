package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        if (bundle!=null){
            String dateString = bundle.getString("Date");
            String dayWeekString = bundle.getString("DayWeek");
            String DatePlusDayWeek = dayWeekString + " " + dateString;
            tvDate.setText(DatePlusDayWeek);
        }

    }
}