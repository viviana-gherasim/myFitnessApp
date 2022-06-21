package com.example.myfitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class ProfileAdapter  extends ArrayAdapter<User> {

    private Context mContext;
    int mRes;

    public ProfileAdapter(@NonNull Context context, int res, @NonNull ArrayList<User> obj) {

        super(context, res, obj);
        mContext = context;
        mRes = res;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String email = getItem(position).getEmail();
        Long weight = Long.valueOf(getItem(position).getWeight());
        Long height = Long.valueOf(getItem(position).getHeight());
        Long targetWeight = Long.valueOf(getItem(position).getTargetWeight());
        String day = getItem(position).getDay();
        String month = getItem(position).getMonth();
        String year = getItem(position).getYear();

        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView=inflater.inflate(mRes,parent,false);

        TextView tEmail = convertView.findViewById(R.id.textView18);
        TextView tWeight = convertView.findViewById(R.id.textView21);
        TextView tHeight = convertView.findViewById(R.id.textView20);
        TextView tTargetWeight = convertView.findViewById(R.id.textView22);
        TextView tDay = convertView.findViewById(R.id.textView19);
        TextView tMonth = convertView.findViewById(R.id.textView26);
        TextView tYear = convertView.findViewById(R.id.textView27);

        tEmail.setText(email);
        tWeight.setText(String.valueOf(weight));
        tHeight.setText(String.valueOf(height));
        tTargetWeight.setText(String.valueOf(targetWeight));
        tDay.setText(day);
        tMonth.setText(month);
        tYear.setText(year);

        return convertView;

    }
}
