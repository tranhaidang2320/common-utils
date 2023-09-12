package com.dangiscoding.commonutils.json;

import com.dangiscoding.commonutils.json.exception.ObjectFromJsonException;
import com.dangiscoding.commonutils.json.exception.ObjectToJsonException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
                {\r
                  "name" : "dang",\r
                  "age" : 27\r
                }""";
        String actualJsonString = JsonUtils.writePrettyJson(testObject);
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



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class TestObject {
        public String name;
        public Integer age;
    }

    @RequiredArgsConstructor
    private static class InvalidObject {
        private final String name;
        private final Integer age;
    }
}