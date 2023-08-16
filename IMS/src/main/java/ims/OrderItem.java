package ims;

public class OrderItem {
    private int orderId;
    private int productId;
    private int quantity;
    private double subtotal;

    public OrderItem(int orderId, int productId, int quantity, double subtotal) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Order Id=" + orderId +
                ", Product Id=" + productId +
                ", Quantity=" + quantity +
                ", Subtotal=" + subtotal +
                '}';
    }
}
