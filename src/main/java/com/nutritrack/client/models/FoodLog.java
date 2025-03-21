
package com.nutritrack.client.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "meal_logs")
public class FoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "account_uid", nullable = false)
    private String accountUid;

    @Column(name = "food_id", nullable = false)
    private String foodId;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "food_calories")
    private BigDecimal foodCalories;

    @Column(name = "food_unit")
    private String foodUnit;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Column(name = "log_date", nullable = false)
    private LocalDateTime logDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public FoodLog() {
    }

    // Getters and setters
    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String getMealId() {
        return foodId;
    }

    public void setMealId(String mealId) {
        this.foodId = mealId;
    }

    public String getMealName() {
        return foodName;
    }

    public void setMealName(String mealName) {
        this.foodName = mealName;
    }

    public BigDecimal getTotalCalories() {
        return foodCalories;
    }

    public void setTotalCalories(BigDecimal totalCalories) {
        this.foodCalories = totalCalories;
    }

    public String getFoodUnit(){
        return foodUnit;
    }

    public void setFoodUnit(String foodUnit){
        this.foodUnit = foodUnit;
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

    public LocalDateTime getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDateTime logDate) {
        this.logDate = logDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "FoodLog{" +
                "id=" + id +
                ", accountUid='" + accountUid + '\'' +
                ", foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodCalories=" + foodCalories +
                ", quantity=" + quantity +
                ", mealType=" + mealType +
                ", logDate=" + logDate +
                ", updatedAt=" + updatedAt +
                '}';
    }
}


