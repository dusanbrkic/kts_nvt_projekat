package gradjanibrzogbroda.backend.config;

public class StorageProperties {

    private static String resourceFolderRelativePath = "src" + System.getProperty("file.separator") + "main" + System.getProperty("file.separator") + "resources";
    private static String zaposleniProfilePicsLocation = "static" + System.getProperty("file.separator") + "zaposleniProfilePics";

    public static String getZaposleniProfilePicsLocation(){
        return zaposleniProfilePicsLocation;
    }

    public static void setZaposleniProfilePicsLocation(String zaposleniProfilePicsLocation){
        StorageProperties.zaposleniProfilePicsLocation = zaposleniProfilePicsLocation;
    }

    public static void setResourceFolderRelativePath(String resourceFolderRelativePath) {
        StorageProperties.resourceFolderRelativePath = resourceFolderRelativePath;
    }

    public static String getResourceFolderRelativePath() {
        return resourceFolderRelativePath;
    }
}
