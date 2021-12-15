package com.jalch.flightsearch.model.data.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.jalch.flightsearch.model.data.service.HttpRetryCodeInterceptor.RETRY_RESULT_CODE;
import static okhttp3.Protocol.HTTP_1_0;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpRetryCodeInterceptorTest {

    private static final int SUCCESS_RESULT_CODE = 200;

    @Mock
    private Interceptor.Chain chain;

    private HttpRetryCodeInterceptor underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new HttpRetryCodeInterceptor();
    }

    @Test
    public void does_not_intercept() throws Exception {
        givenAChainWithResponseWith(SUCCESS_RESULT_CODE);

        whenTheInterceptionIsTried();

        thenThereIsNoInterception();
    }

    @Test
    public void does_intercept() throws Exception {
        givenAChainWithResponseWith(RETRY_RESULT_CODE);

        whenTheInterceptionIsTried();

        thenThereIsInterceptionAndRetries();
    }

    private void givenAChainWithResponseWith(int resultCode) throws IOException {
        Request request = new Request.Builder().url("https://www.skyscanner.net1").build();
        Response response = new Response.Builder().protocol(HTTP_1_0).request(request).message("").code(resultCode).build();
        when(chain.request()).thenReturn(request);
        when(chain.proceed(any(Request.class))).thenReturn(response);
    }

    private void whenTheInterceptionIsTried() throws IOException {
        underTest.intercept(chain);
    }

    private void thenThereIsInterceptionAndRetries() throws IOException {
        verify(chain, times(2)).request();
        verify(chain, times(2)).proceed(any(Request.class));
    }

    private void thenThereIsNoInterception() throws IOException {
        verify(chain, times(1)).request();
        verify(chain, times(1)).proceed(any(Request.class));
    }
}