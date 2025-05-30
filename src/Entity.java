

public class Entity extends MapObject {
    //private ObjectNames name;
    //private String description;
    //private char icon;
    private int bloodAmount;
    private int maxBloodAmount;
    private int waterAmount;
    private int maxWaterAmount;
    private int foodAmount;
    private int maxFoodAmount;
    private int deathLifeTurns;
    private int defaultDeathLifeTurns;
    public Location location;
    private Container inventory;

    public Entity(ObjectNames name, String description, char icon, Location location, int blood, int water, int food) {
        super(name, description, icon, location);

        this.name = name;
        this.description = description;
        this.icon = icon;
        this.location = location;
        bloodAmount = blood;
        maxBloodAmount = blood;
        waterAmount = water;
        maxWaterAmount = water;
        foodAmount = food;
        maxFoodAmount = food;
        deathLifeTurns = 0;
        defaultDeathLifeTurns = 5;
        inventory = new Container(ObjectNames.INVENTORY, "Items in your pockets:", ' ', location, 10, LockTypes.NONE);
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

    public void setLocation(Location location) {
        //System.out.println("Setting location to " + location.getX() + ", " + location.getY());

        this.location = location;
    }

    public int getBloodAmount() {
        return bloodAmount;
    }

    public int getMaxBloodAmount() {
        return maxBloodAmount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public int getMaxWaterAmount() {
        return maxWaterAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public int getMaxFoodAmount() {
        return maxFoodAmount;
    }

    public Container getInventory() {
        return inventory;
    }

    public void setBloodAmount(int blood) {
        bloodAmount = blood;
    }

    public void setWaterAmount(int water) {
        waterAmount = water;
    }

    public void setFoodAmount(int food) {
        foodAmount = food;
    }

    public void drinkWater(int amount) {
        waterAmount += amount;
        if (waterAmount > maxWaterAmount) {
            waterAmount = maxWaterAmount;
        }
    }

    public void eatFood(int amount) {
        foodAmount += amount;
        if (foodAmount > maxFoodAmount) {
            foodAmount = maxFoodAmount;
        }
    }

    public void loseBlood(int amount) {
        bloodAmount -= amount;
        if (bloodAmount < 0) {
            bloodAmount = 0;
        }
    }

    public void gainBlood(int amount) {
        bloodAmount += amount;
        if (bloodAmount > maxBloodAmount) {
            bloodAmount = maxBloodAmount;
        }
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    public void useItem(Item item) {
        if (item.getName().equals("Water")) {
            drinkWater(10);
        }
        else if (item.getName().equals("Food")) {
            eatFood(10);
        }

        else if (item.getName().equals("Food")) {
            eatFood(10);
        }
        removeItem(item);
    }

    public void updateFoodAndWaterValues(int food, int water) {
        foodAmount += food;
        waterAmount += water;
        if (foodAmount > maxFoodAmount) {
            foodAmount = maxFoodAmount;
        }
        else if (foodAmount < 0) {
            foodAmount = 0;
        }

        if (waterAmount > maxWaterAmount) {
            waterAmount = maxWaterAmount;
        }
        else if (waterAmount < 0) {
            waterAmount = 0;
        }
    }

    public void updateDeathLifeTurns() {
        deathLifeTurns--;

        if (deathLifeTurns <= 0) {
            System.out.println("You have died.");
        }
    }

    public void resetDeathLifeTurns() {
        deathLifeTurns = defaultDeathLifeTurns;
    }

    public void printInventory() {
        System.out.println(inventory.getName() + ":");
        for (Item item : inventory.getItems()) {
            System.out.println(item.getName() + " - " + item.getDescription());
        }
    }


}
