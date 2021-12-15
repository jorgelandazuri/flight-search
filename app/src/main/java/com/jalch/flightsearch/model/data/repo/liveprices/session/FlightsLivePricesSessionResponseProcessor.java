package com.jalch.flightsearch.model.data.repo.liveprices.session;

import java.util.logging.Logger;

import retrofit2.Response;

import static com.jalch.flightsearch.common.dep.dagger.FlightLivePricesAPIServiceModule.BASE_URL;
import static java.lang.String.valueOf;
import static java.util.logging.Level.FINE;

public class FlightsLivePricesSessionResponseProcessor implements SessionResponseProcessor {

    private static final String TAG = FlightsLivePricesSessionResponseProcessor.class.getSimpleName();
    static final int SUCCESSFUL_BUT_NOT_CHANGED_RESULT_CODE = 304;
    static final String SESSION_HEADER_KEY = "Location";


    @Override
    public String process(Response response) throws UnableToCreateSessionException {
        if (response.isSuccessful()) {
            String sessionLocationUrl = response.headers().get(SESSION_HEADER_KEY);
            return sessionLocationUrl.replace(BASE_URL, "");
        } else if (response.code() == SUCCESSFUL_BUT_NOT_CHANGED_RESULT_CODE) {
            return valueOf(response.code());
        } else {
            throw new UnableToCreateSessionException(response.code());
        }
    }

    public class UnableToCreateSessionException extends RuntimeException {

        UnableToCreateSessionException(int code) {
            Logger.getGlobal().log(FINE,
                    "Unable to create flights live pricing session, result with code:" + valueOf(code));
        }
    }


}
