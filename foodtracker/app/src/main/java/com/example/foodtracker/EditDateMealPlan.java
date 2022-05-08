package com.example.foodtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodtracker.adapter.MealsAdapter;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.database.MealPlanD;
import com.example.foodtracker.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class EditDateMealPlan extends AppCompatActivity {

    AppDatabase db;
    private int position;
    private String type_meal;
    private String date;
    private String dayWeek;
    private ArrayList<Meal> meals_list;
    private MealsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_date_meal_plan);

        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        TextView tvDate = (TextView)findViewById(R.id.DateEditView);
        Bundle bundle = getIntent().getExtras();

        String dateString = bundle.getString("Date");
        String dayWeekString = bundle.getString("DayWeek");
        String DatePlusDayWeek = dayWeekString + " " + dateString;
        tvDate.setText(DatePlusDayWeek);


        Button buttonBreakfast = (Button) findViewById(R.id.ButtonAddBreakfast);
        Button buttonLunch = (Button) findViewById(R.id.ButtonAddLunch);
        Button buttonDinner = (Button) findViewById(R.id.ButtonAddDinner);

        db = AppDatabase.getInstance(getApplicationContext());

        View view = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_edit_date_meal_plan, null);
        //Loop

        // BREAKFAST LIST VIEW
        String breakfast = "Breakfast";
        List<String> breakfast_meals_q = db.DAO().getName_Meal(dateString, breakfast);
        List<Long> breakfast_ids_q = db.DAO().getId_Meal(dateString, breakfast);

        ArrayList<Meal> breakfast_meals = new ArrayList<>();
        for (int i = 0; i < breakfast_meals_q.size(); i++)
        {
            Meal mealName = new Meal(breakfast_ids_q.get(i),breakfast_meals_q.get(i));
            breakfast_meals.add(mealName);
        }

        ListView list_breakfast = (ListView) findViewById(R.id.ListViewBreakfast);

        MealsAdapter breakfast_adapter = new MealsAdapter(getApplicationContext(),
                R.layout.food_list_item, breakfast_meals);
        list_breakfast.setAdapter(breakfast_adapter);
        //

        TextView emptyFoodBreakfast = findViewById(R.id.TextNoBreakfast);
        if(breakfast_meals.size() >0){
            emptyFoodBreakfast.setVisibility(View.GONE);
        }

        list_breakfast.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Long id_item = breakfast_meals.get(position).getId();
                String name_item = breakfast_meals.get(position).getName();

                ad.setTitle("Delete Item");
                ad.setMessage("Do you want to Delete " + name_item +
                        " from Breakfast?");
                ad.setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                breakfast_meals.remove(position);
                                db.DAO().DeleteMealById(id_item);
                                breakfast_adapter.notifyDataSetChanged();
                                Toast.makeText(EditDateMealPlan.this, name_item + " deleted from Breakfast",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                ad.setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel(); } });
                ad.show();
            } });



        // LUNCH LIST VIEW
        String lunch = "Lunch";
        List<String> lunch_meals_q = db.DAO().getName_Meal(dateString, lunch);
        List<Long> lunch_ids_q = db.DAO().getId_Meal(dateString, lunch);

        ArrayList<Meal> lunch_meals = new ArrayList<>();
        for (int i = 0; i < lunch_meals_q.size(); i++)
        {
            Meal mealName = new Meal(lunch_ids_q.get(i),lunch_meals_q.get(i));
            lunch_meals.add(mealName);
        }

        ListView list_lunch = (ListView) findViewById(R.id.ListViewLunch);

        MealsAdapter lunch_adapter = new MealsAdapter(getApplicationContext(),
                R.layout.food_list_item, lunch_meals);
        list_lunch.setAdapter(lunch_adapter);
        //

        TextView emptyFoodLunch = findViewById(R.id.TextNoLunch);
        if(lunch_meals.size() >0){
            emptyFoodLunch.setVisibility(View.GONE);
        }

        list_lunch.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Long id_item = lunch_meals.get(position).getId();
                String name_item = lunch_meals.get(position).getName();

                ad.setTitle("Delete Item");
                ad.setMessage("Do you want to Delete " + name_item +
                        " from Lunch?");
                ad.setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                lunch_meals.remove(position);
                                db.DAO().DeleteMealById(id_item);
                                lunch_adapter.notifyDataSetChanged();
                                Toast.makeText(EditDateMealPlan.this, name_item + " deleted from Lunch",
                                        Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });
                ad.setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel(); } });
                ad.show();
            } });

        // DINNER LIST VIEW
        String dinner = "Dinner";
        List<String> dinner_meals_q = db.DAO().getName_Meal(dateString, dinner);
        List<Long> dinner_ids_q = db.DAO().getId_Meal(dateString, dinner);

        ArrayList<Meal> dinner_meals = new ArrayList<>();
        for (int i = 0; i < dinner_meals_q.size(); i++)
        {
            Meal mealName = new Meal(dinner_ids_q.get(i), dinner_meals_q.get(i));
            dinner_meals.add(mealName);
        }

        ListView list_dinner = (ListView) findViewById(R.id.ListViewDinner);

        MealsAdapter dinner_adapter = new MealsAdapter(getApplicationContext(),
                R.layout.food_list_item, dinner_meals);
        list_dinner.setAdapter(dinner_adapter);
        //

        TextView emptyFoodDinner = findViewById(R.id.TextNoDinner);

        if(dinner_meals.size() >0){
            emptyFoodDinner.setVisibility(View.GONE);
        }

        list_dinner.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Long id_item = dinner_meals.get(position).getId();
                String name_item = dinner_meals.get(position).getName();

                ad.setTitle("Delete Item");
                ad.setMessage("Do you want to Delete " + name_item +
                        " from Dinner?");
                ad.setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                dinner_meals.remove(position);
                                db.DAO().DeleteMealById(id_item);
                                dinner_adapter.notifyDataSetChanged();
                                Toast.makeText(EditDateMealPlan.this, name_item + " deleted from Dinner",
                                        Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });
                ad.setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel(); } });
                ad.show();
            } });


        // BUTTONS ADD MEAL
        buttonBreakfast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //goActivityMeal("Breakfast", dateString);
                dialogMeal("Breakfast", dateString,
                        breakfast_meals, breakfast_adapter);

            }
        });

        buttonLunch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //goActivityMeal("Lunch", dateString);
                dialogMeal("Lunch", dateString,
                        lunch_meals, lunch_adapter);

            }
        });

        buttonDinner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //goActivityMeal("Lunch", dateString);
                dialogMeal("Dinner", dateString, dinner_meals,
                        dinner_adapter);
            }
        });

    }

    public void goActivityMeal(String meal, String date){

        Intent intent = new Intent(this, AddMeal.class);
        intent.putExtra("Date", date);
        intent.putExtra("Meal", meal);
        startActivity(intent);
    }

    public void dialogMeal(String meal, String date, ArrayList<Meal> list_meals, MealsAdapter adapter){
        // Create the field to show in the Dialog:
        final EditText fieldName = new EditText(this);
        fieldName.setInputType(InputType.TYPE_CLASS_TEXT);

        // Now create the Dialog itself.
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add new meal to " + meal)
                .setPositiveButton("Add Meal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name_meal = fieldName.getText().toString();
                        db = AppDatabase.getInstance(getApplicationContext());
                        MealPlanD new_meal = new MealPlanD(date,
                                meal, name_meal);
                        db.DAO().insertMealPlan(new_meal);
                        Toast.makeText(EditDateMealPlan.this, name_meal + " added to Meal Plan",
                                Toast.LENGTH_SHORT).show();

                        List<String> meal_lists_q = db.DAO().getName_Meal(date, meal);
                        List<Long> meals_ids_q = db.DAO().getId_Meal(date, meal);

                        // Take last position (recently added)
                        Integer idx = meal_lists_q.size() -1 ;
                        Meal mealName = new Meal(meals_ids_q.get(idx),meal_lists_q.get(idx));
                        list_meals.add(mealName);

                        adapter.notifyDataSetChanged();

                        switch (meal){
                            case "Breakfast":
                                findViewById(R.id.TextNoBreakfast).setVisibility(View.GONE);
                                break;
                            case "Lunch":
                                findViewById(R.id.TextNoLunch).setVisibility(View.GONE);
                                break;
                            case "Dinner":
                                findViewById(R.id.TextNoDinner).setVisibility(View.GONE);
                                break;
                        }

                    }
                }).setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel(); }})
                .setView(fieldName)
                .create();

        // The TextWatcher will look for changes to the Dialogs field.
        fieldName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence c, int i, int i2, int i3) {}
            @Override public void onTextChanged(CharSequence c, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Will be called AFTER text has been changed.
                if (editable.toString().length() == 0){
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }
        });

        // Show the Dialog:
        dialog.show();
        // The button is initially deactivated, as the field is initially empty:
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    private void deleteItem(int position, String type_meal, String date, String dayWeek,
                            ArrayList<Meal> meals_list) {

        Long id_item = meals_list.get(position).getId();
        String name_item = meals_list.get(position).getName();

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Delete Item");
        ad.setMessage("Do you want to Delete " + name_item +
                " from " + type_meal);
        ad.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        meals_list.remove(position);
                        db.DAO().DeleteMealById(id_item);
                        Intent intent = new Intent(EditDateMealPlan.this, EditDateMealPlan.class);
                        intent.putExtra("Date", date);
                        intent.putExtra("Meal", type_meal);
                        intent.putExtra("DayWeek", dayWeek);
                        startActivity(intent);
                        Toast.makeText(EditDateMealPlan.this, type_meal + " deleted from Meal Plan",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        ad.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        ad.show();
    }


}
