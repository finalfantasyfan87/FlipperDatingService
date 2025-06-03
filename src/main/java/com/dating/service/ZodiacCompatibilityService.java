package com.dating.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZodiacCompatibilityService {
    private static final Map<String,Integer> ZODIAC_COMPATABILITY_MAP = new HashMap<>();
    static {
        initializeCompatibilityScores();
    }

    private static void initializeCompatibilityScores() {
        //Fire signs
        addCompatibility("aries", "leo", 90);
        addCompatibility("aries", "sagittarius", 90);
        addCompatibility("leo", "sagittarius", 90);

        //water signs
        addCompatibility("cancer", "scorpio", 90);
        addCompatibility("cancer", "pisces", 90);
        addCompatibility("scorpio", "pisces", 90);

        //air signs
        addCompatibility("gemini", "libra", 90);
        addCompatibility("gemini", "aquarius", 90);
        addCompatibility("libra", "aquarius", 90);

        //earth signs
        addCompatibility("taurus", "virgo", 90);
        addCompatibility("taurus", "capricorn", 90);
        addCompatibility("virgo", "capricorn", 90);


    }

    private static String getCompatibilityKey(String sign1, String sign2){
        return (sign1.compareTo(sign2) <= 0) ? (sign1 + "_" + sign2) : (sign2 + "_" + sign1);

    }

    private static void addCompatibility(String sign1, String sign2, int score) {
        String compatibilityKey = getCompatibilityKey(sign1, sign2);
        ZODIAC_COMPATABILITY_MAP.put(compatibilityKey,score);
    }
}
