package com.example.MaaKaKhana;


import com.google.android.gms.maps.model.LatLng;

public class registration_details {
    public String firstName, lastName, emailId, password, contactNo;


    public registration_details()
    {}

    public registration_details(String firstName, String lastName, String emailId, String password, String contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.contactNo = contactNo;
    }

    /*public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getLat() {
        return latLng.latitude;
    }

    public double getLong() {
        return latLng.longitude;
    }*/
}
