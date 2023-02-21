package ru.job4j.ood.srp.formatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CalendarSerializer implements JsonSerializer<GregorianCalendar> {
    @Override
    public JsonElement serialize(GregorianCalendar gregorianCalendar, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return new JsonPrimitive(sdf.format(gregorianCalendar.getTime()));
    }
}
