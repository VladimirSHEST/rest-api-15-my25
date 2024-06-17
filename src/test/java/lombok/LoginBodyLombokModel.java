package lombok;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

/*
    1. Make POST request to https://reqres.in/api/login
    with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check token is QpwL5tke4Pnpja7X4
 */
public class LoginBodyLombokModel {
    @Test
    void loginTest(){                                                                      // с моделями
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

    @Test
    void allureLoginTest(){                                                           //с моделями и обычный аллюр
        LombokLoginBodyModel lombokLoginBodyModel = new LombokLoginBodyModel();
        lombokLoginBodyModel.setEmail("eve.holt@reqres.in");
        lombokLoginBodyModel.setPassword("cityslicka");

        LombokLoginResponsModel lombokLoginResponsModel = given()
                .filter( new AllureRestAssured())                                       // тут аллюр
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
    @Test
    void customAllureLoginTest(){                                                       //с моделями и красивый аллюр
        LombokLoginBodyModel lombokLoginBodyModel = new LombokLoginBodyModel();
        lombokLoginBodyModel.setEmail("eve.holt@reqres.in");
        lombokLoginBodyModel.setPassword("cityslicka");

        LombokLoginResponsModel lombokLoginResponsModel = given()
                .filter(withCustomTemplates())                                            // тут кастом аллюр
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
