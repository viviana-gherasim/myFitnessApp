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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    Calendar selected_date;

    @BindView(R.id.recycler_time_slot)
    RecyclerView recycler_time_slot;

    @BindView(R.id.calendarView)
    HorizontalCalendarView calendarView;

    SimpleDateFormat simpleDateFormat;

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

        selected_date = Calendar.getInstance();
        selected_date.add(Calendar.DATE, 0 );   //init current date
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

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(itemView, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(1)
                .mode(HorizontalCalendar.Mode.DAYS)
                .defaultSelectedDate(startDate)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                if(selected_date.getTimeInMillis() != date.getTimeInMillis()) {
                    selected_date = date; //this code will not load again if you select new day same with day selected
                    loadAvailableTimeSlotOfNutritionist(Common.KEY_NUTRITIONIST,
                            simpleDateFormat.format(date.getTime()));
                }
            }
        });
    }

    private void loadAvailableTimeSlotOfNutritionist(String keyNutritionist, String bookDate) {

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
//        MyTimeSlotAdapter adapter = new MyTimeSlotAdapter(getContext());
//        recycler_time_slot.setAdapter(adapter);
    }
}

