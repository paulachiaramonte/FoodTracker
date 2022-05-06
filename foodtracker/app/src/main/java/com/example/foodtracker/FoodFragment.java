package com.example.foodtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtracker.adapter.AllFoodAdapter;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.model.AllFood;

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

                List<String> all_food = db.DAO().getFoodN();
                List<String> all_exp = db.DAO().getExp();
                List<String> all_place = db.DAO().getPlace();
                List<Integer> all_quantity = db.DAO().getQuant();
                //Iterate though the food and expire date lists
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it1.next(), it2.next(),
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
                List<String> all_food = db.DAO().getFood_place("Fridge");
                List<String> all_exp = db.DAO().getExp_place("Fridge");
                List<String> all_place = db.DAO().getPlace_place("Fridge");
                List<Integer> all_quantity = db.DAO().getQuantity_place("Fridge");
                //Iterate though the food and expire date lists
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it1.next(), it2.next(),
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
                List<String> all_food = db.DAO().getFood_place("Freezer");
                List<String> all_exp = db.DAO().getExp_place("Freezer");
                List<String> all_place = db.DAO().getPlace_place("Freezer");
                List<Integer> all_quantity = db.DAO().getQuantity_place("Freezer");
                //Iterate though the food and expire date lists
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it1.next(), it2.next(),
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
                List<String> all_food = db.DAO().getFood_place("Pantry");
                List<String> all_exp = db.DAO().getExp_place("Pantry");
                List<String> all_place = db.DAO().getPlace_place("Pantry");
                List<Integer> all_quantity = db.DAO().getQuantity_place("Pantry");
                //Iterate though the food and expire date lists
                Iterator<String> it1 = all_food.iterator();
                Iterator<String> it2 = all_exp.iterator();
                Iterator<String> it3 = all_place.iterator();
                Iterator<Integer> it4 = all_quantity.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    allFoodList.add(new AllFood(it1.next(), it2.next(),
                            it3.next(), it4.next()));
                }

                allFoodAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    public List<AllFood> getAll(){
        List<AllFood> allFoodList = new ArrayList<>();
        db = AppDatabase.getInstance(getContext());
        List<String> all_food = db.DAO().getFoodN();
        List<String> all_exp = db.DAO().getExp();
        List<String> all_place = db.DAO().getPlace();
        List<Integer> all_quantity = db.DAO().getQuant();
        //Iterate though the food and expire date lists
        Iterator<String> it1 = all_food.iterator();
        Iterator<String> it2 = all_exp.iterator();
        Iterator<String> it3 = all_place.iterator();
        Iterator<Integer> it4 = all_quantity.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            allFoodList.add(new AllFood(it1.next(), it2.next(),
                    it3.next(), it4.next()));
        }

        return allFoodList;
    }

}