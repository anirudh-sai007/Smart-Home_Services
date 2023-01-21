package com.example.dumee1;

public class ProductsModel {

    String Name,Phone;

    public ProductsModel() {
    }

    public ProductsModel(String name, String phone) {
        this.Name = name;
        this.Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }
}