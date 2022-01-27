package com.example.recipeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input,author_input,ingredients_input,details_input;
    Button update_button,delete_button;
    String id,title,author,ingredients,details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        ingredients_input = findViewById(R.id.recipe_ingredients2);
        details_input = findViewById(R.id.recipe_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        //Prvo ovo pa onda updatedata inace nece radit
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab!=null) {
            ab.setTitle(title);
        };
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                ingredients = ingredients_input.getText().toString().trim();
                details = details_input.getText().toString().trim();
                myDB.updateData(id,title,author,ingredients,details);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
            }
        });




    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("ingredients") && getIntent().hasExtra("details")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            ingredients = getIntent().getStringExtra("ingredients");
            details = getIntent().getStringExtra("details");

            //setting data

            title_input.setText(title);
            author_input.setText(author);
            ingredients_input.setText(ingredients);
            details_input.setText(details);
        } else {
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                MyDatabaseHelper myDb = new MyDatabaseHelper(UpdateActivity.this);
                myDb.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}