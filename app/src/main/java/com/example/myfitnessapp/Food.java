package com.example.myfitnessapp;

public class Food {
    public String food, brand, barcode, calories, carbs, proteins, fats;

    public Food() {}

    public Food(String food, String brand, String barcode, String calories, String proteins, String carbs, String fats ) {
        this.food = food;
        this.brand = brand;
        this.barcode = barcode;
        this.calories = calories;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
    }

    public String getFood() { return food; }

    public void setFood(String food) { this.food = food; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getBarcode() { return barcode; }

    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getCalories() { return calories; }

    public void setCalories(String calories) { this.calories = calories; }

    public String getCarbs() { return carbs; }

    public void setCarbs(String carbs) { this.carbs = carbs; }

    public String getProteins() { return proteins; }

    public void setProteins(String proteins) { this.proteins = proteins; }

    public String getFats() { return fats; }

    public void setFats(String fats) { this.fats = fats; }

}
