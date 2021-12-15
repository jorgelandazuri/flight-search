package com.jalch.flightsearch.view.viewmodel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.valueOf;

public class RatingViewModel implements ViewModel {

    private final String iconResourceId;
    private final String ratingScore;

    private List<IntervalChecker> checkerChain = new ArrayList<>();

    public RatingViewModel(float rating) {
        initChain();
        this.ratingScore = format("%.2f", rating);
        this.iconResourceId = getIconResourceId(rating);
    }

    private void initChain() {
        checkerChain.add(new IntervalChecker(8.0F, 10.0F, "ic_rating_8_10"));
        checkerChain.add(new IntervalChecker(6.0F, 8.0F, "ic_rating_6_8"));
        checkerChain.add(new IntervalChecker(4.0F, 6.0F, "ic_rating_4_6"));
        checkerChain.add(new IntervalChecker(2.0F, 4.0F, "ic_rating_2_4"));
        checkerChain.add(new IntervalChecker(0.0F, 2.0F, "ic_rating_0_2"));
    }

    private String getIconResourceId(float ratingScore) {
        for (IntervalChecker checker: checkerChain){
            if(checker.intervalContains(ratingScore))
                return checker.getRatingIconId();
        }
        return null;
    }

    public String getIconResourceId() {
        return iconResourceId;
    }

    public String getRatingScore() {
        return ratingScore;
    }

    private class IntervalChecker {
        private final float min;
        private final float max;
        private final String ratingIconId;

        public IntervalChecker(float min, float max, String ratingIconId) {
            this.min = min;
            this.max = max;
            this.ratingIconId = ratingIconId;
        }

        public boolean intervalContains(float n) {
            return n >= min && n <= max;
        }

        public String getRatingIconId() {
            return ratingIconId;
        }
    }

    @Override
    public boolean isValid() {
        return iconResourceId != null;
    }
}
