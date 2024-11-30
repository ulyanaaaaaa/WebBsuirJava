package com.bsuir.spring;

import org.springframework.stereotype.Service;

@Service
public class SubstringService {

    public boolean searchSubstring(String fullString, String substring) {
        if (fullString == null || substring == null) {
            return false;
        }
        return fullString.contains(substring);
    }

    public String toUpperCase(String inputString) {
        if (inputString == null) {
            return null;
        }
        return inputString.toUpperCase();
    }
}