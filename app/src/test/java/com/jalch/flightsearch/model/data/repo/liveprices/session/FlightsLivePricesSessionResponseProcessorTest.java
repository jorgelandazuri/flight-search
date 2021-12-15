package com.jalch.flightsearch.model.data.repo.liveprices.session;

import org.junit.Before;
import org.junit.Test;

import okhttp3.Headers;
import okhttp3.Request;
import retrofit2.Response;

import static com.jalch.flightsearch.common.dep.dagger.FlightLivePricesAPIServiceModule.BASE_URL;
import static com.jalch.flightsearch.model.data.repo.liveprices.session.FlightsLivePricesSessionResponseProcessor.SESSION_HEADER_KEY;
import static com.jalch.flightsearch.model.data.repo.liveprices.session.FlightsLivePricesSessionResponseProcessor.SUCCESSFUL_BUT_NOT_CHANGED_RESULT_CODE;
import static java.lang.String.valueOf;
import static okhttp3.MediaType.parse;
import static okhttp3.Protocol.HTTP_1_0;
import static okhttp3.ResponseBody.create;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FlightsLivePricesSessionResponseProcessorTest {

    private static final String EXPECTED_SESSION_LOCATION = "session/location/abcd1234";
    private static final String SESSION_HEADER_VALUE = BASE_URL + EXPECTED_SESSION_LOCATION;
    public static final int ERROR_RESULT_CODE = 400;

    private Response sessionResponse;

    private SessionResponseProcessor underTest;

    private String processedSessionLocation;

    @Before
    public void setUp() throws Exception {
        underTest = new FlightsLivePricesSessionResponseProcessor();
    }

    @Test
    public void success_response() throws Exception {
        givenASuccessSessionResponseWith(200);

        whenTheResponseIsProcessed();

        thenTheSessionLocationShouldBe(EXPECTED_SESSION_LOCATION);
    }

    @Test
    public void error_response_with_same_results_code() throws Exception {
        givenAnErrorSessionResponseWith(SUCCESSFUL_BUT_NOT_CHANGED_RESULT_CODE);

        whenTheResponseIsProcessed();

        thenTheSessionLocationShouldBe(valueOf(SUCCESSFUL_BUT_NOT_CHANGED_RESULT_CODE));
    }

    @Test(expected = FlightsLivePricesSessionResponseProcessor.UnableToCreateSessionException.class)
    public void error_response_throws_exception() throws Exception {
        givenAnErrorSessionResponseWith(ERROR_RESULT_CODE);

        whenTheResponseIsProcessed();
    }

    private void givenASuccessSessionResponseWith(int resultCode) {
        sessionResponse = Response.success("",
                aRawResponseWithCode(resultCode));

    }

    private void givenAnErrorSessionResponseWith(int resultCode) {
        sessionResponse = Response.error(create(parse(""), ""),
                aRawResponseWithCode(resultCode));
    }

    private okhttp3.Response aRawResponseWithCode(int resultCode) {
        Headers headers = new Headers.Builder()
                .add(SESSION_HEADER_KEY, SESSION_HEADER_VALUE).build();
        Request request = new Request.Builder().url("https://www.skyscanner.net1").build();
        return new okhttp3.Response.Builder()
                .request(request).headers(headers).protocol(HTTP_1_0).message("")
                .code(resultCode).build();
    }

    private void whenTheResponseIsProcessed() {
        processedSessionLocation = underTest.process(sessionResponse);
    }

    private void thenTheSessionLocationShouldBe(String expectedSessionLocation) {
        assertThat(processedSessionLocation, is(expectedSessionLocation));
    }
}
