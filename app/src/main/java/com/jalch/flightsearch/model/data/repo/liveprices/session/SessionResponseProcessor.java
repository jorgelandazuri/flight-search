package com.jalch.flightsearch.model.data.repo.liveprices.session;

import retrofit2.Response;

public interface SessionResponseProcessor {

    String process(Response response);
}
