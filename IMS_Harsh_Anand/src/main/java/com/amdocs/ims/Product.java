package com.amdocs.ims;

public class Product {
    private int prod_id;
    private String prod_name;
    private int quantity;
    public Product(int prod_id, String prod_name, int quantity) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.quantity = quantity;
    }
    public int getProd_id() {
        return prod_id;
    }
    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }
    public String getProd_name() {
        return prod_name;
    }
    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return String.format("%-10s%-10s%-10s", prod_id, prod_name,quantity);
    }

}
