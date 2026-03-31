package io.jayksss.api.common.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YnBooleanConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        String v = dbData.trim();
        if (v.equalsIgnoreCase("Y")) {
            return true;
        }
        if (v.equalsIgnoreCase("N")) {
            return false;
        }
        if (v.equals("1") || v.equalsIgnoreCase("true")) {
            return true;
        }
        if (v.equals("0") || v.equalsIgnoreCase("false")) {
            return false;
        }
        throw new IllegalArgumentException("Unsupported Y/N value: " + dbData);
    }
}

