
package com.jalch.flightsearch.model.domain.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leg {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("SegmentIds")
    @Expose
    private List<Long> segmentIds = new ArrayList<Long>();
    @SerializedName("OriginStation")
    @Expose
    private long originStation;
    @SerializedName("DestinationStation")
    @Expose
    private long destinationStation;
    @SerializedName("Departure")
    @Expose
    private String departure;
    @SerializedName("Arrival")
    @Expose
    private String arrival;
    @SerializedName("Duration")
    @Expose
    private int duration;
    @SerializedName("JourneyMode")
    @Expose
    private String journeyMode;
    @SerializedName("Stops")
    @Expose
    private List<Long> stops = new ArrayList<Long>();
    @SerializedName("Carriers")
    @Expose
    private List<Long> carriers = new ArrayList<Long>();
    @SerializedName("OperatingCarriers")
    @Expose
    private List<Long> operatingCarriers = new ArrayList<Long>();
    @SerializedName("Directionality")
    @Expose
    private String directionality;
    @SerializedName("FlightNumbers")
    @Expose
    private List<FlightNumber> flightNumbers = new ArrayList<FlightNumber>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Long> getSegmentIds() {
        return segmentIds;
    }

    public void setSegmentIds(List<Long> segmentIds) {
        this.segmentIds = segmentIds;
    }

    public long getOriginStation() {
        return originStation;
    }

    public void setOriginStation(long originStation) {
        this.originStation = originStation;
    }

    public long getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(long destinationStation) {
        this.destinationStation = destinationStation;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getJourneyMode() {
        return journeyMode;
    }

    public void setJourneyMode(String journeyMode) {
        this.journeyMode = journeyMode;
    }

    public List<Long> getStops() {
        return stops;
    }

    public void setStops(List<Long> stops) {
        this.stops = stops;
    }

    public List<Long> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Long> carriers) {
        this.carriers = carriers;
    }

    public List<Long> getOperatingCarriers() {
        return operatingCarriers;
    }

    public void setOperatingCarriers(List<Long> operatingCarriers) {
        this.operatingCarriers = operatingCarriers;
    }

    public String getDirectionality() {
        return directionality;
    }

    public void setDirectionality(String directionality) {
        this.directionality = directionality;
    }

    public List<FlightNumber> getFlightNumbers() {
        return flightNumbers;
    }

    public void setFlightNumbers(List<FlightNumber> flightNumbers) {
        this.flightNumbers = flightNumbers;
    }

}
