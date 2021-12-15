package com.jalch.flightsearch.presenter;


import com.jalch.flightsearch.common.rx.RxSchedulers;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.usecase.GetFlightsLivePricesUseCase;
import com.jalch.flightsearch.presenter.adapter.ItineraryCardViewModelAdapter;
import com.jalch.flightsearch.presenter.adapter.ItineraryPricingAdapter;
import com.jalch.flightsearch.presenter.adapter.LegItineraryViewModelAdapter;
import com.jalch.flightsearch.presenter.adapter.SearchResultsViewModelAdapter;
import com.jalch.flightsearch.view.FlightsLivePricesFragmentView;
import com.jalch.flightsearch.view.viewmodel.SearchResultsViewModel;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class SearchFlightsLivePricesPresenter extends MVPresenter<FlightsLivePricesFragmentView, GetFlightsLivePricesUseCase> implements FlightsLivePricesPresenter, Observer<FlightsLivePrices> {

    private static final int HALF_SECOND_INTERVAL = 500;
    private static final String UPDATES_COMPLETE_STATUS = "UpdatesComplete";

    private final RxSchedulers schedulers;
    private Disposable subscriptionDisposable;

    public SearchFlightsLivePricesPresenter(FlightsLivePricesFragmentView view, GetFlightsLivePricesUseCase model, RxSchedulers schedulers) {
        super(view, model);
        this.schedulers = schedulers;
    }

    @Override
    public void startSearch(FlightsLivePricesCreateSessionParams searchParams) {
        if (!this.alreadySubscribed())
            model.execute(searchParams)
                    .subscribeOn(schedulers.internet())
                    .repeatWhen(completed -> completed.delay(HALF_SECOND_INTERVAL, TimeUnit.MILLISECONDS))
                    .takeUntil((Predicate<? super FlightsLivePrices>) r -> updatesCompleted(r.getStatus()))
                    .observeOn(schedulers.androidThread())
                    .subscribeWith(this);
    }

    private boolean updatesCompleted(String response) {
        return UPDATES_COMPLETE_STATUS.equals(response);
    }

    @Override
    public void onNext(FlightsLivePrices flightsLivePrices) {
        SearchResultsViewModel resultsViewModel = getViewModelAdapter(flightsLivePrices).adapt(flightsLivePrices);
        view.showLivePrices(resultsViewModel);
    }

    @NonNull
    private SearchResultsViewModelAdapter getViewModelAdapter(FlightsLivePrices flightsLivePrices) {
        LegItineraryViewModelAdapter legItineraryAdapter = new LegItineraryViewModelAdapter(flightsLivePrices);
        ItineraryPricingAdapter pricingAdapter = new ItineraryPricingAdapter(flightsLivePrices);
        ItineraryCardViewModelAdapter itineraryAdapter = new ItineraryCardViewModelAdapter(legItineraryAdapter, pricingAdapter);
        return new SearchResultsViewModelAdapter(flightsLivePrices, itineraryAdapter);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        view.hideLoading();
        view.showError(e.getMessage());
    }

    @Override
    public void onComplete() {
        view.hideLoading();
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.subscriptionDisposable = d;
        view.showLoading();
    }

    @Override
    public void onStop() {
        if (alreadySubscribed())
            subscriptionDisposable.dispose();
    }

    private boolean alreadySubscribed() {
        return subscriptionDisposable != null;
    }
}
