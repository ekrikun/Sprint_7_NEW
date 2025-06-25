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
    public class OrderData {
        private String firstName;
        private String lastName;
        private String address;
        private String metroStation;
        private String phone;
        private String rentTime;
        private String deliveryDate;
        private String comment;
        private String color;

        public OrderData() {
            // пустой конструктор нужен для сериализации
        }

        public OrderData(String firstName, String lastName, String address, String metroStation,
                         String phone, String rentTime, String deliveryDate, String comment, String color) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.metroStation = metroStation;
            this.phone = phone;
            this.rentTime = rentTime;
            this.deliveryDate = deliveryDate;
            this.comment = comment;
            this.color = color;
        }

        public OrderData(String firstName, String lastName, String address, String metroStation, String phone, String rentTime, String deliveryDate, String comment, String[] color) {


        }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public String getMetroStation() { return metroStation; }
        public void setMetroStation(String metroStation) { this.metroStation = metroStation; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getRentTime() { return rentTime; }
        public void setRentTime(int rentTime) { this.rentTime = String.valueOf(rentTime); }

        public String getDeliveryDate() { return deliveryDate; }
        public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }

        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
    }

}
