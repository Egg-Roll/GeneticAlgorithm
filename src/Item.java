// Justin Do

public class Item {

    private final String name; // A label for the item, such as “Jewelry” or “Kindle”
    private final double weight; // The weight of the item in pounds
    private final int value; // The value of the item rounded to the nearest dollar
    private boolean included = false; // Indicates whether the item should be taken or not

    /*
     * Initializes the Item’s =fields to the values that are passed in; the included = field is
     * initialized to false
     */
    public Item(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    /*
     * Initializes this item’s = fields to be the same as the other item’s
     */
    public Item(Item other) {
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    public double getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    public String toString() {
        return name + " (" + weight + " lbs, " + "$" + value + ")";
    }
}
