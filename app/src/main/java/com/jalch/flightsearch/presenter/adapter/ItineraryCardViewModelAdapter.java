package com.jalch.flightsearch.presenter.adapter;

import com.jalch.flightsearch.common.util.NumberUtil;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.Itinerary;
import com.jalch.flightsearch.model.domain.pojo.PricingOption;
import com.jalch.flightsearch.view.viewmodel.ItineraryCardViewModel;
import com.jalch.flightsearch.view.viewmodel.LegItineraryViewModel;
import com.jalch.flightsearch.view.viewmodel.RatingViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class ItineraryCardViewModelAdapter implements ViewModelAdapter<ItineraryCardViewModel, Itinerary> {

    public static final float MIN_RATING = 0.0F;
    public static final float MAX_RATING = 10.0F;

    private final LegItineraryViewModelAdapter legItineraryAdapter;
    private final ItineraryPricingAdapter pricingAdapter;

    public ItineraryCardViewModelAdapter(@NonNull LegItineraryViewModelAdapter legItineraryAdapter,
                                         @NonNull ItineraryPricingAdapter pricingAdapter) {

        this.legItineraryAdapter = legItineraryAdapter;
        this.pricingAdapter = pricingAdapter;
    }

    @Override
    public ItineraryCardViewModel adapt(Itinerary input) {
        if (input != null) {
            List<LegItineraryViewModel> legItineraryViewModels = new ArrayList<>();
            legItineraryViewModels.add(legItineraryAdapter.adapt(input.getOutboundLegId()));
            legItineraryViewModels.add(legItineraryAdapter.adapt(input.getInboundLegId()));
            return new ItineraryCardViewModel(legItineraryViewModels,
                    new RatingViewModel(NumberUtil.generateRandomFloat(MIN_RATING, MAX_RATING)),
                    pricingAdapter.adapt(getPricingOption(input)));
        }
        return ItineraryCardViewModel.invalid();
    }

    private PricingOption getPricingOption(Itinerary input) {
        List<PricingOption> pricingOptions = input.getPricingOptions();
        return !pricingOptions.isEmpty() ? pricingOptions.get(0) : null;
    }
}
