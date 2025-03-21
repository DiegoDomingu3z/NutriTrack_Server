package com.nutritrack.client.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.nutritrack.client.services.FoodService;


@RestController
@RequestMapping("/api/food")
public class FoodController {
    
    // private final FoodService foodService;

    //  @Autowired
    // public FoodController(FoodService foodService) {
    //     this.foodService = foodService;
    // }

    @PostMapping("public/log")
    public ResponseEntity<Void> logFood(){
        try {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error logging food: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    // @PostMapping('/public/search')
    // public ResponseEntity<?> search(@RequestParam("query") String query, HttpServletRequest request){
    //     try {
    //         if (query == null || query.trim().isEmpty()) {
    //             return ResponseEntity.badRequest().body("Barcode parameter is missing or empty");
    //         }
    //           return ResponseEntity.ok().build();
    //     } catch (Exception e) {
    //         System.err.println("Error searching food" + e.getMessage());
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }
}
