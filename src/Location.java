public class Location {
    //private String locationName;
    //private char locationIcon;
    private int x;
    private int y;

    //public Location(String name, char icon, int x, int y) {
    public Location(int x, int y) {
        //locationName = name;
        //locationIcon = icon;
        this.x = x;
        this.y = y;
    }

//    public String getLocationName() {
//        return locationName;
//    }

//    public char getLocationIcon() {
//        return locationIcon;
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getXY() {
        return x + ", " + y;
    }

//    public void setLocationName(String name) {
//        locationName = name;
//    }

//    public void setLocationIcon(char icon) {
//        locationIcon = icon;
//    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x += x;
        this.y += y;
    }

}
