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
        String weight = getItem(position).getWeight();
        String height = getItem(position).getHeight();
        String targetWeight = getItem(position).getTargetWeight();
        String day = getItem(position).getDay();
        String month = getItem(position).getMonth();
        String year = getItem(position).getYear();

        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView=inflater.inflate(mRes,parent,false);

        TextView tEmail = (TextView) convertView.findViewById(R.id.textView18);
        TextView tWeight = (TextView) convertView.findViewById(R.id.textView21);
        TextView tHeight = (TextView) convertView.findViewById(R.id.textView20);
        TextView tTargetWeight = (TextView) convertView.findViewById(R.id.textView22);
        TextView tDay = (TextView) convertView.findViewById(R.id.textView19);
        TextView tMonth = (TextView) convertView.findViewById(R.id.textView26);
        TextView tYear = (TextView) convertView.findViewById(R.id.textView27);

        tEmail.setText(email);
        tWeight.setText(weight);
        tHeight.setText(height);
        tTargetWeight.setText(targetWeight);
        tDay.setText(day);
        tMonth.setText(month);
        tYear.setText(year);

        return convertView;

    }
}
