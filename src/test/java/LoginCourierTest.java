import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
    private CourierSteps courierSteps = new CourierSteps();
    private String login;
    private String password;
    private String firstName;


    @Before
    public void setUp() {
        login = RandomStringUtils.randomAlphabetic(10);
        password = RandomStringUtils.randomAlphabetic(10);
        firstName = RandomStringUtils.randomAlphabetic(10);

        courierSteps.createCourier(login, password, firstName);

    }

    @Test
    public void shouldReturnId() {

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, password)
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Test
    public void mandatoryFieldsShouldBeFilledLogin() {

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier("", password)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    public void mandatoryFieldsShouldBeFilledPassword() {

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, "")
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    public void wrongPasswordNotFound() {


        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, "1234")
                .statusCode(SC_NOT_FOUND)
                .body("message", is("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(login, password)
                .extract().body().path("id");
        if (id != null) {
            courierSteps.deleteCourier(id);
        }
    }


}

