package com.jalch.flightsearch.model.data.repo;

public interface DataParametrizedRepository<D,P> {
    D load(P params);
}
