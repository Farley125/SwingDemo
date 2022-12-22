/**
 * The Shop class controls the cost of the items in the Treasure Hunt game.<p>
 * The Shop class also acts as a go between for the Hunter's buyItem() method.<p>
 */
import java.util.Scanner;

public class Shop
{
    // constants
    private static int waterCost = 2;
    private static int ropeCost = 4;
    private static int macheteCost = 6;
    private static int horseCost = 12;
    private static int boatCost = 20;

    // instance variables
    private double markdown;
    private static Hunter customer;

    // Variables for colors
    final String RED = "\033[0;31m";     // RED
    final String GREEN = "\033[0;32m";   // GREEN
    final String PURPLE = "\033[0;35m";  // PURPLE
    final String CYAN = "\033[0;36m";    // CYAN
    final String YELLOW = "\u001B[33m"; // YELLOW
    final String RESET = "\u001B[0m";
    final String BLUE = "\u001B[34m";    // BLUE

    //Constructor
    public Shop(double markdown)
    {
        this.markdown = markdown;
        customer = null;
    }

    /** method for entering the shop
     * @param hunter  the Hunter entering the shop
     * @param buyOrSell  String that determines if hunter is "B"uying or "S"elling
     */
    public void enter(Hunter hunter, String buyOrSell)
    {
        customer = hunter;

        Scanner scanner = new Scanner(System.in);
        if (buyOrSell.equals("B") || buyOrSell.equals("b"))
        {
            System.out.println("Welcome to the shop! We have the finest wares in town.");
            System.out.println("Currently we have the following items:");
            System.out.println(inventory());
            System.out.print("What're you lookin' to buy? ");
            String item = "";
            while (ShopWindow.didInteract() == false) {
                item = ShopWindow.getActionPerformed();
                System.out.print(item);
            }
            ShopWindow.setActionPerformed("");
            int cost = checkMarketPrice(item, true);
            if (cost == 0)
            {
                System.out.println("We ain't got none of those.");
            }
            else
            {
                System.out.print(" It costed ye' " + YELLOW + cost + " gold. " + RESET);
                buyItem(item);
            }
            ShopWindow.reset();
            item = "";
        }
        else
        {
            System.out.println("What're you lookin' to sell? ");
            System.out.print("You currently have the following items: " + customer.getInventory());
            String item = scanner.nextLine();
            int cost = checkMarketPrice(item, false);
            if (cost == 0)
            {
                System.out.println("We don't want none of those.");
            }
            else
            {
                System.out.print("It'll get you " + cost + " gold. Sell it (y/n)? ");
                String option = scanner.nextLine();

                if (option.equals("y") || option.equals("Y"))
                {
                    sellItem(item);
                }
            }
        }
    }

    /** A method that returns a string showing the items available in the shop (all shops sell the same items)
     *
     * @return  the string representing the shop's items available for purchase and their prices
     */
    public String inventory()
    {
        String str = BLUE + "Water (W): " + waterCost + " gold\n" + RESET;
        str += PURPLE + "Rope (R): " + ropeCost + " gold\n" + RESET;
        str += GREEN + "Machete (M): " + macheteCost + " gold\n" + RESET;
        str += RED + "Horse (H): " + horseCost + " gold\n" + RESET;
        str += CYAN + "Boat (B): " + boatCost + " gold\n" + RESET;

        return str;
    }

    public void cheatModePricing() {
        waterCost = 1;
        ropeCost = 1;
        macheteCost = 1;
        horseCost = 1;
        boatCost = 1;
    }

    public void easyModePricing() {
        waterCost = 1;
        ropeCost = 2;
        macheteCost = 3;
        horseCost = 6;
        boatCost = 10;
    }

    /**
     * A method that lets the customer (a Hunter) buy an item.
     * @param item The item being bought.
     */
    public void buyItem(String item)
    {

        int costOfItem = checkMarketPrice(item, true);
        if (customer.buyItem(item, costOfItem))
        {
            System.out.println("\nYe' got yerself a " + GREEN + item +RESET + ". Come again soon.");
        }
        else
        {
            System.out.println("Hmm, either you don't have enough gold or you've already got one of those!");
        }
    }

    public Hunter getCustomer() {
        return customer;
    }

    /**
     * A pathway method that lets the Hunter sell an item.
     * @param item The item being sold.
     */
    public void sellItem(String item)
    {
        int buyBackPrice = checkMarketPrice(item, false);
        if (customer.sellItem(item, buyBackPrice))
        {
            System.out.println("Pleasure doin' business with you.");
        }
        else
        {
            System.out.println("Stop stringin' me along!");
        }
    }

    /**
     * Determines and returns the cost of buying or selling an item.
     * @param item The item in question.
     * @param isBuying Whether the item is being bought or sold.
     * @return The cost of buying or selling the item based on the isBuying parameter.
     */
    public int checkMarketPrice(String item, boolean isBuying)
    {
        if (isBuying)
        {
            return getCostOfItem(item);
        }
        else
        {
            return getBuyBackCost(item);
        }
    }

    /**
     * Checks the item entered against the costs listed in the static variables.
     *
     * @param item The item being checked for cost.
     * @return The cost of the item or 0 if the item is not found.
     */
    public int getCostOfItem(String item)
    {
        if (item.equalsIgnoreCase("Water") || item.equalsIgnoreCase("W"))
        {
            return waterCost;
        }
        else if (item.equalsIgnoreCase("Rope") || item.equalsIgnoreCase("R"))
        {
            return ropeCost;
        }
        else if (item.equalsIgnoreCase("Machete") || item.equalsIgnoreCase("M"))
        {
            return macheteCost;
        }
        else if (item.equalsIgnoreCase("Horse") || item.equalsIgnoreCase("H"))
        {
            return horseCost;
        }
        else if (item.equalsIgnoreCase("Boat") || item.equalsIgnoreCase("B"))
        {
            return boatCost;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Checks the cost of an item and applies the markdown.
     *
     * @param item The item being sold.
     * @return The sell price of the item.
     */
    public int getBuyBackCost(String item)
    {
        int cost = (int)(getCostOfItem(item) * markdown);
        return cost;
    }
}

