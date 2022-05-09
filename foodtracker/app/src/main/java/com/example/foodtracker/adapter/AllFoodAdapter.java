package com.example.foodtracker.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.foodtracker.AddFoodActivity;
import com.example.foodtracker.R;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.database.FoodD;
import com.example.foodtracker.model.AllFood;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.AllFoodViewHolder> {

    Context context;
    List<AllFood> allFoodList;
    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private AppDatabase db;

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

        String date = allFoodList.get(position).getExp();
        holder.name.setText(allFoodList.get(position).getName());
        holder.expiry.setText(allFoodList.get(position).getExp());
        holder.quantity.setText(allFoodList.get(position).getQuantity().toString());
        holder.place.setText(allFoodList.get(position).getPlace());
        Long idFood = allFoodList.get(position).getId();

        Integer pos = holder.getAdapterPosition();


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

    public class AllFoodViewHolder extends ViewHolder {

        TextView expiry, name, quantity, place, daysLeft;

        public AllFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            expiry = itemView.findViewById(R.id.ExpDateAdapter);
            name = itemView.findViewById(R.id.NameFoodAdapter);
            place = itemView.findViewById(R.id.PlaceAdapter);
            quantity = itemView.findViewById(R.id.QuantityAdapter);
            daysLeft = itemView.findViewById(R.id.DaysLeft);

            LinearLayout menuPopup = itemView.findViewById(R.id.item_layout);
            menuPopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopUp(view);
                }
            });

        }

        private void ShowPopUp(View view){
            PopupMenu menu_edit_delete = new PopupMenu(context, view);
            menu_edit_delete.inflate(R.menu.menu_edit_delete);
            menu_edit_delete.show();

            menu_edit_delete.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.Edit_food:
                            editFoodD(getAdapterPosition());
                        case R.id.Delete_food:
                            ConfirmDelete();
                    }
                    return false;
                }
            });
        }
        public void ConfirmDelete() {
            AlertDialog.Builder ad = new AlertDialog.Builder(context);
            ad.setTitle("Delete from Food list");
            ad.setMessage("Are you sure you want to delete this item?");
            ad.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            deleteFood(getAdapterPosition());
                            dialog.cancel();
                        }
                    });
            ad.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                        }
                    });
            ad.show();
        }
    }
    public void deleteFood(int position) {
        db = AppDatabase.getInstance(context);
        Long id_item = allFoodList.get(position).getId();
        String name_item = allFoodList.get(position).getName();
        Toast.makeText(context, name_item + " deleted from the Food List",
                Toast.LENGTH_LONG).show();

        allFoodList.remove(position);
        FoodD food_delete = db.DAO().findById(id_item);
        db.DAO().deleteFood(food_delete);
        notifyItemRemoved(position);
    }

    public void editFoodD(int position){
        Long id_item = allFoodList.get(position).getId();
        Log.d("Id_found", "allFoodList.get(pos).getId() works");
        String name_item = allFoodList.get(position).getName();
        Integer quantity_item = allFoodList.get(position).getQuantity();
        String exp_item = allFoodList.get(position).getExp();
        String place_item = allFoodList.get(position).getPlace();

        Intent intent = new Intent(context, AddFoodActivity.class);
        intent.putExtra("pos_adapter", position);
        intent.putExtra("id", id_item);
        intent.putExtra("Name",name_item);
        intent.putExtra("Quantity", quantity_item);
        intent.putExtra("Expiry", exp_item);
        intent.putExtra("Place", place_item);
        intent.putExtra("Edit", true);

        context.startActivity(intent);
        Log.d("Add food Activity started", "This is done");
    }
}

