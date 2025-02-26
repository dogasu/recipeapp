package com.example.yemektarifiapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private RecipeAdapter adapter;
    private ArrayList<String> recipes;
    private ArrayList<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recipeRecyclerView);
        searchView = findViewById(R.id.recipeSearchView);

        String[] recipeArray = {"Spaghetti Bolognese", "Chicken Curry", "Beef Stroganoff", "Vegetable Stir Fry", "Tacos", "Sushi", "Pizza Margherita"};
        recipes = new ArrayList<>(Arrays.asList(recipeArray));

        Integer[] imageArray = {R.drawable.spaghetti, R.drawable.curry, R.drawable.stroganoff, R.drawable.stirfry, R.drawable.tacos, R.drawable.sushi, R.drawable.pizza};
        images = new ArrayList<>(Arrays.asList(imageArray));

        adapter = new RecipeAdapter(this, recipes, images, new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String recipeName, int imageResId) {
                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
                intent.putExtra("recipeName", recipeName);
                intent.putExtra("imageResId", imageResId);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}