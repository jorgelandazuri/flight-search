package com.jalch.flightsearch.model.data.service;

public class FlightsLivePricesPollParams {
    private String apiKey;
    private String sessionLocation;

    public FlightsLivePricesPollParams(String apiKey, String sessionLocation) {
        this.apiKey = apiKey;
        this.sessionLocation = sessionLocation;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSessionLocation() {
        return sessionLocation;
    }

    public void setSessionLocation(String sessionLocation) {
        this.sessionLocation = sessionLocation;
    }
}
