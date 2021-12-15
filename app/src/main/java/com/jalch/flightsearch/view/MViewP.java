package com.jalch.flightsearch.view;


import com.jalch.flightsearch.presenter.MVPresenter;

public interface MViewP<P extends MVPresenter> {
    void init(P presenter);
}

