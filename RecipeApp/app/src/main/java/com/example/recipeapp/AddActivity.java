package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText author_input,recipe_input,title_input,recipe_ingredients;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        author_input = findViewById(R.id.author_input);
        recipe_input = findViewById(R.id.recipe_input);
        title_input = findViewById(R.id.title_input);
        recipe_ingredients = findViewById(R.id.recipe_ingredients);

        author_input.addTextChangedListener(addTextWatcher);
        recipe_input.addTextChangedListener(addTextWatcher);
        title_input.addTextChangedListener(addTextWatcher);
        recipe_ingredients.addTextChangedListener(addTextWatcher);


        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addRecipe(title_input.getText().toString().trim(),
                            author_input.getText().toString().trim(),
                            recipe_ingredients.getText().toString().trim(),
                            recipe_input.getText().toString().trim());
                            finish();

            }
        });






    }

    private TextWatcher addTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String titleInput = title_input.getText().toString().trim();
                String authorInput = author_input.getText().toString().trim();
                String ingInput = recipe_ingredients.getText().toString().trim();
                String recipeInput = recipe_input.getText().toString().trim();

                add_button.setEnabled(!titleInput.isEmpty() && !authorInput.isEmpty() && !ingInput.isEmpty() && !recipeInput.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };


}