package com.jalch.flightsearch.presenter;

import com.jalch.flightsearch.common.rx.RxSchedulers;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.usecase.GetFlightsLivePricesUseCase;
import com.jalch.flightsearch.view.FlightsLivePricesFragmentView;
import com.jalch.flightsearch.view.viewmodel.SearchResultsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchFlightsLivePricesPresenterTest {

    private static final String ERROR_MESSAGE = "error_message";

    @Mock
    private FlightsLivePricesCreateSessionParams searchParams;
    @Mock
    private FlightsLivePricesFragmentView view;
    @Mock
    private GetFlightsLivePricesUseCase model;
    @Mock
    private FlightsLivePrices modelResult;
    @Mock
    private Throwable errorResult;

    private Observable<FlightsLivePrices> modelResultObservable = Observable.just(mock(FlightsLivePrices.class));
    @Mock
    private RxSchedulers schedulers;

    private FlightsLivePricesPresenter underTest;

    private Observer<FlightsLivePrices> underTestObserver;

    @Before
    public void setUp() throws Exception {
        underTest = new SearchFlightsLivePricesPresenter(view, model, schedulers);
        underTestObserver = (Observer<FlightsLivePrices>) underTest;
    }

    @Test
    public void search() throws Exception {
        givenAScheduler();
        givenAModel();

        whenThePresenterStartsTheSearch();

        thenTheModelExecutes();
    }

    @Test
    public void on_next() throws Exception {
        whenThePresenterDoOnNextAsObserver();

        thenTheViewShowsFlightsLivePricesViewModel();
    }

    @Test
    public void on_error() throws Exception {
        givenAnError();

        whenThePresenterGetsAnErrorAsObserver();

        theTheViewHidesLoading();
        theTheViewShowsTheError();
    }

    @Test
    public void on_completed() throws Exception {
        whenThePresenterCompletesAsObserver();

        theTheViewHidesLoading();
    }

    @Test
    public void on_subscribe() throws Exception {
        whenThePresenterSubscribesAsObserver();

        theTheViewShowsLoading();
    }

    private void givenAScheduler() {
        when(schedulers.internet()).thenReturn(new TestScheduler());
        when(schedulers.androidThread()).thenReturn(new TestScheduler());
    }

    private void givenAModel(){
        when(model.execute(any())).thenReturn(modelResultObservable);
    }

    private void givenAnError() {
        when(errorResult.getMessage()).thenReturn(ERROR_MESSAGE);
    }

    private void whenThePresenterStartsTheSearch() {
        underTest.startSearch(searchParams);
    }

    private void whenThePresenterDoOnNextAsObserver() {
        underTestObserver.onNext(modelResult);
    }

    private void whenThePresenterGetsAnErrorAsObserver() {
        underTestObserver.onError(errorResult);
    }

    private void whenThePresenterSubscribesAsObserver() {
        underTestObserver.onSubscribe(mock(Disposable.class));
    }

    private void whenThePresenterCompletesAsObserver() {
        underTestObserver.onComplete();
    }

    private void thenTheModelExecutes() {
        verify(model, times(1)).execute(searchParams);
    }

    private void thenTheViewShowsFlightsLivePricesViewModel() {
        verify(view, times(1)).showLivePrices(any(SearchResultsViewModel.class));
    }

    private void theTheViewShowsTheError() {
        verify(view, times(1)).showError(ERROR_MESSAGE);
    }

    private void theTheViewShowsLoading() {
        verify(view, times(1)).showLoading();
    }

    private void theTheViewHidesLoading() {
        verify(view, times(1)).hideLoading();
    }


}
