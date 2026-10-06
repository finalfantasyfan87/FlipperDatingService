package com.dating;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DatingApplication {

	Faker faker = new Faker();
	private List<String> zodiacSigns = List.of("Aries", "Taurus", "Aries", "Taurus", "Gemini", "Cancer",
			"Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius","Pisces");
	private String userName = faker.name().username();
	private String email = faker.internet().emailAddress();
	private String id = faker.numerify("13252350sgew353tyugh");
	private String password = faker.gameOfThrones().house();
	private String gender = faker.demographic().sex();
	private String sexuality = faker.demographic().sex();
	private final List<String> interests = Arrays.asList(faker.book().title(),faker.gameOfThrones().character(), faker.gameOfThrones().quote());
	private String zodiacSign = zodiacSigns.get(faker.random().nextInt(0,11));

	public static void main(String[] args) {
		SpringApplication.run(DatingApplication.class, args);
	}

}
