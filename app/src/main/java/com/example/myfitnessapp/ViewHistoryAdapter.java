package com.example.myfitnessapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewHistoryAdapter extends ArrayAdapter<FoodDiary> {
    private Context mContext;
    int mRes;

    public ViewHistoryAdapter(@NonNull Context context, int res, @NonNull ArrayList<FoodDiary> obj) {
        super(context, res, obj);
        mContext = context;
        mRes = res;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getFoodName();
        String calories = getItem(position).getCalories();
        String carbs=getItem(position).getCarbs();
        String fats=getItem(position).getFats();
        String proteins=getItem(position).getProteins();
        String cantitate=getItem(position).getQuantity();
        float fCalorii=Float.parseFloat(calories);
        String strCalorii=String.valueOf((int)fCalorii);
        float fCarbo=Float.parseFloat(carbs);
        String strCarbo=String.valueOf((int)fCarbo);
        float fGrasimi=Float.parseFloat(fats);
        String strGrasimi=String.valueOf((int)fGrasimi);
        float fProteine=Float.parseFloat(proteins);
        String strProteine= String.valueOf((int)fProteine);
        float fCantitate=Float.parseFloat(cantitate);
        String strCantitate=String.valueOf((int)fCantitate);
        String strMesaj="KCal: "+strCalorii+"\nCarbs: "+strCarbo+"   Fats: "+strGrasimi+"  \nProteins: "+strProteine+"   Quantity: "+strCantitate+"g";

        // Food food=new Food(name,calories);
        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView=inflater.inflate(mRes,parent,false);

        TextView tName = convertView.findViewById(R.id.nameFoodHistory);
        TextView tCalories = convertView.findViewById(R.id.caloriesFoodHistory);

        tName.setText(name);
        tCalories.setText(strMesaj);
        return convertView;
    }
}
