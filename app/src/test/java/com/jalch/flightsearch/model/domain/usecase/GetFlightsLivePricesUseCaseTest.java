package com.jalch.flightsearch.model.domain.usecase;

import com.jalch.flightsearch.model.ModelVP;
import com.jalch.flightsearch.model.data.repo.DataParametrizedRepository;
import com.jalch.flightsearch.model.data.repo.DataRepository;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesPollParams;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetFlightsLivePricesUseCaseTest {

    private static final String API_KEY = "api_key";
    private static final String SESSION_LOCATION = "session/location";

    @Mock
    private FlightsLivePricesCreateSessionParams createSessionParams;
    @Mock
    private DataParametrizedRepository<Single<String>, FlightsLivePricesCreateSessionParams> sessionRepository;
    private Single<String> resultSessionFromRepository = Single.just(SESSION_LOCATION);
    @Mock
    private DataParametrizedRepository<Observable<FlightsLivePrices>, FlightsLivePricesPollParams> pollRepository;
    private FlightsLivePrices flightsLivePricesResult = new FlightsLivePrices();
    private Observable<FlightsLivePrices> resultsFromPollRepository = Observable.just(flightsLivePricesResult);

    @Mock
    private DataRepository<Single<String>> apiKeyRepository;

    private ModelVP<Observable<FlightsLivePrices>, FlightsLivePricesCreateSessionParams> underTest;

    private Observable<FlightsLivePrices> executionResult;


    @Test
    public void get_results() throws Exception {
        givenAnApiKeyRepository();
        givenAFlightLivePricesSessionRepositoryUsingTheApiKey();
        givenAFlightsLivePricesPollResults();
        givenAGetFlightsLivePricesUseCaseModel();

        whenTheUseCaseIsExecuted();

        thenTheApiKeyRepositoryIsLoaded();
        thenTheSessionRepositoryIsLoaded();
        thenThePollResultsRepositoryLoads();
        thenTheExecutionResultIsTheOneFromPollRepository();
    }

    private void givenAGetFlightsLivePricesUseCaseModel() {
        underTest = new GetFlightsLivePricesUseCase(apiKeyRepository, sessionRepository, pollRepository);
    }

    private void givenSomeCreateSessionParams() {

    }

    private void givenAFlightsLivePricesPollResults() {
        when(pollRepository.load(any(FlightsLivePricesPollParams.class))).thenReturn(resultsFromPollRepository);
    }

    private void givenAFlightLivePricesSessionRepositoryUsingTheApiKey() {
        when(createSessionParams.getApiKey()).thenReturn(API_KEY);
        when(sessionRepository.load(any())).thenReturn(resultSessionFromRepository);
    }

    private void givenAnApiKeyRepository() {
        when(apiKeyRepository.load()).thenReturn(Single.just(API_KEY));
    }

    private void whenTheUseCaseIsExecuted() {
        executionResult = underTest.execute(createSessionParams);
    }

    private void thenTheApiKeyRepositoryIsLoaded() {
        verify(apiKeyRepository, times(1)).load();
    }

    private void thenTheSessionRepositoryIsLoaded() {
        verify(createSessionParams, times(1)).setApiKey(API_KEY);
        verify(sessionRepository, times(1)).load(createSessionParams);
        resultSessionFromRepository.test().assertNoErrors().assertValue(SESSION_LOCATION);
    }

    private void thenThePollResultsRepositoryLoads() {
        //Test method in flatMapObservable is executed?
//        verify(pollRepository, times(1)).load(any(FlightsLivePricesPollParams.class));
    }

    private void thenTheExecutionResultIsTheOneFromPollRepository() {

        TestObserver<FlightsLivePrices> testObserver = executionResult.test();
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors().assertValue(flightsLivePricesResult);
    }
}
