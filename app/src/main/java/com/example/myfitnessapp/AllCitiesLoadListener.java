package com.example.myfitnessapp;

import java.util.List;

public interface AllCitiesLoadListener {
    void onAllCitiesLoadSuccess(List<String> areaNameList);
    void onAllCitiesLoadFailed(String message);
}
