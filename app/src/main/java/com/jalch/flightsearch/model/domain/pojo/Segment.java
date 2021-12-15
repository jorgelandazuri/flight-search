
package com.jalch.flightsearch.model.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment {

    @SerializedName("Id")
    @Expose
    private long id;
    @SerializedName("OriginStation")
    @Expose
    private long originStation;
    @SerializedName("DestinationStation")
    @Expose
    private long destinationStation;
    @SerializedName("DepartureDateTime")
    @Expose
    private String departureDateTime;
    @SerializedName("ArrivalDateTime")
    @Expose
    private String arrivalDateTime;
    @SerializedName("Carrier")
    @Expose
    private long carrier;
    @SerializedName("OperatingCarrier")
    @Expose
    private long operatingCarrier;
    @SerializedName("Duration")
    @Expose
    private long duration;
    @SerializedName("FlightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("JourneyMode")
    @Expose
    private String journeyMode;
    @SerializedName("Directionality")
    @Expose
    private String directionality;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public long getCarrier() {
        return carrier;
    }

    public void setCarrier(long carrier) {
        this.carrier = carrier;
    }

    public long getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(long operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getJourneyMode() {
        return journeyMode;
    }

    public void setJourneyMode(String journeyMode) {
        this.journeyMode = journeyMode;
    }

    public String getDirectionality() {
        return directionality;
    }

    public void setDirectionality(String directionality) {
        this.directionality = directionality;
    }

}
