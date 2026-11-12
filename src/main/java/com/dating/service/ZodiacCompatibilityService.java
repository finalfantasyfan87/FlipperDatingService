package com.dating.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class ZodiacCompatibilityService {

    private static final Set<String> VALID_SIGNS = Set.of(
            "aries", "taurus", "gemini", "cancer", "leo", "virgo",
            "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces");

    private static final Map<String, Integer> ZODIAC_COMPATABILITY_MAP = new HashMap<>();

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

    /**
     * Returns the compatibility score for two zodiac signs. The result does not depend on
     * argument order. Signs are matched case-insensitively with surrounding spaces ignored.
     *
     * @throws IllegalArgumentException if either sign is null, blank, or not a zodiac sign
     */
    public int getCompatibilityScore(String sign1, String sign2) {
        String first = normalize(sign1);
        String second = normalize(sign2);
        if (first.equals(second)) {
            return 60;
        }
        return ZODIAC_COMPATABILITY_MAP.getOrDefault(getCompatibilityKey(first, second), 50);
    }

    private static String normalize(String sign) {
        if (sign == null || sign.isBlank()) {
            throw new IllegalArgumentException("Zodiac sign is required");
        }
        String normalized = sign.trim().toLowerCase(Locale.ROOT);
        if (!VALID_SIGNS.contains(normalized)) {
            throw new IllegalArgumentException("Unknown zodiac sign: " + sign);
        }
        return normalized;
    }

    private static String getCompatibilityKey(String sign1, String sign2) {
        return (sign1.compareTo(sign2) <= 0) ? (sign1 + "_" + sign2) : (sign2 + "_" + sign1);
    }

    private static void addCompatibility(String sign1, String sign2, int score) {
        String compatibilityKey = getCompatibilityKey(sign1, sign2);
        ZODIAC_COMPATABILITY_MAP.put(compatibilityKey, score);
    }
}
