package com.example.foodtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.foodtracker.database.AppDatabase;

public class SettingsFragment extends Fragment {

    AppDatabase db;
    private Switch aSwitch;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        Switch switchNotification = view.findViewById(R.id.switchNotificatioms);
        Button FeedBackButton = view.findViewById(R.id.buttonFeedback);
        Button DeleteButton = view.findViewById(R.id.DeleteDataButton);

        switchNotification.setTextOn("On");
        switchNotification.setTextOff("Off");

/*        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                editor.putBoolean(getString(R.string.notifications_preferences), switchNotification.isChecked());
                editor.apply();
                editor.commit();
                Log.d("Key Value", getString(R.string.notifications_preferences));
            }
        });*/
        //theme
        aSwitch = (Switch) view.findViewById(R.id.switchTheme);

        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Log.d("Dark", "onCheckedChanged: Dark");
                    reset();
                } else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    reset();
                    Log.d("Light", "onCheckedChanged: Light");
                }
            }
        });




        FeedBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "100421809@alumnos.uc3m.es" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback FoodTracker");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, ""));
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = AppDatabase.getInstance(getContext());

                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("Warning");
                ad.setMessage("Are you sure you want to delete all your data?");
                ad.setPositiveButton("Delete Data",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                db.clearAllTables(); // delete shopping table

                                Toast toast = Toast.makeText(getContext(), "All Data Deleted",
                                        Toast.LENGTH_LONG);
                                toast.show();
                                dialog.cancel();
                            }
                        });
                ad.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                dialog.cancel();
                            }
                        });
                ad.show();

            }
        });

        return view;

    }
    private void reset() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}