package com.jalch.flightsearch.presenter.adapter;

import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.Itinerary;
import com.jalch.flightsearch.model.domain.pojo.PricingOption;
import com.jalch.flightsearch.view.viewmodel.ItineraryCardViewModel;
import com.jalch.flightsearch.view.viewmodel.ItineraryPricingViewModel;
import com.jalch.flightsearch.view.viewmodel.LegItineraryViewModel;
import com.jalch.flightsearch.view.viewmodel.RatingViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItineraryCardViewModelAdapterTest extends ViewModelAdapterTest<ItineraryCardViewModel, Itinerary, FlightsLivePrices> {

    private static final String OUTBOUND_LEG_ID = "outboud_leg_id";
    private static final String INBOUND_LEG_ID = "inbound_leg_id";
    @Mock
    private LegItineraryViewModel legItineraryViewModel;
    @Mock
    private LegItineraryViewModelAdapter legItineraryAdapter;
    @Mock
    private ItineraryPricingViewModel itineraryPricingViewModel;
    @Mock
    private ItineraryPricingAdapter pricingAdapter;
    @Mock
    private PricingOption pricingOption;
    @Mock
    private RatingViewModel ratingViewModel;

    private ItineraryCardViewModelAdapter underTest;

    @Before
    public void setUp() throws Exception {
        input = mock(Itinerary.class);
        result = mock(ItineraryCardViewModel.class);
        underTest = new ItineraryCardViewModelAdapter(legItineraryAdapter, pricingAdapter);
    }

    @Test
    public void adapt_with_no_pricing_options() throws Exception {
        givenNoPricingOptions();

        whenItIsAdaptedWith(input);

        thenResultIsANotValidViewModel();
    }

    @Override
    protected void givenAValidInput() {
        assertNotNull(input);
        when(input.getOutboundLegId()).thenReturn(OUTBOUND_LEG_ID);
        when(input.getInboundLegId()).thenReturn(INBOUND_LEG_ID);
        when(legItineraryAdapter.adapt(any())).thenReturn(legItineraryViewModel);
        when(legItineraryViewModel.isValid()).thenReturn(true);
        when(input.getPricingOptions()).thenReturn(singletonList(pricingOption));
        when(pricingAdapter.adapt(pricingOption)).thenReturn(itineraryPricingViewModel);
        when(itineraryPricingViewModel.isValid()).thenReturn(true);
        when(ratingViewModel.isValid()).thenReturn(true);
    }

    private void givenNoPricingOptions() {
        givenAValidInput();
        when(input.getPricingOptions()).thenReturn(emptyList());
        ItineraryPricingViewModel invalidPriceOptionViewModel = mock(ItineraryPricingViewModel.class);
        when(invalidPriceOptionViewModel.isValid()).thenReturn(false);
        when(pricingAdapter.adapt(null)).thenReturn(invalidPriceOptionViewModel);
    }

    @Override
    protected Itinerary givenAnInvalidInput() {
        return null;
    }

    @Override
    protected void whenItIsAdaptedWith(Itinerary input) {
        result = underTest.adapt(input);

    }

    @Override
    protected void thenResultIsAValidViewModel() {
        assertTrue(result.isValid());
        verify(legItineraryAdapter, times(1)).adapt(OUTBOUND_LEG_ID);
        verify(legItineraryAdapter, times(1)).adapt(INBOUND_LEG_ID);
        verify(pricingAdapter, times(1)).adapt(input.getPricingOptions().get(0));
        verify(pricingAdapter, times(1)).adapt(input.getPricingOptions().get(0));
    }

    @Override
    protected void thenResultIsANotValidViewModel() {
        assertFalse(result.isValid());
    }
}
