package com.jalch.flightsearch.presenter.adapter;

import org.junit.Test;

public abstract class ViewModelAdapterTest<R, I, D> {

    protected I input;

    protected D dataSource;

    protected R result;

    @Test
    public void adapt_valid() throws Exception {
        givenAValidInput();

        whenItIsAdaptedWith(input);

        thenResultIsAValidViewModel();
    }

    @Test
    public void adapt_invalid() throws Exception {
        I input = givenAnInvalidInput();

        whenItIsAdaptedWith(input);

        thenResultIsANotValidViewModel();
    }

    protected abstract void givenAValidInput();
    protected abstract I givenAnInvalidInput();

    protected abstract void whenItIsAdaptedWith(I input);

    protected abstract void thenResultIsAValidViewModel();
    protected abstract void thenResultIsANotValidViewModel();

}
