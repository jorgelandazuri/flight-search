package com.jalch.flightsearch.presenter.adapter;

import com.jalch.flightsearch.model.domain.pojo.Carrier;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.Leg;
import com.jalch.flightsearch.model.domain.pojo.Place;
import com.jalch.flightsearch.view.viewmodel.LegItineraryViewModel;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.DateTime.parse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LegItineraryViewModelAdapterTest extends ViewModelAdapterTest<LegItineraryViewModel, String, FlightsLivePrices> {

    private static final String A_LEG_ID = "11235-1803051020--32356-0-13771-1803051135";
    private static final String DEPARTURE_DATE = "2018-03-05T08:45:00";
    private static final String ARRIVAL_DATE = "2018-03-05T10:15:00";
    private static final Long AN_ORIGIN_STATION_ID = 11235L;
    private static final String AN_ORIGIN_STATION_CODE = "EDI";
    private static final Long A_DESTINATION_STATION_ID = 13465L;
    private static final String A_DESTINATION_STATION_CODE = "LGW";
    private static final Long A_STOP_ID = 13880L;
    private static final Long A_CARRIER_ID = 881L;
    private static final String A_CARRIER_LOGO_URL = "http://s1.apideeplink.com/images/airlines/BA.png";
    private static final String A_CARRIER_NAME = "British Airways";
    public static final int DURATION = 9999;

    public static final String EXPECTED_ARRIVAL_DEPARTURE_TIMES = format("%s - %s", parse(DEPARTURE_DATE).toString("HH:mm"), parse(ARRIVAL_DATE).toString("HH:mm"));
    public static final String EXPECTED_ROUTE = format("%s-%s", AN_ORIGIN_STATION_CODE, A_DESTINATION_STATION_CODE);
    public static final String EXPECTED_ROUTE_AND_CARRIER = format("%s, %s", EXPECTED_ROUTE, A_CARRIER_NAME);
    private static final String EXPECTED_DURATION_LABEL = getExpectedFormattedDuration();

    @Mock
    private Leg leg;
    @Mock
    private Carrier carrier;
    @Mock
    private Place originPlace;
    @Mock
    private Place destinationPlace;

    private ViewModelAdapter<LegItineraryViewModel, String> underTest;

    @Before
    public void setUp() throws Exception {
        input = A_LEG_ID;
        dataSource = mock(FlightsLivePrices.class);
        underTest = new LegItineraryViewModelAdapter(dataSource);
    }

    @Test
    public void adapt_with_no_carriers() throws Exception {
        givenNoCarriersForItinerary();

        whenItIsAdaptedWith(input);

        thenResultIsAValidViewModelWithRouteAndCarrierInfo("", EXPECTED_ROUTE);
    }

    @Test
    public void adapt_with_no_places() throws Exception {
        givenNoPlacesForItinerary();

        whenItIsAdaptedWith(input);

        thenResultIsAValidViewModelWithRouteAndCarrierInfo(A_CARRIER_LOGO_URL, A_CARRIER_NAME);
    }

    @Override
    protected void givenAValidInput() {
        when(dataSource.getLegs()).thenReturn(singletonList(leg));

        when(leg.getId()).thenReturn(A_LEG_ID);
        when(leg.getDeparture()).thenReturn(DEPARTURE_DATE);
        when(leg.getArrival()).thenReturn(ARRIVAL_DATE);
        when(leg.getDuration()).thenReturn(DURATION);
        when(leg.getOriginStation()).thenReturn(AN_ORIGIN_STATION_ID);
        when(leg.getDestinationStation()).thenReturn(A_DESTINATION_STATION_ID);
        when(leg.getStops()).thenReturn(singletonList(A_STOP_ID));
        when(leg.getCarriers()).thenReturn(singletonList(A_CARRIER_ID));

        when(dataSource.getCarriers()).thenReturn(singletonList(carrier));
        when(dataSource.getPlaces()).thenReturn(asList(originPlace, destinationPlace));
        when(originPlace.getId()).thenReturn(AN_ORIGIN_STATION_ID);
        when(originPlace.getCode()).thenReturn(AN_ORIGIN_STATION_CODE);
        when(destinationPlace.getId()).thenReturn(A_DESTINATION_STATION_ID);
        when(destinationPlace.getCode()).thenReturn(A_DESTINATION_STATION_CODE);
        when(carrier.getId()).thenReturn(A_CARRIER_ID);
        when(carrier.getImageUrl()).thenReturn(A_CARRIER_LOGO_URL);
        when(carrier.getName()).thenReturn(A_CARRIER_NAME);
    }

    @Override
    protected String givenAnInvalidInput() {
        return null;
    }

    private void givenNoCarriersForItinerary() {
        givenAValidInput();
        when(dataSource.getCarriers()).thenReturn(emptyList());
    }

    private void givenNoPlacesForItinerary() {
        givenAValidInput();
        when(dataSource.getPlaces()).thenReturn(emptyList());
    }

    @Override
    protected void whenItIsAdaptedWith(String input) {
        result = underTest.adapt(input);
    }

    @Override
    protected void thenResultIsAValidViewModel() {
        assertThat(result.isValid(), is(true));
        assertThat(result.getArrivalAndDepartureTimes(), is(EXPECTED_ARRIVAL_DEPARTURE_TIMES));
        thenResultIsAValidViewModelWithRouteAndCarrierInfo(A_CARRIER_LOGO_URL, EXPECTED_ROUTE_AND_CARRIER);
        assertThat(result.getStopsNumber(), is(1));
        assertThat(result.getDuration(), is(EXPECTED_DURATION_LABEL));
    }
    @Override
    protected void thenResultIsANotValidViewModel() {
        assertThat(result.isValid(), is(false));
    }

    private void thenResultIsAValidViewModelWithRouteAndCarrierInfo(String logoUrl, String routeAndCarrier) {
        assertThat(result.getCarrierLogo(), is(logoUrl));
        assertThat(result.getRouteAndCarrier(), is(routeAndCarrier));
    }

    private static String getExpectedFormattedDuration() {
        Period period = new Period(Duration.standardMinutes(DURATION));
        return new PeriodFormatterBuilder()
                .appendHours().appendSuffix("h ")
                .appendMinutes().appendSuffix("m")
                .toFormatter().print(period);
    }
}
