package com.example.foodtracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.foodtracker.R;
import com.example.foodtracker.model.BuyList;

import java.util.List;



public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyFoodViewHolder> {

    Context context;
    List<BuyList> buyListList;

    public BuyListAdapter(Context context, List<BuyList> buyListList) {
        this.context = context;
        this.buyListList = buyListList;
    }

    @NonNull
    @Override
    public BuyFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.buy_list_row_item , parent , false);

        return new BuyFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyFoodViewHolder holder, final int position) {

        holder.name.setText(buyListList.get(position).getName());
        holder.number.setText(String.valueOf(buyListList.get(position).getNumber()));

        //in some cases, it will prevent unwanted situations
        holder.checkBox.setOnCheckedChangeListener(null);
        //if true, your checkbox will be selected, else unselected
        //holder.checkBox.setChecked(objIncome.isSelected());
    }

    @Override
    public int getItemCount() {
        return buyListList.size();
    }

    public static final class BuyFoodViewHolder extends ViewHolder{

        CheckBox checkBox; //a
        TextView number, name;

        public BuyFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.buy_quantity);
            name = itemView.findViewById(R.id.buy_name);
            checkBox = itemView.findViewById(R.id.shopping_checkbox);//a
        }
    }
}
