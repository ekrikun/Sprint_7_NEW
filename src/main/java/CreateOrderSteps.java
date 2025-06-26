import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateOrderSteps {
    private static final String ORDERS = "/api/v1/orders";

    @Step("Создать заказ")
    public ValidatableResponse createOrder(String firstName, String lastName, String address, String metroStation,
                                           String phone, String rentTime, String deliveryDate, String comment, String[] color) {
        OrderData orderData = new OrderData(firstName, lastName, address, metroStation,
                phone, rentTime, deliveryDate, comment, color);

        return given().log().ifValidationFails()
                .contentType(ContentType.JSON)
                .baseUri(Config.HOST)
                .body(orderData)
                .when()
                .post(ORDERS)
                .then();
    }


}
