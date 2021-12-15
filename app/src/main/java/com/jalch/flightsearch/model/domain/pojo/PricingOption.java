
package com.jalch.flightsearch.model.domain.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PricingOption {

    @SerializedName("Agents")
    @Expose
    private List<Long> agents = new ArrayList<Long>();
    @SerializedName("QuoteAgeInMinutes")
    @Expose
    private long quoteAgeInMinutes;
    @SerializedName("Price")
    @Expose
    private double price;
    @SerializedName("DeeplinkUrl")
    @Expose
    private String deeplinkUrl;

    public List<Long> getAgents() {
        return agents;
    }

    public void setAgents(List<Long> agents) {
        this.agents = agents;
    }

    public long getQuoteAgeInMinutes() {
        return quoteAgeInMinutes;
    }

    public void setQuoteAgeInMinutes(long quoteAgeInMinutes) {
        this.quoteAgeInMinutes = quoteAgeInMinutes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }

}
