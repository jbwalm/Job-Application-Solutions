import java.util.*;

/**
 * Main Class for pack planner software test program.
 * This class sorts the input items and uses two support classes to represent packs and items.
 * Then this class produces the desired output of planning packs.
 */
public class packPlanner{

    /**
     * global variables for use throughout the class.
     */
    private static String[] sortOrders = {"NATURAL", "SHORT_TO_LONG", "LONG_TO_SHORT"};
    private static ArrayList<Item> items = new ArrayList<Item>();
    private static ArrayList<Pack> packs = new ArrayList<Pack>();
    private static int index = 0; 
    private static String sortOrder;
    private static int maxPiecesPerPack;
    private static float maxWeightPerPack;
    
    /**
     * The main method to serve as an entry point to plan packs.
     * Scans in the input and prepares it for use.
     * Converts input data into class Item instances.
     * Calls method packedBrain() to carry out the planning logic.
     * Calls method report() to report the desired outpuit.
     * @param args user input of item data.
     */
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        String[] parts;
        try {
            parts = scan.nextLine().split(",");
            sortOrder = parts[0];
            maxPiecesPerPack = Integer.parseInt(parts[1]);
            maxWeightPerPack = Float.valueOf(parts[2]).floatValue();

            while (scan.hasNext()){
                parts = scan.nextLine().split(",");
                Item item = new Item(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.parseInt(parts[2]), Float.valueOf(parts[3]).floatValue());
                items.add(item);
            }
        }catch(Exception e){
            System.out.println("Incorrect input given with error: " + e);
            System.exit(0);
        }
        
        packerBrain();
        report();

        return;
    }

    /**
     * Coordinates the work by sorting the items as desired.
     * Creats class Pack instances as required.
     * Calls method packer() to pack each generated pack. 
     */
    private static void packerBrain(){
        if (sortOrder.equals(sortOrders[1])){
            Collections.sort(items);
        }else if(sortOrder.equals(sortOrders[2])){
            Collections.sort(items);
            Collections.reverse(items);
        }

        Integer counter = 1;
        boolean packingDone = false;
        while (!packingDone){
            Pack pack = new Pack(counter);
            packs.add(pack);
            counter += 1;

            packingDone = packer(pack);
        }

    }

    /**
     * Uses pack class to add items to current pack.
     * Acknowledges if an item was split by weight or quantity for next pack.
     * @param pack the current pack that is being packed.
     * @return false for a full pack and the need to create a new one, true if finished planning packs.
     */
    private static boolean packer(Pack pack){
        for (int i = index; i < items.size(); i++){
            Item item = items.get(i);

            if (item.getWeight() > maxWeightPerPack){
                System.out.println("There is an item in the input list that is heavier than the max pack weight.");
                System.out.println("This item has been skipped and pack planning continues.");
                continue;
            }

            boolean outcome = pack.addItem(item, maxWeightPerPack, maxPiecesPerPack);

            if (outcome == false){
                index = i;
                items.set(i, pack.getLeftOver());
                return false;
            }
        }

        return true;
    }

    /**
     * Reports the desired output of how the packs were planned.
     */
    private static void report(){
        for (Pack pack : packs){
            System.out.println("Pack Number: " + pack.getPackID());
            ArrayList<Item> packItems = pack.getItems();
            for (Item packItem : packItems){
                System.out.println(packItem.toString());
            }
            System.out.println("Pack Length: " + pack.getMaxItemLength() + ", Pack Weight: " + pack.getPackWeight() + "\n");
        }
    }

}

