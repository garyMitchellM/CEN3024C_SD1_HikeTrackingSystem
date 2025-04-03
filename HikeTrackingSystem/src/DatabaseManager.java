/* Gary Montero
 *  CEN3024C - Software Development 1
 *  March 28, 2025
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

 /**  DatabaseManager.java
  *  This class contains the method that connects the database to the system. It also handles all operations
  * that require accessing hike data from the database, such as inserting, selecting, updating, and deleting hikes,
  * as well as calculating the total distance of all the hikes currently in the database.
  * @author Gary Montero
  * */
public class DatabaseManager {

    public static Connection connection;

    /** Establishes a connection to the database using credentials retrieved from the DatabaseLoginGUI class.
     * @return Connection object
     * @throws SQLException
     */
    public static Connection connect() throws SQLException {
        try {
            //Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Get database credentials from DatabaseLoginGUI class
            String jdbcUrl = DatabaseLoginGUI.getDbUrl();
            String user = DatabaseLoginGUI.getDbUsername();
            String password = DatabaseLoginGUI.getDbPassword();

            //Opens a connection using the credentials
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            return connection;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /** Inserts a new hike record into the Hikes table in the database.
     * @param hikeName
     * @param hikeLocation
     * @param hikeDistance
     * @param hikeDuration
     * @param hikeElevation
     * @param hikeDiffLvl
     */
    public static void insertHike(String hikeName, String hikeLocation, double hikeDistance,
                                  String hikeDuration, int hikeElevation, String hikeDiffLvl) {
        try {
            //Prepare SQL statement for inserting a hike
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Hikes " +
                    "(hike_Name, hike_Location, hike_Distance, hike_Duration, hike_Elevation, hike_DiffLvl) " +
                    "VALUES (?,?,?,?,?,?)");

            //Setting values for the prepared statement
            stm.setString(1, hikeName);
            stm.setString(2, hikeLocation);
            stm.setDouble(3, hikeDistance);
            stm.setString(4, hikeDuration);
            stm.setInt(5, hikeElevation);
            stm.setString(6, hikeDiffLvl);

            //Execute SQL statement
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Deletes the most recent hike record with the specified name from the database. Returns true if a record
     * was deleted, otherwise false.
     * @param hikeName
     * @return boolean
     */
    public static boolean deleteHike(String hikeName) {
        try {
            //Prepare SQL statement to delete the most recent hike entry with the given name
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Hikes" +
                    " WHERE hike_Name = ? ORDER BY hike_ID DESC LIMIT 1");
            stm.setString(1, hikeName);
            System.out.println(stm);

            //Execute delete statement and check affected rows
            int rowsAffected = stm.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes all hike records from the Hikes table in the database.
     */
    public static void deleteAllHikes() {
        try {
            //Prepare and execute SQL statement to delete all hikes
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Hikes");
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Calculates and returns the total distance of all hikes from the hike_Distance column in the database.
     * @return double
     */
    public static double calcualteTotalDistance(){
        double sum = 0;
        try{
            //Prepare SQL statement to sum all hike distances
            PreparedStatement stm = connection.prepareStatement("SELECT sum(hike_Distance) FROM Hikes;");
            ResultSet rs = stm.executeQuery();

            //Retrieve the sum result
            while(rs.next()){
                sum = rs.getDouble(1);
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println(sum);
        return sum;
    }

    /** Retrieves all hike records from the Hikes table and returns them as a ResultSet object.
     * @return ResultSet
     * @throws SQLException
     */
   public static ResultSet fillTable() throws SQLException {
       //Prepare and execute SQL query to retrieve all hikes
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Hikes;");
        ResultSet rs = stm.executeQuery();
        return rs;
   }

    /** Reads hike data from a text file, validates it, and inserts valid records into the database.
     * Returns true if all records are valid and inserted successfully, otherwise false.
     * @param filePath
     * @return boolean
     */
    public static boolean addFromTextFile(String filePath){

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            System.out.println("\nHere is the contents of the text file: \n");
            boolean flag = false;

            //Reads in values from a file, stores them in a Hike object and validates the data
            while ((line = br.readLine()) != null) {
                //splits data from text file using a comma as a delimiter and stores the data in an array called 'values'
                String[] values = line.split(",");
                System.out.println(Arrays.toString(values));

                //Assigning the indexes of the 'values' array to variables that correspond to what the data is
                String name = values[0].trim();
                String location = values[1].trim();
                double distance = Double.parseDouble(values[2].trim());
                String duration = values[3].trim();
                int elevation = Integer.parseInt(values[4].trim());
                String diffLvl = values[5].trim();

                //Validating the data from the text file using the variables that were initialized above
                if (Hike.invalidName(name)) {
                    System.out.println("--Unable to enter hike because there was no name.");
                    flag = true;
                    continue;
                } else if (Hike.invalidLocation(location)) {
                    System.out.println("--Unable to enter hike because there was no location.");
                    flag = true;
                    continue;
                } else if (Hike.invalidDistance(distance)) {
                    System.out.println("--Unable to enter hike because distance was not a positive number.");
                    flag = true;
                    continue;
                } else if (Hike.invalidDuration(duration)) {
                    System.out.println("--Unable to enter hike because there was no duration.");
                    flag = true;
                    continue;
                } else if (Hike.invalidElevation(elevation)) {
                    System.out.println("--Unable to enter hike because elevation was not a valid number.");
                    flag = true;
                    continue;
                } else if (Hike.invalidDiffLvl(diffLvl)) {
                    System.out.println("--Unable to enter hike because there was no difficulty level.");
                    flag = true;
                    continue;
                }

                //Prepare SQL statement for inserting a hike
                PreparedStatement stm = connection.prepareStatement("INSERT INTO Hikes " +
                        "(hike_Name, hike_Location, hike_Distance, hike_Duration, hike_Elevation, hike_DiffLvl) " +
                        "VALUES (?,?,?,?,?,?)");

                //Setting values for the prepared statement
                stm.setString(1, name);
                stm.setString(2, location);
                stm.setDouble(3, distance);
                stm.setString(4, duration);
                stm.setInt(5, elevation);
                stm.setString(6, diffLvl);

                //Execute SQL statement
                stm.execute();
            }
            //Returns true if the new Hike object is added to 'hikeList'
            return !flag;

        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (NumberFormatException nfe) {
            System.out.println("Error: distance or elevation fields should be a number.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Returns false if an exception is caught. The incorrectly formatted hike is not inserted
        return false;

    }


    /* Method: updateHike
     *  Parameters: String hikeName, String hikeLocation, double hikeDistance, String hikeDuration, int hikeElevation,
     *              String hikeDiffLvl, String beforeName
     *  Return: void
     *  Purpose: Updates an existing hike record in the database with new values.
     */

    /** Updates an existing hike record in the database with new values.
     * @param hikeName
     * @param hikeLocation
     * @param hikeDistance
     * @param hikeDuration
     * @param hikeElevation
     * @param hikeDiffLvl
     * @param beforeName If updating the name, this would be the old hike name.
     */
    public static void updateHike(String hikeName, String hikeLocation, double hikeDistance,
                                  String hikeDuration, int hikeElevation, String hikeDiffLvl, String beforeName){
        try{
            //Prepare SQL update statement
            PreparedStatement stm = connection.prepareStatement("UPDATE Hikes SET" +
                    " hike_Name = ?, hike_Location = ?, hike_Distance = ?, hike_Duration = ?, hike_Elevation = ?, hike_DiffLvl = ? " +
                    "WHERE hike_Name = ?");

            //Set new values for the hike record
            stm.setString(1, hikeName);
            stm.setString(2, hikeLocation);
            stm.setDouble(3, hikeDistance);
            stm.setString(4, hikeDuration);
            stm.setInt(5, hikeElevation);
            stm.setString(6, hikeDiffLvl);
            stm.setString(7, beforeName);

            //Execute update statement
            stm.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}