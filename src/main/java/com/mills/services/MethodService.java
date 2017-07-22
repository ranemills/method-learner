package com.mills.services;

import com.mills.models.PlaceBellNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MethodService {

    private Collection<String> _methodNames;

    private final PlaceBellService _placeBellService;

    @Autowired
    private MethodService(PlaceBellService placeBellService)
    {
        _methodNames = new ArrayList<>();
        _placeBellService = placeBellService;
    }

    public void addMethod(String methodName, List<PlaceBellNumber> placeBells)
    {
        _methodNames.add(methodName);
        for(PlaceBellNumber placeBell : placeBells)
        {
            _placeBellService.addPlaceBell(methodName, placeBell);
        }
    }

    public Collection<String> getMethods()
    {
        return _methodNames;
    }

}
