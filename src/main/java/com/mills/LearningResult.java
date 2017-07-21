package com.mills;

import org.joda.time.DateTime;

/**
 * Created by ryan on 21/07/17.
 */
public class LearningResult {

    private final DateTime _dateTime;
    private final Rating _rating;

    private LearningResult(DateTime dateTime, Rating rating)
    {
        _dateTime = dateTime;
        _rating = rating;
    }

    public static LearningResult of(DateTime dateTime, Rating rating)
    {
        return new LearningResult(dateTime, rating);
    }

    public DateTime getDateTime() {
        return _dateTime;
    }

    public Rating getRating() {
        return _rating;
    }
}
