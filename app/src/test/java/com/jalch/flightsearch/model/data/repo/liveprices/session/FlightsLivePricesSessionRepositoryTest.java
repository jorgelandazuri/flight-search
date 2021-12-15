package com.jalch.flightsearch.model.data.repo.liveprices.session;

import com.jalch.flightsearch.model.data.repo.DataParametrizedRepository;
import com.jalch.flightsearch.model.data.service.FlightLivePricesAPIService;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;
import com.jalch.flightsearch.model.domain.pojo.SessionResponseBody;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import retrofit2.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightsLivePricesSessionRepositoryTest {

    private static final String API_KEY = "api_key";
    private static final String COUNTRY = "country";
    private static final String CURRENCY = "currency";
    private static final String LOCALE = "locale";
    private static final String ORIGIN_PLACE = "origin_place";
    private static final String DESTINATION_PLACE = "destination_place";
    private static final String OUTBOUND_DATE = "outbound_date";
    private static final String INBOUND_DATE = "inbound_date";
    private static final String CABIN_CLASS = "economy_class";
    private static final String ADULTS = "1";
    private static final String A_LOCATION_SCHEMA = "a_location_schema";

    private static final String PROCESSED_SESSION_LOCATION = "/session/location/abcd1234";


    @Mock
    private SessionResponseProcessor sessionResponseProcessor;
    @Mock
    private FlightsLivePricesCreateSessionParams createSessionParams;
    @Mock
    private FlightLivePricesAPIService sessionService;
    private Single<Response<SessionResponseBody>> sessionServiceResponse = Single.just(Response.success( mock(SessionResponseBody.class)));

    private DataParametrizedRepository<Single<String>, FlightsLivePricesCreateSessionParams> underTest;

    private Single<String> loadedSessionLocation;

    @Test
    public void provides_session_id() {
        givenSomeCreateSessionParams();
        givenASessionResponseProcessor();
        givenASessionResponse();
        givenAFlightLivePricesSessionRepository();

        whenItLoadsTheSessionId();

        thenTheServiceIsCalled();
        thenTheResultIsMappedToReturnUsingTheProcessor();
    }

    private void givenSomeCreateSessionParams() {
        when(createSessionParams.getApiKey()).thenReturn(API_KEY);
        when(createSessionParams.getCountry()).thenReturn(COUNTRY);
        when(createSessionParams.getCurrency()).thenReturn(CURRENCY);
        when(createSessionParams.getLocale()).thenReturn(LOCALE);
        when(createSessionParams.getOriginPlace()).thenReturn(ORIGIN_PLACE);
        when(createSessionParams.getDestinationPlace()).thenReturn(DESTINATION_PLACE);
        when(createSessionParams.getOutboundDate()).thenReturn(OUTBOUND_DATE);
        when(createSessionParams.getInboundDate()).thenReturn(INBOUND_DATE);
        when(createSessionParams.getCabinClass()).thenReturn(CABIN_CLASS);
        when(createSessionParams.getAdults()).thenReturn(ADULTS);
        when(createSessionParams.getLocationSchema()).thenReturn(A_LOCATION_SCHEMA);
    }

    private void givenASessionResponseProcessor() {
        when(sessionResponseProcessor.process(any())).thenReturn(PROCESSED_SESSION_LOCATION);
    }

    private void givenASessionResponse() {
        when(sessionService.createSession(COUNTRY, CURRENCY, LOCALE, ORIGIN_PLACE,
                DESTINATION_PLACE, OUTBOUND_DATE, INBOUND_DATE, CABIN_CLASS, ADULTS,
                A_LOCATION_SCHEMA, API_KEY)).thenReturn(sessionServiceResponse);
    }

    private void givenAFlightLivePricesSessionRepository() {
        underTest = new FlightsLivePricesSessionRepository(sessionService, sessionResponseProcessor);
    }

    private void whenItLoadsTheSessionId() {
        loadedSessionLocation = underTest.load(createSessionParams);
    }

    private void thenTheServiceIsCalled() {
        verify(sessionService, times(1))
                .createSession(COUNTRY, CURRENCY, LOCALE, ORIGIN_PLACE, DESTINATION_PLACE,
                        OUTBOUND_DATE, INBOUND_DATE, CABIN_CLASS, ADULTS, A_LOCATION_SCHEMA, API_KEY);
    }

    private void thenTheResultIsMappedToReturnUsingTheProcessor() {
        TestObserver<String> testObserver = loadedSessionLocation.test();
        testObserver.assertNoErrors();
        testObserver.assertValue(PROCESSED_SESSION_LOCATION);
        verify(sessionResponseProcessor, times(1)).process(any());
    }

}
