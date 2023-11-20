package api.test;

import api.AuthorizationAPI;
import api.UserAPI;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserViaApi {
    AuthorizationAPI authorizationAPI;
    UserAPI userAPI;
    Faker faker = new Faker();

    @Test
    public void registerNewUserViaApi() {
        String expectedUsername = faker.internet().uuid();

        // register new user and get user id
        authorizationAPI = new AuthorizationAPI();
        JsonPath createdUser = authorizationAPI.registerUser(expectedUsername).jsonPath();
        String userId = createdUser.getString("userID");

        // generate token
        String token = authorizationAPI.generateToken(expectedUsername);
        boolean authorization = authorizationAPI.isAuthorized(expectedUsername);
        Assert.assertTrue(authorization, "User is not authorized, status: " + authorization);

        // get data of created user
        userAPI = new UserAPI(token);
        String actualUsername = userAPI.getUser(userId).jsonPath().getString("username");
        Assert.assertEquals(actualUsername, expectedUsername, actualUsername + " is not equal " + expectedUsername);

        // delete created user
        userAPI.deleteUser(userId);
        JsonPath errorMessage = authorizationAPI.isDeleted(expectedUsername).jsonPath();
        Assert.assertEquals(errorMessage.getString("message"), "User not found!");
    }
}
