package com.example.myfitnessapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Manager.Nutritionist;

import java.util.ArrayList;
import java.util.List;

public class MyNutritionistAdapter extends RecyclerView.Adapter<MyNutritionistAdapter.MyViewHolder> {

    Context context;
    List<Nutritionist> nutritionistList;
    List<CardView> cardViewList;
    LocalBroadcastManager localBroadcastManager;

    public MyNutritionistAdapter(Context context, List<Nutritionist> nutritionistList){
        this.context = context;
        this.nutritionistList = nutritionistList;
        cardViewList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
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

        if(!cardViewList.contains(myViewHolder.card_nutritionist)) {
            cardViewList.add(myViewHolder.card_nutritionist);
        }

        myViewHolder.setRecyclerItemSelectedListener(new RecyclerItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {

                Common.currentNutritionist = nutritionistList.get(pos);

                //set white background color for all card
                for(CardView cardView:cardViewList)
                    cardView.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));

                //set background color for only selected item
                myViewHolder.card_nutritionist.setCardBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));

                //send Broadcast to tell Booking Activity enable button NEXT
                Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                intent.putExtra(Common.KEY_NUTRITIONIST, nutritionistList.get(pos));
                intent.putExtra(Common.KEY_STEP, 2);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nutritionistList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_nutritionist_name, txt_nutritionist_address;
        CardView card_nutritionist;

        RecyclerItemSelectedListener recyclerItemSelectedListener;

        public void setRecyclerItemSelectedListener(RecyclerItemSelectedListener recyclerItemSelectedListener) {
            this.recyclerItemSelectedListener = recyclerItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card_nutritionist = (CardView) itemView.findViewById(R.id.card_nutritionist);
            txt_nutritionist_name = (TextView) itemView.findViewById(R.id.txt_nutritionist_name);
            txt_nutritionist_address = (TextView) itemView.findViewById(R.id.txt_nutritionist_address);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItemSelectedListener.onItemSelectedListener(v, getAdapterPosition());
        }
    }
}
