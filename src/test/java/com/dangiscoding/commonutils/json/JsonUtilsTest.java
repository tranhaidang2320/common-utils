package com.dangiscoding.commonutils.json;

import com.dangiscoding.commonutils.json.exception.ObjectFromJsonException;
import com.dangiscoding.commonutils.json.exception.ObjectToJsonException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    @Test
    void givenSomeValidObject_whenWriteJson_thenReturnJsonString() {
        TestObject testObject = new TestObject("dang", 27);
        String expectedJsonString = "{\"name\":\"dang\",\"age\":27}";
        String actualJsonString = JsonUtils.writeJson(testObject);
        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    void givenSomeInvalidObject_whenWriteJson_thenThrowObjectToJsonException() {
        final InvalidObject invalidObject = new InvalidObject("a", 15);
        assertThrows(ObjectToJsonException.class, () -> JsonUtils.writePrettyJson(invalidObject));
    }

    @Test
    void givenSomeValidObject_whenWritePrettyJson_thenReturnJsonString() {
        TestObject testObject = new TestObject("dang", 27);
        String expectedJsonString = """
                {
                  "name" : "dang",
                  "age" : 27
                }""";
        String actualJsonString = JsonUtils.writePrettyJson(testObject).replace("\r", "");
        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    void givenSomeInvalidObject_whenWritePrettyJson_thenThrowObjectToJsonException() {
        final InvalidObject invalidObject = new InvalidObject("a", 15);
        assertThrows(ObjectToJsonException.class, () -> JsonUtils.writeJson(invalidObject));
    }

    @Test
    void givenSomeValidJsonString_whenReadJson_thenReturnValidObject() {
        String jsonString = "{\"name\":\"John Doe\",\"age\":27}";
        TestObject expectedObject = new TestObject("John Doe", 27);
        TestObject actualObject = JsonUtils.readFromJson(jsonString, TestObject.class);
        assertEquals(expectedObject, actualObject);
    }

    @Test
    void givenSomeInvalidJsonString_whenReadJson_thenThrowJsonToObjectException() {
        final var invalidJsonString = "{\"name\":\"John,\"age\":27}";
        assertThrows(ObjectFromJsonException.class, () -> JsonUtils.readFromJson(invalidJsonString, TestObject.class));
    }


    private record TestObject(String name, int age) {
    }

    @SuppressWarnings("all")
    private static class InvalidObject {
        public InvalidObject(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        private final String name;
        private final Integer age;
    }
}