package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.ActivitiesBodyModel;
import models.ActivitiesResponseModel;
import models.ActivitiesWrongBodyModel;
import models.ActivitiesWrongTestDataResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ActivitiesSpec.*;

@Feature("Тесты на Activities")
public class AzureActivitiesApiTests extends TestBase {

    @Test
    @DisplayName("[API] Успешное получение всех записей Activities")
    @Severity(SeverityLevel.NORMAL)
    @Owner("tsvetlitskaya")
    public void getAllActivitiesSuccessTest() {

        List<ActivitiesResponseModel> activities = given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activities")
                .then()
                .spec(successfulResponseSpec)
                .extract()
                .jsonPath()
                .getList("", ActivitiesResponseModel.class);

        step("Проверить, что вернулся список", () -> {
            assertThat(activities).isNotEmpty();
        });
        step("Проверить структуру объектов", () -> {
            assertThat(activities)
                    .allSatisfy(activity -> {
                        assertThat(activity.getId()).isNotNull().isPositive();
                        assertThat(activity.getTitle()).isNotNull().isNotBlank();
                        assertThat(activity.getCompleted()).isNotNull();
                        assertThat(activity.getDueDate()).isNotNull().isNotBlank();
                    });
        });
        step("Проверить формат даты", () -> {
            activities.forEach(activity -> {
                assertDoesNotThrow(() -> Instant.parse(activity.getDueDate()));
            });
        });
        step("Проверить количество возвращаемых элементов", () -> {
            assertThat(activities).hasSize(30);
        });
    }

    @Test
    @DisplayName("[API] Успешное получение записи Activities по существующему id")
    @Severity(SeverityLevel.MINOR)
    @Owner("tsvetlitskaya")
    public void getByExistIdSuccessTest() {

        given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activities/" + randomId)
                .then()
                .spec(successfulRegistrationResponseSpec);
    }

    @Test
    @DisplayName("[API] Ошибка при попытке запроса записи Activities по несуществующему id")
    @Severity(SeverityLevel.MINOR)
    @Owner("tsvetlitskaya")
    public void getByDoesntExistIdWrongTest() {

        given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activities/" + invalidId)
                .then()
                .spec(failedGetDoesntExistIdResponseSpec);
    }

    @Test
    @DisplayName("[API] Ошибка при попытке запроса на несуществующий endpoint Activities")
    @Severity(SeverityLevel.MINOR)
    @Owner("tsvetlitskaya")
    public void getWrongPathTest() {

        given(activitiesRequestSpec)
                .when()
                .get("/api/v1/Activitie")
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("[API] Успешное добавление записи Activities")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("tsvetlitskaya")
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

        step("Проверить соответствие id, title, dueDate, completed", () -> {
            assertEquals(id, activitiesResponse.getId());
            assertEquals(title, activitiesResponse.getTitle());
            assertEquals(dueDate, activitiesResponse.getDueDate());
            assertEquals(completed, activitiesResponse.getCompleted());
        });
    }


    @Test
    @DisplayName("[API] Ошибка при попытке добавления записи Activities с неверным типом данных в completed")
    @Severity(SeverityLevel.MINOR)
    @Owner("tsvetlitskaya")
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

        step("Проверить соответствие type, title", () -> {
            assertEquals("https://tools.ietf.org/html/rfc7231#section-6.5.1", activitiesWrongTestDataResponseModel.getType());
            assertEquals("One or more validation errors occurred.", activitiesWrongTestDataResponseModel.getTitle());
        });
    }


    @Test
    @DisplayName("[API] Успешное удаление записи Activities")
    @Severity(SeverityLevel.MINOR)
    @Owner("tsvetlitskaya")
    public void deleteSuccessTest() {

        given(activitiesRequestSpec)
                .when()
                .delete("/api/v1/Activities/" + randomId)
                .then()
                .spec(successfulResponseSpec);
    }

    @Test
    @DisplayName("[API] Ошибка при попытке удаления записи Activities без id в endpoint")
    @Severity(SeverityLevel.MINOR)
    @Owner("tsvetlitskaya")
    public void deleteNoIdWrongTest() {

        given(activitiesRequestSpec)
                .when()
                .delete("/api/v1/Activities/")
                .then()
                .spec(failedDeleteNoIdResponseSpec);
    }
}


