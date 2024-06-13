package lombok;

import model.LoginBodyModel;
import model.LoginResponsModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

/*
    1. Make POST request to https://reqres.in/api/login
    with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check token is QpwL5tke4Pnpja7X4
 */
public class LoginBodyLombokModel {
    @Test
    void loginTest(){

        LombokLoginBodyModel lombokLoginBodyModel = new LombokLoginBodyModel();
        lombokLoginBodyModel.setEmail("eve.holt@reqres.in");
        lombokLoginBodyModel.setPassword("cityslicka");

        LombokLoginResponsModel lombokLoginResponsModel = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(lombokLoginBodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LombokLoginResponsModel.class);

        assertThat(lombokLoginResponsModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}
