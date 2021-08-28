package api.request;

import api.utility.RequestSpecHelper;
import configuration.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class BaseApiRequest {

    public BaseApiRequest() {
        RestAssured.requestSpecification = RequestSpecHelper.getDefaultRequestSpec();
        RestAssured.requestSpecification.given().baseUri(Config.getUrl(Config.API_URL));
        RestAssured.given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("form-data", ContentType.TEXT)));
    }
}
