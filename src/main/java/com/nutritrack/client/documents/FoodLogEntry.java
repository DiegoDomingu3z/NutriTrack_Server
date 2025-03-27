package com.nutritrack.client.documents;

import java.math.BigDecimal;

public class FoodLogEntry {
    
    private Integer id;
    private String foodId;
    private String foodName;
    private String foodBrand;
    private String foodQuantity;
    private String foodUnit;
    private BigDecimal calories;

    public FoodLogEntry() {
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }
    public String getFoodId() {
        return foodId;
    }
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getFoodBrand() {
        return foodBrand;
    }
    public void setFoodBrand(String foodBrand) {
        this.foodBrand = foodBrand;
    }
    public String getFoodQuantity() {
        return foodQuantity;
    }
    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }
    public String getFoodUnit() {
        return foodUnit;
    }
    public void setFoodUnit(String foodUnit) {
        this.foodUnit = foodUnit;
    }
    public BigDecimal getCalories(){
        return calories;
    }
    public void setCalories(BigDecimal calories){
        this.calories = calories;
    }



    @Override
    public String toString() {
        return "FoodLogEntry{" +
                "foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodBrand='" + foodBrand + '\'' +
                ", foodQuantity='" + foodQuantity + '\'' +
                ", foodUnit='" + foodUnit + '\'' +
                '}';
    }
}
