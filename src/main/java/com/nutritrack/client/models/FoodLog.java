package com.nutritrack.client.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_log", indexes = {
    @Index(name = "idx_log_date", columnList = "log_date"),
    @Index(name = "idx_food_id", columnList = "food_id"),
    @Index(name = "idx_daily_log_id", columnList = "daily_log_id")
})
public class FoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Foreign key relationship to daily_log
    @ManyToOne
    @JoinColumn(name = "daily_log_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_food_log_daily_log"))
    private DailyLog dailyLog;

    @Column(name = "accountId", nullable = false)
    private String accountUid;

    @Column(name = "food_id", nullable = false)
    private String foodId;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "food_calories", precision = 5, scale = 2, nullable = false)
    private BigDecimal foodCalories;

    @Column(name = "food_unit", nullable = false)
    private String foodUnit;

    @Column(name = "food_brand", length = 244)
    private String foodBrand;

    @Column(name = "quantity", precision = 5, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Column(name = "log_date", nullable = false)
    private OffsetDateTime logDate;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public FoodLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DailyLog getDailyLog() {
        return dailyLog;
    }

    public void setDailyLog(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
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

    public BigDecimal getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(BigDecimal foodCalories) {
        this.foodCalories = foodCalories;
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
        return "FoodLog{" +
                "id=" + id +
                ", dailyLogId=" + (dailyLog != null ? dailyLog.getId() : null) +
                ", accountUid='" + accountUid + '\'' +
                ", foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodCalories=" + foodCalories +
                ", foodUnit='" + foodUnit + '\'' +
                ", foodBrand='" + foodBrand + '\'' +
                ", quantity=" + quantity +
                ", mealType=" + mealType +
                ", logDate=" + logDate +
                ", updatedAt=" + updatedAt +
                '}';
    }
}