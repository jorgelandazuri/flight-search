package com.jalch.flightsearch.common.util;

import java.util.Random;

public class NumberUtil {

    public static float generateRandomFloat(float min, float max) {
        return min + new Random().nextFloat() * (max - min);
    }
}
