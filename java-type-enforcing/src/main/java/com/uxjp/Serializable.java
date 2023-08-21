package com.uxjp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class Serializable {
    private static final Map<String, Class<?>> fieldMappings = new HashMap<>();
    static {
        fieldMappings.put("field1", int.class);
        fieldMappings.put("field2", String.class);
    }

    public static void validateMapping(GSClass gsInstance) {
        for (Map.Entry<String, Class<?>> entry : fieldMappings.entrySet()) {
            String fieldName = entry.getKey();
            Class<?> expectedType = entry.getValue();

            try {
                Field gsField = GSClass.class.getDeclaredField(fieldName);
                Class<?> gsFieldType = gsField.getType();

                if (!expectedType.isAssignableFrom(gsFieldType)) {
                    throw new RuntimeException("Mapping error: " + fieldName);
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Field not found: " + fieldName);
            }
        }
    }
}
