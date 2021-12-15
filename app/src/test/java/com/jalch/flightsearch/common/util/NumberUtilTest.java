package com.jalch.flightsearch.common.util;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NumberUtilTest {

    @Test
    public void random_between_bounds() {
        assertBetweenBounds(0F, 10F);
        assertBetweenBounds(-10F, 0F);
        assertBetweenBounds(0F, 1F);
        assertBetweenBounds(-1F, 0F);
    }

    private void assertBetweenBounds(float min, float max){
        float generated = NumberUtil.generateRandomFloat(min, max);
        assertThat(generated >= min && generated <= max , is(true));
    }
}