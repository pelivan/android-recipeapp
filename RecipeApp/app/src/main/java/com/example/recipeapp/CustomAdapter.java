package com.example.recipeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList recipe_id, recipe_title,recipe_author,recipe_ingredients,recipe_details;
    int position;

    CustomAdapter(Activity activity, Context context, ArrayList recipe_id, ArrayList recipe_title, ArrayList recipe_author, ArrayList recipe_ingredients, ArrayList recipe_details) {
        this.activity = activity;
        this.context = context;
        this.recipe_id = recipe_id;
        this.recipe_title = recipe_title;
        this.recipe_author = recipe_author;
        this.recipe_ingredients = recipe_ingredients;
        this.recipe_details = recipe_details;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.recipe_id_text.setText(String.valueOf(recipe_id.get(position)));
        holder.recipe_title_text.setText(String.valueOf(recipe_title.get(position)));
        holder.recipe_author_text.setText(String.valueOf(recipe_author.get(position)));
        holder.recipe_ingredients_text.setText(String.valueOf(recipe_ingredients.get(position)));
        holder.recipe_detail_text.setText(String.valueOf(recipe_details.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(recipe_id.get(position)));
                intent.putExtra("title",String.valueOf(recipe_title.get(position)));
                intent.putExtra("author",String.valueOf(recipe_author.get(position)));
                intent.putExtra("ingredients",String.valueOf(recipe_ingredients.get(position)));
                intent.putExtra("details",String.valueOf(recipe_details.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipe_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipe_id_text, recipe_title_text, recipe_author_text, recipe_ingredients_text, recipe_detail_text;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipe_id_text = itemView.findViewById(R.id.recipe_id_text);
            recipe_title_text = itemView.findViewById(R.id.recipe_title_text);
            recipe_author_text = itemView.findViewById(R.id.recipe_author_text);
            recipe_ingredients_text = itemView.findViewById(R.id.recipe_ingredients_text);
            recipe_detail_text = itemView.findViewById(R.id.recipe_details_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
