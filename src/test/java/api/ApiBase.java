package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {
    final String BASE_URI = "https://demoqa.com/";
    private final RequestSpecification spec;

    public ApiBase() {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    public ApiBase(String token) {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", token)
                .build();
    }

    protected Response getRequest(String endPoint, int responseCode) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    protected Response getRequestWithParam(String endPoint, int responseCode, String paramName, String value) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam(paramName, value)
                .log().all()
                .get(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    protected Response postRequest(String endPoint, int responseCode, Object body) {
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    protected Response putRequest(String endPoint, int responseCode, Object body) {
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .put(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }

    protected Response deleteRequest(String endPoint, int responseCode, String value) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("UserId", value)
                .log().all()
                .delete(endPoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(responseCode);
        return response;
    }
}
