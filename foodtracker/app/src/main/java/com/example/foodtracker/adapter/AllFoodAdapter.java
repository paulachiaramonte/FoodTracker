package com.example.foodtracker.adapter;

import static java.util.concurrent.TimeUnit.DAYS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.foodtracker.R;
import com.example.foodtracker.model.AllFood;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.AllFoodViewHolder> {

    Context context;
    List<AllFood> allFoodList;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public AllFoodAdapter(Context context, List<AllFood> allFoodList) {
        this.context = context;
        this.allFoodList = allFoodList;
    }

    @NonNull
    @Override
    public AllFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.all_food_row_item , parent , false);

        return new AllFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllFoodViewHolder holder, int position) {

        //holder.foodImage.setImageResource(allFoodList.get(position).getImageUrl());
        String date = allFoodList.get(position).getExp();
        holder.name.setText(allFoodList.get(position).getName());
        holder.expiry.setText(allFoodList.get(position).getExp());
        holder.quantity.setText(allFoodList.get(position).getQuantity().toString());
        holder.place.setText(allFoodList.get(position).getPlace());

        Date expDate = new Date();
        try {
            expDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();
        long miliSecDiff = expDate.getTime() - currentDate.getTime();
        long daysDiffInt = TimeUnit.DAYS.convert(miliSecDiff, TimeUnit.MILLISECONDS);
        String daysDiff = Integer.toString((int) daysDiffInt);
        holder.daysLeft.setText(daysDiff);

    }

    @Override
    public int getItemCount() {
        return allFoodList.size();
    }

    public static final class AllFoodViewHolder extends ViewHolder{

        //ImageView foodImage;
        TextView expiry, name, quantity, place, daysLeft;

        public AllFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            expiry = itemView.findViewById(R.id.ExpDateAdapter);
            name = itemView.findViewById(R.id.NameFoodAdapter);
            place = itemView.findViewById(R.id.PlaceAdapter);
            quantity = itemView.findViewById(R.id.QuantityAdapter);
            daysLeft = itemView.findViewById(R.id.DaysLeft);

        }
    }
}
