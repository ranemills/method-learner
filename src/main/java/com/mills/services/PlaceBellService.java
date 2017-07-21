package com.mills.services;

import com.mills.PlaceBell;
import com.mills.PlaceBellNumber;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceBellService {

    private List<PlaceBell> _placeBells;

    public void addPlaceBell(String methodName, PlaceBellNumber placeBellNumber)
    {
        _placeBells.add(new PlaceBell(methodName, placeBellNumber));
    }

    public Collection<PlaceBell> getPlaceBellsForMethod(String methodName)
    {
        return _placeBells.stream().filter(pb -> pb.getMethodName().equals(methodName)).collect(Collectors.toList());
    }

    public PlaceBell getNextPlaceBell()
    {
        Optional<PlaceBell> placeBellOptional = _placeBells.stream()
                                                           .sorted(Comparator.comparing(PlaceBell::getPlaceBellRating))
                                                           .findFirst();

        return placeBellOptional.orElse(null);
    }

}
