package com.mills.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ryan on 21/07/17.
 */
public enum Rating {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int _rating;

    private static Map<Integer, Rating> intToPlaceBellMap = new HashMap<>();

    static {
        for(Rating number : values())
        {
            intToPlaceBellMap.put(number._rating, number);
        }
    }

    Rating(int rating)
    {
        _rating = rating;
    }

    public int asInt() {
        return _rating;
    }

    public static Rating valueOf(Integer number)
    {
        return intToPlaceBellMap.get(number);
    }

}
