package com.example.myfitnessapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myfitnessapp.Manager.Nutritionist;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookingStep4Fragment extends Fragment {

    static BookingStep4Fragment instance;
    protected Nutritionist nutritionist;
    LocalBroadcastManager localBroadcastManager;

    @BindView(R.id.txt_booking_nutritionist_text)
    TextView nameNutritionist;

    @BindView(R.id.txt_address_name)
    TextView addressNutritionist;

    @BindView(R.id.txt_city_name)
    TextView cityNutritionist;

    Unbinder unbinder;
    Button confirm;

    @OnClick(R.id.btnConfirm)
    void confirmBooking() {
        Toast.makeText(getActivity(), "Successful appointment!", Toast.LENGTH_SHORT).show();
    }

    public void updateUI() {
        if (Common.currentNutritionist != null) {
            nameNutritionist.setText(Common.currentNutritionist.getNutritionist());
            addressNutritionist.setText(Common.currentNutritionist.getAddress());
            cityNutritionist.setText(Common.currentNutritionist.getCity());
        }
    }

    public static BookingStep4Fragment getInstance() {
        if(instance == null)
            instance = new BookingStep4Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step4, container, false);
        unbinder = ButterKnife.bind(this, itemView);

        nameNutritionist = itemView.findViewById(R.id.txt_booking_nutritionist_text);
        addressNutritionist = itemView.findViewById(R.id.txt_address_name);
        cityNutritionist = itemView.findViewById(R.id.txt_city_name);

        confirm = (Button) itemView.findViewById(R.id.btnConfirm);

        return itemView;
    }

}
