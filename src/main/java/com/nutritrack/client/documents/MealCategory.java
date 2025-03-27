package com.nutritrack.client.documents;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class MealCategory {
    
    private BigDecimal totalCalories;
    
    // Using a custom field mapping so that JSON uses "Meals"
    @Field("Meals")
    private List<FoodLogEntry> meals;

    public MealCategory() {
    }

    // Getters and Setters
    public BigDecimal getTotalCalories() {
        return totalCalories;
    }
    public void setTotalCalories(BigDecimal totalCalories) {
        this.totalCalories = totalCalories;
    }
    public List<FoodLogEntry> getMeals() {
        return meals;
    }
    public void setMeals(List<FoodLogEntry> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "MealCategory{" +
                "totalCalories=" + totalCalories +
                ", meals=" + meals +
                '}';
    }
}
