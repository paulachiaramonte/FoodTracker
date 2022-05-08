package com.example.foodtracker.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodtracker.R;
import com.example.foodtracker.model.Meal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealsAdapter extends ArrayAdapter<Meal> {
    ArrayList<Meal> mealList;
    Context mContext;
    int mResource;

    public MealsAdapter(Context context, int resource, ArrayList<Meal> mealList) {
        super(context, resource, mealList);
        this.mealList = mealList;
        mContext = context;
        mResource = resource;

    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Long id = mealList.get(position).getId();
        String name = mealList.get(position).getName();

        Meal meal = new Meal(id, name);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.FoodTextViewTitle);
        tvDate.setTextColor(Color.WHITE);
        tvDate.setTextSize(18);
        tvDate.setText(name);
        return convertView;
    }

}
