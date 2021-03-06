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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    @BindView(R.id.txt_booking_time_text)
    TextView booking_slot;

    Unbinder unbinder;
    Button confirm;
    SimpleDateFormat simpleDateFormat;

    DatabaseReference databaseReference;
    FirebaseDatabase database;


    BroadcastReceiver confirmBookingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setData();
        }
    };

    private void setData() {
        booking_slot.setText(new StringBuilder(Common.convertTimeSlotToString(Common.currentTimeSlot))
                .append(" at ")
                .append(simpleDateFormat.format(Common.currentDate.getTime())));
    }

    @OnClick(R.id.btnConfirm)
    void confirmBooking() {
        Toast.makeText(getActivity(), "Successful appointment!", Toast.LENGTH_SHORT).show();
        String nutritionistCity = Common.currentNutritionist.getCity();


        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("City");

        Query cityByNameQuery = databaseReference.orderByChild("name").equalTo(nutritionistCity);
        cityByNameQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String id = snapshot.getKey();
                DatabaseReference newRef = databaseReference.child(id).child("Nutritionists").child(Common.currentNutritionist.id);

                newRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()) {
                            List<TimeSlot> timeSlots = new ArrayList<>();
                            Nutritionist nutritionist = task.getResult().getValue(Nutritionist.class);
                            
                            nutritionist.bookDate.get(Common.currentDate);
                            List<String> dates = nutritionist.bookDate.get(Common.returnBookDate(Common.currentDate.getTime()));
                            if(dates == null) {
                                dates = new ArrayList<>();
                            }
                            dates.add(Common.currentTimeSlot + "");
                            nutritionist.bookDate.put(Common.returnBookDate(Common.currentDate.getTime()), dates);

//                            nutritionist.bookDate.add(Common.currentTimeSlot + "");
                            newRef.setValue(nutritionist);
                            startActivity(new Intent(requireActivity(), NutritionMain.class));

                        }
                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(confirmBookingReceiver, new IntentFilter(Common.KEY_CONFIRM_BOOKING));

    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(confirmBookingReceiver);
        super.onDestroy();
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
        booking_slot = itemView.findViewById(R.id.txt_booking_time_text);

        confirm = (Button) itemView.findViewById(R.id.btnConfirm);

        return itemView;
    }

}
