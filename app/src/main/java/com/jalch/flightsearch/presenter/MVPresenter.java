package com.jalch.flightsearch.presenter;

import com.jalch.flightsearch.model.ModelVP;
import com.jalch.flightsearch.view.MViewP;

public abstract class MVPresenter<V extends MViewP, M extends ModelVP> {

    protected final V view;
    protected final M model;

    public MVPresenter(V view, M model) {
        this.view = view;
        this.model = model;
    }

}
