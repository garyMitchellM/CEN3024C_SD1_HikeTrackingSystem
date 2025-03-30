/* Gary Montero
 *  CEN3024C - Software Development 1
 *  March 28, 2025
 *  UIController.java
 *  This class contains the logic for the graphical user interface of the system. It displays all the possible options
 * that the user can choose from the menu and accepts input for what the user wants to do. If the user enters any
 * invalid data then the border around the text field will turn red. The user can only exit the program by closing the
 * window.
 * */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UIController extends JFrame {

    //Java Swing Components
    private JPanel contentPane;
    private JTextField inputName;
    private JTextField inputLocation;
    private JTextField inputDistance;
    private JTextField inputDuration;
    private JTextField inputElevation;
    private JTextField inputDiffLvl;
    private JButton addFromTextFileButton;
    private JButton addHikeButton;
    private JButton updateHikeButton;
    private JButton deleteHikeButton;
    private JButton calculateTotalDistanceHikedButton;
    private JButton clearButton;
    private JButton exitButton;
    private JTable listOfHikes;
    private JButton deleteAllHikes;
    private DefaultTableModel model;


    //Constructor
    public UIController() throws SQLException {
        setContentPane(contentPane);
        setTitle("Hike Tracking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 300));
        setVisible(true);
        setLocationRelativeTo(null);
        pack();


        //Creating a new instance of the DefaultTableModel
        model = new DefaultTableModel() {
            //Overriding the isCellEditable method to not allow the cells in the table to be edited
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listOfHikes.setModel(model);
        //adding the columns with the name of each one at the top
        model.addColumn("Name");
        model.addColumn("Location");
        model.addColumn("Distance (mi)");
        model.addColumn("Duration");
        model.addColumn("Elevation (ft)");
        model.addColumn("Difficulty Level");

        //populates the table with the data from the database
        popTable(DatabaseManager.fillTable());

        //handles the logic for when the "Add Hike" button is pressed
        addHikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] validateFlag = new int[6];
                double hikeDistance = -1;
                int hikeElevation = -1;

                //gets the input from the text field and removes the red border if it is valid
                String hikeName = inputName.getText();
                inputName.setBorder(BorderFactory.createEmptyBorder());

                String hikeLocation = inputLocation.getText();
                inputLocation.setBorder(BorderFactory.createEmptyBorder());

                //try-catch blocks for double and int data types to account for null inputs. Sets validateFlag to 1
                try {
                    hikeDistance = Double.parseDouble(inputDistance.getText());
                    inputDistance.setBorder(BorderFactory.createEmptyBorder());
                } catch (NumberFormatException ex) {
                    validateFlag[2] = 1;
                }

                String hikeDuration = inputDuration.getText();
                inputDuration.setBorder(BorderFactory.createEmptyBorder());

                try {
                    hikeElevation = Integer.parseInt(inputElevation.getText());
                    inputElevation.setBorder(BorderFactory.createEmptyBorder());
                } catch (NumberFormatException ex) {
                    validateFlag[4] = 1;
                }

                String hikeDiffLvl = inputDiffLvl.getText();
                inputDiffLvl.setBorder(BorderFactory.createEmptyBorder());

                //Calls the static validation methods from the Hike class and sets flag to 1 if validation fails (all flags start as 0s)
                //turns the border of the text field red if it is invalid
                if (Hike.invalidName(hikeName)) {
                    validateFlag[0] = 1;
                    inputName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                if (Hike.invalidLocation(hikeLocation)) {
                    validateFlag[1] = 1;
                    inputLocation.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                if (validateFlag[2] == 1 || Hike.invalidDistance(hikeDistance)) {
                    validateFlag[2] = 1;
                    inputDistance.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                if (Hike.invalidDuration(hikeDuration)) {
                    validateFlag[3] = 1;
                    inputDuration.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                if (validateFlag[4] == 1 || Hike.invalidElevation(hikeElevation)) {
                    validateFlag[4] = 1;
                    inputElevation.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                if (Hike.invalidDiffLvl(hikeDiffLvl)) {
                    validateFlag[5] = 1;
                    inputDiffLvl.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }

                int allFlags = 0;

                //add the flags together
                for (int i = 0; i < 6; i++) {
                    allFlags += validateFlag[i];
                }
                //if any failed then allFlags will be greater than 0
                if (allFlags == 0) {

                    //Inserts hike data using the static method "insertHike" from the DatabaseManager class
                    DatabaseManager.insertHike(hikeName, hikeLocation, hikeDistance, hikeDuration, hikeElevation, hikeDiffLvl);

                    //Adds the hike values to the table and resets the text fields
                    model.addRow(new Object[]{hikeName, hikeLocation, hikeDistance, hikeDuration, hikeElevation, hikeDiffLvl});
                    resetFields();
                }
            }
        });

        //handles the logic for when the "Clear" button is pressed
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //resets the text fields
                resetFields();
            }
        });

        //handles the logic for when the "Delete Hike" button is pressed
        deleteHikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameofHikeRemoved = JOptionPane.showInputDialog("Enter the name of hike to remove.");
                if (nameofHikeRemoved == null || nameofHikeRemoved.isEmpty()) {
                    return;
                }
                try {
                    //Deletes hike data using the static method "deleteHike" from the DatabaseManager class
                    //if the hike was removed, then the table gets repopulated and the user is notified
                    if (DatabaseManager.deleteHike(nameofHikeRemoved)) {
                        popTable(DatabaseManager.fillTable());
                        JOptionPane.showMessageDialog(null, "The hike: '" + nameofHikeRemoved + "' was removed from the system.");
                    } else {
                        JOptionPane.showMessageDialog(null, "That hike does not exist.");
                    }

                } catch (Exception err) {
                    return;
                }

                //resets the text fields
                resetFields();
            }
        });

        //handles the logic for when the user clicks on a hike in the table. It adds the details of that hike to the correct text fields
        listOfHikes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = listOfHikes.rowAtPoint(e.getPoint());
                System.out.println("Clicked on row: " + row);
                inputName.setText(listOfHikes.getValueAt(row, 0).toString());
                inputLocation.setText(listOfHikes.getValueAt(row, 1).toString());
                inputDistance.setText(listOfHikes.getValueAt(row, 2).toString());
                inputDuration.setText(listOfHikes.getValueAt(row, 3).toString());
                inputElevation.setText(listOfHikes.getValueAt(row, 4).toString());
                inputDiffLvl.setText(listOfHikes.getValueAt(row, 5).toString());
            }
        });

        //handles the logic for when the "Calculate Total Distance Hiked" button is pressed
        calculateTotalDistanceHikedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalDistance = DatabaseManager.calcualteTotalDistance();
                JOptionPane.showMessageDialog(null, "The total distance you have hiked is: " + totalDistance + " miles");
            }
        });


        //handles the logic for when the "Add from Text File" button is pressed
        addFromTextFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        //If any of the hikes from the text file are incorrectly formated and not entered into the
                        // database then the user is notified
                        if (!DatabaseManager.addFormTextFile(file.getPath())) {
                            JOptionPane.showMessageDialog(null, "One or more of the hikes in the" +
                                    " text file had invalid data and were not added to the system.");
                            //Populates the table with what's in the database.
                            popTable(DatabaseManager.fillTable());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Enter a valid text file");
                    }
                }
            }
        });

        //handles the logic for when the "Add Hike" button is pressed. Has similar logic to the "Add Hike" button
        updateHikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] validateFlag = new int[6];
                try {
                    double hikeDistance = -1;
                    int hikeElevation = -1;

                    String hikeName = inputName.getText();
                    inputName.setBorder(BorderFactory.createEmptyBorder());

                    String hikeLocation = inputLocation.getText();
                    inputLocation.setBorder(BorderFactory.createEmptyBorder());

                    try {
                        hikeDistance = Double.parseDouble(inputDistance.getText());
                        inputDistance.setBorder(BorderFactory.createEmptyBorder());
                    } catch (NumberFormatException ex) {
                        validateFlag[2] = 1;
                    }

                    String hikeDuration = inputDuration.getText();
                    inputDuration.setBorder(BorderFactory.createEmptyBorder());

                    try {
                        hikeElevation = Integer.parseInt(inputElevation.getText());
                        inputElevation.setBorder(BorderFactory.createEmptyBorder());
                    } catch (NumberFormatException ex) {
                        validateFlag[4] = 1;
                    }

                    String hikeDiffLvl = inputDiffLvl.getText();
                    inputDiffLvl.setBorder(BorderFactory.createEmptyBorder());


                    if (Hike.invalidName(hikeName)) {
                        validateFlag[0] = 1;
                        inputName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    }
                    if (Hike.invalidLocation(hikeLocation)) {
                        validateFlag[1] = 1;
                        inputLocation.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    }
                    if (validateFlag[2] == 1 || Hike.invalidDistance(hikeDistance)) {
                        validateFlag[2] = 1;
                        inputDistance.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    }
                    if (Hike.invalidDuration(hikeDuration)) {
                        validateFlag[3] = 1;
                        inputDuration.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    }
                    if (validateFlag[4] == 1 || Hike.invalidElevation(hikeElevation)) {
                        validateFlag[4] = 1;
                        inputElevation.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    }
                    if (Hike.invalidDiffLvl(hikeDiffLvl)) {
                        validateFlag[5] = 1;
                        inputDiffLvl.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    }

                    int allFlags = 0;

                    //add the flags together. probably cleaner as a for loop
                    for (int i = 0; i < 6; i++) {
                        allFlags += validateFlag[i];
                    }
                    //if any failed then someVar will be greater than 0
                    if (allFlags == 0) {

                        //assigns "row" to the row that the user selects
                        int row = listOfHikes.getSelectedRow();
                        String firstHikeName = (String) listOfHikes.getValueAt(row, 0);
                        System.out.println(firstHikeName);
                        //Updates hike data using the static method "updateHike" from the DatabaseManager class
                        DatabaseManager.updateHike(hikeName, hikeLocation, hikeDistance, hikeDuration, hikeElevation, hikeDiffLvl, firstHikeName);
                        //Populates the table with what's in the database.
                        popTable(DatabaseManager.fillTable());

                        //Resets the text fields
                        resetFields();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Cannot update hike because there are currently none in the system." +
                            "\nPlease add to the system if you wish to update a hike.");
                }
            }
        });

        //handles the logic for when the "Quit" button is pressed
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //handles the logic for when the "Delete All Hikes" button is pressed
        deleteAllHikes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int deleteAllButton = JOptionPane.showConfirmDialog(null,
                        "This action will delete every hike you have added!\n\nAre you sure you want to continue?\n",
                        "WARNING!", JOptionPane.YES_NO_OPTION);

                if (deleteAllButton == JOptionPane.YES_OPTION) {
                    try {
                        //deletes all hike data using the static method "deleteAllHikes" from the DatabaseManager class
                        DatabaseManager.deleteAllHikes();
                        //Populates the table with what's in the database.
                        popTable(DatabaseManager.fillTable());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null, "All hikes have been deleted.");
                }
            }
        });
    }

    /* Method: resetFields
     *  Parameters: none
     *  Return: void
     *  Purpose: Resets all the text input fields by replacing the current text to an empty string and removes
     * red border from any fields that have a red border.
     * */
    private void resetFields() {
        inputName.setText("");
        inputLocation.setText("");
        inputDistance.setText("");
        inputDuration.setText("");
        inputElevation.setText("");
        inputDiffLvl.setText("");
        inputName.setBorder(BorderFactory.createEmptyBorder());
        inputLocation.setBorder(BorderFactory.createEmptyBorder());
        inputDistance.setBorder(BorderFactory.createEmptyBorder());
        inputDuration.setBorder(BorderFactory.createEmptyBorder());
        inputElevation.setBorder(BorderFactory.createEmptyBorder());
        inputDiffLvl.setBorder(BorderFactory.createEmptyBorder());
    }

    /* Method: popTable
     * Parameters: rs with type ResultSet
     * Return: void
     * Purpose: retrieves data from the database using the column names and populates the UI table
     * */
    private void popTable(ResultSet rs) {
        model.setRowCount(0);
        try {
            while (rs.next()) {
                String hikeName = rs.getString("hike_Name");
                String hikeLocation = rs.getString("hike_Location");
                double hikeDistance = rs.getDouble("hike_Distance");
                String hikeDuration = rs.getString("hike_Duration");
                int hikeElevation = rs.getInt("hike_Elevation");
                String hikeDiffLvl = rs.getString("hike_DiffLvl");

                model.addRow(new Object[]{hikeName,
                        hikeLocation,
                        hikeDistance,
                        hikeDuration,
                        hikeElevation,
                        hikeDiffLvl});
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

