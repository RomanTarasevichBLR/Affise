package utility;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class ResourceUtil {

    private ResourceUtil() {
    }

    public static String getResourceUrl(String resourceName) {
        try {
            URL resourceURL = ResourceUtil.class.getClassLoader().getResource(resourceName);
            return Objects.requireNonNull(resourceURL).getPath();
        } catch (NullPointerException var2) {
            throw new IllegalArgumentException(String.format("Resource file %1$s was not found or cannot be loaded", resourceName), var2);
        }
    }

    public static String getResourcePath(String resourceName) {
        String url = getResourceUrl(resourceName);
        try {
            return new URI(url).normalize().getPath();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(String.format("Invalid syntax of provided url: '%s'. Error: %s", url, e.getMessage()));
        }
    }
}
