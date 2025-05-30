import BrianUtils.DebugLogger;

import java.util.ArrayList;

public class Item extends MapObject {
    private ArrayList<ItemType> itemType;
    private ItemState itemState;
    private LockTypes lockType;

    public Item(ObjectNames name, String description, Location location, ItemState itemState, LockTypes lockType, char itemIcon, ItemType... itemTypes) {
        super(name, description, itemIcon, location);

        this.name = name;
        this.description = description;
        this.location = location;

        itemType = new ArrayList<ItemType>();
        for(ItemType iType : itemTypes) {
            this.itemType.add(iType);
        }

        this.itemState = itemState;
        this.lockType = lockType;
        this.icon = itemIcon;
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
        // may need to update the location of the item in the map also, as item will not know about map
        this.location = location;
    }

    public String getNameWithDescription() {
        return name.toString().toLowerCase() + ": " + description;
    }

    public ArrayList<ItemType> getItemType() {
        return itemType;
    }

    public void clearItemType() {
        itemType.clear();
    }

    public void addItemType(ItemType ... itemTypes) {
        for(int i = 0; i < itemTypes.length; i++) {

            this.itemType.add(itemTypes[i]);
        }
    }

    public ItemState getItemState() {
        return itemState;
    }

    public void setItemState(ItemState itemState) {
        this.itemState = itemState;
    }

    public String getItemIcon() {
        return Character.toString(icon);
    }

    public LockTypes getLockType() {
        return lockType;
    }

    public boolean isLocked() {
        if(lockType == LockTypes.NONE) {
            return false;
        }

        return lockType != LockTypes.UNLOCKED;
    }

    public boolean unlock(LockTypes keyForLockType) {

        if (keyForLockType == lockType) {
            lockType = LockTypes.UNLOCKED;

            DebugLogger.log("Item is unlocked");

            return true;
        }

        return lockType == LockTypes.UNLOCKED;
    }
}
