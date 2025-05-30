import BrianUtils.DebugLogger;

public class Map {

    private MapObject[][] map;

    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new MapObject[width][height];

        generateMap();
    }

    private void generateMap() {
        /*
            "#" - wall
            " " - empty space
            "P" - Player
            "I" - Item
            "E" - Entrance
            "X" - Exit
            "D" - Door
            "C" - Container
            "T" - Table
            "B" - Barrel
            "d" - Desk
            "S" - Shelf
            "L" - Locker
            "C" - Chest

            item locations:
                - "NO LETTER" = Key (in a container)
                - L = Lights (usable, not something that is looted
                - F = Food
                - W = Water
                - M = Medkit


            container locations:
                - c = Crate
                - C = Cabinet
                - B = Barrel
                - S = Shelf
                - D = Desk
                - T = Table


            objective locations:
                - D = Door
                -

         */
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if(i == 0 || i == height - 1 || j == 0 || j == width - 1) {

                    Item item = new Item(ObjectNames.WALL, "A wall", new Location(i, j), ItemState.UNLOOTABLE, LockTypes.NONE, '#', ItemType.WALL);
                    map[j][i] = item;
                }
                else {

                    map[j][i] = new Item(ObjectNames.EMPTY, "An empty space", new Location(i, j), ItemState.UNLOOTABLE, LockTypes.NONE, ' ', ItemType.EMPTY);
                }
            }
        }
    }

    public MapObject findObjectByName(String name) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[j][i].getName().equals(name)) {
                    return map[j][i];
                }
            }
        }
        return null;
    }

    public MapObject findObjectByLocation(Location location) {

        if(location == null || location.getX() < 0 || location.getY() < 0 || location.getX() >= width || location.getY() >= height) {
            DebugLogger.log("find objects by Location: " + location + ", is out of bounds");

            return null;
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[j][i].getLocation() != null && map[j][i].getLocation().equals(location)) {
                    return map[j][i];
                }
            }
        }
        return null;
    }

    public MapObject getLocation(int x, int y) {
        return map[x][y];
    }

    public MapObject getObjectLocation(Location location) {
        return map[location.getX()][location.getY()];
    }

    public void updateLocation(int x, int y, MapObject location) {

        System.out.println("Updating location: " + x + ", " + y);

        // clear the current location
        map[x][y] = new Item(ObjectNames.EMPTY, "An empty space", new Location(x, y), ItemState.UNLOOTABLE, LockTypes.NONE, ' ', ItemType.EMPTY);


        // set the new location
        map[x][y] = location;

        System.out.println("Updated location: " + x + ", " + y);
    }

    public void updateLocation(MapObject location) {

        int x = location.getLocation().getX();
        int y = location.getLocation().getY();

        System.out.println("Updating location: " + x + ", " + y);

        // clear the current location
        map[x][y] = new Item(ObjectNames.EMPTY, "An empty space", new Location(x, y), ItemState.UNLOOTABLE, LockTypes.NONE, ' ', ItemType.EMPTY);


        // set the new location
        map[x][y] = location;

        System.out.println("Updated location: " + x + ", " + y);
    }

    public void setLocation(int x, int y, MapObject location) {

        map[x][y] = location;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isGameOver() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].getObjectName() == ObjectNames.EXIT) {
                    DebugLogger.log("Exit Found and is unlocked, game over");

                    return (((Item)map[i][j]).getLockType() == LockTypes.UNLOCKED);
                }
            }
        }

        return false;
    }

    public boolean checkMapLocationForCollision(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            DebugLogger.log("Location: " + x + ", " + y + ", is out of bounds");

            return true;
        }

        String name = map[x][y].getName();

        if(name == ObjectNames.WALL.toString() || name == ObjectNames.TABLE.toString() || name == ObjectNames.DOOR.toString() ||
                name == ObjectNames.EXIT.toString() || name == ObjectNames.ENTRANCE.toString() || name == ObjectNames.BARREL.toString() ||
                name == ObjectNames.SHELF.toString() || name == ObjectNames.DESK.toString() || name == ObjectNames.CHEST.toString() || name == ObjectNames.CABINET.toString() ||
                name == ObjectNames.LOCKER_1.toString() || name == ObjectNames.LOCKER_2.toString() || name == ObjectNames.LOCKER_3.toString()) {
            DebugLogger.log("Location: " + (map[x][y].getLocation() != null ? map[x][y].getLocation().getXY() : x + ", " + y) + ", is blocked by: " + map[x][y].getName());

            return true;
        }

        DebugLogger.log("Object at location[" + x + "][" + y + "]: " + map[x][y].getName());

        return false;
    }

    public void printMap() {

        System.out.println("=".repeat(width));

        String name = "MAP";
        int left = width / 2 - name.length() / 2 - 2;
        int right = width / 2 + name.length() / 2 - 3;
        StringBuilder sb = new StringBuilder();
        sb.append("=");
        sb.append(" ".repeat(left));
        sb.append(name);
        sb.append(" ".repeat(right));
        sb.append("=");
        System.out.println(sb);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[j][i].getIcon());
            }
            System.out.println();
        }

        System.out.println();


        System.out.println(map[13][4].getObjectName() + " " + map[13][4].getName() + " " + map[13][4].getLocation().getXY());


//        System.out.println("Num MapObjects: " + (width * height));
//
//        int count = 0;
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                count++;
//            }
//        }
//        System.out.println("Num mapObjectsInArray: " + count);

    }

    public void clearLocation(int x, int y) {

        DebugLogger.log("Clearing location: " + x + ", " + y);

        setLocation(x, y, new Item(ObjectNames.EMPTY, "An empty space", new Location(x, y), ItemState.UNLOOTABLE, LockTypes.NONE, ' ', ItemType.EMPTY)
        );

    }
}
