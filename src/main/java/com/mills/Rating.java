package com.mills;

/**
 * Created by ryan on 21/07/17.
 */
enum Rating {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int _rating;

    Rating(int rating)
    {
        _rating = rating;
    }

    public int asInt() {
        return _rating;
    }

}
