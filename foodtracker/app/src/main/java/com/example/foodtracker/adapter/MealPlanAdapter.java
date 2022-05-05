package com.example.foodtracker.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.foodtracker.R;
import com.example.foodtracker.model.MealDate;

import java.util.ArrayList;

public class MealPlanAdapter extends ArrayAdapter<MealDate> {

    Context mContext;
    int mResource;
    ArrayList<MealDate> daysList;

    /**
     * Default constructor for the MealPlanAdapter
     * @param context
     * @param resource
     * @param daysList
     */

    public MealPlanAdapter(Context context, int resource, ArrayList<MealDate> daysList){
        super(context, resource, daysList);
        mContext = context;
        mResource = resource;
        daysList = daysList;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override

    public View getView(int position, View convertView, ViewGroup parent){
        //get the date information
        String date = getItem(position).getDate();
        String day_of_week = getItem(position).getDayOfWeek();

        //Create the MealDate object
        MealDate mealdate = new MealDate(date, day_of_week);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.DateText);
        TextView tvDayWeek = (TextView) convertView.findViewById(R.id.DayOfWeekText);

        tvDate.setText(date);
        tvDayWeek.setText(day_of_week);

        return convertView;

    }
}
