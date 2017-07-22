package com.mills.models.template;

import com.mills.models.PlaceBellNumber;

public class PlaceBellResult {

    private int _value;
    private String _methodName;
    private PlaceBellNumber _placeBell;

    public int getValue() {
        return _value;
    }

    public void setValue(int value) {
        _value = value;
    }

    public String getMethodName()
    {
        return _methodName;
    }

    public void setMethodName(String methodName)
    {
        _methodName = methodName;
    }

    public PlaceBellNumber getPlaceBell()
    {
        return _placeBell;
    }

    public void setPlaceBell(PlaceBellNumber placeBell)
    {
        _placeBell = placeBell;
    }
}
