import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShopWindow extends JFrame {

    private static JFrame j;
    private JLabel label;
    private JButton choiceOne;
    private JButton choiceTwo;
    private JButton choiceThree;
    private JButton choiceFour;
    private JButton choiceFive;
    private JPanel buttonPanel;
    private static String actionPerformed = "";
    private static boolean test = false;

    private final String ROPE = "Rope";
    private final String WATER = "Water";
    private final String MACHETE = "Machete";
    private final String HORSE = "Horse";
    private final String BOAT = "Boat";

    public ShopWindow(String display) {

        j = new JFrame(display);
        j.setSize(800, 600);
        j.setLocation(5, 5);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("<html>Welcome to the shop!<br/>We have the finest wares in town.<br/>What're you lookin' to buy? <html>", SwingConstants.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 0, 0));
        buttonPanel.setSize(100, 300);

        choiceOne = new JButton(WATER);
        choiceTwo = new JButton(ROPE);
        choiceThree = new JButton(MACHETE);
        choiceFour = new JButton(HORSE);
        choiceFive = new JButton(BOAT);

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

        choiceOne.setBounds(10, 100, 200, 100);
        buttonPanel.add(choiceOne);
        choiceTwo.setBounds(10, 100, 200, 100);
        buttonPanel.add(choiceTwo);
        choiceThree.setBounds(10, 100, 200, 100);
        buttonPanel.add(choiceThree);
        choiceFour.setBounds(10, 100, 200, 100);
        buttonPanel.add(choiceFour);
        choiceFour.setBounds(10, 100, 200, 100);
        buttonPanel.add(choiceFive);


        label.setFont(font);
        j.add(label, BorderLayout.CENTER);
        j.add(buttonPanel, BorderLayout.NORTH);
        j.setVisible(false);

    }

    public static String getActionPerformed() {
        return actionPerformed;
    }

    public static boolean didInteract() {
        return test;
    }

    public static void reset() {
        test = false;
    }

    public static void setActionPerformed(String set) {
        actionPerformed = set;
    }


    public void performAction(String button) {
        if (button.equals(WATER)) {
            actionPerformed = "Water";
            test = true;
        }
        else if (button.equals(ROPE)) {
            actionPerformed = "Rope";
            test = true;
        }
        else if (button.equals(MACHETE)) {
            actionPerformed = "Machete";
            test = true;
        }
        else if (button.equals(HORSE)) {
            actionPerformed = "Horse";
            test = true;
        }
        else if (button.equals(BOAT)) {
            actionPerformed = "Boat";
            test = true;
        }
    }

    public static void activateShop() {
        j.setVisible(true);
    }

    public static void deactivateShop() {
        j.setVisible(false);
    }

}
