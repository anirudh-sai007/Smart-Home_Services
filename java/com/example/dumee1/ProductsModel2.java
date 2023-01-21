package com.example.dumee1;

public class ProductsModel2 {

    String Name,Phone,Slot;

    public ProductsModel2() {
    }

    public ProductsModel2(String name, String phone) {
        this.Name = name;
        this.Phone = phone;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
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