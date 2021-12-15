
package com.jalch.flightsearch.model.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Locale")
    @Expose
    private String locale;
    @SerializedName("Adults")
    @Expose
    private long adults;
    @SerializedName("Children")
    @Expose
    private long children;
    @SerializedName("Infants")
    @Expose
    private long infants;
    @SerializedName("OriginPlace")
    @Expose
    private String originPlace;
    @SerializedName("DestinationPlace")
    @Expose
    private String destinationPlace;
    @SerializedName("OutboundDate")
    @Expose
    private String outboundDate;
    @SerializedName("InboundDate")
    @Expose
    private String inboundDate;
    @SerializedName("LocationSchema")
    @Expose
    private String locationSchema;
    @SerializedName("CabinClass")
    @Expose
    private String cabinClass;
    @SerializedName("GroupPricing")
    @Expose
    private boolean groupPricing;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public long getAdults() {
        return adults;
    }

    public void setAdults(long adults) {
        this.adults = adults;
    }

    public long getChildren() {
        return children;
    }

    public void setChildren(long children) {
        this.children = children;
    }

    public long getInfants() {
        return infants;
    }

    public void setInfants(long infants) {
        this.infants = infants;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public String getLocationSchema() {
        return locationSchema;
    }

    public void setLocationSchema(String locationSchema) {
        this.locationSchema = locationSchema;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public boolean isGroupPricing() {
        return groupPricing;
    }

    public void setGroupPricing(boolean groupPricing) {
        this.groupPricing = groupPricing;
    }

}
