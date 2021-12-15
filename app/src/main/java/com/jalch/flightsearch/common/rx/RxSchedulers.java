package com.jalch.flightsearch.common.rx;

import io.reactivex.Scheduler;

public interface RxSchedulers {

    Scheduler io();
    Scheduler compute();
    io.reactivex.Scheduler androidThread();
    Scheduler internet();
}
