package api.utility;

import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import java.util.Map;
import static io.restassured.RestAssured.given;

@UtilityClass
public class RestRequestsHelper {

    public static Response getRequest(String url, Map<String, Object> params) {
        return given().queryParams(params).when().get(url).then().extract().response();
    }

    public static Response getRequest(String url) {
        return given().when().get(url).then().extract().response();
    }

    public static Response postRequest(String url, Map<String, Object> params) {
        return given().formParams(params).when().post(url).then().extract().response();
    }

}
