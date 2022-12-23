/**
 * This class is responsible for controlling the Treasure Hunter game.<p>
 * It handles the display of the menu and the processing of the player's choices.<p>
 * It handles all of the display based on the messages it receives from the Town object.
 *
 */
import java.util.Scanner;

public class TreasureHunter
{
    //Instance variables
    private Town currentTown;
    private Hunter hunter;
    private boolean hardMode;
    private boolean cheatMode;
    private boolean easyMode;
    private boolean windowMade;
    private GameWindow frame;
    private ShopWindow shopWindow;

    // Variables for colors
    final String RED = "\033[0;31m";     // RED
    final String GREEN = "\033[0;32m";   // GREEN
    final String PURPLE = "\033[0;35m";  // PURPLE
    final String CYAN = "\033[0;36m";    // CYAN
    final String RESET = "\u001B[0m";
    final String BLUE = "\u001B[34m";    // BLUE

    //Constructor
    /**
     * Constructs the Treasure Hunter game.
     */
    public TreasureHunter()
    {
        // these will be initialized in the play method
        currentTown = null;
        hunter = null;
        hardMode = false;
        cheatMode = false;
        easyMode = false;
        frame = new GameWindow("Welcome to Treasure Hunter!");
        shopWindow = new ShopWindow("Welcome to the Shop!");
    }

    // starts the game; this is the only public method
    public void play ()
    {
        welcomePlayer();
        enterTown();
        if (hunter.isGameOver() == false) {
            showMenu();
        } else System.exit(0);
    }

    /**
     * Creates a hunter object at the beginning of the game and populates the class member variable with it.
     */
    private void welcomePlayer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(GREEN + "Welcome to TREASURE HUNTER!" + RESET);
        System.out.println("Going hunting for the big treasure, eh?");
        System.out.print("What's your name, Hunter? ");
        String name = scanner.nextLine();

        // set hunter instance variable
        hunter = new Hunter(name, 10);

        System.out.print("Easy, normal, or hard? (e, n, h) ");
        String hard = scanner.nextLine();
        if (hard.equals("H") || hard.equals("h"))
        {
            hardMode = true;
            hunter = new Hunter(name, 10);
        } else if (hard.equals("c") || hard.equals("C"))
        {
            cheatMode = true;
            hunter = new Hunter(name, 10);
        } else if (hard.equals("e") || hard.equals("E")) {
            easyMode = true;
            hunter = new Hunter(name, 50);
        }
    }

    /**
     * Creates a new town and adds the Hunter to it.
     */
    private void enterTown()
    {
        double markdown = 0.25;
        double toughness = 0.4;
        if (hardMode)
        {
            // in hard mode, you get less money back when you sell items
            markdown = 0.5;

            // and the town is "tougher"
            toughness = 0.75;
        }

        // note that we don't need to access the Shop object
        // outside of this method, so it isn't necessary to store it as an instance
        // variable; we can leave it as a local variable
        Shop shop = new Shop(markdown);
        if (cheatMode) {
            shop.cheatModePricing();
        }

        if (easyMode) {
            shop.easyModePricing();
        }

        // creating the new Town -- which we need to store as an instance
        // variable in this class, since we need to access the Town
        // object in other methods of this class
        currentTown = new Town(shop, toughness);
        currentTown.setCheatMode(cheatMode);
        currentTown.setEasyMode(easyMode);
        // calling the hunterArrives method, which takes the Hunter
        // as a parameter; note this also could have been done in the
        // constructor for Town, but this illustrates another way to associate
        // an object with an object of a different class
        currentTown.hunterArrives(hunter);
    }

    /**
     * Displays the menu and receives the choice from the user.<p>
     * The choice is sent to the processChoice() method for parsing.<p>
     * This method will loop until the user chooses to exit.
     */
    private void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!(choice.equals("X") || choice.equals("x")) && (hunter.isGameOver() == false))
        {
            shopWindow.deactivateShop();
            System.out.println();
            System.out.println(currentTown.getLatestNews());
            System.out.println("***");
            System.out.println(hunter);
            System.out.println("Relics: " + hunter.getRelics());
            System.out.println(currentTown);
            System.out.println("(B)uy something at the shop.");
            System.out.println("(S)ell something at the shop.");
            System.out.println("(H)unt for treasure!");
            System.out.println("(M)ove on to a different town.");
            System.out.println("(L)ook for trouble!");
            System.out.println("Give up the hunt and e(X)it.");
            System.out.println();
            System.out.print("What's your next move? ");
            while (choice.equals("")) {
                choice = GameWindow.getChoice();
                System.out.print("");
            }
            frame.setVisible(false);
            processChoice(choice);
            choice = "";
            GameWindow.setChoice("");
            TreasureHunter.clearScreen();

            if (hunter.getRelics().indexOf("an Emerald") != -1 && hunter.getRelics().indexOf("a Cool Hat") != -1 && hunter.getRelics().indexOf("a Golden Relic") != -1) {
                hunter.setGameOver(true);
            }
        }
        if (hunter.getRelics().indexOf("an Emerald") != -1 && hunter.getRelics().indexOf("a Cool Hat") != -1 && hunter.getRelics().indexOf("a Golden Relic") != -1) {
            System.out.println("Congrats, you've collected all of the relics!");
        } else {
            System.out.println("\nHe stabbed your appendix and you bled out!\nYou die! (Game Over!)");
        }
    }

    /**
     * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
     * @param choice The action to process.
     */
    private void processChoice(String choice)
    {
        if (choice.equals("B") || choice.equals("b") || choice.equals("S") || choice.equals("s"))
        {
            //shopWindow = new ShopWindow("Welcome to the Shop!");
            shopWindow.activateShop();
            currentTown.enterShop(choice);
        }
        else if (choice.equals("M") || choice.equals("m"))
        {
            if (currentTown.leaveTown())
            {
                //This town is going away so print its news ahead of time.
                System.out.println(currentTown.getLatestNews());
                enterTown();
            }
        }
        else if (choice.equals("L") || choice.equals("l"))
        {
            currentTown.lookForTrouble();
        }
        else if ((choice.equals("H") || choice.equals("h")) && currentTown.canSearch())
        {
            if (currentTown.getTreasure().equals("nothing") == false) {
                System.out.println("You search for treasure, you find " + currentTown.getTreasure() + "!");
                if (hunter.getRelics().indexOf(currentTown.getTreasure()) == -1)
                {
                    hunter.addRelic(currentTown.getTreasure() + ", ");
                }
                else
                {
                    System.out.println("You already have this relic!");
                }
            } else System.out.println("You found nothing!");

            currentTown.setCanSearch(false);
        }
        else if (choice.equals("X") || choice.equals("x"))
        {
            System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}