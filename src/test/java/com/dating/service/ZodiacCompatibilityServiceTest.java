package com.dating.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Behavior tests for {@link ZodiacCompatibilityService#getCompatibilityScore(String, String)}.
 * The prompt must name this method and its exception type so that any correct
 * implementation can be checked against these tests.
 */
class ZodiacCompatibilityServiceTest {

    private final ZodiacCompatibilityService service = new ZodiacCompatibilityService();

    @ParameterizedTest(name = "{0} and {1} score 90 in both argument orders")
    @CsvSource({"aries,leo", "cancer,pisces", "gemini,aquarius", "taurus,capricorn"})
    @DisplayName("Signs listed in the compatibility table score 90 regardless of order")
    void tablePairsScoreNinetyInEitherOrder(String first, String second) {
        assertEquals(90, service.getCompatibilityScore(first, second));
        assertEquals(90, service.getCompatibilityScore(second, first));
    }

    @Test
    @DisplayName("Two different signs that are not in the table score 50 in either order")
    void unlistedPairScoresFifty() {
        assertEquals(50, service.getCompatibilityScore("aries", "cancer"));
        assertEquals(50, service.getCompatibilityScore("cancer", "aries"));
    }

    @Test
    @DisplayName("A sign paired with itself scores 60")
    void sameSignScoresSixty() {
        assertEquals(60, service.getCompatibilityScore("leo", "leo"));
    }

    @Test
    @DisplayName("Letter case and surrounding spaces are ignored")
    void caseAndWhitespaceAreIgnored() {
        assertEquals(90, service.getCompatibilityScore("  ARIES ", "Leo"));
    }

    @ParameterizedTest(name = "rejects unknown sign \"{0}\"")
    @ValueSource(strings = {"notasign", "", "   "})
    @DisplayName("Unknown or blank signs are rejected with IllegalArgumentException")
    void unknownOrBlankSignIsRejected(String badSign) {
        assertThrows(IllegalArgumentException.class,
                () -> service.getCompatibilityScore(badSign, "leo"));
    }

    @ParameterizedTest(name = "rejects null sign in position {arguments}")
    @NullSource
    @DisplayName("A null sign is rejected with IllegalArgumentException")
    void nullSignIsRejected(String nullSign) {
        assertThrows(IllegalArgumentException.class,
                () -> service.getCompatibilityScore(nullSign, "leo"));
    }
}
