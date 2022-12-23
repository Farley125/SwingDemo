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
    private JPanel buttonPanel;

    private final String SHOP = "Buy items";
    private final String SELL = "Sell items";
    private final String TOWNS = "Change towns";
    private final String BRAWL = "Brawl!";
    private final String HUNT = "Hunt";

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


        choiceOne = new JButton(SHOP);
        choiceTwo = new JButton(SELL);
        choiceThree = new JButton(TOWNS);
        choiceFour = new JButton(BRAWL);
        choiceFive = new JButton(HUNT);


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

        choiceFive.addActionListener(new ActionListener() {
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
        choiceFive.setBounds(5, 100, 100, 50);
        buttonPanel.add(choiceFive);

        label.setFont(font);
        j.add(label, BorderLayout.CENTER);
        j.add(buttonPanel, BorderLayout.NORTH);
        j.show();
    }
    public static String getChoice() {
        return choice;
    }

    public JFrame getJ() {
        return j;
    }

    public static void setChoice(String set) {
        choice = set;
    }

    public void performAction(String button) {
        if (button.equals(SHOP)) {
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