package com.jalch.flightsearch.model.data.repo.liveprices.session;

import com.jalch.flightsearch.model.data.repo.DataParametrizedRepository;
import com.jalch.flightsearch.model.data.service.FlightLivePricesAPIService;
import com.jalch.flightsearch.model.data.service.FlightsLivePricesCreateSessionParams;
import com.jalch.flightsearch.model.domain.pojo.SessionResponseBody;

import io.reactivex.Single;
import retrofit2.Response;

public class FlightsLivePricesSessionRepository implements DataParametrizedRepository<Single<String>, FlightsLivePricesCreateSessionParams> {

    private final FlightLivePricesAPIService sessionService;
    private final SessionResponseProcessor sessionResponseProcessor;

    public FlightsLivePricesSessionRepository(FlightLivePricesAPIService sessionService, SessionResponseProcessor sessionResponseProcessor) {
        this.sessionService = sessionService;
        this.sessionResponseProcessor = sessionResponseProcessor;
    }

    @Override
    public Single<String> load(FlightsLivePricesCreateSessionParams params) {
        return getSessionLocationResponse(params).map(r -> sessionResponseProcessor.process(r));
    }


    private Single<Response<SessionResponseBody>> getSessionLocationResponse(FlightsLivePricesCreateSessionParams params) {
        return sessionService.createSession(params.getCountry(), params.getCurrency(),
                params.getLocale(), params.getOriginPlace(), params.getDestinationPlace(),
                params.getOutboundDate(), params.getInboundDate(), params.getCabinClass(),
                params.getAdults(), params.getLocationSchema(), params.getApiKey());
    }

}
