package com.jalch.flightsearch.view.viewmodel;

public class LegItineraryViewModel implements ViewModel {

    private final String carrierLogo;
    private final String arrivalAndDepartureTimes;
    private final String routeAndCarrier;
    private final int stopsNumber;
    private final String duration;

    public LegItineraryViewModel(String carrierLogo, String arrivalAndDepartureTimes, String routeAndCarrier, int stopsNumber, String duration) {
        this.carrierLogo = carrierLogo;
        this.arrivalAndDepartureTimes = arrivalAndDepartureTimes;
        this.routeAndCarrier = routeAndCarrier;
        this.stopsNumber = stopsNumber;
        this.duration = duration;
    }

    public String getCarrierLogo() {
        return carrierLogo;
    }

    public String getArrivalAndDepartureTimes() {
        return arrivalAndDepartureTimes;
    }

    public String getRouteAndCarrier() {
        return routeAndCarrier;
    }

    public int getStopsNumber() {
        return stopsNumber;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public boolean isValid() {
        return arrivalAndDepartureTimes != null && routeAndCarrier != null && stopsNumber != -1;
    }

    public static LegItineraryViewModel invalid() {
        return new LegItineraryViewModel(null, null, null, -1, null);
    }
}
