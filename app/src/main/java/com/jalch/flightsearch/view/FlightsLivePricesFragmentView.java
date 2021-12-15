package com.jalch.flightsearch.view;

import com.jalch.flightsearch.presenter.FlightsLivePricesPresenter;
import com.jalch.flightsearch.presenter.SearchFlightsLivePricesPresenter;
import com.jalch.flightsearch.view.viewmodel.SearchResultsViewModel;

public interface FlightsLivePricesFragmentView extends MViewP<SearchFlightsLivePricesPresenter> {

    void showLivePrices(SearchResultsViewModel searchResults);

    void showLoading();

    void hideLoading();

    void showError(String message);
}
