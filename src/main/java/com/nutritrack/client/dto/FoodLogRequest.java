package com.nutritrack.client.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.nutritrack.client.models.MealType;

public class FoodLogRequest {

    private String accountId;
    private String foodId;
    private String foodName;
    private String foodUnit;
    private String foodBrand;
    private String servingId;
    private BigDecimal calories;
    private BigDecimal quantity;
    private MealType mealType;
    private OffsetDateTime logDate;
    private OffsetDateTime updatedAt;

    // No-argument constructor
    public FoodLogRequest() {}

    // All-arguments constructor
    public FoodLogRequest(String accountId, String foodId, String foodName, String foodUnit,
                          String foodBrand, String servingId, BigDecimal calories, BigDecimal quantity,
                          MealType mealType, OffsetDateTime logDate, OffsetDateTime updatedAt) {
        this.accountId = accountId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodUnit = foodUnit;
        this.foodBrand = foodBrand;
        this.servingId = servingId;
        this.calories = calories;
        this.quantity = quantity;
        this.mealType = mealType;
        this.logDate = logDate;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
    
    public String getFoodUnit() {
        return foodUnit;
    }
    public void setFoodUnit(String foodUnit) {
        this.foodUnit = foodUnit;
    }
    
    public String getFoodBrand() {
        return foodBrand;
    }
    public void setFoodBrand(String foodBrand) {
        this.foodBrand = foodBrand;
    }

    public String getServingId(){
        return servingId;
    }
    
    public void setServingId(String servingId){
        this.servingId = servingId;
    }
    public BigDecimal getCalories() {
        return calories;
    }
    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }
    
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public MealType getMealType() {
        return mealType;
    }
    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
    
    public OffsetDateTime getLogDate() {
        return logDate;
    }
    public void setLogDate(OffsetDateTime logDate) {
        this.logDate = logDate;
    }
    
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "FoodLogRequest{" +
                "accountId='" + accountId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodUnit='" + foodUnit + '\'' +
                ", foodBrand='" + foodBrand + '\'' +
                ", foodCalories=" + calories +
                ", quantity=" + quantity +
                ", mealType=" + mealType +
                ", logDate=" + logDate +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
