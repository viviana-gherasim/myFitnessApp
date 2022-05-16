package com.example.myfitnessapp;

public class Nutritionist {

    public String nutritionist, email_addr, address, phone, city;

    public Nutritionist() {}

    public Nutritionist(String nutritionist, String email_addr, String address, String phone, String city) {
        this.nutritionist = nutritionist;
        this.email_addr = email_addr;
        this.address = address;
        this.phone = phone;
        this.city = city;
    }

    public String getNutritionist() { return nutritionist; }

    public void setNutritionist(String nutritionist) { this.nutritionist = nutritionist; }

    public String getEmail_addr() { return email_addr; }

    public void setEmail_addr(String email_addr) { this.email_addr = email_addr; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

}
