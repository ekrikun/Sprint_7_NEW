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

    public class OrderListFilter {
        private Integer courierId;
        private String nearestStation;
        private Integer limit;
        private Integer page;

        public OrderListFilter() {
        }

        public OrderListFilter(Integer courierId, String nearestStation, Integer limit, Integer page) {
            this.courierId = courierId;
            this.nearestStation = nearestStation;
            this.limit = limit;
            this.page = page;
        }

        public Integer getCourierId() {
            return courierId;
        }

        public void setCourierId(Integer courierId) {
            this.courierId = courierId;
        }

        public String getNearestStation() {
            return nearestStation;
        }

        public void setNearestStation(String nearestStation) {
            this.nearestStation = nearestStation;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }
    }
}