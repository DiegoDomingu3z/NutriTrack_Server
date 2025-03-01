package com.nutritrack.client.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FatSecretFood {

    @JsonProperty("food")
    private Food food;

    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Food {
        @JsonProperty("food_id")
        private String foodId;
        @JsonProperty("food_name")
        private String foodName;
        @JsonProperty("food_type")
        private String foodType;
        @JsonProperty("food_url")
        private String foodUrl;
        @JsonProperty("food_images")
        private FoodImages foodImages;
        @JsonProperty("servings")
        private Servings servings;

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
        public FoodImages getFoodImages() {
            return foodImages;
        }
        public void setFoodImages(FoodImages foodImages) {
            this.foodImages = foodImages;
        }
        public Servings getServings() {
            return servings;
        }
        public void setServings(Servings servings) {
            this.servings = servings;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoodImages {
        @JsonProperty("food_image")
        private List<FoodImage> foodImage;

        public List<FoodImage> getFoodImage() {
            return foodImage;
        }
        public void setFoodImage(List<FoodImage> foodImage) {
            this.foodImage = foodImage;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoodImage {
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("image_type")
        private String imageType;

        public String getImageUrl() {
            return imageUrl;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
        public String getImageType() {
            return imageType;
        }
        public void setImageType(String imageType) {
            this.imageType = imageType;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Servings {
        @JsonProperty("serving")
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        private List<Serving> serving;

        public List<Serving> getServing() {
            return serving;
        }
        public void setServing(List<Serving> serving) {
            this.serving = serving;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Serving {
        @JsonProperty("serving_id")
        private String servingId;
        @JsonProperty("serving_description")
        private String servingDescription;
        @JsonProperty("serving_url")
        private String servingUrl;
        @JsonProperty("metric_serving_amount")
        private String metricServingAmount;
        @JsonProperty("metric_serving_unit")
        private String metricServingUnit;
        @JsonProperty("number_of_units")
        private String numberOfUnits;
        @JsonProperty("measurement_description")
        private String measurementDescription;
        @JsonProperty("is_default")
        private String isDefault;
        @JsonProperty("calories")
        private String calories;
        @JsonProperty("carbohydrate")
        private String carbohydrate;
        @JsonProperty("protein")
        private String protein;
        @JsonProperty("fat")
        private String fat;
        @JsonProperty("saturated_fat")
        private String saturatedFat;
        @JsonProperty("polyunsaturated_fat")
        private String polyunsaturatedFat;
        @JsonProperty("monounsaturated_fat")
        private String monounsaturatedFat;
        @JsonProperty("cholesterol")
        private String cholesterol;
        @JsonProperty("sodium")
        private String sodium;
        @JsonProperty("potassium")
        private String potassium;
        @JsonProperty("fiber")
        private String fiber;
        @JsonProperty("sugar")
        private String sugar;
        @JsonProperty("vitamin_a")
        private String vitaminA;
        @JsonProperty("vitamin_c")
        private String vitaminC;
        @JsonProperty("calcium")
        private String calcium;
        @JsonProperty("iron")
        private String iron;

        // Getters and setters for all fields...
        public String getServingId() {
            return servingId;
        }
        public void setServingId(String servingId) {
            this.servingId = servingId;
        }
        public String getServingDescription() {
            return servingDescription;
        }
        public void setServingDescription(String servingDescription) {
            this.servingDescription = servingDescription;
        }
        public String getServingUrl() {
            return servingUrl;
        }
        public void setServingUrl(String servingUrl) {
            this.servingUrl = servingUrl;
        }
        public String getMetricServingAmount() {
            return metricServingAmount;
        }
        public void setMetricServingAmount(String metricServingAmount) {
            this.metricServingAmount = metricServingAmount;
        }
        public String getMetricServingUnit() {
            return metricServingUnit;
        }
        public void setMetricServingUnit(String metricServingUnit) {
            this.metricServingUnit = metricServingUnit;
        }
        public String getNumberOfUnits() {
            return numberOfUnits;
        }
        public void setNumberOfUnits(String numberOfUnits) {
            this.numberOfUnits = numberOfUnits;
        }
        public String getMeasurementDescription() {
            return measurementDescription;
        }
        public void setMeasurementDescription(String measurementDescription) {
            this.measurementDescription = measurementDescription;
        }
        public String getIsDefault() {
            return isDefault;
        }
        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }
        public String getCalories() {
            return calories;
        }
        public void setCalories(String calories) {
            this.calories = calories;
        }
        public String getCarbohydrate() {
            return carbohydrate;
        }
        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }
        public String getProtein() {
            return protein;
        }
        public void setProtein(String protein) {
            this.protein = protein;
        }
        public String getFat() {
            return fat;
        }
        public void setFat(String fat) {
            this.fat = fat;
        }
        public String getSaturatedFat() {
            return saturatedFat;
        }
        public void setSaturatedFat(String saturatedFat) {
            this.saturatedFat = saturatedFat;
        }
        public String getPolyunsaturatedFat() {
            return polyunsaturatedFat;
        }
        public void setPolyunsaturatedFat(String polyunsaturatedFat) {
            this.polyunsaturatedFat = polyunsaturatedFat;
        }
        public String getMonounsaturatedFat() {
            return monounsaturatedFat;
        }
        public void setMonounsaturatedFat(String monounsaturatedFat) {
            this.monounsaturatedFat = monounsaturatedFat;
        }
        public String getCholesterol() {
            return cholesterol;
        }
        public void setCholesterol(String cholesterol) {
            this.cholesterol = cholesterol;
        }
        public String getSodium() {
            return sodium;
        }
        public void setSodium(String sodium) {
            this.sodium = sodium;
        }
        public String getPotassium() {
            return potassium;
        }
        public void setPotassium(String potassium) {
            this.potassium = potassium;
        }
        public String getFiber() {
            return fiber;
        }
        public void setFiber(String fiber) {
            this.fiber = fiber;
        }
        public String getSugar() {
            return sugar;
        }
        public void setSugar(String sugar) {
            this.sugar = sugar;
        }
        public String getVitaminA() {
            return vitaminA;
        }
        public void setVitaminA(String vitaminA) {
            this.vitaminA = vitaminA;
        }
        public String getVitaminC() {
            return vitaminC;
        }
        public void setVitaminC(String vitaminC) {
            this.vitaminC = vitaminC;
        }
        public String getCalcium() {
            return calcium;
        }
        public void setCalcium(String calcium) {
            this.calcium = calcium;
        }
        public String getIron() {
            return iron;
        }
        public void setIron(String iron) {
            this.iron = iron;
        }
    }
}
