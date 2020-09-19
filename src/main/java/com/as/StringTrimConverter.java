package com.as;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringTrimConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData.trim();
    }

}