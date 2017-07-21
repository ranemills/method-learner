package com.mills.services;

import com.mills.models.LearningResult;
import com.mills.models.PlaceBell;
import com.mills.models.PlaceBellNumber;
import com.mills.models.Rating;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlaceBellServiceTest {

    private static final String METHOD_NAME_1 = "methodName";

    @Autowired
    private PlaceBellService _placeBellService;

    @Before
    public void clearContext()
    {
        _placeBellService.clearContext();
    }

    @Test
    public void canAddAndRetrievePlaceBells()
    {
        _placeBellService.addPlaceBell("methodName", PlaceBellNumber.TWO);
        _placeBellService.addPlaceBell("anotherMethodName", PlaceBellNumber.TWO);

        Collection<PlaceBell> placeBells1 = _placeBellService.getPlaceBellsForMethod("methodName");
        assertThat(placeBells1).containsExactly(new PlaceBell("methodName", PlaceBellNumber.TWO));

        Collection<PlaceBell> placeBells2 = _placeBellService.getPlaceBellsForMethod("anotherMethodName");
        assertThat(placeBells2).containsExactly(new PlaceBell("anotherMethodName", PlaceBellNumber.TWO));
    }

    @Test
    public void getNextPlaceBellWithOnePlaceBell()
    {
        _placeBellService.addPlaceBell(METHOD_NAME_1, PlaceBellNumber.TWO);

        PlaceBell placeBell = _placeBellService.getNextPlaceBell();
        assertThat(placeBell).isEqualTo(new PlaceBell(METHOD_NAME_1, PlaceBellNumber.TWO));
    }

    @Test
    public void getNextPlaceBellWithTwoPlaceBellsChoosesHighestRating()
    {
        _placeBellService.addPlaceBell("methodName", PlaceBellNumber.TWO);
        _placeBellService.addPlaceBell("anotherMethodName", PlaceBellNumber.TWO);

        _placeBellService.addLearningResultForPlaceBell(METHOD_NAME_1, PlaceBellNumber.TWO, LearningResult.of(DateTime.now(), Rating.FIVE));

        assertThat(_placeBellService.getNextPlaceBell()).isEqualTo(new PlaceBell(METHOD_NAME_1, PlaceBellNumber.TWO));
    }

    @Test
    public void getNextPlaceBellWithMethod()
    {
        _placeBellService.addPlaceBell("methodName", PlaceBellNumber.TWO);
        _placeBellService.addPlaceBell("anotherMethodName", PlaceBellNumber.TWO);

        assertThat(_placeBellService.getNextPlaceBell(METHOD_NAME_1)).isEqualTo(new PlaceBell(METHOD_NAME_1, PlaceBellNumber.TWO));
    }

}
