package com.jalch.flightsearch.view.viewmodel;

public class ItineraryPricingViewModel implements ViewModel {

    private final String price;
    private final String agentName;

    public ItineraryPricingViewModel(String price, String agentName) {
        this.price = price;
        this.agentName = agentName;
    }

    public String getPrice() {
        return price;
    }

    public String getAgentName() {
        return agentName;
    }

    @Override
    public boolean isValid() {
        return price != null && !price.equals(Double.NaN) && agentName != null;
    }

    public static ItineraryPricingViewModel invalid() {
        return new ItineraryPricingViewModel(null, null);
    }

}
