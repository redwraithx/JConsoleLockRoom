import BrianUtils.DebugLogger;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        System.out.println("Welcome to a Simple Escape Room Console Game!");

        Map map = new Map(30, 8);


        // Generate Map
        // fill map with Entrance and Exit
        Item entrance = new Item(ObjectNames.ENTRANCE, "An entrance, except it looks completely welded shut.", new Location(0, 2), ItemState.UNLOOTABLE, LockTypes.NONE, 'E', ItemType.WALL);
        map.setLocation(entrance.getLocation().getX(), entrance.getLocation().getY(), entrance);

        Item exit = new Item(ObjectNames.EXIT, "An exit out of here.", new Location(map.getWidth() - 1, 5), ItemState.UNLOOTABLE, LockTypes.UNLOCK_EXIT, 'X', ItemType.DOOR);
        map.setLocation(exit.getLocation().getX(), exit.getLocation().getY(), exit);


        // create little room, at location 0, 24 to 4, 30
        int lowerBoundI = 20;
        int upperBoundI = 30;
        int lowerBoundJ = 0;
        int upperBoundJ = 3;

        for(int i = lowerBoundI; i < upperBoundI; i++) {
            for(int j = lowerBoundJ; j < upperBoundJ; j++) {

                if(i == lowerBoundI || i == upperBoundI - 1 || j == lowerBoundJ || j == upperBoundJ - 1) {
                    Item wall = new Item(ObjectNames.WALL, "A wall", new Location(i, j), ItemState.UNLOOTABLE, LockTypes.NONE, '#', ItemType.WALL);
                    map.setLocation(i, j, wall);
                }
                else {
                    Item empty = new Item(ObjectNames.EMPTY, "An empty space", new Location(i, j), ItemState.UNLOOTABLE, LockTypes.NONE, ' ', ItemType.EMPTY);
                    map.setLocation(i, j, empty);
                }

            }
        }

        // add a key to the room for the Exit

        Container desk = new Container(ObjectNames.DESK, "A desk that is covered in dust.", 'd', new Location(map.getWidth() - 3, 1), 5, LockTypes.DESK);
        map.setLocation(desk.getLocation().getX(), desk.getLocation().getY(), desk);
        Item exitKey = new Item(ObjectNames.KEY, "A unmarked key but whats it for...", new Location(0, 0), ItemState.UNLOOTABLE, LockTypes.UNLOCK_EXIT, 'K', ItemType.KEY);
        desk.addItem(exitKey);


        // add door to new room
        Item officeDoor = new Item(ObjectNames.DOOR, "A office door", new Location(19, 2), ItemState.UNLOOTABLE, LockTypes.OFFICE_DOOR, 'D', ItemType.DOOR, ItemType.WALL);
        map.setLocation(20, 1, officeDoor);

        // add cabinet to new room, in lower left corner of room
        Container cabinet = new Container(ObjectNames.CABINET, "An old cabinet that is starting to fall apart.", 'C', new Location(2, map.getHeight() - 2), 5, LockTypes.CABINET);
        map.setLocation(2, map.getHeight() - 2, cabinet);

        Item officeKey = new Item(ObjectNames.KEY, "A rusty key with tag tied to it, O.K. #13 key, don't lose it!", new Location(0, 0), ItemState.LOOT, LockTypes.OFFICE_DOOR, 'K', ItemType.KEY);
        cabinet.addItem(officeKey);


        // add a double table in the middle of the room
        Container tableLeft = new Container(ObjectNames.TABLE, "A table that has seen better days.", 'T', new Location((map.getWidth() - 1) / 2, (map.getHeight() - 1) / 2), 10, LockTypes.NONE);
        map.setLocation((map.getWidth() - 1) / 2, (map.getHeight() - 1) / 2, tableLeft);
        Container tableRight = new Container(ObjectNames.TABLE, "A table that is recovering from a spill of some sort.", 'T', new Location((map.getWidth() - 1) / 2, (map.getHeight() - 1) / 2 + 1), 10, LockTypes.NONE);
        map.setLocation((map.getWidth() - 1) / 2, (map.getHeight() - 1) / 2 + 1, tableRight);

        Item lockerKey3 = new Item(ObjectNames.KEY, "A key with a tag that says: locker #3 key", new Location(1, 1), ItemState.LOOT, LockTypes.LOCKER_3, 'K', ItemType.KEY);

        int keyLocation = (int)(Math.random() * 2);
        if(keyLocation == 1) {
            tableLeft.addItem(lockerKey3);
        }
        else {
            tableRight.addItem(lockerKey3);
        }
        DebugLogger.log("Locker 3 Key location: " + keyLocation);


        // add 3 lockers near the entrance
        Container locker1 = new Container(ObjectNames.LOCKER_1, "A locker that is locked, marked #1.", 'L', new Location(1, 1), 5, LockTypes.LOCKER_1);
        map.setLocation(2, 1, locker1);
        Container locker2 = new Container(ObjectNames.LOCKER_2, "A locker that is locked, marked #2.", 'L', new Location(1, 2), 5, LockTypes.LOCKER_2);
        map.setLocation(3, 1, locker2);
        Container locker3 = new Container(ObjectNames.LOCKER_3, "A locker that is locked, marked #3.", 'L', new Location(1, 3), 5, LockTypes.LOCKER_3);
        map.setLocation(4, 1, locker3);

        // add items to the locker #1 - a memo, a bunch of rags, and a key for a desk, not all useful but lore is good to use as misc items
        Item memo = new Item(ObjectNames.MEMO, "A memo that says: 'Please do not use the key for the desk on the lockers. It will not work.'", new Location(1, 1), ItemState.LOOT, LockTypes.NONE, 'M', ItemType.MEMO);
        locker1.addItem(memo);
        Item rags = new Item(ObjectNames.RAGS, "A bunch of rags that are covered in grease.", new Location(1, 1), ItemState.LOOT, LockTypes.NONE, 'R', ItemType.RAGS);
        locker1.addItem(rags);
        Item lockerKey2 = new Item(ObjectNames.KEY, "A key with a tag that says: locker #2 key", new Location(1, 1), ItemState.LOOT, LockTypes.LOCKER_2, 'K', ItemType.KEY);
        locker1.addItem(lockerKey2);
        Item drink = new Item(ObjectNames.WATER, "A bottle of water that is starting to rust.", new Location(1, 1), ItemState.LOOT, LockTypes.NONE, 'W', ItemType.CONSUMABLE_WATER);
        locker1.addItem(drink);
        

        // add misc lore items to locker #2
        Item note2 = new Item(ObjectNames.NOTE, "A note that says: 'Had to seal the main entrance due to the water that was coming into the building.", new Location(1, 2), ItemState.LOOT, LockTypes.NONE, 'N', ItemType.NOTE);
        locker2.addItem(note2);
        Item memo2 = new Item(ObjectNames.MEMO, "A very faded memo that says: 'P_ease do n_t use t__ _e_ ___ __e ____ on t_e _______rs. _______________.'", new Location(1, 2), ItemState.LOOT, LockTypes.NONE, 'M', ItemType.MEMO);
        locker2.addItem(memo2);
        Item deskKey = new Item(ObjectNames.KEY, "A key with a tag that says: desk key", new Location(1, 2), ItemState.LOOT, LockTypes.DESK, 'K', ItemType.KEY);
        locker2.addItem(deskKey);

        // add misc lore items to locker #3
        Item note3 = new Item(ObjectNames.NOTE, "A note that says: 'Had to seal the main entrance due to the water that was coming into the building.", new Location(1, 3), ItemState.LOOT, LockTypes.NONE, 'N', ItemType.NOTE);
        locker3.addItem(note3);
        Item Note4 = new Item(ObjectNames.NOTE, "A note that says: 'I placed the Exit door key in the office. Make sure to lock up before you leave, thanks.", new Location(1, 3), ItemState.LOOT, LockTypes.NONE, 'N', ItemType.NOTE);
        locker3.addItem(Note4);
        Item food = new Item(ObjectNames.FOOD, "A can of food that is starting to rust.", new Location(1, 3), ItemState.LOOT, LockTypes.NONE, 'F', ItemType.CONSUMABLE_FOOD);
        locker3.addItem(food);
        Item water = new Item(ObjectNames.WATER, "A bottle of water that is starting to rust.", new Location(1, 3), ItemState.LOOT, LockTypes.NONE, 'W', ItemType.CONSUMABLE_WATER);
        locker3.addItem(water);
        Item cabinetKey = new Item(ObjectNames.KEY, "A key with a tag that says: cabinet key", new Location(1, 3), ItemState.LOOT, LockTypes.CABINET, 'K', ItemType.KEY);
        locker3.addItem(cabinetKey);

        Container barrel = new Container(ObjectNames.BARREL, "A barrel that is starting to rot.", 'B', new Location(map.getWidth() - 3, 3), 5, LockTypes.NONE);
        map.setLocation(barrel.getLocation().getX(), barrel.getLocation().getY(), barrel);
        Item locker1Key = new Item(ObjectNames.KEY, "A key with a tag that says: locker #1 key", new Location(1, 4), ItemState.LOOT, LockTypes.LOCKER_1, 'K', ItemType.KEY);
        barrel.addItem(locker1Key);

        // create player
        Entity player = new Entity(ObjectNames.PLAYER, "You! the player", 'P', new Location(2, 4), 100, 100, 100);
        map.setLocation(player.getLocation().getX(), player.getLocation().getY(), player);


        // End of Map Generation

        map.printMap();





        // Game Loop
        boolean isGameLoopRunning = true;
        int counter = 0;

        while(isGameLoopRunning) {

            if(isGameLoopRunning && counter < 100) {


                DebugLogger.log("Player Location: " + player.getLocation().getX() + ", " + player.getLocation().getY());


                System.out.println("1) Move Player");
                if(player.getInventory().getSize() > 0) {
                    System.out.println("2) View Inventory");
                }
                System.out.println("3) Look around this Area");
                System.out.println("4) Interact with nearby object");
                System.out.println("5) Map Legend");
                System.out.print("What would you like to do? ");
                Scanner input = new Scanner(System.in);


                if(input.hasNext()) {

                    switch (input.nextLine().charAt(0)) {
                        case '1':
                        case 'm':
                        case 'M':
                            DebugLogger.log("Moving Player");

                            movePlayer(map, player);

                            break;
                        case '2':
                        case 'v':
                        case 'V':


                            DebugLogger.log("Viewing Inventory");

                            viewPlayerInventory(player);

                            break;
                        case '3':
                        case 'l':
                        case 'L':
                            DebugLogger.log("Looking around this Area");


                            // temp container for objects in the vicinity
                            ArrayList<MapObject> objectsInVicinity = new ArrayList<>();


                            // check map for object at location x and y and return object if found else return a wall item
                            int x = player.getLocation().getX();
                            int y = player.getLocation().getY();


                            int y1 = y - 1;
                            if (x >= 0 || x < map.getWidth() || y1 >= 0 || y1 < map.getHeight()) {

                                MapObject mObj1 = map.getLocation(x, y1);
                                if (mObj1 == null || mObj1.getObjectName() == ObjectNames.EMPTY || mObj1.getObjectName() == ObjectNames.WALL) {
                                    DebugLogger.logError("Object not found at: " + x + ", " + y1);
                                } else {
                                    objectsInVicinity.add(mObj1);

                                    DebugLogger.log(mObj1.getName());
                                }
                            }

                            int x2 = x - 1;
                            if (x2 >= 0 || x2 < map.getWidth() || y >= 0 || y < map.getHeight()) {

                                MapObject mObj2 = map.getLocation(x2, y);

                                if (mObj2 == null || mObj2.getObjectName() == ObjectNames.EMPTY || mObj2.getObjectName() == ObjectNames.WALL) {
                                    DebugLogger.logError("Object not found at: " + x2 + ", " + y);
                                } else {
                                    objectsInVicinity.add(mObj2);

                                    DebugLogger.log(mObj2.getName());
                                }
                            }

                            int x3 = x + 1;
                            if (x3 >= 0 || x3 < map.getWidth() || y >= 0 || y < map.getHeight()) {

                                MapObject mObj3 = map.getLocation(x3, y);

                                if (mObj3 == null || mObj3.getObjectName() == ObjectNames.EMPTY || mObj3.getObjectName() == ObjectNames.WALL) {
                                    DebugLogger.logError("Object not found at: " + x3 + ", " + y);
                                } else {
                                    objectsInVicinity.add(mObj3);

                                    DebugLogger.log(mObj3.getName());
                                }
                            }

                            int y4 = y + 1;
                            if (x >= 0 || x < map.getWidth() || y4 >= 0 || y4 < map.getHeight()) {

                                MapObject mObj4 = map.getLocation(x, y4);

                                if (mObj4 == null || mObj4.getObjectName() == ObjectNames.EMPTY || mObj4.getObjectName() == ObjectNames.WALL) {
                                    DebugLogger.logError("Object not found at: " + x + ", " + y4);
                                } else {
                                    objectsInVicinity.add(mObj4);

                                    DebugLogger.log(mObj4.getName());
                                }
                            }


                            boolean isLookingAround = true;

                            while (isLookingAround) {

                                System.out.println("\n\n" + objectsInVicinity.size() + " Objects in the vicinity:");
                                for (MapObject mOjb : objectsInVicinity) {
                                    System.out.printf("\t-\t%s", mOjb.getName().toLowerCase());
                                }

                                System.out.println("\n1) Look at an object");
                                System.out.println("2) Inspect an object");
                                if (player.getInventory().getSize() > 0) {
                                    System.out.println("3) Use an Item from your inventory on an object");
                                }
                                System.out.println("4) Back");
                                System.out.print("What would you like to do? ");
                                input = new Scanner(System.in);

                                if(input.hasNext()) {


                                    switch (input.nextLine().charAt(0)) {
                                        case '1':
                                        case 'l':
                                        case 'L':
                                            DebugLogger.log("Looking at object");


                                            lookAtAnObject(objectsInVicinity);

                                            break;
                                        case '2':
                                        case 'i':
                                        case 'I':
                                            DebugLogger.log("Inspect an object");

                                            if (objectsInVicinity.isEmpty()) {
                                                System.out.println("There are no objects in the vicinity.");
                                                break;
                                            }

                                            for (int i = 0; i < objectsInVicinity.size(); i++) {
                                                System.out.printf("\t%d)\t%s\n", (i + 1), objectsInVicinity.get(i).getName());
                                            }
                                            System.out.println("\t" + (objectsInVicinity.size() + 1) + ")\tBack");

                                            System.out.print("Enter the number of the object you want to interact with: ");
                                            input = new Scanner(System.in);

                                            // number - 1 = index of item in array, makes navigation easier to add 1 to the index
                                            int objectNumber2 = input.nextInt() - 1;


                                            if (objectNumber2 >= 0 && objectNumber2 < objectsInVicinity.size()) {

                                                // if container show items in container
                                                int size = objectsInVicinity.get(objectNumber2) instanceof Container ? ((Container) objectsInVicinity.get(objectNumber2)).getItems().size() : 0;

                                                boolean isObjectLocked = isIsObjectLocked(objectsInVicinity, objectNumber2);


                                                if (size > 0 && !isObjectLocked) {
                                                    if (objectsInVicinity.get(objectNumber2) instanceof Container container) {

                                                        System.out.println("the " + objectsInVicinity.get(objectNumber2).getName().toLowerCase() + " contains: ");

                                                        for (Item item : container.getItems()) {

                                                            System.out.println("\t- " + item.getNameWithDescription());

                                                            System.out.println("You pickup the " + item.getName().toLowerCase() + " from the " + container.getName().toLowerCase() + " and place it in your pocket.");
                                                            player.getInventory().addItem(item);
                                                        }
                                                    } else if (objectsInVicinity.get(objectNumber2) instanceof Item) {
                                                        System.out.println("Item: " + objectsInVicinity.get(objectNumber2).getName().toLowerCase());

                                                    } else {
                                                        System.out.println("Unknown object type.");

                                                    }
                                                } else {

                                                    if (isObjectLocked) {
                                                        System.out.println(objectsInVicinity.get(objectNumber2).getName().toLowerCase() + " is locked.");
                                                    } else {
                                                        System.out.println(objectsInVicinity.get(objectNumber2).getName().toLowerCase() + " has nothing of use.");
                                                    }
                                                }


                                            } else {
                                                DebugLogger.logError("Invalid object number.");
                                            }


                                            break;
                                        case '3':
                                        case 'u':
                                        case 'U':
                                            DebugLogger.log("Using an item on an object");

                                            if (!player.getInventory().getItems().isEmpty()) {

                                                DebugLogger.log("Player Inventory Size: " + player.getInventory().getSize());

                                                for (int i = 0; i < player.getInventory().getItems().size(); i++) {
                                                    System.out.printf("\t%d)\t%s\n", (i + 1), player.getInventory().getItems().get(i).getNameWithDescription());
                                                }

                                                System.out.print("Enter the index of the item you want to use: ");
                                                input = new Scanner(System.in);

                                                int itemIndex = input.nextInt() - 1;

                                                if (itemIndex >= 0 && itemIndex < player.getInventory().getItems().size()) {

                                                    DebugLogger.log("try to use selected item on objects in vicinity");

                                                    // unlock selected object
                                                    for (MapObject mapObject : objectsInVicinity) {
                                                        boolean containerUnlocked = false;

                                                        if (mapObject instanceof Container) {
                                                            DebugLogger.log("Object in vicinity: " + mapObject.getName() + " is a container");

                                                            containerUnlocked = ((Container) mapObject).unlock(player.getInventory().getItems().get(itemIndex).getLockType());
                                                        } else if (mapObject instanceof Item) {
                                                            DebugLogger.log("Object in vicinity: " + mapObject.getName() + " is not a container");

                                                            containerUnlocked = ((Item) mapObject).unlock(player.getInventory().getItems().get(itemIndex).getLockType());

                                                            DebugLogger.log("Removing door from map");
                                                            //map.clearLocation(mapObject.getLocation().getX(), mapObject.getLocation().getY());

                                                            if(mapObject.getObjectName() != ObjectNames.EXIT) {

                                                                mapObject.setIcon(' ');
                                                                mapObject.setName(ObjectNames.EMPTY);
                                                                mapObject.setDescription("An empty space");
                                                                ((Item) mapObject).clearItemType();
                                                                ((Item) mapObject).addItemType(ItemType.EMPTY);
                                                                ((Item) mapObject).setItemState(ItemState.UNLOOTABLE);

                                                            }

                                                        }

                                                        if (containerUnlocked) {
                                                            System.out.println("You unlocked the " + mapObject.getName().toLowerCase() + " with the " + player.getInventory().getItems().get(itemIndex).getName().toLowerCase() + ".");

                                                            player.getInventory().removeItem(player.getInventory().getItems().get(itemIndex));

                                                        } else {
                                                            System.out.println("You could not unlock the " + mapObject.getName().toLowerCase() + " with the " + player.getInventory().getItems().get(itemIndex).getName().toLowerCase() + ".");
                                                        }
                                                    }


                                                } else {
                                                    DebugLogger.logError("Invalid item number.");
                                                }
                                            } else {
                                                System.out.println("You currently have no items in your inventory to use.");
                                            }

                                            break;
                                        default:
                                            DebugLogger.log("Moving on");

                                            isLookingAround = false;
                                    }
                                }
                            }


                            break;
                        case '5':
                            DebugLogger.log("Showing Map Legend");

                            System.out.println("\nMap Legend:");
                            System.out.println("\t # = Wall");
                            System.out.println("\t E = Entrance");
                            System.out.println("\t X = Exit");
                            System.out.println("\t D = Door");
                            System.out.println("\t T = Table");
                            System.out.println("\t C = Cabinet");
                            System.out.println("\t L = Locker");
                            System.out.println("\t d = Desk");
                            System.out.println("\t B = Barrel");
                            System.out.println("\t P = Player");

                            // pause for a moment to let the player read the legend, continue on Enter key
                            System.out.println("\nPress Enter to continue...");
                            input = new Scanner(System.in);
                            input.nextLine();


                            break;
                        default:
                            DebugLogger.log("Interacting with nearby object");


                            break;

                    }
                }
            }

            System.out.println();

            map.printMap();

            DebugLogger.log("CHECK - is Game over: " + map.isGameOver());

            if(map.isGameOver()) {

                System.out.println("You have escaped the building!");

                isGameLoopRunning = false;
            }

            if(counter >= 100) {
                System.out.println("Game Over! You have lost faint and no one ever finds you.");

                isGameLoopRunning = false;
            }

            // increase counter for a finite time before game is over
            counter++;
        }

    }

    private static boolean isIsObjectLocked(ArrayList<MapObject> objectsInVicinity, int objectNumber2) {
        boolean isObjectLocked = false;

        if(objectsInVicinity.get(objectNumber2) instanceof Container container) {
            isObjectLocked = container.isLocked();
        }
        else if(objectsInVicinity.get(objectNumber2) instanceof Item item) {
            isObjectLocked = item.isLocked();
        }
        return isObjectLocked;
    }

    private static void lookAtAnObject(ArrayList<MapObject> objectsInVicinity) {

        if(!objectsInVicinity.isEmpty()) {

            Scanner input;
            for (int i = 0; i < objectsInVicinity.size(); i++) {
                System.out.printf("\t%d)\t%s\n", (i + 1), objectsInVicinity.get(i).getName());

            }

            System.out.println("\t" + (objectsInVicinity.size() + 1) + ")\tBack");

            System.out.print("Enter your selection: ");
            input = new Scanner(System.in);


            int objectNumber = input.nextInt() - 1;

            if (objectNumber >= 0 && objectNumber < objectsInVicinity.size()) {
                System.out.println(objectsInVicinity.get(objectNumber).getName() + " - " + objectsInVicinity.get(objectNumber).getDescription());
            } else {
                System.out.println("Invalid object number.");
            }
        }
        else {
            System.out.println("There are no objects in the vicinity.");
        }
    }

    private static void viewPlayerInventory(Entity player) {
        int invLength = player.getInventory().getItems().size();

        if(invLength > 0) {
            System.out.println("\nYou have " + invLength + " items in your inventory:");

            for (int i = 0; i < invLength; i++) {
                System.out.printf("\t%d)\t%s\n", (i + 1), player.getInventory().getItems().get(i).getNameWithDescription());
            }

            System.out.println();
        }
        else {
            System.out.println("\nYour inventory is empty.");
        }
    }

    private static void movePlayer(Map map, Entity player) {
        Scanner input;
        Direction direction;

        System.out.println("1) NORTH");
        System.out.println("2) SOUTH");
        System.out.println("3) EAST");
        System.out.println("4) WEST");
        System.out.println("5) Back");
        System.out.print("Enter move Direction: ");
        input = new Scanner(System.in);

        char moveDirectionValue = input.nextLine().charAt(0);

        if(moveDirectionValue != '5') {


            System.out.print("Enter number of spaces to move (EX: 1 or 10): ");
            input = new Scanner(System.in);

            int moveDistance = input.nextInt();


            direction = switch (moveDirectionValue) {
                case '1', 'n', 'N' -> Direction.NORTH;
                case '2', 's', 'S' -> Direction.SOUTH;
                case '3', 'e', 'E' -> Direction.EAST;
                case '4', 'w', 'W' -> Direction.WEST;
                default -> Direction.NONE; // Back

            };


            if(direction != Direction.NONE) {
                updatePlayer(map, player, direction, moveDistance);
            }

        }

    }

    public static void updatePlayer(Map map, Entity player, Direction direction, int moveDistance) {
        int oldX = player.getLocation().getX();
        int oldY = player.getLocation().getY();

        DebugLogger.log("Player Original Location: " + player.getLocation().getX() + ", " + player.getLocation().getY());

        DebugLogger.log("oldX: " + oldX + " oldY: " + oldY);

        int newX;
        int newY;

        int validDistance = 0;

        DebugLogger.log("DIRECTION: " + direction + " moveDistance: " + moveDistance);

        boolean isValidMove = true;
        if (direction == Direction.NORTH) {

            DebugLogger.log("Moving North");

            for (int i = 1 ; i <= moveDistance; i++) {

                if(isValidMove) {
                    if (map.checkMapLocationForCollision(oldX, (oldY - i))) {
                        isValidMove = false;
                    } else {
                        validDistance = i;
                    }
                }
            }


            DebugLogger.log("North - Valid Distance: " + validDistance + ", oldY - validDistance: " + (oldY - validDistance));


            newX = oldX;
            newY = oldY - validDistance;
        }
        else if (direction == Direction.SOUTH) {

            DebugLogger.log("Moving South");

            for (int i = 1; i <= moveDistance; i++) {

                if(isValidMove) {
                    if (map.checkMapLocationForCollision(oldX, (oldY + i))) {
                        isValidMove = false;
                    } else {
                        validDistance = i;
                    }
                }
            }

            DebugLogger.log("South - Valid Distance: " + validDistance + ", oldY + validDistance: " + (oldY + validDistance));

            newX = oldX;
            newY = oldY + validDistance;
        }
        else if (direction == Direction.EAST) {

            DebugLogger.log("Moving East");

            for (int i = 1; i <= moveDistance; i++) {

                if( isValidMove) {
                    if (map.checkMapLocationForCollision((oldX + i), oldY)) {
                        isValidMove = false;
                    } else {
                        validDistance = i;
                    }
                }
            }

            DebugLogger.log("East - Valid Distance: " + validDistance + ", oldX + validDistance: " + (oldX + validDistance));

            newX = oldX + validDistance;
            newY = oldY;
        }
        else { // Moving West

            DebugLogger.log("Moving West");

            for (int i = 1; i <= moveDistance; i++) {

                if (isValidMove) {
                    if ( map.checkMapLocationForCollision((oldX - i), oldY)) {
                        isValidMove = false;
                    } else {
                        validDistance = i;
                    }
                }
            }

            if(oldX - validDistance < 0 || oldX - validDistance >= map.getWidth()) {
                DebugLogger.log("Moving: " + direction +  " - Location is out of bounds X at: " + (oldX - validDistance) + ", " + oldX);

            }

            DebugLogger.log("West - Valid Distance: " + validDistance + ", oldX - validDistance: " + (oldX - validDistance));

            newX = oldX - validDistance;
            newY = oldY;

        }

        if (!map.checkMapLocationForCollision(newX, newY)) {

            player.setLocation(new Location(newX, newY));


            DebugLogger.log("No Collision detected! player can move to: " + newX + ", " + newY);

            Item empty = new Item(ObjectNames.EMPTY, "An empty space", new Location(oldX, oldY), ItemState.UNLOOTABLE, LockTypes.NONE, ' ', ItemType.EMPTY);
            map.setLocation(oldX, oldY, empty);

            map.setLocation(player.getLocation().getX(), player.getLocation().getY(), player);
            map.updateLocation(player);
        }

    }

}
