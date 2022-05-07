package com.example.foodtracker.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class DinnerAdapter extends ArrayAdapter<Meal> {
    List<String> mealList;
    Context mContext;
    int mResource;

    public DinnerAdapter(Context context, int resource, List<String> mealList) {
        super(context, resource);
        this.mealList = mealList;
        mContext = context;
        mResource = resource;

    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        String date = getItem(position).getDate();
        String type = getItem(position).getType();

        Meal meal = new Meal(date, type, name);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //ListView listView = (ListView) convertView.findViewById(R.id.ListViewDinner);

        TextView tvDate = (TextView) convertView.findViewById(R.id.TextNoDinner);

        tvDate.setText(name);
        return convertView;
    }
}
