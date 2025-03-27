package com.nutritrack.client.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nutritrack.client.documents.FoodLogDocument;
import com.nutritrack.client.documents.FoodLogEntry;
import com.nutritrack.client.documents.MealCategory;
import com.nutritrack.client.dto.FoodLogRequest;
import com.nutritrack.client.models.DailyLog;
import com.nutritrack.client.models.FoodLog;
import com.nutritrack.client.repositories.FoodLogDocumentRepository;

@Service
public class MongoFoodLogService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private final FoodLogDocumentRepository foodLogDocumentRepository;

    public MongoFoodLogService(FoodLogDocumentRepository foodLogDocumentRepository) {
        this.foodLogDocumentRepository = foodLogDocumentRepository;
    }

    public FoodLogDocument addMealToDocument(FoodLogRequest request, DailyLog dailyLog, FoodLog foodLog) {
        // Create a custom dailyLogId (e.g., "user123_2025-03-20")
        Integer dailyLogId = dailyLog.getId();
        String accountId = dailyLog.getAccountUid();
        // Retrieve the existing document, if any
        FoodLogDocument document = foodLogDocumentRepository.findByDailyLogId(dailyLogId);
        if (document == null) {
            document = new FoodLogDocument();
            document.setDailyLogId(dailyLogId);
            document.setAccountId(accountId);
            document.setCreated(request.getLogDate());
        }
        document.setUpdated(request.getLogDate());
        
        // Create a FoodLogEntry from FoodLog data
        FoodLogEntry entry = new FoodLogEntry();
        entry.setId(foodLog.getId());
        entry.setFoodId(foodLog.getFoodId());
        entry.setFoodName(foodLog.getFoodName());
        entry.setFoodBrand(foodLog.getFoodBrand());
        entry.setFoodQuantity(foodLog.getQuantity().toString()); // converting as needed
        entry.setFoodUnit(foodLog.getFoodUnit());
        entry.setCalories(foodLog.getFoodCalories());
        // Add the entry to the correct MealCategory based on the meal type
        switch (foodLog.getMealType()) {
            case breakfast -> document.setBreakfast(addMealEntry(document.getBreakfast(), entry));
            case lunch -> document.setLunch(addMealEntry(document.getLunch(), entry));
            case dinner -> document.setDinner(addMealEntry(document.getDinner(), entry));
            case snack -> document.setSnacks(addMealEntry(document.getSnacks(), entry));
            default -> {
            }
        }
        // Handle default or error if needed
        
        return foodLogDocumentRepository.save(document);
    }
    
    private MealCategory addMealEntry(MealCategory category, FoodLogEntry entry) {
        
        if (category == null) {
            category = new MealCategory();
        }
        if (category.getMeals() == null) {
            category.setMeals(new ArrayList<>());
        }
        category.getMeals().add(entry);
        BigDecimal calories = (category.getTotalCalories() != null) 
        ? category.getTotalCalories() 
        : BigDecimal.ZERO;
        calories = calories.add(entry.getCalories());
        category.setTotalCalories(calories);
        // Optionally update totalCalories, etc.
        return category;
    }


    public List<FoodLogDocument> getLogs(Date startDate, Date endDate, String id){
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    
        String formattedStart = sdf.format(startDate);
        String formattedEnd = sdf.format(endDate);
        
        Query query = new Query();
        query.addCriteria(Criteria.where("created").gte(formattedStart).lt(formattedEnd));
        
        query.addCriteria(Criteria.where("accountId").is(id));
        return mongoTemplate.find(query, FoodLogDocument.class);

    }
}