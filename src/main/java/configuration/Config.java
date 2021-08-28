package configuration;

public class Config {

    public static final String START_URL = "/startUrl";
    public static final String API_URL = "/apiUrl";

    private Config() {
    }

    public static String getUrl(String nameValue) {
        return Environment.getCurrentEnvironment().getValue(nameValue).toString();
    }
}
