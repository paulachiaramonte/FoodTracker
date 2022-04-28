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

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);


        List<AllFood> allFoodList = new ArrayList<>();
        db = AppDatabase.getInstance(getContext());
        List<String> all_food = db.DAO().getFoodN();
        List<String> all_exp = db.DAO().getExp();
        //Iterate though the food and expire date lists
        Iterator<String> it1 = all_food.iterator();
        Iterator<String> it2 = all_exp.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            allFoodList.add(new AllFood(it1.next(), it2.next()));

            foodRecycler = view.findViewById(R.id.food_recycler);
            AllFoodAdapter allFoodAdapter = new AllFoodAdapter(getContext(), allFoodList);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            foodRecycler.setLayoutManager(linearLayoutManager);
            foodRecycler.setAdapter(allFoodAdapter);

        }
        return view;
    }

    //we set recycler view first we make a model class then adapter class

    // now recycler view row item layout file

    //bind data with recyclerview
}