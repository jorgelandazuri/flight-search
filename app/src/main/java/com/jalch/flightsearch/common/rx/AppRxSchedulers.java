package com.jalch.flightsearch.common.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppRxSchedulers implements RxSchedulers {

    private static Executor internetExecutor = Executors.newCachedThreadPool();
    public static Scheduler INTERNET_SCHEDULERS = Schedulers.from(internetExecutor);

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler compute() {
        return Schedulers.computation();
    }

    @Override
    public io.reactivex.Scheduler androidThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler internet() {
        return INTERNET_SCHEDULERS;
    }
}
