package com.jalch.flightsearch.model.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionResponseBody {

    @SerializedName("ServiceQuery")
    @Expose
    private ServiceQuery serviceQuery;

    public ServiceQuery getServiceQuery() {
        return serviceQuery;
    }

    public void setServiceQuery(ServiceQuery serviceQuery) {
        this.serviceQuery = serviceQuery;
    }
}
