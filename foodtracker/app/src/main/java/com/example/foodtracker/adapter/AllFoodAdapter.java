package com.example.foodtracker.adapter;

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

import java.util.List;

public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.AllFoodViewHolder> {

    Context context;
    List<AllFood> allFoodList;

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
        holder.name.setText(allFoodList.get(position).getName());
        holder.expiry.setText(allFoodList.get(position).getExp());

    }

    @Override
    public int getItemCount() {
        return allFoodList.size();
    }

    public static final class AllFoodViewHolder extends ViewHolder{

        //ImageView foodImage;
        TextView expiry, name;

        public AllFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            //foodImage = itemView.findViewById(R.id.food_image);
            expiry = itemView.findViewById(R.id.buy_quantity);
            name = itemView.findViewById(R.id.buy_name);
        }
    }
}
