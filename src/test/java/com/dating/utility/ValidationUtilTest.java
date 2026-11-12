package com.dating.utility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Behavior tests for {@link ValidationUtil#isEmailValid(String)}. */
class ValidationUtilTest {

    @ParameterizedTest(name = "accepts \"{0}\"")
    @ValueSource(strings = {
            "love_hurts943@example.com",
            "first.last@sub.example.org",
            "a-b@example.co"
    })
    @DisplayName("Ordinary email addresses are accepted")
    void wellFormedEmailsAreAccepted(String email) {
        assertTrue(ValidationUtil.isEmailValid(email));
    }

    @ParameterizedTest(name = "rejects \"{0}\"")
    @ValueSource(strings = {
            "plainaddress",
            "missing-domain@",
            "@no-user.com",
            "two@@example.com"
    })
    @DisplayName("Malformed email addresses are rejected")
    void malformedEmailsAreRejected(String email) {
        assertFalse(ValidationUtil.isEmailValid(email));
    }

    @Test
    @DisplayName("A null email is rejected")
    void nullEmailIsRejected() {
        assertFalse(ValidationUtil.isEmailValid(null));
    }
}
