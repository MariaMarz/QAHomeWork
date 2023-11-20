package api.test;

import api.ApiBase;
import api.AuthorizationAPI;
import api.UserAPI;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserAuthorizationTest extends ApiBase {
    AuthorizationAPI authorizationAPI;
    UserAPI userAPI;
    Faker faker = new Faker();

    @Test
    public void successLogin() {

        String username = "Maria";
        String password = "12345";
        authorizationAPI = new AuthorizationAPI();
        Response response = authorizationAPI.login(username, password, 200);

    }


}

