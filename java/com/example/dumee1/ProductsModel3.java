package com.example.dumee1;

public class ProductsModel3
{
    String Name,Phone,REPORT;

public ProductsModel3() {
        }

public ProductsModel3(String name, String phone) {
        this.Name = name;
        this.Phone = phone;
        }

    public String getREPORT() {
        return REPORT;
    }

    public void setREPORT(String REPORT) {
        this.REPORT = REPORT;
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