package com.jalch.flightsearch.model.data.repo.liveprices;

import com.jalch.flightsearch.model.data.repo.DataParametrizedRepository;
import com.jalch.flightsearch.model.data.service.FlightLivePricesAPIService;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesPollParams;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;

import io.reactivex.Observable;

public class FlightsLivePricesRepository implements DataParametrizedRepository<Observable<FlightsLivePrices>, FlightsLivePricesPollParams> {
    private final FlightLivePricesAPIService service;

    public FlightsLivePricesRepository(FlightLivePricesAPIService service) {
        this.service = service;
    }

    @Override
    public Observable<FlightsLivePrices> load(FlightsLivePricesPollParams params) {
        return service.getFlightsLivePrices(params.getSessionLocation(), params.getApiKey());
    }
}
