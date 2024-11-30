package com.example.web.service;

import org.springframework.stereotype.Service;

@Service
public class StringService {

    public boolean containsSubstring(String mainString, String substring) {
        return mainString != null && substring != null && mainString.contains(substring);
    }

    public String convertToUpperCase(String input) {
        return input != null ? input.toUpperCase() : null;
    }
}
