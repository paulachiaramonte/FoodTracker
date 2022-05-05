package com.example.foodtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.database.MealPlanD;
import com.example.foodtracker.database.ShoppingD;

import org.w3c.dom.Text;

public class AddMeal extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        Bundle bundle = getIntent().getExtras();

        TextView tvDate = findViewById(R.id.DateMealTextView);
        TextView tvMeal = findViewById(R.id.MealTextView);

        tvDate.setText(bundle.getString("Date"));
        tvMeal.setText(bundle.getString("Meal"));
    }

    private void register_mealPlan(){

        EditText EditMealName = findViewById(R.id.MealNameEditText);
        TextView EditDate = findViewById(R.id.DateMealTextView);
        TextView EditType = findViewById(R.id.MealTextView);



        String meal_date = EditDate.getText().toString();
        String meal_type = EditType.getText().toString();
        String meal_name = EditMealName.getText().toString();


        // if food quantity set by default 1
        if (meal_name.isEmpty() || meal_date.isEmpty() || meal_type.isEmpty()) {
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
            MealPlanD new_meal = new MealPlanD(meal_date,
                    meal_type, meal_name);
            db.DAO().insertMealPlan(new_meal);
            setResult(RESULT_OK);
            Intent intent = new Intent(this, MealPlanFragment.class);
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
}