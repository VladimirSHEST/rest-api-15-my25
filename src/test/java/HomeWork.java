import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    1. Make POST request to https://reqres.in/api/users
    with body { "name": "morpheus", "job": "leader" }
    2. post response { "name": "morpheus", "job": "leader", "id": "674", "createdAt": "2024-06-11T21:43:42.193Z" }
    3. Check name is morpheus
 */

public class HomeWork {
    @Test
    void createTest(){
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
}
