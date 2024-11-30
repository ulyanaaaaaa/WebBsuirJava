package com.bsuir.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubstringServiceTests {

    @Autowired
    private SubstringService substringService;

    @Test
    void testSearchSubstring() {
        assertTrue(substringService.searchSubstring("hello world", "world"));
        assertFalse(substringService.searchSubstring("hello world", "java"));
    }

    @Test
    void testToUpperCase() {
        assertEquals("HELLO WORLD", substringService.toUpperCase("hello world"));
        assertNull(substringService.toUpperCase(null));
    }
}
