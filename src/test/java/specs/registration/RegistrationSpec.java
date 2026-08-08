package specs.registration;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class RegistrationSpec {
    public static RequestSpecification activitiesRequestSpec = with()
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification successfulRegistrationResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/azure_activities_response_schema.json")).build();

    public static ResponseSpecification successfulResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200).build();

    public static ResponseSpecification failedRegistrationResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400).build();

    public static ResponseSpecification failedGetDoesntExistIdResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404).build();
}
