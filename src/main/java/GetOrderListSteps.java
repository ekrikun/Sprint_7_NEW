import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetOrderListSteps {
    private static final String ORDERS = "/api/v1/orders";

    @Step("Получить список заказов")
    public ValidatableResponse getOrderList(Integer courierId, String nearestStation, Integer limit, Integer page) {
        OrderListFilter filter = new OrderListFilter(courierId, nearestStation, limit, page);

        return given().log().ifValidationFails()
                .contentType(ContentType.JSON)
                .baseUri(Config.HOST)
                .body(filter)
                .when()
                .get(ORDERS)
                .then();
    }
}