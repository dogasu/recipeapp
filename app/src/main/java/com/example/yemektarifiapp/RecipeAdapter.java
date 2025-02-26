package com.example.yemektarifiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> implements Filterable {

    private Context context;
    private List<String> recipeList;
    private List<String> recipeListFull;
    private List<Integer> imageList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String recipeName, int imageResId);
    }

    public RecipeAdapter(Context context, List<String> recipeList, List<Integer> imageList, OnItemClickListener listener) {
        this.context = context;
        this.recipeList = recipeList;
        this.recipeListFull = new ArrayList<>(recipeList);
        this.imageList = imageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String recipeName = recipeList.get(position);
        int imageResId = imageList.get(position);

        holder.recipeNameTextView.setText(recipeName);
        holder.recipeImageView.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @Override
    public Filter getFilter() {
        return recipeFilter;
    }

    private Filter recipeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(recipeListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String item : recipeListFull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeList.clear();
            recipeList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNameTextView;
        ImageView recipeImageView;

        RecipeViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipeNameTextView);
            recipeImageView = itemView.findViewById(R.id.recipeImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(recipeList.get(position), imageList.get(position));
                    }
                }
            });
        }
    }
}