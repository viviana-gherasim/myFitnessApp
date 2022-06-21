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


public class DiaryActivityAdapter extends ArrayAdapter<FoodDiary> {
    private Context mContext;
    int mRes;

    public DiaryActivityAdapter(@NonNull Context context, int res, @NonNull ArrayList<FoodDiary> obj) {

        super(context, res, obj);
        mContext = context;
        mRes = res;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String email = getItem(position).getEmail();
        String day = getItem(position).getDay();
        String month = getItem(position).getMonth();
        String year = getItem(position).getYear();
        String quantity = getItem(position).getQuantity();
        String foodName = getItem(position).getFoodName();
        String category = getItem(position).getCategory();
        String brandName = getItem(position).getBrandName();
        String calories = getItem(position).getCalories();
        String proteins = getItem(position).getProteins();
        String carbs = getItem(position).getCarbs();
        String fats = getItem(position).getFats();
        String barcode = getItem(position).getBarcode();

        FoodDiary foodDiary = new FoodDiary(email, day, month, year, quantity, foodName, category, brandName, calories, proteins, carbs, fats, barcode);

        float fCalorii = Float.parseFloat(calories);
        String strCalorii = String.valueOf((int)fCalorii);

        float fProteine=Float.parseFloat(proteins);
        String strProteine= String.valueOf((int)fProteine);

        float fCarbo=Float.parseFloat(carbs);
        String strCarbo=String.valueOf((int)fCarbo);

        float fGrasimi=Float.parseFloat(fats);
        String strGrasimi=String.valueOf((int)fGrasimi);

        float fCantitate=Float.parseFloat(quantity);
        String strCantitate=String.valueOf((int)fCantitate);

        String strMesaj="KCal: "+strCalorii+"\nCarbs: "+strCarbo+"  Fats: "+strGrasimi+" \nProteins: "+strProteine+"  Quantity: "+strCantitate+"g";

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRes, parent, false);

        TextView tName = convertView.findViewById(R.id.textView38);
        TextView tCalories = convertView.findViewById(R.id.textView39);

        tName.setText(foodName);
        tCalories.setText(strMesaj);
        return convertView;
    }
}
