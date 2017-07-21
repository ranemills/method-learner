package com.mills.services;

import com.mills.LearningResult;
import com.mills.PlaceBell;
import com.mills.PlaceBellNumber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlaceBellService {

    private List<PlaceBell> _placeBells;

    private PlaceBellService()
    {
        _placeBells = new ArrayList<>();
    }

    public void clearContext()
    {
        _placeBells = new ArrayList<>();
    }

    public void addPlaceBell(String methodName, PlaceBellNumber placeBellNumber)
    {
        _placeBells.add(new PlaceBell(methodName, placeBellNumber));
    }

    public Collection<PlaceBell> getPlaceBellsForMethod(String methodName)
    {
        return _placeBells.stream().filter(pb -> pb.getMethodName().equals(methodName)).collect(Collectors.toList());
    }

    public PlaceBell getNextPlaceBell(String... methodNames)
    {
        List<String> methods = Arrays.asList(methodNames);

        Stream<PlaceBell> stream = _placeBells.stream();

        if (!methods.isEmpty()) {
            stream = stream.filter(pb -> methods.contains(pb.getMethodName()));
        }

        Optional<PlaceBell> placeBellOptional = stream.sorted(Comparator.comparing(PlaceBell::getPlaceBellRating).reversed())
                                                      .findFirst();

        return placeBellOptional.orElse(null);
    }

    public void addLearningResultForPlaceBell(String methodName, PlaceBellNumber placeBellNumber, LearningResult result)
    {
        _placeBells.stream()
                   .filter(pb -> pb.getMethodName().equals(methodName) &&
                                 pb.getPlaceBellNumber().equals(placeBellNumber))
                   .findFirst()
                   .ifPresent(placeBell -> placeBell.addLearningResult(result));
    }

}
