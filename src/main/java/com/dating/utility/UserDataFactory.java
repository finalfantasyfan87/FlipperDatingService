package com.dating.utility;

import com.dating.model.User;
import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;

public final class UserDataFactory {
    private static final Faker FAKER = new Faker();
    private static final List<String> ZODIAC_SIGNS = List.of(
            "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
            "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces");

    private UserDataFactory() {
    }

    public static User createDemoUser() {
        User user = new User();
        user.setId(FAKER.numerify("13252350sgew353tyugh"));
        user.setUserName(FAKER.name().username());
        user.setEmail(FAKER.internet().emailAddress());
        user.setPassword(FAKER.gameOfThrones().house());
        user.setGender(FAKER.demographic().sex());
        user.setInterests(Arrays.asList(
                FAKER.book().title(),
                FAKER.gameOfThrones().character(),
                FAKER.gameOfThrones().quote()));
        user.setZodiacSign(ZODIAC_SIGNS.get(FAKER.random().nextInt(0, ZODIAC_SIGNS.size() - 1)));
        return user;
    }
}
