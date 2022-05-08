package com.example.foodtracker.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.foodtracker.AddFoodActivity;
import com.example.foodtracker.EditDateMealPlan;
import com.example.foodtracker.R;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.model.BuyList;

import java.util.ArrayList;
import java.util.List;


public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyFoodViewHolder> {

    Context context;
    ArrayList<BuyList> buyListList;
    AppDatabase db;


    public BuyListAdapter(Context context, ArrayList<BuyList> buyListList) {
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

        Integer pos = holder.getAdapterPosition();
        String nameFood = buyListList.get(pos).getName();
        Integer quantityFood = buyListList.get(pos).getNumber();
        Long idFood = buyListList.get(pos).getId();

        holder.name.setText(nameFood);
        holder.number.setText(String.valueOf(quantityFood));

    }

    @Override
    public int getItemCount() {
        return buyListList.size();
    }

    public class BuyFoodViewHolder extends ViewHolder implements CompoundButton.OnCheckedChangeListener{

        public CheckBox checkBox; //a
        public TextView number, name;

        public BuyFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            number = (TextView) itemView.findViewById(R.id.buy_quantity);
            name = (TextView) itemView.findViewById(R.id.buy_name);
            checkBox = (CheckBox) itemView.findViewById(R.id.shopping_checkbox);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setSelected(false);

        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            AlertDialog.Builder ad = new AlertDialog.Builder(context);
            ad.setTitle("Edit Shopping List");
            ad.setMessage("Do you want to delete from Shopping List or add in Food?");
            ad.setPositiveButton("Add to Food",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            addToFood(getAdapterPosition(), this);
                            dialog.cancel();
                        }
                    });
            ad.setNegativeButton("Delete",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            removeAt(getAdapterPosition(),this);
                            dialog.cancel();
                        }
                    });
            ad.setNeutralButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            checkBox.setSelected(false);
                        }
                    });
            ad.show();

        }

    }
    public void removeAt(int position, DialogInterface.OnClickListener viewHolder) {
        db = AppDatabase.getInstance(context);
        Long id_item = buyListList.get(position).getId();
        String name_item = buyListList.get(position).getName();
        Toast.makeText(context, name_item + " deleted from the Shopping List",
                Toast.LENGTH_LONG).show();
        buyListList.remove(position);
        db.DAO().deleteShopFoodbyID(id_item);
        notifyItemRemoved(position);
    }

    public void addToFood(int position, DialogInterface.OnClickListener viewHolder){
        db = AppDatabase.getInstance(context);
        Long id_item = buyListList.get(position).getId();
        String name_item = buyListList.get(position).getName();
        Integer quantity_item = buyListList.get(position).getNumber();

        Intent intent = new Intent(context, AddFoodActivity.class);
        intent.putExtra("Name",name_item);

        buyListList.remove(position);
        db.DAO().deleteShopFoodbyID(id_item);
        notifyItemRemoved(position);

        context.startActivity(intent);

    }
}
