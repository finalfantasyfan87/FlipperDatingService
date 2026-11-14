package com.dating.controller;

import com.dating.service.ZodiacCompatibilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/userapi")
public class CompatibilityController {

    private final ZodiacCompatibilityService zodiacCompatibilityService;

    public CompatibilityController(ZodiacCompatibilityService zodiacCompatibilityService) {
        this.zodiacCompatibilityService = zodiacCompatibilityService;
    }

    @GetMapping("/compatibility")
    public ResponseEntity<Map<String, Integer>> getCompatibility(@RequestParam String first,
                                                                 @RequestParam String second) {
        int score = zodiacCompatibilityService.getCompatibilityScore(first, second);
        return ResponseEntity.ok(Map.of("score", score));
    }
}
