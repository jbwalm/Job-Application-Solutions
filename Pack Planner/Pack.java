import java.util.*;
import java.math.*;

/**
 * This class represents the Object Pack.
 * Stores appropriate variables for Pack instances.
 * This class supplies appropriate get methods as well as a method to add items to it.
 */
public class Pack{

    /**
     * Variables for class use and to be retrieved by external classes.
     */
    private Integer packID;
    private ArrayList<Item> items = new ArrayList<Item>();
    private float packWeight = 0.0f;
    private int packPieces = 0; 
    private Item leftOver;
    private int maxItemLength = 0;

    /**
     * Pack class constructor.
     * @param packID Unique identifier for this pack.
     */
    public Pack(Integer packID){
        this.packID = packID;
    }

    /**
     * Retrieves pack ID.
     * @return Pack unique identifier.
     */
    public Integer getPackID(){
        return packID;
    }
    
    /**
     * Retrieves packs item list.
     * @return item list.
     */
    public ArrayList<Item> getItems(){
        return items;
    }

    /**
     * Retrieves left over pieces of an item if an item had to be split for next pack.
     * This is represented as a new Item isntance with shared identifier (ID).
     * @return left over Item instance.
     */
    public Item getLeftOver(){
        return leftOver;
    }

    /**
     * Retrieves longest item in the pack.
     * @return longest item length.
     */
    public int getMaxItemLength(){
        return maxItemLength;
    }

    /**
     * Retrieves total weight of the pack.
     * @return pack weight.
     */
    public float getPackWeight(){
        return packWeight;
    }

    /**
     * Adds the given item to the pack if possible.
     * If every piece of the item cannot be added, it will be split for the next pack.
     * @param item the item to be added.
     * @param maxWeight the maximum weight a pack can have.
     * @param maxPieces the maximum number of pieces a pack can contain.
     * @return true if the item was succesfully added, false if the item was split or cannot be added.
     */
    public boolean addItem(Item item, float maxWeight, int maxPieces){
        int piecesSpace = maxPieces - packPieces;
        float weightSpace = maxWeight - packWeight;

        if (piecesSpace == 0 || weightSpace < item.getWeight()){
            leftOver = item;
            return false;
        }

        float totalItemWeight = item.getWeight() * item.getQuantity();
        if (totalItemWeight <= weightSpace && item.getQuantity() <= piecesSpace){
            items.add(item);
            packWeight += totalItemWeight;
            packPieces += item.getQuantity();

            if (item.getLength() > maxItemLength){
                maxItemLength = item.getLength();
            }
            return true;
        }

        float piecesSpaceWeight = piecesSpace * item.getWeight();
        int piecesToAdd = 0;
        if (piecesSpaceWeight > weightSpace){
            piecesToAdd = (int) Math.floor(weightSpace / item.getWeight());
        }else{
            piecesToAdd = piecesSpace;
        }

        leftOver = new Item(item.getItemID(), item.getLength(), item.getQuantity() - piecesToAdd, item.getWeight());
        item.setQuantity(piecesToAdd);
        items.add(item);
        packWeight += piecesToAdd * item.getWeight();
        packPieces += piecesToAdd;

        if (item.getLength() > maxItemLength){
            maxItemLength = item.getLength();
        }

        return false;
    }

}