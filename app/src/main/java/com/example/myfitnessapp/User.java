package com.example.myfitnessapp;

public class User {
    private String email, password, day, month, year;
    private Long  weight, height, targetWeight;

    public User() { }

    public User(String email, String password, Long weight, Long height, Long targetWeight, String day, String month, String year) {
        this.email=email;
        this.password=password;
        this.weight=weight;
        this.height=height;
        this.targetWeight=targetWeight;
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Long targetWeight) {
        this.targetWeight = targetWeight;
    }

}
