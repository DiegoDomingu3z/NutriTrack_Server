package com.nutritrack.client.controllers;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutritrack.client.documents.FoodLogDocument;
import com.nutritrack.client.dto.FoodLogRequest;
import com.nutritrack.client.services.FoodService;
import com.nutritrack.client.services.MongoFoodLogService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/food")
public class FoodController {
    
    private final FoodService foodService;

    private final MongoFoodLogService mongoFoodService;

     @Autowired
    public FoodController(FoodService foodService, MongoFoodLogService mongoFoodLogService) {
        this.foodService = foodService;
        this.mongoFoodService = mongoFoodLogService;
    }

    @PostMapping("/secure/save-log")
    public ResponseEntity<String> logFood (HttpServletRequest httpRequest, @RequestBody FoodLogRequest request){
        try {
            String uid = (String) httpRequest.getAttribute("uid");
            request.setAccountId(uid);
            foodService.saveMeal(request, uid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error logging food: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    @GetMapping("/secure/log")
   public ResponseEntity<?> logs(
            HttpServletRequest httpRequest,
            // Accept a start date query parameter, e.g. /secure/log?st=2025-03-26T06:00:00Z
            @RequestParam("st") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            // Optionally, accept an end date parameter
            @RequestParam(value = "et", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        try {
            String id = (String) httpRequest.getAttribute("uid");
            if (endDate == null) {
                endDate = startDate.plusDays(1);
            }
            startDate = startDate.minusDays(1);
            endDate = endDate.plusDays(1);
            Date start = Date.from(startDate.toInstant());
            Date end = Date.from(endDate.toInstant());
            List<FoodLogDocument> logs = mongoFoodService.getLogs(start, end, id);
              return ResponseEntity.ok(logs);
        } catch (Exception e) {
            System.err.println("Error searching food" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
