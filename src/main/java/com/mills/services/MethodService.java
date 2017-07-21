package com.mills.services;

import com.mills.PlaceBellNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodService {

    @Autowired
    private PlaceBellService _placeBellService;

    public void addMethod(String methodName, List<PlaceBellNumber> placeBells)
    {
        for(PlaceBellNumber placeBell : placeBells)
        {
            _placeBellService.addPlaceBell(methodName, placeBell);
        }
    }

}
