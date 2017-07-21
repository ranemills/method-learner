package com.mills.models;

import java.util.HashMap;
import java.util.Map;

public enum PlaceBellNumber {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12);

    private Integer _value;

    private static Map<Integer, PlaceBellNumber> intToPlaceBellMap = new HashMap<>();

    static {
        for(PlaceBellNumber number : values())
        {
            intToPlaceBellMap.put(number._value, number);
        }
    }

    PlaceBellNumber(Integer value)
    {
        _value = value;
    }

    public static PlaceBellNumber valueOf(Integer number)
    {
        return intToPlaceBellMap.get(number);
    }
}
