package com.example.web.controller;

import com.example.web.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class StringController {

    @Autowired
    private StringService stringService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/api/string/contains")
    @ResponseBody
    public boolean contains(@RequestParam String mainString, @RequestParam String substring) {
        return stringService.containsSubstring(mainString, substring);
    }
}