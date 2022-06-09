package com.example.myfitnessapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyTimeSlotAdapter extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;

//    public MyTimeSlotAdapter(Context context) {
//        this.context = context;
//        this.timeSlotList = new ArrayList<>();
//    }

    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_time_slot, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.txt_time_slot.setText(new StringBuilder(Common.convertTimeSlotToString(position)).toString());
        if(timeSlotList.size() == 0){ //if all positions are available, just show list

            myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
            myViewHolder.txt_time_slot_description.setText("Available");
            myViewHolder.txt_time_slot_description.setTextColor(context.getResources().getColor(android.R.color.white));
            myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.black));

        }
//        else {  //if have position is full booked
//            for (TimeSlot slotValue : timeSlotList) {
//                //loop all time slot from server and set different color
//                int slot = Integer.parseInt(slotValue.getSlot().toString());
//                if(slot == position) { //if slot == position
//                    myViewHolder.card_time_slot.setCardBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
//                    myViewHolder.txt_time_slot_description.setText("Full");
//                    myViewHolder.txt_time_slot_description.setTextColor(context.getResources().getColor(android.R.color.white));
//                    myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(android.R.color.white));
//                }
//            }
//        }
    }

    @Override
    public int getItemCount() {

        return Common.TIME_SLOT_TOTAL;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_time_slot, txt_time_slot_description;
        CardView card_time_slot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_time_slot = (CardView) itemView.findViewById(R.id.card_time_slot);
            txt_time_slot = (TextView) itemView.findViewById(R.id.txt_time_slot);
            txt_time_slot_description = (TextView) itemView.findViewById(R.id.txt_time_slot_description);
        }
    }
}