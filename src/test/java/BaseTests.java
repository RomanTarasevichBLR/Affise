
import io.restassured.response.Response;
import ui.page.LoginPage;
import ui.page.MainPage;

public class BaseTests {

    protected Response response;
    protected LoginPage loginPage;
    protected MainPage mainPage;

    protected void statusCodeIs(int code) {
        response.then().statusCode(code);
    }

}
