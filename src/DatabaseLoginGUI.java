/* Gary Montero
 *  CEN3024C - Software Development 1
 *  March 28, 2025
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DatabaseLoginGUI.java
 * This class contains the logic for the GUI responsible for logging into the database that connects to the system as
 * well as setting the database connection properties. It contains three input fields for the database URL, username,
 * and password. It also contains two buttons that clear the fields and submit the values entered.
 *
 * @author Gary Montero
 */
public class DatabaseLoginGUI extends JFrame {
    private JPanel loginPane;
    private JLabel url;
    private JLabel username;
    private JLabel password;
    private JTextField urlField;
    private JTextField userField;
    private JPasswordField passField;
    private JButton submitButton;
    private JButton clearButton;


    /**
     * This is the constructor builds the window, text fields, and buttons that connect to the MySQL database. The
     * user is required to enter a database URL, username, and password and then those credentials are passed into
     * the setProperties method. The static method "connect" is called from the DatabaseManager class and if a
     * successful connection is made then the login window closes and a new instance of the UIController class is
     * created, allowing the user to use the system.
     */
    public DatabaseLoginGUI() {

        ImageIcon background = new ImageIcon("/Users/garymontero/Documents/CEN3024C - SD1/HikeTrackingSystem/images/neom-gj7WgSOIIu4-unsplash.jpg");
        Image img = background.getImage();
        Image temp = img.getScaledInstance(850,550,Image.SCALE_SMOOTH);
        background = new ImageIcon(temp);

        //Creating the background label
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, 600, 550);

        //Creating the layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 550));

        //Important so the background shows through
        loginPane.setOpaque(false);
        loginPane.setBounds(0, 0, 600, 550);

        layeredPane.add(backgroundLabel, Integer.valueOf(0)); //This is the background
        layeredPane.add(loginPane, Integer.valueOf(1)); //This is the foreground

        setContentPane(layeredPane);
        pack();
        setResizable(false);
        setVisible(true);


        setTitle("Hike Tracking System Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 550));
        setVisible(true);
        setLocationRelativeTo(null);
        pack();


        //Handles the logic for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                String user = userField.getText();
                String pass = passField.getText();

                //gives the fields a red border if they are empty
                if (url.isEmpty()) {
                    urlField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                if (user.isEmpty()) {
                    userField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                if (pass.isEmpty()) {
                    passField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                //Calls the static method "setProperties" and passes in the values from the text inputs

                DatabaseLoginGUI.setProperties(url, user, pass);


                try {
                    //calls the static method "connect" to try and form a connection to the database
                    DatabaseManager.connect();

                    //If the connection is null then set the border of the fields to red and notifies the user
                    if (DatabaseManager.connection == null) {
                        urlField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                        userField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                        passField.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                        JOptionPane.showMessageDialog(null, "Unable to connect");
                        return;
                    }

                    //If a connection is made then the login window closes and a new instance of the UIController class is made
                    dispose();
                    new UIController();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //handles the logic for the clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urlField.setText("");
                urlField.setBorder(BorderFactory.createEmptyBorder());
                userField.setText("");
                userField.setBorder(BorderFactory.createEmptyBorder());
                passField.setText("");
                passField.setBorder(BorderFactory.createEmptyBorder());
            }
        });
    }


    // Static Properties object to store database connection details
    private static final Properties properties = new Properties();


    /**
     * Sets the corresponding parameters using the setProperty method. In this case, the database
     * connection credentials
     *
     * @param url
     * @param username
     * @param password
     */
    public static void setProperties(String url, String username, String password) {
        properties.setProperty("db.url", url);
        properties.setProperty("db.username", username);
        properties.setProperty("db.password", password);
    }


    /**
     * Name: getDbUrl
     *
     * @return the value that is retrieved when the getProperty method is called
     */
    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    /**
     * Name: getDbUsername
     *
     * @return the value that is retrieved when the getProperty method is called
     */
    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    /**
     * Name: getDbPassword
     *
     * @return the value that is retrieved when the getProperty method is called
     */
    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }


}
