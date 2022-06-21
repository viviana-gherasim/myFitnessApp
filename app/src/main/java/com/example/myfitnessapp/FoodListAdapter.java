package com.example.myfitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodListAdapter extends ArrayAdapter<Food> {

    private Context mContext;
    int mRes;

    public FoodListAdapter(@NonNull Context context, int res, @NonNull ArrayList<Food> obj) {
        super(context, res, obj);
        mContext = context;
        mRes = res;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getFood();
        String brandName = getItem(position).getBrand();
        String barcode = getItem(position).getBarcode();
        String calories = getItem(position).getCalories();
        String carbs = getItem(position).getCarbs();
        String proteins = getItem(position).getProteins();
        String fats = getItem(position).getFats();

        Food food = new Food(name, brandName, barcode, calories, carbs, proteins, fats);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRes, parent, false);

        TextView textName = convertView.findViewById(R.id.textView28);
        TextView textBrand = convertView.findViewById(R.id.textView36);
        TextView textBarcode = convertView.findViewById(R.id.textView37);

        textName.setText(name);
        textBrand.setText(brandName);
        textBarcode.setText(barcode);

        return convertView;
    }
}
