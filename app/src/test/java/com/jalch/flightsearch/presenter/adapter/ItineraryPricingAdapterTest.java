package com.jalch.flightsearch.presenter.adapter;

import com.jalch.flightsearch.model.domain.pojo.Agent;
import com.jalch.flightsearch.model.domain.pojo.Currency;
import com.jalch.flightsearch.model.domain.pojo.FlightsLivePrices;
import com.jalch.flightsearch.model.domain.pojo.PricingOption;
import com.jalch.flightsearch.model.domain.pojo.Query;
import com.jalch.flightsearch.view.viewmodel.ItineraryPricingViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItineraryPricingAdapterTest extends ViewModelAdapterTest<ItineraryPricingViewModel, PricingOption, FlightsLivePrices> {

    private static final String AN_AGENT_NAME = "lastminute.com";
    private static final double EXPECTED_PRICE = 99.99;
    private static final long AN_AGENT_ID = 2363321;
    private static final String POUND_SYMBOL = "£";
    private static final String CURRENCY_CODE = "GBP";

    @Mock
    private Agent agent;
    @Mock
    private Currency currency;
    @Mock
    private Query query;

    private ViewModelAdapter<ItineraryPricingViewModel, PricingOption> underTest;

    @Before
    public void setUp() throws Exception {
        input = mock(PricingOption.class);
        dataSource = mock(FlightsLivePrices.class);
        result = mock(ItineraryPricingViewModel.class);
        underTest = new ItineraryPricingAdapter(dataSource);
    }

    @Test
    public void adapt_with_no_input_agent() throws Exception {
        givenNoInputAgents();

        whenItIsAdaptedWith(input);

        thenResultIsValidWith(POUND_SYMBOL + EXPECTED_PRICE, "");
    }

    @Test
    public void adapt_with_no_data_source_agent() throws Exception {
        givenNoDataSourceAgents();

        whenItIsAdaptedWith(input);

        thenResultIsValidWith(POUND_SYMBOL + EXPECTED_PRICE, "");
    }

    @Test
    public void adapt_with_no_currency_symbol() throws Exception {
        givenNoCurrencies();

        whenItIsAdaptedWith(input);

        thenResultIsValidWith(valueOf(EXPECTED_PRICE), AN_AGENT_NAME);
    }

    protected void givenAValidInput() {
        when(input.getAgents()).thenReturn(singletonList(AN_AGENT_ID));
        when(dataSource.getAgents()).thenReturn(singletonList(agent));
        when(dataSource.getCurrencies()).thenReturn(singletonList(currency));

        when(input.getPrice()).thenReturn(EXPECTED_PRICE);
        when(agent.getId()).thenReturn(AN_AGENT_ID);
        when(agent.getName()).thenReturn(AN_AGENT_NAME);
        when(currency.getSymbol()).thenReturn(POUND_SYMBOL);
        when(query.getCurrency()).thenReturn(CURRENCY_CODE);
    }

    protected PricingOption givenAnInvalidInput() {
        return null;
    }

    protected void whenItIsAdaptedWith(PricingOption input) {
        result = underTest.adapt(input);
    }

    protected void thenResultIsAValidViewModel() {
        thenResultIsValidWith(POUND_SYMBOL + EXPECTED_PRICE, AN_AGENT_NAME);
    }

    protected void thenResultIsANotValidViewModel() {
        assertThat(result.isValid(), is(false));
    }

    private void thenResultIsValidWith(String price, String agentName) {
        assertThat(result.isValid(), is(true));
        assertThat(result.getAgentName(), is(agentName));
        assertThat(result.getPrice(), is(price));
    }

    private void givenNoInputAgents() {
        givenAValidInput();
        when(input.getAgents()).thenReturn(emptyList());
    }

    private void givenNoDataSourceAgents() {
        givenAValidInput();
        when(dataSource.getAgents()).thenReturn(emptyList());
    }

    private void givenNoCurrencies() {
        givenAValidInput();
        when(dataSource.getCurrencies()).thenReturn(emptyList());
    }

}
