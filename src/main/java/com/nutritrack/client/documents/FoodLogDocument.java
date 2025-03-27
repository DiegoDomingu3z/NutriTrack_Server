package com.nutritrack.client.documents;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "food_log")
public class FoodLogDocument {

    @Id
    private String id;              // MongoDB's ObjectId as String
    private Integer dailyLogId; 
    private String accountId;   
    private OffsetDateTime created;  
    private OffsetDateTime updated;
    
    private MealCategory breakfast;
    private MealCategory lunch;
    private MealCategory dinner;
    private MealCategory snacks;

    public FoodLogDocument() {
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getDailyLogId() {
        return dailyLogId;
    }
    public void setDailyLogId(Integer dailyLogId) {
        this.dailyLogId = dailyLogId;
    }
    public String getAccoundId(){
        return accountId;
    }
    public void setAccountId(String accountId){
        this.accountId = accountId;
    }
    public OffsetDateTime getCreated() {
        return created;
    }
    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }
    public OffsetDateTime getUpdated() {
        return updated;
    }
    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }
    public MealCategory getBreakfast() {
        return breakfast;
    }
    public void setBreakfast(MealCategory breakfast) {
        this.breakfast = breakfast;
    }
    public MealCategory getLunch() {
        return lunch;
    }
    public void setLunch(MealCategory lunch) {
        this.lunch = lunch;
    }
    public MealCategory getDinner() {
        return dinner;
    }
    public void setDinner(MealCategory dinner) {
        this.dinner = dinner;
    }
    public MealCategory getSnacks() {
        return snacks;
    }
    public void setSnacks(MealCategory snacks) {
        this.snacks = snacks;
    }

    @Override
    public String toString() {
        return "DailyFoodLogDocument{" +
                "id='" + id + '\'' +
                ", dailyLogId='" + dailyLogId + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", breakfast=" + breakfast +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                ", snacks=" + snacks +
                '}';
    }
}
