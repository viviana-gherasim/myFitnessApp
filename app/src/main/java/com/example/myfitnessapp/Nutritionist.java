package com.example.myfitnessapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Nutritionist implements Parcelable {

    public String nutritionist, email_addr, address, phone, city;

    public Nutritionist() {}

    protected Nutritionist(Parcel in) {
        nutritionist = in.readString();
        email_addr = in.readString();
        address = in.readString();
        phone = in.readString();
        city = in.readString();
    }

    public static final Creator<Nutritionist> CREATOR = new Creator<Nutritionist>() {
        @Override
        public Nutritionist createFromParcel(Parcel in) {
            return new Nutritionist(in);
        }

        @Override
        public Nutritionist[] newArray(int size) {
            return new Nutritionist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nutritionist);
        dest.writeString(email_addr);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(city);
    }

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
