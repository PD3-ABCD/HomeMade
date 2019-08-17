package com.example.MaaKaKhana;

public class registration_details {
    public String firstName, lastName, emailId, password, contactNo;

    public registration_details()
    {

    }

    public registration_details(String firstName, String lastName, String emailId, String password, String contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.contactNo = contactNo;
    }
}
