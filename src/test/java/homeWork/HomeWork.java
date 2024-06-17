package homeWork;

import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 Дом задание: сделать автотесты с моделями, спецификациями, алюром, jenkins
    1. Make POST request to https://reqres.in/api/users
    with body { "name": "morpheus", "job": "leader" }
    2. post response { "name": "morpheus", "job": "leader", "id": "674", "createdAt": "2024-06-11T21:43:42.193Z" }
    3. Check name is morpheus
 */

public class HomeWork {
    @Test
    void createTest() {
// надо сделать проверку одинаковое ли имя в запросе и ответе
// переводим запрос в строку при помощи регул выражений
// делаем метод гивен() а в тело запроса подставляем нашу строку,
// а в тело ответа подставляем значения из ответа
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void createTestModel() {
        RequestModel requestModel = new RequestModel();
        requestModel.setName("morpheus");


        ResponseModel responseModel = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestModel)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(ResponseModel.class);

        assertThat(responseModel.getName()).isEqualTo("morpheus");


    }
}
