package com.mills;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.mills.PlaceBellNumber.FIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;


class PlaceBellTest {

    private static final String ZANUSSI = "Zanussi";

    @Test
    void noResultsGivesRatingOfZero()
    {
        PlaceBell placeBell = new PlaceBell(ZANUSSI, FIVE);

        assertEquals(0, placeBell.getPlaceBellRating());
    }

    @Test
    void fiveDaysOfTopRatingGivesRatingOfOneHundred()
    {
        PlaceBell placeBell = new PlaceBell(ZANUSSI, FIVE);

        placeBell.addLearningResult(LearningResult.of(DateTime.now().minusDays(5), Rating.FIVE));
        placeBell.addLearningResult(LearningResult.of(DateTime.now().minusDays(4), Rating.FIVE));
        placeBell.addLearningResult(LearningResult.of(DateTime.now().minusDays(3), Rating.FIVE));
        placeBell.addLearningResult(LearningResult.of(DateTime.now().minusDays(2), Rating.FIVE));
        placeBell.addLearningResult(LearningResult.of(DateTime.now().minusDays(1), Rating.FIVE));

        assertEquals(100, placeBell.getPlaceBellRating());
    }

    @Test
//    @Ignore
    void loggingWithTopRating()
    {

        Set<DateTime> availableDays = ImmutableSet.of(DateTime.now().minusDays(7),
                                                      DateTime.now().minusDays(6),
                                                      DateTime.now().minusDays(5),
                                                      DateTime.now().minusDays(4),
                                                      DateTime.now().minusDays(3),
                                                      DateTime.now().minusDays(2),
                                                      DateTime.now().minusDays(1),
                                                      DateTime.now());


        for (Set<DateTime> combination : Sets.powerSet(availableDays)) {
            PlaceBell placeBell = new PlaceBell("Zanussi", FIVE);


            for (DateTime date : combination) {
                placeBell.addLearningResult(LearningResult.of(date, Rating.FIVE));
            }

            System.out.print(placeBell.getPlaceBellRating() + ", ");

            for (DateTime day : availableDays) {
                if (combination.contains(day)) {
                    System.out.print(new Duration(day, DateTime.now()).getStandardDays() + 1 + ", ");
                } else {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }

    }

}