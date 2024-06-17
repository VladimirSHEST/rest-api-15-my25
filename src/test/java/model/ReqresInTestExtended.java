package model;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    1. Make POST request to https://reqres.in/api/login
    with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check token is QpwL5tke4Pnpja7X4
 */
public class ReqresInTestExtended {
    @Test
    void loginWithoutModelTest(){
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token",is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void loginTest(){

        RequestLoginBodyModel loginBodyModel = new RequestLoginBodyModel();
        loginBodyModel.setEmail("eve.holt@reqres.in");
        loginBodyModel.setPassword("cityslicka");

        LoginResponsModel loginResponsModel = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(loginBodyModel)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsModel.class);

//        assertEquals("QpwL5tke4Pnpja7X4",loginResponsModel.getToken());
        assertThat(loginResponsModel.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}
