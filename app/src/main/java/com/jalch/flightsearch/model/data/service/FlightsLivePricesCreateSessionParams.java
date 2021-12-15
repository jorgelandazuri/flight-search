package com.jalch.flightsearch.model.data.service;

import retrofit2.Callback;

public class FlightsLivePricesCreateSessionParams {

    private String apiKey;
    private final String country;
    private final String currency;
    private final String locale;
    private final String originPlace;
    private final String destinationPlace;
    private final String outboundDate;
    private final String inboundDate;
    private final String cabinClass;
    private final String adults;
    private final String locationSchema;

    public FlightsLivePricesCreateSessionParams(String apiKey, String country, String currency,
                                                String locale, String originPlace, String destinationPlace,
                                                String outboundDate, String inboundDate, String cabinClass,
                                                String adults, String locationSchema) {
        this.apiKey = apiKey;
        this.country = country;
        this.currency = currency;
        this.locale = locale;
        this.originPlace = originPlace;
        this.destinationPlace = destinationPlace;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.cabinClass = cabinClass;
        this.adults = adults;
        this.locationSchema = locationSchema;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getLocale() {
        return locale;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public String getAdults() {
        return adults;
    }

    public String getLocationSchema() {
        return locationSchema;
    }

}