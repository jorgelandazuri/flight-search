package com.jalch.flightsearch.view.viewmodel;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class RatingViewModelTest {

    private static final String IC_RATING_0_2 = "ic_rating_0_2";
    private static final String IC_RATING_2_4 = "ic_rating_2_4";
    private static final String IC_RATING_4_6 = "ic_rating_4_6";
    private static final String IC_RATING_6_8 = "ic_rating_6_8";
    private static final String IC_RATING_8_10 = "ic_rating_8_10";

    @Test
    public void rating_0_2(){
        assertThat(new RatingViewModel(0F).getIconResourceId(), is(IC_RATING_0_2));
        assertThat(new RatingViewModel(1.1F).getIconResourceId(), is(IC_RATING_0_2));
        assertThat(new RatingViewModel(1.99F).getIconResourceId(), is(IC_RATING_0_2));
    }

    @Test
    public void rating_2_4() throws Exception {
        assertThat(new RatingViewModel(2.0F).getIconResourceId(), is(IC_RATING_2_4));
        assertThat(new RatingViewModel(2.1F).getIconResourceId(), is(IC_RATING_2_4));
        assertThat(new RatingViewModel(3.99F).getIconResourceId(), is(IC_RATING_2_4));
    }

    @Test
    public void rating_4_6() throws Exception {
        assertThat(new RatingViewModel(4.0F).getIconResourceId(), is(IC_RATING_4_6));
        assertThat(new RatingViewModel(4.1F).getIconResourceId(), is(IC_RATING_4_6));
        assertThat(new RatingViewModel(5.99F).getIconResourceId(), is(IC_RATING_4_6));
    }

    @Test
    public void rating_6_8() throws Exception {
        assertThat(new RatingViewModel(6.0F).getIconResourceId(), is(IC_RATING_6_8));
        assertThat(new RatingViewModel(6.1F).getIconResourceId(), is(IC_RATING_6_8));
        assertThat(new RatingViewModel(7.99F).getIconResourceId(), is(IC_RATING_6_8));
    }

    @Test
    public void rating_8_10() throws Exception {
        assertThat(new RatingViewModel(8.0F).getIconResourceId(), is(IC_RATING_8_10));
        assertThat(new RatingViewModel(8.1F).getIconResourceId(), is(IC_RATING_8_10));
        assertThat(new RatingViewModel(9.99F).getIconResourceId(), is(IC_RATING_8_10));
    }
}