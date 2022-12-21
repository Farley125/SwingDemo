import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow extends JFrame {

    private JFrame j;
    private JLabel label;
    private JButton choiceOne;
    private JButton choiceTwo;
    private JButton choiceThree;
    private JButton choiceFour;
    private JButton choiceFive;
    private JButton choiceSix;
    private JButton choiceSeven;
    private JButton choiceEight;
    private JButton choiceNine;
    private JButton choiceTen;
    private JPanel buttonPanel;
    private JPanel buttonPanel2;

    private final String SHOP = "Buy items";
    private final String SELL = "Sell items";
    private final String TOWNS = "Change towns";
    private final String BRAWL = "Brawl!";
    private final String HUNT = "Hunt";

    private final String WATER = "Water";
    private final String ROPE = "Rope";
    private final String MACHETE = "Machete";
    private final String HORSE = "Horse";
    private final String BOAT = "Boat";
    private static String choice = "";

    public GameWindow(String display) {

        j = new JFrame(display);
        j.setSize(800, 600);
        j.setLocation(5, 5);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Welcome to Treasure Hunter!", SwingConstants.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 0, 0));
        buttonPanel.setSize(100, 300);

        buttonPanel2 = new JPanel();
        buttonPanel2.setBackground(new Color(200, 200, 200));
        buttonPanel2.setSize(250, 300);
        buttonPanel2.hide();

        choiceOne = new JButton(SHOP);
        choiceTwo = new JButton(SELL);
        choiceThree = new JButton(TOWNS);
        choiceFour = new JButton(BRAWL);
        choiceFive = new JButton(HUNT);
        choiceSix = new JButton(WATER);
        choiceSeven = new JButton(ROPE);
        choiceEight = new JButton(MACHETE);
        choiceNine = new JButton(HORSE);
        choiceTen = new JButton(BOAT);


        choiceOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                performAction(evt.getActionCommand());
            }
        });

        choiceTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                performAction(evt.getActionCommand());
            }
        });

        choiceThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                performAction(evt.getActionCommand());
            }
        });

        choiceFour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                performAction(evt.getActionCommand());
            }
        });

        //create new Font
        Font font = new Font("Courier", Font.BOLD,16);

        choiceOne.setBounds(5, 100, 100, 50);
        buttonPanel.add(choiceOne);
        choiceTwo.setBounds(5, 100, 100, 50);
        buttonPanel.add(choiceTwo);
        choiceThree.setBounds(5, 100, 100, 50);
        buttonPanel.add(choiceThree);
        choiceFour.setBounds(5, 100, 100, 50);
        buttonPanel.add(choiceFour);
        choiceFour.setBounds(5, 100, 100, 50);
        buttonPanel.add(choiceFive);

        choiceOne.setBounds(5, 100, 100, 50);
        buttonPanel2.add(choiceSix);
        choiceTwo.setBounds(5, 100, 100, 50);
        buttonPanel2.add(choiceSeven);
        choiceThree.setBounds(5, 100, 100, 50);
        buttonPanel2.add(choiceEight);
        choiceFour.setBounds(5, 100, 100, 50);
        buttonPanel2.add(choiceNine);
        choiceFour.setBounds(5, 100, 100, 50);
        buttonPanel2.add(choiceTen);

        label.setFont(font);
        j.add(label, BorderLayout.CENTER);
        j.add(buttonPanel, BorderLayout.NORTH);
        j.add(buttonPanel2, BorderLayout.WEST);
        j.show();
    }
    public static String getChoice() {
        return choice;
    }

    public static void setChoice(String set) {
        choice = set;
    }

    public void performAction(String button) {
        if (button.equals(SHOP)) {
            ShopWindow frame = new ShopWindow("Welcome to the Shop!");
            choice = "b";
        }
        else if (button.equals(SELL)) {
            choice = "s";
        }
        else if (button.equals(TOWNS)) {
            choice = "m";
        }
        else if (button.equals(BRAWL)) {
            choice = "l";
        }
        else if (button.equals(HUNT)) {
            choice = "h";
        }
    }

}