package com.nutritrack.client.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Model class for FatSecret food search response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FatSecretFoodSearchResponse {

    @JsonProperty("foods")
    private FoodsSearch foods;

    public FoodsSearch getFoods() {
        return foods;
    }

    public void setFoods(FoodsSearch foods) {
        this.foods = foods;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoodsSearch {
        @JsonProperty("max_results")
        private String maxResults;
        @JsonProperty("total_results")
        private String totalResults;
        @JsonProperty("page_number")
        private String pageNumber;
        @JsonProperty("food")
        private List<Food> food;

        public String getMaxResults() {
            return maxResults;
        }

        public void setMaxResults(String maxResults) {
            this.maxResults = maxResults;
        }

        public String getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(String totalResults) {
            this.totalResults = totalResults;
        }

        public String getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(String pageNumber) {
            this.pageNumber = pageNumber;
        }

        public List<Food> getFood() {
            return food;
        }

        public void setFood(List<Food> food) {
            this.food = food;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Food {
        @JsonProperty("food_id")
        private String foodId;
        @JsonProperty("food_name")
        private String foodName;
        @JsonProperty("brand_name")
        private String brandName;
        @JsonProperty("food_type")
        private String foodType;
        @JsonProperty("food_url")
        private String foodUrl;
        @JsonProperty("food_description")
        private String foodDescription;

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

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getFoodType() {
            return foodType;
        }

        public void setFoodType(String foodType) {
            this.foodType = foodType;
        }

        public String getFoodUrl() {
            return foodUrl;
        }

        public void setFoodUrl(String foodUrl) {
            this.foodUrl = foodUrl;
        }

        public String getFoodDescription() {
            return foodDescription;
        }

        public void setFoodDescription(String foodDescription) {
            this.foodDescription = foodDescription;
        }
    }
}