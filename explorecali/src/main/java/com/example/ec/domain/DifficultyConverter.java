package com.example.ec.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Hibernate Converter for the Difficulty Enum to DB Column.
 *
 * Created by Mary Ellen Bowman
 */
@Converter(autoApply = true)
public class DifficultyConverter implements AttributeConverter<Difficulty, String> {
    @Override
    public String convertToDatabaseColumn(Difficulty difficulty) {
        return difficulty.name();
    }

    @Override
    public Difficulty convertToEntityAttribute(String s) {
        return Difficulty.valueOf(s);
    }
}
