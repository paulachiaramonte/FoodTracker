package com.example.foodtracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtracker.adapter.MealPlanAdapter;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.model.MealDate;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.util.Locale;

public class MealPlanFragment extends Fragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_plan, container, false);

        Button buttonPrev = view.findViewById(R.id.ButtonPreviousWeek);
        Button buttonNext = view.findViewById(R.id.ButtonNextWeek);
        Button buttonCurrent = view.findViewById(R.id.CurrentWeekButton);

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatDayWeek = new SimpleDateFormat("EEEE");

        DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
        String weekdays[] = dfs.getWeekdays();

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);


        ArrayList<MealDate> days = new ArrayList<>();
        for (int i = 0; i < 7; i++)
        {
            String date_value = format.format(calendar.getTime());
            int dayOfWeek_int = calendar.get(Calendar.DAY_OF_WEEK);
            String nameOfDay = weekdays[dayOfWeek_int];
            MealDate dateI = new MealDate(date_value, nameOfDay);
            days.add(dateI);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        // Return to current date
        calendar.add(Calendar.DATE, -7);

        ListView listDates = (ListView) view.findViewById(R.id.DatesListView);

        MealPlanAdapter adapter = new MealPlanAdapter(view.getContext(), R.layout.date_list_item, days);
        listDates.setAdapter(adapter);

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar.add(Calendar.DATE, -7);

                for (int i = 0; i < 7; i++) {
                    String date_value = format.format(calendar.getTime());
                    int dayOfWeek_int = calendar.get(Calendar.DAY_OF_WEEK);
                    String nameOfDay = weekdays[dayOfWeek_int];
                    MealDate dateI = new MealDate(date_value, nameOfDay);
                    days.set(i, dateI);

                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                //Return to current Calendar
                calendar.add(Calendar.DATE, -7);
                MealPlanAdapter adapter = new MealPlanAdapter(view.getContext(), R.layout.date_list_item, days);
                listDates.setAdapter(adapter);

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar.add(Calendar.DATE, 7);

                for (int i = 0; i < 7; i++) {
                    String date_value = format.format(calendar.getTime());
                    int dayOfWeek_int = calendar.get(Calendar.DAY_OF_WEEK);
                    String nameOfDay = weekdays[dayOfWeek_int];
                    MealDate dateI = new MealDate(date_value, nameOfDay);
                    days.set(i, dateI);

                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                // Restore to correct calendar
                calendar.add(Calendar.DATE, -7);
                MealPlanAdapter adapter = new MealPlanAdapter(view.getContext(), R.layout.date_list_item, days);
                listDates.setAdapter(adapter);
            }

        });

        buttonCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.MONTH, currentMonth);
                calendar.set(Calendar.DAY_OF_MONTH, currentDay);

                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

                for (int i = 0; i < 7; i++) {
                    String date_value = format.format(calendar.getTime());
                    int dayOfWeek_int = calendar.get(Calendar.DAY_OF_WEEK);
                    String nameOfDay = weekdays[dayOfWeek_int];
                    MealDate dateI = new MealDate(date_value, nameOfDay);
                    days.set(i, dateI);

                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                calendar.add(Calendar.DATE, -7);
                MealPlanAdapter adapter = new MealPlanAdapter(view.getContext(), R.layout.date_list_item, days);
                listDates.setAdapter(adapter);
            }
        });

        listDates.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), EditDateMealPlan.class);

                intent.putExtra("Date",days.get(position).getDate());
                intent.putExtra("DayWeek", days.get(position).getDayOfWeek());
                startActivity(intent);
            }
        });
        return view;
    }


}