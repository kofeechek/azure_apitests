package tests;

import models.ActivitiesBodyModel;
import models.ActivitiesResponseModel;
import models.ActivitiesWrongBodyModel;
import models.ActivitiesWrongTestDataResponseModel;
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
import static specs.ActivitiesSpec.*;

public class AzureActivitiesApiTests extends TestBase {

    @Test
    public void getAllActivitiesSuccessTest() {

        List<ActivitiesResponseModel> activities = given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activities")
                .then()
                .spec(successfulResponseSpec)
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

    @Test
    public void getByExistIdSuccessTest() {

        given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activities/" + randomId)
                .then()
                .spec(successfulRegistrationResponseSpec);
    }

    @Test
    public void getByDoesntExistIdWrongTest() {

        given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activities/" + invalidId)
                .then()
                .spec(failedGetDoesntExistIdResponseSpec);
    }

    @Test
    public void getWrongPathTest() {

        given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activitie")
                .then()
                .statusCode(404);
    }

    @Test
    public void addActivitiesSuccessTest() {

        ActivitiesBodyModel data = ActivitiesBodyModel.builder()
                .id(id)
                .title(title)
                .dueDate(dueDate)
                .completed(completed)
                .build();

        ActivitiesResponseModel activitiesResponse = given(activitiesRequestSpec)
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


    @Test
    public void addActivitiesWrongTestDataTest() {

        ActivitiesWrongBodyModel wrongData = ActivitiesWrongBodyModel.builder()
                .id(id)
                .title(title)
                .dueDate(dueDate)
                .completed(wrongCompleted)
                .build();

        ActivitiesWrongTestDataResponseModel activitiesWrongTestDataResponseModel = given(activitiesRequestSpec)
                .body(wrongData)
                .when()
                .post("/api/v1/Activities")
                .then()
                .spec(failedRegistrationResponseSpec)
                .extract()
                .as(ActivitiesWrongTestDataResponseModel.class);

        assertEquals("https://tools.ietf.org/html/rfc7231#section-6.5.1", activitiesWrongTestDataResponseModel.getType());
        assertEquals("One or more validation errors occurred.", activitiesWrongTestDataResponseModel.getTitle());
    }


    @Test
    public void deleteSuccessTest() {

        given(activitiesRequestSpec)
                .when()
                .delete("/api/v1/Activities/" + randomId)
                .then()
                .spec(successfulResponseSpec);
    }

    @Test
    public void deleteNoIdWrongTest() {

        given(activitiesRequestSpec)
                .when()
                .delete("/api/v1/Activities/")
                .then()
                .spec(failedDeleteNoIdResponseSpec);
    }
}


