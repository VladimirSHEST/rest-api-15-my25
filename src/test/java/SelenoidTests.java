import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SelenoidTests {
    /*
 1. Сделать запрос по адресу https://selenoid.autotests.cloud/status
 2. Получить ответ { total: 20, used: 0, queued: 0, pending: 0, browsers:
    { chrome: { 100.0: { }, 120.0: { }, 121.0: { }, 122.0: { }, 99.0: { } },
     firefox: { 122.0: { }, 123.0: { } }, opera: { 106.0: { }, 107.0: { } } } }
 3. И в этом ответе делаем проверку например тотал равен 200
   */

    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalAndStatus() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotalWithGiven(){
        given()
                .log().all()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void checkTotalWithSomeLog(){
        given()
                .when()
                .log().uri()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }
}

