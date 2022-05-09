package com.example.foodtracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtracker.adapter.AllFoodAdapter;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.model.AllFood;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FoodFragment extends Fragment{

    RecyclerView foodRecycler;
    AllFoodAdapter allFoodAdapter;
    AppDatabase db;
    List<AllFood> allFoodList;
    private Button All;
    private Button fridge;
    private Button freezer;
    private Button pantry;
    private FloatingActionButton filter;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);


        List<AllFood> allFoodList = getAll();
        foodRecycler = view.findViewById(R.id.food_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        allFoodAdapter = new AllFoodAdapter(getContext(), allFoodList);
        foodRecycler.setLayoutManager(linearLayoutManager);
        foodRecycler.setAdapter(allFoodAdapter);


        if (allFoodList.size() == 0){
            ImageView emptyFoodImage = view.findViewById(R.id.imageEmptyFood);
            TextView emptyFoodTextView = view.findViewById(R.id.TextViewEmptyFood);

            emptyFoodImage.setVisibility(view.VISIBLE);
            emptyFoodTextView.setVisibility(view.VISIBLE);
        }

        //Get buttons
        All = view.findViewById(R.id.Allbutton);
        fridge = view.findViewById(R.id.FridgeButton);
        freezer = view.findViewById(R.id.FreezerButton);
        pantry = view.findViewById(R.id.PantryButton);

        //Do when click button
        All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Empty your list:
                allFoodList.clear();

                List<Long> all_ids = db.DAO().getIds();
                List<String> all_food = db.DAO().getFoodN();
                List<String> all_exp = db.DAO().getExp();
                List<String> all_place = db.DAO().getPlace();
                List<Integer> all_quantity = db.DAO().getQuant();
                //Iterate though the food and expire date lists
                Iterator<Long> it0 = all_ids.iterator();
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it0.next(), it1.next(), it2.next(),
                            it3.next(), it4.next()));
                }

                allFoodAdapter.notifyDataSetChanged();
            }
        });

        fridge.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Empty your list:
                allFoodList.clear();

                db = AppDatabase.getInstance(getContext());
                List<Long> all_ids = db.DAO().getId_place("Fridge");
                List<String> all_food = db.DAO().getFood_place("Fridge");
                List<String> all_exp = db.DAO().getExp_place("Fridge");
                List<String> all_place = db.DAO().getPlace_place("Fridge");
                List<Integer> all_quantity = db.DAO().getQuantity_place("Fridge");
                //Iterate though the food and expire date lists
                Iterator<Long> it0 = all_ids.iterator();
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it0.next(), it1.next(), it2.next(),
                            it3.next(), it4.next()));
                }

                allFoodAdapter.notifyDataSetChanged();
            }
        }));

        freezer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Empty your list:
                allFoodList.clear();
                db = AppDatabase.getInstance(getContext());
                List<Long> all_ids = db.DAO().getId_place("Freezer");
                List<String> all_food = db.DAO().getFood_place("Freezer");
                List<String> all_exp = db.DAO().getExp_place("Freezer");
                List<String> all_place = db.DAO().getPlace_place("Freezer");
                List<Integer> all_quantity = db.DAO().getQuantity_place("Freezer");
                //Iterate though the food and expire date lists
                Iterator<Long> it0 = all_ids.iterator();
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it0.next(), it1.next(), it2.next(),
                            it3.next(), it4.next()));
                }

                allFoodAdapter.notifyDataSetChanged();
            }
        });

        pantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Empty your list:
                allFoodList.clear();
                //Get new info from database
                db = AppDatabase.getInstance(getContext());
                List<Long> all_ids = db.DAO().getId_place("Pantry");
                List<String> all_food = db.DAO().getFood_place("Pantry");
                List<String> all_exp = db.DAO().getExp_place("Pantry");
                List<String> all_place = db.DAO().getPlace_place("Pantry");
                List<Integer> all_quantity = db.DAO().getQuantity_place("Pantry");
                //Iterate though the food and expire date lists
                Iterator<Long> it0 = all_ids.iterator();
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it0.next(), it1.next(), it2.next(),
                            it3.next(), it4.next()));
                }

                allFoodAdapter.notifyDataSetChanged();
            }
        });


        filter = view.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setTitle("Filter By:");
                ad.setNegativeButton("Closest expiry",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                //Empty your list:
                                allFoodList.clear();
                                db = AppDatabase.getInstance(getContext());
                                List<Long> all_ids = db.DAO().getId_byexp();
                                List<String> all_food = db.DAO().getFood_byexp();
                                List<String> all_exp = db.DAO().getExp_byexp();
                                List<String> all_place = db.DAO().getPlace_byexp();
                                List<Integer> all_quantity = db.DAO().getQuant_byexp();
                                //Iterate though the food and expire date lists
                                Iterator<Long> it0 = all_ids.iterator();
                                Iterator<String> it1 = all_food.iterator();
                                Iterator<String> it2 = all_exp.iterator();
                                Iterator<String> it3 = all_place.iterator();
                                Iterator<Integer> it4 = all_quantity.iterator();
                                while (it1.hasNext() && it2.hasNext()) {
                                    allFoodList.add(new AllFood(it0.next(), it1.next(), it2.next(),
                                            it3.next(), it4.next()));
                                }
                                dialog.cancel();
                                allFoodAdapter.notifyDataSetChanged();
                            }
                        });
                ad.setPositiveButton("Name",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                //Empty your list:
                                allFoodList.clear();
                                db = AppDatabase.getInstance(getContext());
                                List<Long> all_ids = db.DAO().getId_byname();
                                List<String> all_food = db.DAO().getFood_byname();
                                List<String> all_exp = db.DAO().getExp_byname();
                                List<String> all_place = db.DAO().getPlace_byname();
                                List<Integer> all_quantity = db.DAO().getQuant_byname();
                                //Iterate though the food and expire date lists
                                Iterator<Long> it0 = all_ids.iterator();
                                Iterator<String> it1 = all_food.iterator();
                                Iterator<String> it2 = all_exp.iterator();
                                Iterator<String> it3 = all_place.iterator();
                                Iterator<Integer> it4 = all_quantity.iterator();
                                while (it1.hasNext() && it2.hasNext()) {
                                    allFoodList.add(new AllFood(it0.next(), it1.next(), it2.next(),
                                            it3.next(), it4.next()));
                                }
                                dialog.cancel();
                                allFoodAdapter.notifyDataSetChanged();
                            }
                        });
                ad.setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                ad.show();
            }});


        return view;
    }

    public List<AllFood> getAll(){
        List<AllFood> allFoodList = new ArrayList<>();
        db = AppDatabase.getInstance(getContext());
        List<Long> all_ids = db.DAO().getIds();
        List<String> all_food = db.DAO().getFoodN();
        List<String> all_exp = db.DAO().getExp();
        List<String> all_place = db.DAO().getPlace();
        List<Integer> all_quantity = db.DAO().getQuant();
        //Iterate though the food and expire date lists
        Iterator<Long> it0 = all_ids.iterator();
        Iterator<String> it1 = all_food.iterator();
        Iterator<String> it2 = all_exp.iterator();
        Iterator<String> it3 = all_place.iterator();
        Iterator<Integer> it4 = all_quantity.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            allFoodList.add(new AllFood(it0.next(), it1.next(), it2.next(),
                    it3.next(), it4.next()));
        }

        return allFoodList;
    }

}