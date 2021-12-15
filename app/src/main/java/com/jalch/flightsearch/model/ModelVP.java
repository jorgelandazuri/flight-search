package com.jalch.flightsearch.model;

public interface ModelVP<D, P> {
    D execute(P params);
}
