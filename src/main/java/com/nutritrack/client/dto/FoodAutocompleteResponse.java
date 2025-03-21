package com.nutritrack.client.dto;

import java.util.List;

public class FoodAutocompleteResponse {
    private Suggestions suggestions;

    public Suggestions getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Suggestions suggestions) {
        this.suggestions = suggestions;
    }

    public static class Suggestions {
        private List<String> suggestion;

        public List<String> getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(List<String> suggestion) {
            this.suggestion = suggestion;
        }
    }
}
