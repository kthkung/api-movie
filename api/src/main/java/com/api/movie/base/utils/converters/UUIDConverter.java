package com.api.movie.base.utils.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Converter
public class UUIDConverter implements AttributeConverter<UUID, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(UUID uuid) {
        return uuid.toString().replace("-", "").getBytes(StandardCharsets.US_ASCII);
    }

    @Override
    public UUID convertToEntityAttribute(byte[] s) {
        return UUID.fromString(new String(s, StandardCharsets.US_ASCII));
    }
}
