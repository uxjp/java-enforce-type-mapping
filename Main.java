import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");

        GSClass gsInstance = new GSClass();
        gsInstance.setField1(42);
        gsInstance.setField2("Hello");
        
        Serializable.validateMapping(gsInstance);
    }

    static class GSClass {
        private int field1;
        private String field2;

        public void setField1(int value) {
            field1 = value;
        }

        public void setField2(String value) {
            field2 = value;
        }
    }

    static class Serializable {
        private static final Map<String, Class<?>> fieldMappings = new HashMap<>();
        static {
            fieldMappings.put("field1", Float.class);
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
}
