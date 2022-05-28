package com.example.myfitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyNutritionistAdapter extends RecyclerView.Adapter<MyNutritionistAdapter.MyViewHolder> {

    Context context;
    List<Nutritionist> nutritionistList;

    public MyNutritionistAdapter(Context context, List<Nutritionist> nutritionistList){
        this.context = context;
        this.nutritionistList = nutritionistList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_nutritionist, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txt_nutritionist_name.setText(nutritionistList.get(i).getNutritionist());
        myViewHolder.txt_nutritionist_address.setText(nutritionistList.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return nutritionistList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nutritionist_name, txt_nutritionist_address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_nutritionist_name = (TextView) itemView.findViewById(R.id.txt_nutritionist_name);
            txt_nutritionist_address = (TextView) itemView.findViewById(R.id.txt_nutritionist_address);
        }
    }
}
