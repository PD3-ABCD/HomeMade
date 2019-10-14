package com.example.MaaKaKhana.ui.login_home;

public class FoodItem {

        public FoodItem() {

        }

        private String food_desc;
        private String food_name;
        private Double food_price;
        private Integer food_quantity;
        private String ID;

        public String getId() {
                return ID;
        }

        public void setId(String ID) {
                this.ID = ID;
        }

        public String getFood_desc() {
                return food_desc;
        }

        public void setFood_desc(String food_desc) {
                this.food_desc = food_desc;
        }

        public String getFood_name() {
                return food_name;
        }

        public void setFood_name(String food_name) {
                this.food_name = food_name;
        }

        public Double getFood_price() {
                return food_price;
        }

        public void setFood_price(Double food_price) {
                this.food_price = food_price;
        }

        public Integer getFood_quantity() {
                return food_quantity;
        }

        public void setFood_quantity(Integer food_quantity) {
                this.food_quantity = food_quantity;
        }
}