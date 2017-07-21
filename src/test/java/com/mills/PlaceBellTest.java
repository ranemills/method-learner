package com.mills;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.powerSet;
import static com.mills.PlaceBellNumber.FIVE;
import static org.junit.jupiter.api.Assertions.*;


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
        List<List<DateTime>> scenarios = new ArrayList<>();

        DateTime sevenDaysAgo = DateTime.now().minusDays(7);
        DateTime sixDaysAgo = DateTime.now().minusDays(6);
        DateTime fiveDaysAgo = DateTime.now().minusDays(5);
        DateTime fourDaysAgo = DateTime.now().minusDays(4);
        DateTime threeDaysAgo = DateTime.now().minusDays(3);
        DateTime twoDaysAgo = DateTime.now().minusDays(2);
        DateTime oneDayAgo = DateTime.now().minusDays(1);
        DateTime today = DateTime.now();

        Set<DateTime> availableDays = ImmutableSet.of(sevenDaysAgo, sixDaysAgo, fiveDaysAgo, fourDaysAgo, threeDaysAgo, twoDaysAgo, oneDayAgo, today);


        for(Set<DateTime> combination : Sets.powerSet(availableDays))
        {
            PlaceBell placeBell = new PlaceBell("Zanussi", FIVE);


            for(DateTime date : combination)
            {
                placeBell.addLearningResult(LearningResult.of(date, Rating.FIVE));
            }

            System.out.print(placeBell.getPlaceBellRating() + ", ");

            for(DateTime day : availableDays)
            {
                if(combination.contains(day))
                {
                    System.out.print(new Duration(day, today).getStandardDays()+1 + ", ");
                }
                else
                {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }

    }

}