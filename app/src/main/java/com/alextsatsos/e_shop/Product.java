package com.alextsatsos.e_shop;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Δημιουργία πίνακα στην βάση με όνομα product και πεδία id,brand category,price,unit,feature
@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true) // το id είναι το κύριο κλειδί του πίνακα και αυξάνεται αυτόματα κατα ένα
    private int id;

    private String brand;

    private String category;

    private double price;

    private int unit;

    private String  feature;

    private String imageUrl;

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getUnit() {
        return unit;
    }
}