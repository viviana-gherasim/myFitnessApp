package com.example.myfitnessapp;

import java.util.List;

public interface BranchLoadListener {
    void onBranchLoadSuccess(List<Nutritionist> nutritionistList);
    void onBranchLoadFailed(String message);
}
