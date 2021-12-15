package com.jalch.flightsearch.presenter.adapter;

import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.Itinerary;
import com.jalch.flightsearch.view.viewmodel.ItineraryCardViewModel;
import com.jalch.flightsearch.view.viewmodel.SearchResultsViewModel;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchResultsViewModelAdapterTest extends ViewModelAdapterTest<SearchResultsViewModel, FlightsLivePrices, FlightsLivePrices> {

    @Mock
    private Itinerary itinerary;
    @Mock
    private ItineraryCardViewModelAdapter adapter;
    @Mock
    private ItineraryCardViewModel cardViewModel;

    private SearchResultsViewModelAdapter underTest;

    @Before
    public void setUp() throws Exception {
        input = mock(FlightsLivePrices.class);
        dataSource = mock(FlightsLivePrices.class);
        result = mock(SearchResultsViewModel.class);
        underTest = new SearchResultsViewModelAdapter(dataSource, adapter);
    }

    @Override
    protected void givenAValidInput() {
        assertNotNull(input);
        List<Itinerary> itineraries = Arrays.asList(itinerary, itinerary, itinerary);
        when(input.getItineraries()).thenReturn(itineraries);
        when(adapter.adapt(any())).thenReturn(cardViewModel);
    }

    @Override
    protected FlightsLivePrices givenAnInvalidInput() {
        return null;
    }

    @Override
    protected void whenItIsAdaptedWith(FlightsLivePrices input) {
        result = underTest.adapt(input);
    }

    @Override
    protected void thenResultIsAValidViewModel() {
        verify(adapter, times(3)).adapt(any(Itinerary.class));
        assertThat(result.getItineraryCardsViewModels().size(), is(3));
    }

    @Override
    protected void thenResultIsANotValidViewModel() {
        assertThat(result.getItineraryCardsViewModels().size(), is(0));
    }
}
