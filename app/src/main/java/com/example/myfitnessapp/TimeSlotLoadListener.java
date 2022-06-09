package com.example.myfitnessapp;

import java.util.List;

public interface TimeSlotLoadListener {
    void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotList);
    void onTimeSlotLoadFailed(String message);
    void onTimeSlotLoadEmpty();
}
