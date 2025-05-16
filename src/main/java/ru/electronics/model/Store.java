package ru.electronics.model;

public class Store {
    private static int idCounter = 1;
    private int id;
    private String brand;
    private String productName;
    private Double price;

    public Store() {
        this.id = idCounter++;
    }
    public int getId() { return id; }
    public void setID(int id)
    {
        this.id = id;
    }
    public String getBrand()
    {
        return brand;
    }
    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public Double getPrice()
    {
        return price;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }
}
