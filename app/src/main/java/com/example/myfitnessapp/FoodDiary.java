package com.example.myfitnessapp;

public class FoodDiary {
    private String email, day, month, year, quantity, foodName, category, brandName, calories, proteins, carbs, fats, barcode;

    public FoodDiary() {}

    public FoodDiary(String email, String day, String month, String year, String quantity, String foodName, String category, String brandName, String calories, String proteins, String carbs, String fats, String barcode) {
        this.email = email;
        this.day = day;
        this.month = month;
        this.year = year;
        this.quantity = quantity;
        this.foodName = foodName;
        this.category = category;
        this.brandName = brandName;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
        this.barcode = barcode;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getDay() { return day; }

    public void setDay(String day) { this.day = day; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getQuantity() { return quantity; }

    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getFoodName() { return foodName; }

    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getBrandName() { return brandName; }

    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getCalories() { return calories; }

    public void setCalories(String calories) { this.calories = calories; }

    public String getProteins() { return proteins; }

    public void setProteins(String proteins) { this.proteins = proteins; }

    public String getCarbs() { return carbs; }

    public void setCarbs(String carbs) { this.carbs = carbs; }

    public String getFats() { return fats; }

    public void setFats(String fats) { this.fats = fats; }

    public String getBarcode() { return barcode; }

    public void setBarcode(String barcode) { this.barcode = barcode; }
}
