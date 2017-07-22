package com.mills.models.template;

import java.util.List;

public class MethodModel {

    private String _methodName;
    private List<Integer> _placeBells;

    public String getMethodName() {
        return _methodName;
    }

    public MethodModel setMethodName(String methodName) {
        _methodName = methodName;
        return this;
    }

    public List<Integer> getPlaceBells() {
        return _placeBells;
    }

    public MethodModel setPlaceBells(List<Integer> placeBells) {
        _placeBells = placeBells;
        return this;
    }
}
