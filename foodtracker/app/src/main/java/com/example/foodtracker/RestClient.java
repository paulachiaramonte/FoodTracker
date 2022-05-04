package com.example.foodtracker;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.foodtracker.model.FoodItem;


public class RestClient extends AsyncTask<String, Void, String> {

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private ArrayAdapter urlAdapter;
    private List<String> nameList;
    private List<String> urlList;
    private HashMap dictFoodId;


    public RestClient(ListView listView, ArrayAdapter arrayAdapter, ArrayAdapter urlAdapter,
                      ArrayList nameList, ArrayList urlList, HashMap dictFoodId)
    {
        this.listView = listView;
        this.arrayAdapter = arrayAdapter;
        this.urlAdapter = urlAdapter;
        this.nameList = nameList;
        this.urlList = urlList;
        this.dictFoodId = dictFoodId;
    }

    @Override
    protected String doInBackground(String... urls) {
        String result = null;
        try {
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            result = response.toString();

        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "Exception reading URL", e);
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        Gson gson = new Gson();
        FoodItem[] foodItems = gson.fromJson(result, FoodItem[].class);
        int i = 0;
        for (FoodItem food : foodItems) {
            nameList.add(food.getName());
            urlList.add(food.getURL_food());
            dictFoodId.put(food.getName(), i);
            i += 1;

        }
        arrayAdapter.notifyDataSetChanged();
        urlAdapter.notifyDataSetChanged();

    }



}