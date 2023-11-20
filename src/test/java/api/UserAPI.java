package api;

import io.restassured.response.Response;

public class UserAPI extends ApiBase {
    public UserAPI(String token) {
        super(token);
    }

    public Response getUser(String valueUserId) {
        String endpoint = "/Account/v1/User/{UserId}";
        return getRequestWithParam(endpoint, 200, "UserId", valueUserId);
    }

    public Response deleteUser(String valueUserId) {
        String endpoint = "/Account/v1/User/{UserId}";
        return deleteRequest(endpoint, 204, valueUserId);
    }
}
