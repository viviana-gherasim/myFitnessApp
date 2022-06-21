package com.example.myfitnessapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Manager.Nutritionist;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class BookingStep3Fragment extends Fragment implements TimeSlotLoadListener {

    TimeSlotLoadListener timeSlotLoadListener;

    Unbinder unbinder;
    LocalBroadcastManager localBroadcastManager;

    @BindView(R.id.recycler_time_slot)
    RecyclerView recycler_time_slot;

    @BindView(R.id.calendarView)
    HorizontalCalendarView calendarView;

    SimpleDateFormat simpleDateFormat;

    DatabaseReference databaseReference;
    FirebaseDatabase database;

    BroadcastReceiver displayTimeSlot = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Calendar date = Calendar.getInstance();
            date.add(Calendar.DATE, 0); //add current date
            loadAvailableTimeSlotOfNutritionist(Common.KEY_NUTRITIONIST,
                simpleDateFormat.format(date.getTime()));
        }
    };
    

    static BookingStep3Fragment instance;

    public static BookingStep3Fragment getInstance() {
        if(instance == null)
            instance = new BookingStep3Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timeSlotLoadListener = this;

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(displayTimeSlot, new IntentFilter(Common.KEY_DISPLAY_TIME_SLOT));

        simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy");

    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(displayTimeSlot);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step3, container, false);
        unbinder = ButterKnife.bind(this, itemView);

        init(itemView);

        return itemView;

    }

    private void init(View itemView) {
        recycler_time_slot.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recycler_time_slot.setLayoutManager(gridLayoutManager);
        recycler_time_slot.addItemDecoration(new SpacesItemDecoration(8));

        //calendar
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 0 );

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 2 );     //2 day left

        Calendar beforeDate = Calendar.getInstance();
        beforeDate.add(Calendar.DATE, 2);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(itemView, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(1)
                .mode(HorizontalCalendar.Mode.DAYS)
                .defaultSelectedDate(startDate)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                if(Common.currentDate.getTimeInMillis() != date.getTimeInMillis()) {
                    Common.currentDate = date;
                    loadAvailableTimeSlotOfNutritionist(Common.KEY_NUTRITIONIST,
                            simpleDateFormat.format(date.getTime()));
                }
            }
        });
    }

    private void loadAvailableTimeSlotOfNutritionist(String keyNutritionist, String bookDate) {
        onTimeSlotLoadSuccess(new ArrayList<TimeSlot>());

        String nutritionistName = Common.currentNutritionist.getNutritionist();
        String nutritionistAddress = Common.currentNutritionist.getAddress();
        String nutritionistEmail = Common.currentNutritionist.getEmail_addr();
        String nutritionistPhone = Common.currentNutritionist.getPhone();
        String nutritionistCity = Common.currentNutritionist.getCity();


        database = FirebaseDatabase.getInstance("https://my-fitness-app-aa2ef-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("City");

        Query cityByNameQuery = databaseReference.orderByChild("name").equalTo(nutritionistCity);

        cityByNameQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String id = snapshot.getKey();
                Nutritionist nutritionist = new Nutritionist(nutritionistName, nutritionistEmail, nutritionistAddress, nutritionistPhone, nutritionistCity);

                DatabaseReference newRef = databaseReference.child(id).child("Nutritionists").child(Common.currentNutritionist.id);

                newRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()) {
                            List<TimeSlot> timeSlots = new ArrayList<>();
                            Nutritionist nutritionist = task.getResult().getValue(Nutritionist.class);
                            System.out.println(id);
                            Map<String, List<String>> bookDates = nutritionist.getBookDate();
                            List<String> dateList = bookDates.get(Common.returnBookDate(Common.currentDate.getTime()));
                            if(dateList != null) {

                                for (String bookDate : dateList) {
                                    TimeSlot timeSlot = new TimeSlot();
                                    timeSlot.setSlot(Long.valueOf(bookDate));
                                    timeSlots.add(timeSlot);
                                }
                                timeSlotLoadListener.onTimeSlotLoadSuccess(timeSlots);
                            }
                        }
                        else {

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        timeSlotLoadListener.onTimeSlotLoadFailed(e.getMessage());
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

    @Override
    public void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotList) {
        MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getContext(), timeSlotList);
        recycler_time_slot.setAdapter(adapter);
    }

    @Override
    public void onTimeSlotLoadFailed(String message) {
        Toast.makeText(getContext(), "message", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTimeSlotLoadEmpty() {
        MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getContext());
        recycler_time_slot.setAdapter(adapter);
    }
}

