package org.sogeti.functions.models;

import org.sogeti.functions.enums.ProductCategory;
import org.sogeti.functions.enums.TransportationBy;
import org.sogeti.functions.enums.VATRate;

public class ExportTransaction {
    private String PartitionKey;
    private String RowKey;
    private String origin;
    private String destination;
    private TransportationBy transportationBy;
    private ProductCategory productCategory;
    private String productName;
    private VATRate vatRate;
    private int quantity;
    private double cost;

    public ExportTransaction(String partitionKey, String rowKey, String origin, String destination,
                             TransportationBy transportationBy, ProductCategory productCategory, String productName,
                             VATRate vatRate, int quantity, double cost) {
        PartitionKey = partitionKey;
        RowKey = rowKey;
        this.origin = origin;
        this.destination = destination;
        this.transportationBy = transportationBy;
        this.productCategory = productCategory;
        this.productName = productName;
        this.vatRate = vatRate;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getPartitionKey() {
        return PartitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        PartitionKey = partitionKey;
    }

    public String getRowKey() {
        return RowKey;
    }

    public void setRowKey(String rowKey) {
        RowKey = rowKey;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public TransportationBy getTransportationBy() {
        return transportationBy;
    }

    public void setTransportationBy(TransportationBy transportationBy) {
        this.transportationBy = transportationBy;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public VATRate getVatRate() {
        return vatRate;
    }

    public void setVatRate(VATRate vatRate) {
        this.vatRate = vatRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}