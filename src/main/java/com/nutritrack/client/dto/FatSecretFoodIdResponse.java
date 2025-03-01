package com.nutritrack.client.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FatSecretFoodIdResponse {
    @JsonProperty("food_id")
    private FoodId foodId;

    public String getFoodId() {
        return (foodId != null) ? foodId.getValue() : null;
    }

    public void setFoodId(FoodId foodId) {
        this.foodId = foodId;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FoodId {
        @JsonProperty("value")
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}


