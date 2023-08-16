package ims;

import java.util.Date;

public class Order {
    private int orderId;
    private int custId;
    private Date orderDate;
    private double totalAmount;

    public Order(int orderId, int custId, Date orderDate, double totalAmount) {
        this.orderId = orderId;
        this.custId = custId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "{" +
                "Order Id=" + orderId +
                ", Customer Id=" + custId +
                ", Order Date=" + orderDate +
                ", Total Amount=" + totalAmount +
                '}';
    }
}
