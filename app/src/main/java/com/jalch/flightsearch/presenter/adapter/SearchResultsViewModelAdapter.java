package com.jalch.flightsearch.presenter.adapter;

import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.Itinerary;
import com.jalch.flightsearch.view.viewmodel.ItineraryCardViewModel;
import com.jalch.flightsearch.view.viewmodel.SearchResultsViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultsViewModelAdapter implements ViewModelAdapter<SearchResultsViewModel, FlightsLivePrices> {

    private final FlightsLivePrices dataSource;
    private final ItineraryCardViewModelAdapter adapter;

    public SearchResultsViewModelAdapter(FlightsLivePrices dataSource, ItineraryCardViewModelAdapter adapter) {
        this.dataSource = dataSource;
        this.adapter = adapter;
    }

    @Override
    public SearchResultsViewModel adapt(FlightsLivePrices input) {
        if (input != null && !input.getItineraries().isEmpty()) {
            List<ItineraryCardViewModel> cardViewModels = new ArrayList<>();
            for (Itinerary itinerary : input.getItineraries()) {
                cardViewModels.add(adapter.adapt(itinerary));
            }
            return new SearchResultsViewModel(cardViewModels);
        }
        return new SearchResultsViewModel(Collections.emptyList());
    }
}
