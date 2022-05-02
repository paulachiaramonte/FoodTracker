package com.example.foodtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.database.ShoppingD;

public class AddShoppingList extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);


        findViewById((R.id.buttonAddShop)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                register_shop();

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void register_shop(){

        EditText EditFoodName = findViewById(R.id.editFoodName_shop);
        EditText EditQuantity = findViewById(R.id.editQuantity_shop);

        String food_name = EditFoodName.getText().toString();
        String food_quantity = EditQuantity.getText().toString();

        // if food quantity set by default 1
        if (food_name.isEmpty() || food_quantity.isEmpty()) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle(R.string.title_dialog);
            ad.setMessage(R.string.message_food_dialog);
            ad.setPositiveButton(R.string.button_ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                        }
                    });
            ad.show();
        } else {
            db = AppDatabase.getInstance(getApplicationContext());
            ShoppingD new_food = new ShoppingD(food_name,
                    Integer.parseInt(food_quantity));
            db.DAO().insertShopping(new_food);
            setResult(RESULT_OK);
            Intent intent = new Intent(this, ShoppingFragment.class);
            intent.putExtra("intFragment", 2);
            successToast();
            startActivity(intent);
        }
    }

    private void successToast() {
        EditText EditFoodName = findViewById(R.id.editFoodName_shop);
        String food_name = EditFoodName.getText().toString();
        String message = getResources().getString(R.string.success_shop);
        String message_toast = food_name + " " + message;
        Toast.makeText(this.getApplicationContext(), message_toast,
                Toast.LENGTH_LONG).show();
    }

    public void goBackFood(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("intFragment", 2);
        startActivity(intent);
    }
}
