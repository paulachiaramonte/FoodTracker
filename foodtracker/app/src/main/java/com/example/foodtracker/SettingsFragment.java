package com.example.foodtracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.foodtracker.database.AppDatabase;

public class SettingsFragment extends Fragment {

    AppDatabase db;

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
}