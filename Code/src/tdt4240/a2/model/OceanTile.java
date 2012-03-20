package tdt4240.a2.model;

/**
 */
public enum OceanTile {
    OCCUPIED,
    EMPTY,
    EMPTY_BOMBED;

    private final String propertyName = "OceanTile";

    public String  getPropertyName(){
        return propertyName;
    }
}
