import java.util.*;

/**
 * Item class to represent Item instances.
 * Stores variables relating to individual items.
 * Implements comparator for sorting.
 * provides appropriate get and set methods for external use.
 * Provides custom toString method for report output.
*/
public class Item implements Comparable<Item>{

    /**
     * Item instance variables
     */
    private Integer itemID;
    private Integer length = 0;
    private int quantity;
    private float weight;

    /**
     * Item constructor for instance creation.
     * @param itemID Unique identifier for items.
     * @param length Length of item.
     * @param quantity quantity in pieces of item.
     * @param weight weight of each individual item piece.
     */
    public Item(Integer itemID, Integer length, int quantity, float weight){
        this.itemID = itemID;
        this.length = length;
        this.quantity = quantity;
        this.weight = weight;
    }

    /**
     * Retrieves item unique identifier (ID).
     * @return item unique identifier.
     */
    public Integer getItemID(){
        return itemID;
    }

    /**
     * Retrieves the number of pieces for this item.
     * @return number of pieces.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * sets the quantity value for an item instance.
     * @param quantity the value to be set.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * retrieves the length of an item piece.
     * @return item piece length.
     */
    public Integer getLength(){
        return length;
    }

    /**
     * retrieves the weight of each item piece.
     * @return item piece weight.
     */
    public float getWeight(){
        return weight;
    }

    /**
     * Custom toString() method for this class.
     */
    @Override
    public String toString(){
        return itemID + "," + length + "," + quantity + "," + weight;
    }

    /**
     * Customer compareTo() method for appropriate comparison when using Collections.sort().
     * @param item Item instance to compare this instance to.
     * @return comparison result.
     */
    @Override
    public int compareTo(Item item){
        if (getLength() == 0 || item.getLength() == 0){
            return 0;
        }

        return getLength().compareTo(item.getLength());


    }

}