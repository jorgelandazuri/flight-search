
package com.jalch.flightsearch.model.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agent {

    @SerializedName("Id")
    @Expose
    private long id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("OptimisedForMobile")
    @Expose
    private boolean optimisedForMobile;
    @SerializedName("Type")
    @Expose
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOptimisedForMobile() {
        return optimisedForMobile;
    }

    public void setOptimisedForMobile(boolean optimisedForMobile) {
        this.optimisedForMobile = optimisedForMobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
