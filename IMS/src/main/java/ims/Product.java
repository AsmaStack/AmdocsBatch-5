package ims;

public class Product {
    private int productId;
    private String productName;
    private String category;
    private double unitPrice;

    public Product(int productId, String productName, String category, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "{" +
                "Product Id=" + productId +
                ", Product Name='" + productName + '\'' +
                ", Category='" + category + '\'' +
                ", Unit Price=" + unitPrice +
                '}';
    }
}
