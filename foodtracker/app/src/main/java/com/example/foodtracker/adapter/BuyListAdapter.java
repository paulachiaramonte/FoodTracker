package com.example.foodtracker.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

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

        //in some cases, it will prevent unwanted situations
        holder.checkBox.setOnCheckedChangeListener(null);

/*        holder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final boolean isChecked = holder.checkBox.isChecked();
                if (isChecked){
                    AlertDialog.Builder ad = new AlertDialog.Builder(holder.checkBox.getContext());
                    ad.setTitle("Edit " + nameFood);
                    ad.setMessage("Do you want to delete " + nameFood + " from Shopping List or add " + nameFood  + " in Food?");
                    ad.setPositiveButton("Add to Food",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int arg1) {
                                    dialog.cancel();
                                }
                            });
                    ad.setNegativeButton("Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int arg1) {
                                    //db.DAO().deleteShopFoodbyID(idFood);
                                    buyListList.remove(pos);
                                    Log.d("ID of food clicked", String.valueOf((long) idFood));
                                    dialog.cancel();
                                }
                            });
                    ad.show();

                }
            }
        });*/

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
        private BuyListAdapter adapter;

        public BuyFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.buy_quantity);
            name = itemView.findViewById(R.id.buy_name);
            checkBox = itemView.findViewById(R.id.shopping_checkbox);

/*            checkBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    final boolean isChecked = checkBox.isChecked();
                    if (isChecked){
                        AlertDialog.Builder ad = new AlertDialog.Builder(checkBox.getContext());
                        ad.setTitle("Edit Shopping List");
                        ad.setMessage("Do you want to delete from Shopping List or add in Food?");
                        ad.setPositiveButton("Add to Food",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        dialog.cancel();
                                    }
                                });
                        ad.setNegativeButton("Delete",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        //db.DAO().deleteShopFoodbyID(idFood);
                                        Log.d("POS", "ID: " + adapter.buyListList.get(getAdapterPosition()));
                                        adapter.buyListList.remove(getAdapterPosition());
                                        //adapter.notifyItemChanged(getAdapterPosition());
                                        dialog.cancel();
                                    }
                                });
                        ad.show();

                    }
                }
            });*/


        }
    }
}
