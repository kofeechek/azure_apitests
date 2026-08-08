package tests;

import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.time.Instant;

public class TestBase {

    protected int id;
    protected String title;
    protected String dueDate;
    protected boolean completed;
    protected String wrongCompleted;

    protected int randomId;
    protected int invalidId;

    @BeforeEach
    public void prepareTestData() {
        Faker faker = new Faker();

        id = faker.number().numberBetween(0, 1000);
        title = faker.text().text(10);

        long randomMillis = faker.number().numberBetween(0L, System.currentTimeMillis());
        Instant instant = Instant.ofEpochMilli(randomMillis);
        dueDate = instant.toString();

        completed = faker.bool().bool();
        wrongCompleted = faker.text().text(10);

        randomId = faker.number().numberBetween(1, 30);
        invalidId = faker.number().numberBetween(31, 1000);
    }

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

    }
}



