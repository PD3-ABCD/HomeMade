package com.example.MaaKaKhana.ui.OrderDetails;

public class ListData4 {
    private String id;
    // same as firebase field name
    private String food_name;
    private String food_desc;
    private Long food_price;
    private Long food_quantity;


    public Long getFood_price() {
        return food_price;
    }

    public void setFood_price(Long food_price) {
        this.food_price = food_price;
    }

    public Long getFood_quantity() {
        return food_quantity;
    }

    public void setFood_quantity(Long food_quantity) {
        this.food_quantity = food_quantity;
    }

    public ListData4() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_desc() {
        return food_desc;
    }

    public void setFood_desc(String food_desc) {
        this.food_desc = food_desc;
    }
}

