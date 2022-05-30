package com.example.myfitnessapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookingStep2Fragment extends Fragment {

    static BookingStep2Fragment instance;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    protected Nutritionist nutritionist;
    private List<Nutritionist> nutritionistList = new ArrayList<Nutritionist>();

    @BindView(R.id.textView188)
    TextView nameNutritionist;

    @BindView(R.id.textView184)
    TextView emailNutritionist;

    @BindView(R.id.textView185)
    TextView addressNutritionist;

    @BindView(R.id.textView186)
    TextView phoneNutritionist;

    Unbinder unbinder;

    public void updateUI() {
        if (Common.currentNutritionist != null) {
            nameNutritionist.setText(Common.currentNutritionist.getNutritionist());
            addressNutritionist.setText(Common.currentNutritionist.getAddress());
            emailNutritionist.setText(Common.currentNutritionist.getEmail_addr());
            phoneNutritionist.setText(Common.currentNutritionist.getPhone());
        }
    }

    public static BookingStep2Fragment getInstance() {
        if(instance == null)
            instance = new BookingStep2Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("City");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step2, container, false);
        unbinder = ButterKnife.bind(this, itemView);

        nameNutritionist = itemView.findViewById(R.id.textView188);
        emailNutritionist = itemView.findViewById(R.id.textView184);
        addressNutritionist = itemView.findViewById(R.id.textView185);
        phoneNutritionist = itemView.findViewById(R.id.textView186);

        return itemView;
    }

}
