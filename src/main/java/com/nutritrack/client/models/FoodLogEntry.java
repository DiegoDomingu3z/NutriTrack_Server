package com.nutritrack.client.models;

import java.math.BigDecimal;

public class FoodLogEntry {
    private String foodId;
    private String foodName;
    private String foodBrand;
    private BigDecimal foodCalories;
    private BigDecimal foodQuantity;
    private String foodUnit;

    // Constructors
    public FoodLogEntry() {}

    public FoodLogEntry(String foodId, String foodName, String foodBrand, BigDecimal foodCalories, BigDecimal foodQuantity, String foodUnit) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodBrand = foodBrand;
        this.foodCalories = foodCalories;
        this.foodQuantity = foodQuantity;
        this.foodUnit = foodUnit;
    }

    // Getters and setters
    public String getFoodId() { return foodId; }
    public void setFoodId(String foodId) { this.foodId = foodId; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public String getFoodBrand() { return foodBrand; }
    public void setFoodBrand(String foodBrand) { this.foodBrand = foodBrand; }

    public BigDecimal getFoodCalories() { return foodCalories; }
    public void setFoodCalories(BigDecimal foodCalories) { this.foodCalories = foodCalories; }

    public BigDecimal getFoodQuantity() { return foodQuantity; }
    public void setFoodQuantity(BigDecimal foodQuantity) { this.foodQuantity = foodQuantity; }

    public String getFoodUnit() { return foodUnit; }
    public void setFoodUnit(String foodUnit) { this.foodUnit = foodUnit; }

    @Override
    public String toString() {
        return "FoodLogEntry{" +
                "foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodBrand='" + foodBrand + '\'' +
                ", foodCalories=" + foodCalories +
                ", foodQuantity=" + foodQuantity +
                ", foodUnit='" + foodUnit + '\'' +
                '}';
    }
}