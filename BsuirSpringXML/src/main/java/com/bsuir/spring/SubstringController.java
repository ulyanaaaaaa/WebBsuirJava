package com.bsuir.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubstringController {

    @Autowired
    private SubstringService substringService;

    @GetMapping("/search")
    public boolean searchSubstring(@RequestParam String fullString, @RequestParam String substring) {
        return substringService.searchSubstring(fullString, substring);
    }

    @PostMapping("/uppercase")
    public String toUpperCase(@RequestBody String inputString) {
        return substringService.toUpperCase(inputString);
    }
}