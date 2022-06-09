package com.example.myfitnessapp;

import com.example.myfitnessapp.Manager.Nutritionist;

import java.util.List;

public interface BranchLoadListener {
    void onBranchLoadSuccess(List<Nutritionist> nutritionistList);
    void onBranchLoadFailed(String message);
}
