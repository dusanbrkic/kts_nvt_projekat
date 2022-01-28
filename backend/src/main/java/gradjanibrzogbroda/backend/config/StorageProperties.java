package gradjanibrzogbroda.backend.config;

public class StorageProperties {

    private static final String RESOURCE_LOCATION = "src" + System.getProperty("file.separator") + "main" + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator");

    public static final String ZAPOSLENI_LOCATION = RESOURCE_LOCATION + "static" + System.getProperty("file.separator") + "zaposleniProfilePics" + System.getProperty("file.separator");
    public static final String JELA_LOCATION = RESOURCE_LOCATION + "static" + System.getProperty("file.separator") + "jela" + System.getProperty("file.separator");
    public static final String PICA_LOCATION = RESOURCE_LOCATION + "static" + System.getProperty("file.separator") + "pica" + System.getProperty("file.separator");
    public static final String ZONE_LOCATION = RESOURCE_LOCATION + "static" + System.getProperty("file.separator") + "zone" + System.getProperty("file.separator");

}
