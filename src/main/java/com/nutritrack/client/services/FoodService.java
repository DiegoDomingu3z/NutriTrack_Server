package com.nutritrack.client.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nutritrack.client.dto.FoodLogRequest;
import com.nutritrack.client.models.DailyLog;
import com.nutritrack.client.models.FoodLog;
import com.nutritrack.client.repositories.DailyLogRepository;
import com.nutritrack.client.repositories.FoodLogRepository;

@Service
public class FoodService {


    private final DailyLogRepository dailyLogRepository;

    private final FoodLogRepository foodLogRepository;

    private final MongoFoodLogService mongoFoodLogService;

    public FoodService(DailyLogRepository dailyLogRepository, FoodLogRepository foodLogRepository, MongoFoodLogService mongoFoodLogService) {
        this.dailyLogRepository = dailyLogRepository;
        this.foodLogRepository = foodLogRepository;
        this.mongoFoodLogService = mongoFoodLogService;
    }


    public DailyLog getOrCreateDailyLog(FoodLogRequest data, String userId){
        Optional<DailyLog> dailyLog = dailyLogRepository.findByAccountUidAndLogDate(userId, data.getLogDate());
        if (dailyLog.isPresent()){
            DailyLog log = dailyLog.get();
            log.setUpdateeOn(data.getUpdatedAt());
            return log;
        }
        else {
            DailyLog newLog = new DailyLog();
            newLog.setAccountUid(userId);
            // Convert ZonedDateTime to LocalDate
            newLog.setLogDate(data.getLogDate());
            newLog.setUpdateeOn(data.getUpdatedAt());
            dailyLogRepository.save(newLog);
            return newLog;
        }
    }

    public FoodLog saveMeal(FoodLogRequest data, String userId){
        DailyLog dailyLog = getOrCreateDailyLog(data, userId);

        FoodLog foodLog = new FoodLog();
        foodLog.setDailyLog(dailyLog); // Associate the daily log (and its generated ID) with the FoodLog entry
        foodLog.setAccountUid(userId);
        foodLog.setFoodId(data.getFoodId());
        foodLog.setFoodName(data.getFoodName());
        foodLog.setFoodCalories(data.getCalories()); // convert to BigDecimal as needed
        foodLog.setFoodUnit(data.getFoodUnit());
        foodLog.setFoodBrand(data.getFoodBrand());
        foodLog.setQuantity(data.getQuantity());
        foodLog.setMealType(data.getMealType());
        foodLog.setLogDate(data.getLogDate());      // Assuming this is a LocalDate (or converted accordingly)
        foodLog.setUpdatedAt(data.getUpdatedAt());  // Same as above
        // Assuming you have an instance of MongoFoodLogService injected
        FoodLog log = foodLogRepository.save(foodLog);
        mongoFoodLogService.addMealToDocument(data, dailyLog, log);
        return log;

    }

 

   

    
    
}
