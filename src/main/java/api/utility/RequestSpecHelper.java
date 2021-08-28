package api.utility;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestSpecHelper {
    private static RequestSpecification specification;

    public static RequestSpecification getDefaultRequestSpec() {
        if (specification == null)
        return RestAssured.given().spec(new RequestSpecBuilder()
                .log(LogDetail.URI)
                .addHeader("API-Key", "456505a43730b5f2b4a98fb4ce5408c4")
                .build());
        return specification;
    }
}
