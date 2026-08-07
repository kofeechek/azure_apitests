package tests;

import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net";

    }
}

