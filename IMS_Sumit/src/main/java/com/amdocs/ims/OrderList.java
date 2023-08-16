package com.amdocs.ims;

class OrderList {
    private int order_id;
    private int user_id;
    private int prod_id;
    private int purchased_quantity;

    public OrderList(int order_id, int user_id, int prod_id, int purchased_quantity) {
        super();
        this.order_id = order_id;
        this.prod_id = prod_id;
        this.user_id = user_id;
        this.purchased_quantity = purchased_quantity;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public int getProd_id() {
        return prod_id;
    }
    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getPurchased_quantity() {
        return purchased_quantity;
    }
    public void setPurchased_quantity(int purchased_quantity) {
        this.purchased_quantity = purchased_quantity;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%-10s%-10s%-10s%-10s",order_id, user_id, prod_id, purchased_quantity);
    }
}
