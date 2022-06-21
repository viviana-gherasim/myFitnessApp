package com.example.myfitnessapp.Manager;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.myfitnessapp.TimeSlot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nutritionist implements Parcelable {

    public String nutritionist, email_addr, address, phone, city;
    //public List<String> bookDate = new ArrayList<String>();
    public Map<String, List<String>> bookDate = new HashMap<>();
    public transient String id;

    public Nutritionist() {}

    protected Nutritionist(Parcel in) {
        nutritionist = in.readString();
        email_addr = in.readString();
        address = in.readString();
        phone = in.readString();
        city = in.readString();
        //bookDate = in.createStringArrayList();
        id = in.readString();
        int size = in.readInt();
        
        for(int i = 0; i< size; i++) {
            String key = in.readString();
            int valSize = in.readInt();
            List<String> newList = new ArrayList<>();
            for(int j =0; j < valSize; j++) {
                String value = in.readString();
                newList.add(value);
            }

            bookDate.put(key, newList);
        }

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
        //dest.writeStringList(bookDate);
        dest.writeString(id);

        dest.writeInt(bookDate.size());
        for(Map.Entry<String, List<String>> entry : bookDate.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue().size());
            for (String value: entry.getValue()
                 ) {
                dest.writeString(value);
                
            }
        }
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

//    public List<String> getBookDate() { return bookDate; }
//
//    public void setBookDate(List<String> bookDate) { this.bookDate = bookDate; }


    public Map<String, List<String>> getBookDate() {
        return bookDate;
    }
}
