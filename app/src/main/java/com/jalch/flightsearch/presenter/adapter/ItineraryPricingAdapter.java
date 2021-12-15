package com.jalch.flightsearch.presenter.adapter;


import com.jalch.flightsearch.model.domain.pojo.Agent;
import com.jalch.flightsearch.model.domain.pojo.Currency;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.PricingOption;
import com.jalch.flightsearch.view.viewmodel.ItineraryPricingViewModel;

import java.util.List;

import static java.lang.String.valueOf;

public class ItineraryPricingAdapter implements ViewModelAdapter<ItineraryPricingViewModel, PricingOption> {


    private final FlightsLivePrices itineraryDataSource;

    public ItineraryPricingAdapter(FlightsLivePrices itineraryDataSource) {
        this.itineraryDataSource = itineraryDataSource;
    }

    @Override
    public ItineraryPricingViewModel adapt(PricingOption input) {

        if (input != null) {
            return new ItineraryPricingViewModel(
                    adaptPrice(input.getPrice()), adaptAgentName(input));
        } else {
            return ItineraryPricingViewModel.invalid();
        }
    }

    private String adaptAgentName(PricingOption input) {
        String viewAgentName = "";
        if(!input.getAgents().isEmpty()){
            long lookUpAgentId = input.getAgents().get(0);
            for (Agent agent : itineraryDataSource.getAgents()) {
                if (lookUpAgentId == agent.getId()) {
                    viewAgentName = agent.getName();
                    break;
                }
            }

        }
        return viewAgentName;
    }

    private String adaptPrice(Double price) {
        List<Currency> currencies = itineraryDataSource.getCurrencies();
        String viewCurrencySymbol = !currencies.isEmpty() ? currencies.get(0).getSymbol() : "";
        return viewCurrencySymbol + valueOf(price);
    }
}
