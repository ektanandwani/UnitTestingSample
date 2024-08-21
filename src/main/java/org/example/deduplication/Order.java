package org.example.deduplication;

public class Order {

    private int orderID;
    private String shippingAddress;
    private Product product;


    public Order(int orderID, String shippingAddress, Product product) {
        this.orderID = orderID;
        this.shippingAddress = shippingAddress;
        this.product = product;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return ("\n\nOrder Details: \norder ID: " + orderID + "\nShipping Address: " + shippingAddress);
    }

    @Override
    public boolean equals(Object obj) {
        return orderID == ((Order) obj).orderID;
    }

    @Override
    public int hashCode() {
        return orderID;
    }
}
