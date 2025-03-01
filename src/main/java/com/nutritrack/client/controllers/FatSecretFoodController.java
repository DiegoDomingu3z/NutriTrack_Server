package com.nutritrack.client.controllers;

import com.nutritrack.client.models.FatSecretFood;
import com.nutritrack.client.services.FatSecretFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FatSecretFoodController {

    private final FatSecretFoodService foodService;

    @Autowired
    public FatSecretFoodController(FatSecretFoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * GET /api/food/secure/barcode/find-by-id?barcode=XYZ
     * Returns the food details corresponding to the scanned barcode.
     */
    @GetMapping("/public/barcode/find-by-id")
    public ResponseEntity<?> GetFoodByBarcode(@RequestParam("barcode") String barcode) {
        if (barcode == null || barcode.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Barcode parameter is missing or empty");
        }
        try {
            FatSecretFood foodDetails = foodService.getFoodByBarcode(barcode);
            return ResponseEntity.ok(foodDetails);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}
