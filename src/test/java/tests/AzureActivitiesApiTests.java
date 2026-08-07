package tests;

import models.ActivitiesBodyModel;
import models.ActivitiesResponseModel;
import models.ActivitiesWrongTestDataResponseModel;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.registration.RegistrationSpec.*;

public class AzureActivitiesApiTests extends TestBase {

    private int id;
    private String title;
    private int wrongTitle;
    private String dueDate;
    private boolean completed;


    @BeforeEach
    public void prepareTestData() {
        Faker faker = new Faker();
        id = faker.number().numberBetween(0, 1000);
        title = faker.text().text(10);
        wrongTitle = faker.number().numberBetween(0, 9);

        long randomMillis = faker.number().numberBetween(0L, System.currentTimeMillis());
        Instant instant = Instant.ofEpochMilli(randomMillis);
        dueDate = instant.toString();

        completed = faker.bool().bool();
    }


    @Test
    public void addActivitiesSuccessTest() {


        ActivitiesBodyModel data = new ActivitiesBodyModel();
        data.setId(id);
        data.setTitle(title);
        data.setDueDate(dueDate);
        data.setCompleted(completed);

        ActivitiesResponseModel activitiesResponse = given(registrationRequestSpec)
                .body(data)
                .when()
                .post("/api/v1/Activities")
                .then()
                .spec(successfulRegistrationResponseSpec)
                .extract()
                .as(ActivitiesResponseModel.class);

        assertEquals(id, activitiesResponse.getId());
        assertEquals(title, activitiesResponse.getTitle());
        assertEquals(dueDate, activitiesResponse.getDueDate());
        assertEquals(completed, activitiesResponse.getCompleted());
    }


//    @Test
//    public void addActivitiesWrongTestDataTest() {
//
//
//        ActivitiesBodyModel wrongData = ActivitiesBodyModel.builder()
//                .id(id)
//                .wrongTitle(wrongTitle)
//                .dueDate(dueDate)
//                .completed(completed)
//                .build();
//
//        ActivitiesWrongTestDataResponseModel activitiesWrongTestDataResponse = given(registrationRequestSpec)
//                .body(wrongData)
//                .when()
//                .post("/api/v1/Activities")
//                .then()
//                .spec(failedRegistrationResponseSpec)
//                .extract()
//                .as(ActivitiesWrongTestDataResponseModel.class);
//
//    }


}

//POST с ошибкой (неверный формат данных, 400)
//Получение всего списка
//Запрос на кривую ручку
//Пут запрос
//Delete