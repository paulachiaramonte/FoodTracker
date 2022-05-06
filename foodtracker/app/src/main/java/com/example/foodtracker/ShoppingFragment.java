package com.example.foodtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtracker.adapter.BuyListAdapter;
import com.example.foodtracker.database.AppDatabase;
import com.example.foodtracker.model.BuyList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ShoppingFragment extends Fragment {

    RecyclerView shoppingRecycler;
    BuyListAdapter buyListAdapter;
    AppDatabase db;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping,container,false);

        ArrayList<BuyList> shoppingList = new ArrayList<BuyList>();

        db = AppDatabase.getInstance(getContext());
        List<Long> all_ids_shops = db.DAO().getID_shops();
        List<String> all_food = db.DAO().getFood_shop();
        List<Integer> all_quant = db.DAO().getQuantity();

        ImageView emptyFoodImage = view.findViewById(R.id.imageEmptyShop);
        TextView emptyFoodTextView = view.findViewById(R.id.TextViewEmptyShop);

        if (all_food.size() == 0){
            emptyFoodImage.setVisibility(view.VISIBLE);
            emptyFoodTextView.setVisibility(view.VISIBLE);
        }
/*
        shoppingList.add(new BuyList("Buy 1" , 1));
        shoppingList.add(new BuyList("Buy 2" , 2));
        shoppingList.add(new BuyList("Buy 3" , 17));
*/
        Iterator<Long> it0 = all_ids_shops.iterator();
        Iterator<String> it1 = all_food.iterator();
        Iterator<Integer> it2 = all_quant.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            shoppingList.add(new BuyList(it0.next(),it1.next(), it2.next()));

            shoppingRecycler = view.findViewById(R.id.shopping_recycler);
            BuyListAdapter buyListAdapter = new BuyListAdapter(getContext(), shoppingList);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            shoppingRecycler.setLayoutManager(linearLayoutManager);
            shoppingRecycler.setAdapter(buyListAdapter);
        }

        return view;
    }
}