package com.example.foodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodtracker.adapter.SearchFilterAdapter;
import com.example.foodtracker.model.FoodItem;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckShelfLife extends AppCompatActivity {

    private EditText filterText = null;
    SearchFilterAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_shelf_life);

        Button searchButton = findViewById(R.id.SearchFoodDatesButton);
        filterText = (EditText) findViewById(R.id.EditTextSearch);

        filterText.addTextChangedListener(filterTextWatcher);

        ListView listView = findViewById(R.id.listLinksApp);
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> urlList = new ArrayList<>();

        adapter = new SearchFilterAdapter(getApplicationContext(),
                R.layout.food_list_item,nameList);

        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int pos, long id) {
                Intent intent = new Intent(view.getContext(), WebPageActivity.class);

                intent.putExtra("urlPage", urlList.get(pos));
                startActivity(intent);
            }
        });

        RestClient foodDataBase = new RestClient(listView, adapter,nameList, urlList);
        foodDataBase.execute("https://shelf-life-api.herokuapp.com/search?");


    }
    /**
     * Filter for filtering items in the list.
     */
    private TextWatcher filterTextWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (adapter != null) {
                adapter.getFilter().filter(s);
            } else {
                Log.d("filter", "no filter available");
            }
        }
    };
}

