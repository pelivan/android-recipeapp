package com.example.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> recipe_id,recipe_title,recipe_author,recipe_ingredients,recipe_details;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(MainActivity.this);
        recipe_id = new ArrayList<>();
        recipe_title = new ArrayList<>();
        recipe_author = new ArrayList<>();
        recipe_ingredients = new ArrayList<>();
        recipe_details = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this,this,recipe_id,recipe_title,recipe_author,recipe_ingredients,recipe_details);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();


        if(cursor.getCount() == 0) {
            Toast.makeText(this,"No data to read!",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                recipe_id.add(cursor.getString(0));
                recipe_title.add(cursor.getString(1));
                recipe_author.add(cursor.getString(2));
                recipe_ingredients.add(cursor.getString(3));
                recipe_details.add(cursor.getString(4));
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new MyDatabaseHelper(MainActivity.this);
        recipe_id = new ArrayList<>();
        recipe_title = new ArrayList<>();
        recipe_author = new ArrayList<>();
        recipe_ingredients = new ArrayList<>();
        recipe_details = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this,this,recipe_id,recipe_title,recipe_author,recipe_ingredients,recipe_details);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }


}