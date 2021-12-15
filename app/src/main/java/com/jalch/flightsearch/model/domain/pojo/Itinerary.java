
package com.jalch.flightsearch.model.domain.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Itinerary {

    @SerializedName("OutboundLegId")
    @Expose
    private String outboundLegId;
    @SerializedName("InboundLegId")
    @Expose
    private String inboundLegId;
    @SerializedName("PricingOptions")
    @Expose
    private List<PricingOption> pricingOptions = new ArrayList<PricingOption>();
    @SerializedName("BookingDetailsLink")
    @Expose
    private BookingDetailsLink bookingDetailsLink;

    public String getOutboundLegId() {
        return outboundLegId;
    }

    public void setOutboundLegId(String outboundLegId) {
        this.outboundLegId = outboundLegId;
    }

    public String getInboundLegId() {
        return inboundLegId;
    }

    public void setInboundLegId(String inboundLegId) {
        this.inboundLegId = inboundLegId;
    }

    public List<PricingOption> getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(List<PricingOption> pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public BookingDetailsLink getBookingDetailsLink() {
        return bookingDetailsLink;
    }

    public void setBookingDetailsLink(BookingDetailsLink bookingDetailsLink) {
        this.bookingDetailsLink = bookingDetailsLink;
    }

}
