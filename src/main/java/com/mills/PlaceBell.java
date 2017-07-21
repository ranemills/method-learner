package com.mills;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class PlaceBell {
    private static final int DAYS_BACK_TO_GO = 7;
    private static final double MAX_RATING_WITH_ALGORITHM = 3.62685925185925;
    private static final double RATING_FOR_100 = (0.6)*MAX_RATING_WITH_ALGORITHM;

    private String _methodName;
    private PlaceBellNumber _placeBellNumber;
    private List<LearningResult> _learningResults;

    public PlaceBell(String methodName, PlaceBellNumber placeBellNumber)
    {
        _methodName = methodName;
        _placeBellNumber = placeBellNumber;
        _learningResults = new ArrayList<>();
    }

    public void addLearningResult(LearningResult learningResult)
    {
        _learningResults.add(learningResult);
    }

    public double getPlaceBellRating()
    {
        if(!_learningResults.isEmpty())
        {
            DateTime now = DateTime.now();

            double sumLastWeek =
                _learningResults.stream()
                                .collect(Collectors.groupingBy(lr -> lr.getDateTime().withTimeAtStartOfDay(),
                                                               Collectors.averagingInt(lr -> lr.getRating().asInt())))
                                .entrySet().stream()
                                .filter(e -> e.getKey().isAfter(now.minusDays(DAYS_BACK_TO_GO+1).withTimeAtStartOfDay()))
                                .mapToDouble(e -> e.getValue() / (DAYS_BACK_TO_GO+1 - new Duration(now, e.getKey()).getStandardDays()) )
                                .sum();

            return (int) Math.round(Math.min(RATING_FOR_100, sumLastWeek)*100/RATING_FOR_100 );
        }
        return 0;

    }

}

class LearningResult {

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

enum PlaceBellNumber {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    ELEVEN,
    TWELVE;
}
