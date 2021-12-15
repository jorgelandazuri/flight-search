
package com.jalch.flightsearch.model.domain.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightsLivePrices {

    @SerializedName("SessionKey")
    @Expose
    private String sessionKey;
    @SerializedName("Query")
    @Expose
    private Query query;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Itineraries")
    @Expose
    private List<Itinerary> itineraries = new ArrayList<Itinerary>();
    @SerializedName("Legs")
    @Expose
    private List<Leg> legs = new ArrayList<Leg>();
    @SerializedName("Segments")
    @Expose
    private List<Segment> segments = new ArrayList<Segment>();
    @SerializedName("Carriers")
    @Expose
    private List<Carrier> carriers = new ArrayList<Carrier>();
    @SerializedName("Agents")
    @Expose
    private List<Agent> agents = new ArrayList<Agent>();
    @SerializedName("Places")
    @Expose
    private List<Place> places = new ArrayList<Place>();
    @SerializedName("Currencies")
    @Expose
    private List<Currency> currencies = new ArrayList<Currency>();
    @SerializedName("ServiceQuery")
    @Expose
    private ServiceQuery serviceQuery;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public ServiceQuery getServiceQuery() {
        return serviceQuery;
    }

    public void setServiceQuery(ServiceQuery serviceQuery) {
        this.serviceQuery = serviceQuery;
    }

}
