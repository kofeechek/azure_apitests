package tests;

import models.ActivitiesBodyModel;
import models.ActivitiesResponseModel;
import models.ActivitiesWrongBodyModel;
import models.ActivitiesWrongTestDataResponseModel;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.registration.RegistrationSpec.*;

public class AzureActivitiesApiTests extends TestBase {

//    @Test
//    public void addActivitiesSuccessTest() {
//
//
//        ActivitiesBodyModel data = ActivitiesBodyModel.builder()
//                .id(id)
//                .title(title)
//                .dueDate(dueDate)
//                .completed(completed)
//                .build();
//
//        ActivitiesResponseModel activitiesResponse = given(registrationRequestSpec)
//                .body(data)
//                .when()
//                .post("/api/v1/Activities")
//                .then()
//                .spec(successfulRegistrationResponseSpec)
//                .extract()
//                .as(ActivitiesResponseModel.class);
//
//        assertEquals(id, activitiesResponse.getId());
//        assertEquals(title, activitiesResponse.getTitle());
//        assertEquals(dueDate, activitiesResponse.getDueDate());
//        assertEquals(completed, activitiesResponse.getCompleted());
//    }
//
//
//    @Test
//    public void addActivitiesWrongTestDataTest() {
//
//
//        ActivitiesWrongBodyModel wrongData = ActivitiesWrongBodyModel.builder()
//                .id(id)
//                .title(title)
//                .dueDate(dueDate)
//                .completed(wrongCompleted)
//                .build();
//
//        ActivitiesWrongTestDataResponseModel activitiesWrongTestDataResponseModel = given(registrationRequestSpec)
//                .body(wrongData)
//                .when()
//                .post("/api/v1/Activities")
//                .then()
//                .spec(failedRegistrationResponseSpec)
//                .extract()
//                .as(ActivitiesWrongTestDataResponseModel.class);
//
//        assertEquals("https://tools.ietf.org/html/rfc7231#section-6.5.1", activitiesWrongTestDataResponseModel.getType());
//        assertEquals("One or more validation errors occurred.", activitiesWrongTestDataResponseModel.getTitle());
//    }

    @Test
    public void getAllActivitiesSuccessTest() {

        List<ActivitiesResponseModel> activities = given(registrationRequestSpec)
                .when()
                .get("/api/v1/Activities")
                .then()
                .spec(successfulGetResponseSpec)
                .extract()
                .jsonPath()
                .getList("", ActivitiesResponseModel.class);

        assertThat(activities).isNotEmpty(); //Проверяем, что вернулся список

        assertThat(activities) //Проверяем структуру объектов
                .allSatisfy(activity -> {
                    assertThat(activity.getId()).isNotNull().isPositive();
                    assertThat(activity.getTitle()).isNotNull().isNotBlank();
                    assertThat(activity.getCompleted()).isNotNull();
                    assertThat(activity.getDueDate()).isNotNull().isNotBlank();
                });

        activities.forEach(activity -> { //Проверяем формат даты
            assertDoesNotThrow(() -> Instant.parse(activity.getDueDate()));
        });

        assertThat(activities).hasSize(30); //Проверка количества возвращаемых элементов
    }
}


//        assertEquals(id, activitiesResponse.getId());
//        assertEquals(title, activitiesResponse.getTitle());
//        assertEquals(dueDate, activitiesResponse.getDueDate());
//        assertEquals(completed, activitiesResponse.getCompleted());


//POST с ошибкой (неверный формат данных, 400) +
//Получение всего списка
//Запрос на кривую ручку
//Пут запрос
//Delete