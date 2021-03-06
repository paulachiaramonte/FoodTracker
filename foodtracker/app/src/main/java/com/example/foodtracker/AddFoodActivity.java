package com.example.foodtracker;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.database.FoodD;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity {

    private AppDatabase db;
    String[] places = {"Fridge", "Freezer", "Pantry"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        EditText EditFoodName = findViewById(R.id.editFoodName_food);
        EditText EditQuantity = findViewById(R.id.editTextQuantity_food);
        EditText EditDate = findViewById(R.id.editDateExpiration_food);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        if (intent.hasExtra("Name")) {
            String nameFood = bundle.getString("Name");
            EditFoodName.setText(nameFood, TextView.BufferType.EDITABLE);
        }
        if (intent.hasExtra("Quantity")){
            int quantity = bundle.getInt("Quantity");
            EditQuantity.setText(String.valueOf(quantity), TextView.BufferType.EDITABLE);
        }
        if (intent.hasExtra("Expiry")){
            String exp = bundle.getString("Expiry");
            EditDate.setText(exp, TextView.BufferType.EDITABLE);
        }

        Spinner spinner = (Spinner) findViewById(R.id.Spinner_Place_food);
        //spinner.setOnItemClickListener(this);

        ArrayAdapter<String> place_adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, places);
        place_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(place_adapter);



        findViewById(R.id.AddDate_Button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                datePicker();

            }
        });

        findViewById(R.id.CheckDatesButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckShelfLife.class);
                startActivity(intent);

            }
        });


        findViewById((R.id.buttonAddFood)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if (intent.hasExtra("Edit")){
                    edit_food();
                }else{
                register_food();
                try {
                    // 2 days notification
                    createNotification(2);
                    // 1 days notification
                    createNotification(1);
                    // 0 days notification
                    createNotification(0);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
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

    private void datePicker() {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    EditText txtDate = findViewById(R.id.editDateExpiration_food);
                    txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void register_food(){

        Spinner spinner_place = (Spinner) findViewById(R.id.Spinner_Place_food);
        String place = spinner_place.getSelectedItem().toString(); //Come here later to fix this

        EditText EditFoodName = findViewById(R.id.editFoodName_food);
        EditText EditDateExp = findViewById(R.id.editDateExpiration_food);
        EditText EditQuantity = findViewById(R.id.editTextQuantity_food);

        String food = EditFoodName.getText().toString();
        String expire = EditDateExp.getText().toString();
        String quantity = EditQuantity.getText().toString();

        if (food.isEmpty() || expire.isEmpty() || place.isEmpty() || quantity.isEmpty()) {
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
            FoodD new_food = new FoodD(food,
                    place,
                    expire,
                    Integer.parseInt(quantity));
            db.DAO().insertFoodD(new_food);
            setResult(RESULT_OK);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("intFragment", 1);
            successToast();
            startActivity(intent);
        }
    }

    private void edit_food(){

        EditText EditFoodName = findViewById(R.id.editFoodName_food);
        EditText EditDate = findViewById(R.id.editDateExpiration_food);
        EditText EditQuantity = findViewById(R.id.editTextQuantity_food);

        Intent intent = getIntent();
        long id_food = intent.getExtras().getLong("id");
        String place_original = intent.getExtras().getString("Place");

        db = AppDatabase.getInstance(getApplicationContext());
        FoodD food_to_edit = db.DAO().findById(id_food);

        Spinner spinner_place = (Spinner) findViewById(R.id.Spinner_Place_food);
        String place = spinner_place.getSelectedItem().toString();
        String food = EditFoodName.getText().toString();
        String expire = EditDate.getText().toString();
        String quantity = EditQuantity.getText().toString();

        /*if(place_original != place){
            food_to_edit.setPlace(place);
        }
        food_to_edit.setFood(food);
        food_to_edit.setExpire(expire);
        food_to_edit.setQuantity(Integer.parseInt(quantity));*/

        db.DAO().updateFoodById(food, expire, place, Integer.parseInt(quantity), id_food);

        Log.d("Item updated", " " + food_to_edit.getFood());

        setResult(RESULT_OK);
        Intent intentNew = new Intent(this, MainActivity.class);
        intent.putExtra("intFragment", 1);
        Toast.makeText(this, "Edit correctly done", Toast.LENGTH_SHORT).show();
        startActivity(intentNew);
    }

    private void successToast() {
        EditText EditFoodName = findViewById(R.id.editFoodName_food);
        String food_name = EditFoodName.getText().toString();
        String message = getResources().getString(R.string.success_food);
        String message_toast = food_name + " " + message;
        Toast.makeText(this.getApplicationContext(), message_toast,
                Toast.LENGTH_LONG).show();
    }

    public void goBackFood(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("intFragment", 1);
        startActivity(intent);
    }

    public void createNotification(Integer num_days) throws ParseException {
        // Set notificationId & text
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        db = AppDatabase.getInstance(getApplicationContext());
        Long notificationId = db.DAO().getLastIdFoodTable();
        String nameFoodItem = db.DAO().getFoodFromId(notificationId);
        String dateFoodItem = db.DAO().getDateFromId(notificationId);


        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("foodName", nameFoodItem);
        intent.putExtra("daysLeft", num_days);

        //PendingIntent
        PendingIntent alarmIntent = PendingIntent.getBroadcast(
                AddFoodActivity.this, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(format.parse(dateFoodItem));


        cal.set(Calendar.HOUR_OF_DAY, 8);
        //cal.set(Calendar.MINUTE, 26);
        //cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MINUTE, 1);

        // Create alarm for -num_days notification
        cal.add(Calendar.DATE, -num_days);
        Log.d("Notification added: " ,"time: " + cal.getTime().toString());
        long alarmStartTime = cal.getTimeInMillis();
        //Set Alarm
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

    }
}