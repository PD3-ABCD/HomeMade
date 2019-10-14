package com.example.MaaKaKhana.ui.login_home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    public User(){

    }

    private MyLocation Location;
    private Map<String,FoodItem> MyCart;
    private Map<String,FoodItem> MyFoodItems;
    private String contactNo;
    private String emailId;
    private String firstName;
    private String lastName;
    private String password;

    public MyLocation getLocation() {
        return Location;
    }

    public void setLocation(MyLocation location) {
        Location = location;
    }

    public Map<String, FoodItem> getMyCart() {
        return MyCart;
    }

    public void setMyCart(Map<String, FoodItem> myCart) {
        MyCart = myCart;
    }

    public Map<String, FoodItem> getMyFoodItems() {
        return MyFoodItems;
    }

    public void setMyFoodItems(Map<String, FoodItem> myFoodItems) {
        MyFoodItems = myFoodItems;
    }

    public List<FoodItem> getMyFoodItemsAsList() {
        return mapToList(MyFoodItems);
    }

    public List<FoodItem> getMyCartAsList(){
        return mapToList(MyCart);
    }

    public void setMyFoodItemsFromList(List<FoodItem> myFoodItems){
        MyFoodItems.putAll( listToMap(myFoodItems));
    }

    public void setMyCartFromList(List<FoodItem> myCart){
        MyCart.putAll(listToMap(myCart));
    }

    private Map<String, FoodItem> listToMap(List<FoodItem> myFoodItems) {
        Map<String,FoodItem> items = new HashMap<>(myFoodItems.size());
        for (FoodItem foodIttem:
             myFoodItems) {
            items.put(foodIttem.getId(),foodIttem);
        }
        return items;
    }

    private List<FoodItem> mapToList(Map<String, FoodItem> map) {
        if(map==null){
            return null;
        }
        else {
            List<FoodItem> list = new ArrayList<>(map.size());
            for (String x :
                    map.keySet()) {
                FoodItem item = map.get(x);
                item.setId(x);
                list.add(item);
            }
            return list;
        }
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

