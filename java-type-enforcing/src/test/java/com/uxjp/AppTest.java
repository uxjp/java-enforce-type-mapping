package com.uxjp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void testAdd() {
        assertEquals(8, 4 + 4); // This test will pass
    }


    @Test
    public void testValidateMappingWithValidFields() {
        GSClass gsInstance = new GSClass();
        gsInstance.setField1(new Float(23.54));
        gsInstance.setField2("Hello");

        assertDoesNotThrow(() -> Serializable.validateMapping(gsInstance));
    }
}
