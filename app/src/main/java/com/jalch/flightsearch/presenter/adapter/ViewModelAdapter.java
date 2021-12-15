package com.jalch.flightsearch.presenter.adapter;

public interface ViewModelAdapter<V, I> {
    V adapt(I input);
}
