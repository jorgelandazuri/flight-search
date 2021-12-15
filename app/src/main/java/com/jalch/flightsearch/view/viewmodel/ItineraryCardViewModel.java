package com.jalch.flightsearch.view.viewmodel;

import java.util.List;

import static java.util.Collections.emptyList;

public class ItineraryCardViewModel implements ViewModel {

    private final boolean empty;
    private final List<LegItineraryViewModel> legItineraryViewModels;
    private final RatingViewModel ratingViewModel;
    private final ItineraryPricingViewModel itineraryPricingViewModel;

    public ItineraryCardViewModel(List<LegItineraryViewModel> legItineraryViewModels, RatingViewModel ratingViewModel, ItineraryPricingViewModel itineraryPricingViewModel) {
        this.legItineraryViewModels = legItineraryViewModels;
        this.ratingViewModel = ratingViewModel;
        this.itineraryPricingViewModel = itineraryPricingViewModel;
        this.empty = false;
    }

    private ItineraryCardViewModel(boolean empty, List<LegItineraryViewModel> legItineraryViewModels, RatingViewModel ratingViewModel, ItineraryPricingViewModel itineraryPricingViewModel) {
        this.empty = empty;
        this.legItineraryViewModels = legItineraryViewModels;
        this.ratingViewModel = ratingViewModel;
        this.itineraryPricingViewModel = itineraryPricingViewModel;
    }

    public List<LegItineraryViewModel> getLegItineraryViewModels() {
        return legItineraryViewModels;
    }

    public RatingViewModel getRatingViewModel() {
        return ratingViewModel;
    }

    public ItineraryPricingViewModel getItineraryPricingViewModel() {
        return itineraryPricingViewModel;
    }

    @Override
    public boolean isValid() {
        return validLegViewModels(legItineraryViewModels)
                && itineraryPricingViewModel.isValid();
    }

    public boolean isEmpty() {
        return empty;
    }

    private boolean validLegViewModels(List<LegItineraryViewModel> legItineraryViewModels) {
        boolean result = !legItineraryViewModels.isEmpty();
        if (result) {
            for (LegItineraryViewModel legModel : legItineraryViewModels) {
                if (!legModel.isValid()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static ItineraryCardViewModel invalid() {
        return new ItineraryCardViewModel(emptyList(), null,
                ItineraryPricingViewModel.invalid());
    }

    public static ItineraryCardViewModel empty() {
        return new ItineraryCardViewModel(true, emptyList(), null,
                null);
    }
}
