import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    private static final String COURIER = "/api/v1/courier";
    private static final String LOGIN = "/api/v1/courier/login";
    private static final String DELETE = "/api/v1/courier/{id}";

    @Step("Создать курьера")
    public ValidatableResponse createCourier(String login, String password, String firstName) {
        Courier courier = new Courier(login, password, firstName);
        return given().log().ifValidationFails()
                .contentType(ContentType.JSON)
                .baseUri(Config.HOST)
                .body(courier)
                .when()
                .post(COURIER)
                .then();
    }

    @Step("Авторизоваться под логином и паролем курьера")
    public ValidatableResponse loginCourier(String login, String password) {
        CourierLogin courierLogin = new CourierLogin(login, password);
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Config.HOST)
                .body(courierLogin)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Удалить курьера")
    public ValidatableResponse deleteCourier(int id) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Config.HOST)
                .pathParam("id", id)
                .when()
                .delete(DELETE)
                .then();
    }
}

// POJO класс для создания курьера
class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // геттеры и сеттеры
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
}

// POJO класс для логина курьера
class CourierLogin {
    private String login;
    private String password;

    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // геттеры и сеттеры
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}