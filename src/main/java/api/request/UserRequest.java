package api.request;

import api.endpoint.UserEndPoints;
import api.utility.RestRequestsHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class UserRequest extends BaseApiRequest {

    public UserRequest() {
        RestAssured.requestSpecification.given().basePath("/3.0/admin/");
    }

    public Response createNewUser(Map<String, Object> userData) {
        return RestRequestsHelper.postRequest(UserEndPoints.ADD_NEW_USER, userData);
    }

    public Response returnListOfAllUsers() {
        return RestRequestsHelper.getRequest(UserEndPoints.GET_LIST_OF_USERS);
    }

    public Response returnListOfAllUsersFromPage(Map<String, Object> params) {
        return RestRequestsHelper.getRequest(UserEndPoints.GET_LIST_OF_USERS, params);
    }


}
