package com.jalch.flightsearch.view.viewmodel;

import com.jalch.flightsearch.presenter.adapter.ViewModelAdapter;

import java.util.List;

public class SearchResultsViewModel implements ViewModel {

    private final List<ItineraryCardViewModel> itineraryCardsViewModels;

    public SearchResultsViewModel(List<ItineraryCardViewModel> itineraryCardsViewModels) {
        this.itineraryCardsViewModels = itineraryCardsViewModels;
    }

    public List<ItineraryCardViewModel> getItineraryCardsViewModels() {
        return itineraryCardsViewModels;
    }

    @Override
    public boolean isValid() {
        return itineraryCardsViewModels.size() > 0;
    }
}
