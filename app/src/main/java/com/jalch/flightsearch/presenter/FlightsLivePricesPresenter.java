package com.jalch.flightsearch.presenter;

import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;

public interface FlightsLivePricesPresenter {
    void startSearch(FlightsLivePricesCreateSessionParams searchParams);
    void onStop();
}
