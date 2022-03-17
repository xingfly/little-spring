package com.xingfly.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * PropertyValues
 *
 * @author supers
 * 2022/3/17
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<>(16);

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValues.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValues) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
