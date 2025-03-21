package com.nutritrack.client.documents;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.nutritrack.client.models.FoodLogEntry;
import com.nutritrack.client.models.MealType;

@Document(collection = "food_log")
public class FoodLogDocument {

    @Id
    private String id; 
    private String accountUid;
    private String mealId;
    private String mealName;
    private BigDecimal totalCalories;
    private BigDecimal quantity;
    private MealType mealType;
    private LocalDateTime logDate;
    private LocalDateTime updatedAt;

    @Field("breakfastLog")
    private List<FoodLogEntry> breakfastLog;
    @Field("lunchLog")
    private List<FoodLogEntry> lunchLog;
    @Field("dinnerLog")
    private List<FoodLogEntry> dinnerLog;
    @Field("snackLog")
    private List<FoodLogEntry> snackLog;

    // Constructors
    public FoodLogDocument() {
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public BigDecimal getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(BigDecimal totalCalories) {
        this.totalCalories = totalCalories;
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

    public List<FoodLogEntry> getBreakfastLog() {
        return breakfastLog;
    }

    public void setBreakfastLog(List<FoodLogEntry> breakfastLog) {
        this.breakfastLog = breakfastLog;
    }

    public List<FoodLogEntry> getLunchLog() {
        return lunchLog;
    }

    public void setLunchLog(List<FoodLogEntry> lunchLog) {
        this.lunchLog = lunchLog;
    }

    public List<FoodLogEntry> getDinnerLog() {
        return dinnerLog;
    }

    public void setDinnerLog(List<FoodLogEntry> dinnerLog) {
        this.dinnerLog = dinnerLog;
    }

    public List<FoodLogEntry> getSnackLog() {
        return snackLog;
    }

    public void setSnackLog(List<FoodLogEntry> snackLog) {
        this.snackLog = snackLog;
    }

    @Override
    public String toString() {
        return "FoodLogDocument{" +
                "id='" + id + '\'' +
                ", accountUid='" + accountUid + '\'' +
                ", mealId='" + mealId + '\'' +
                ", mealName='" + mealName + '\'' +
                ", totalCalories=" + totalCalories +
                ", quantity=" + quantity +
                ", mealType=" + mealType +
                ", logDate=" + logDate +
                ", updatedAt=" + updatedAt +
                ", breakfastLog=" + breakfastLog +
                ", lunchLog=" + lunchLog +
                ", dinnerLog=" + dinnerLog +
                ", snackLog=" + snackLog +
                '}';
    }
}
