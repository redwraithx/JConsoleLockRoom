public abstract class MapObject {
    protected ObjectNames name;
    protected String description;
    protected char icon;
    protected Location location;

    public MapObject(ObjectNames name, String description, char icon, Location location) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.location = location;
    }

    public abstract String getName();
    public abstract void setName(ObjectNames name);

    public abstract ObjectNames getObjectName();
    public abstract void setObjectName(ObjectNames name);

    public abstract String getDescription();
    public abstract void setDescription(String description);

    public abstract char getIcon();
    public abstract void setIcon(char icon);

    public abstract Location getLocation();
    public abstract void setLocation(Location location);
}
