package com.mills.services;

import com.google.common.collect.ImmutableList;
import com.mills.Main;
import com.mills.PlaceBellNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MethodServiceTest {

    @Autowired
    private MethodService _methodService;

    @Test
    public void canAddMethod()
    {
        // Given
        _methodService.addMethod("methodName", ImmutableList.of(PlaceBellNumber.TWO,
                                                                PlaceBellNumber.THREE,
                                                                PlaceBellNumber.FOUR));
        // Then
        Collection<String> methods = _methodService.getMethods();
        assertThat(methods).containsExactly("methodName");
    }

}