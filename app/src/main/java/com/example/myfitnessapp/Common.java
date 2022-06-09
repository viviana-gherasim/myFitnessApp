package com.example.myfitnessapp;

import com.example.myfitnessapp.Manager.Nutritionist;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_NUTRITIONIST = "NUTRITIONIST_SAVE";
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";
    public static final int TIME_SLOT_TOTAL = 10;
    public static final String KEY_CONFIRM_BOOKING = "CONFIRM_BOOKING";
    public static final String KEY_NUTRITIONIST_SELECTED = "NUTRITIONIST_SELECTED";
    public static final String KEY_STEP = "STEP";
    public static Nutritionist currentNutritionist;
    public static int step = 0;

    public static String convertTimeSlotToString(int slot) {
        switch (slot) {
            case 0:
                return "9:00-10:00";
            case 1:
                return "10:00-11:00";
            case 2:
                return "11:00-12:00";
            case 3:
                return "12:00-13:00";
            case 4:
                return "13:00-14:00";
            case 5:
                return "14:00-15:00";
            case 6:
                return "15:00-16:00";
            case 7:
                return "16:00-17:00";
            case 8:
                return "17:00-18:00";
            case 9:
                return "18:00-19:00";
            default:
                return "Closed";
        }
    }
}
