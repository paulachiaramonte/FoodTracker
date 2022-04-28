package com.example.foodtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtracker.adapter.BuyListAdapter;
import com.example.foodtracker.model.BuyList;

import java.util.ArrayList;
import java.util.List;


public class ShoppingFragment extends Fragment {

    RecyclerView shoppingRecycler;
    BuyListAdapter buyListAdapter;

    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping,container,false);

        List<BuyList> shoppingList = new ArrayList<>();

        shoppingList.add(new BuyList("Buy 1" , 1));
        shoppingList.add(new BuyList("Buy 2" , 2));
        shoppingList.add(new BuyList("Buy 3" , 17));

        shoppingRecycler = view.findViewById(R.id.shopping_recycler);
        BuyListAdapter buyListAdapter = new BuyListAdapter(getContext() , shoppingList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        shoppingRecycler.setLayoutManager(linearLayoutManager);
        shoppingRecycler.setAdapter(buyListAdapter);

        return view;
    }
}