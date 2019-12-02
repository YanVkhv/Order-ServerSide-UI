package com.switchfully.order.gui.components.converters;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class FloatToIntegerConverter implements Converter<Double, Float> {

    @Override
    public Result<Float> convertToModel(Double aDouble, ValueContext valueContext) {
        return Result.ok(aDouble.floatValue());
    }

    @Override
    public Double convertToPresentation(Float aFloat, ValueContext valueContext) {
        return aFloat.doubleValue();
    }
}
