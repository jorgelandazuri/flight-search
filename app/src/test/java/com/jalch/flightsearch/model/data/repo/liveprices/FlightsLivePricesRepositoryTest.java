package com.jalch.flightsearch.model.data.repo.liveprices;

import com.google.gson.Gson;
import com.jalch.flightsearch.model.data.repo.DataParametrizedRepository;
import com.jalch.flightsearch.model.data.service.FlightLivePricesAPIService;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesPollParams;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightsLivePricesRepositoryTest {

    private static final String API_KEY = "api_key";
    private static final String SESSION_LOCATION = "session_location";

    @Mock
    private Observable<FlightsLivePrices> serviceResults;
    @Mock
    private FlightsLivePricesPollParams flightsLivePricesPollParams;
    @Mock
    private FlightLivePricesAPIService service;

    private DataParametrizedRepository<Observable<FlightsLivePrices>, FlightsLivePricesPollParams> underTest;

    private Observable<FlightsLivePrices> loadedResults;

    @Test
    public void provide_flights_live_prices_results() throws Exception {
        givenAFlightsLivePricesAPIService();
        givenAFlightsLivePricesResultsRepository();

        whenTheRepositoryLoadsTheResults();

        thenTheServiceIsCalled();
        thenTheLoadedResultsAreTheOnesFromTheService();
    }

    private void givenAFlightsLivePricesAPIService() {
        when(flightsLivePricesPollParams.getApiKey()).thenReturn(API_KEY);
        when(flightsLivePricesPollParams.getSessionLocation()).thenReturn(SESSION_LOCATION);
        when(service.getFlightsLivePrices(SESSION_LOCATION, API_KEY)).thenReturn(serviceResults);
    }

    private void givenAFlightsLivePricesResultsRepository() {
        underTest = new FlightsLivePricesRepository(service);
    }

    private void whenTheRepositoryLoadsTheResults() {
        loadedResults = underTest.load(flightsLivePricesPollParams);
    }

    private void thenTheLoadedResultsAreTheOnesFromTheService() {
        assertTrue(loadedResults.equals(serviceResults));
    }

    private void thenTheServiceIsCalled() {
        verify(service).getFlightsLivePrices(flightsLivePricesPollParams.getSessionLocation(),
                flightsLivePricesPollParams.getApiKey());
    }


}
