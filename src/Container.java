import BrianUtils.DebugLogger;

import java.util.ArrayList;


public class Container extends MapObject {
    private int maxSize;
    private ArrayList<Item> items;
    private LockTypes lockType;

    public Container(ObjectNames name, String description, char icon, Location location, int size, LockTypes lockType) {
        super(name, description, icon, location);

        this.name = name;
        this.description = description;
        icon = icon;
        maxSize = size;
        items = new ArrayList<Item>();
        this.lockType = lockType;
    }

    @Override
    public String getName() {
        return name.toString();
    }

    @Override
    public void setName(ObjectNames name) {
        this.name = name;
    }

    @Override
    public ObjectNames getObjectName() {
        return name;
    }

    @Override
    public void setObjectName(ObjectNames name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public char getIcon() {
        return icon;
    }

    @Override
    public void setIcon(char icon) {
        this.icon = icon;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    public String getNameWithDescription() {
        return name.toString() + ": " + description;
    }

    public int getSize() {
        return maxSize;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public LockTypes getLockType() {
        return lockType;
    }

    public void setLockType(LockTypes lockType) {
        this.lockType = lockType;
    }

    public boolean unlock(LockTypes keyForLockType) {

        DebugLogger.log("Key for lock type: " + keyForLockType.toString());
        DebugLogger.log("Lock type: " + lockType.toString());
        DebugLogger.log("Container key comparisons: " + (keyForLockType.toString() == ("UNLOCK_" + lockType.toString())));

        //if (keyForLockType.toString() == ("UNLOCK_" + lockType.toString())) {
        if(keyForLockType == lockType) {
            lockType = LockTypes.UNLOCKED;

            DebugLogger.log("Container isLocked: " + isLocked());

            return true;
        }

        return false;
    }

    public boolean isLocked() {
        if(lockType == LockTypes.NONE) {
            return false;
        }

        return lockType != LockTypes.UNLOCKED;
    }


    public void displayContents() {
        System.out.println("Container: " + name.toString());
        System.out.println("Description: " + description);
        System.out.println("Items: ");
        int counter = 1;
        for (Item item : items) {
            System.out.println(counter + ") " + item.getNameWithDescription());

            counter++;
        }
    }
}
