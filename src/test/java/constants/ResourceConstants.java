package constants;

import java.nio.file.Paths;

public final class ResourceConstants {
    public static final String BASE_DIRECTORY = System.getProperty("user.dir");
    public static final String PATH_TO_AVATAR = Paths.get("src", "test", "java", "resources", "images", "avatar.jpg").toString();
    public static final String ABSOLUTE_FILE_PATH_TO_AVATAR = Paths.get(BASE_DIRECTORY, PATH_TO_AVATAR).toString();
}
