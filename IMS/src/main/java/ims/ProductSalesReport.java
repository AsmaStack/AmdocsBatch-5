package ims;

public class ProductSalesReport {
    private int productId;
    private String productName;
    private int totalQuantity;
    private double totalRevenue;

    public ProductSalesReport(int productId, String productName, int totalQuantity, double totalRevenue) {
        this.productId = productId;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        this.totalRevenue = totalRevenue;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    @Override
    public String toString() {
        return "{" +
                "Product Id=" + productId +
                ", Product Name='" + productName + '\'' +
                ", Total Quantity=" + totalQuantity +
                ", Total Revenue=" + totalRevenue +
                '}';
    }
}
