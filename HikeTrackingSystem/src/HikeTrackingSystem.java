/* Gary Montero
 *  CEN3024C - Software Development 1
 *  March 28, 2025
 */
/**
 *  HikeTrackingSystem.java
 *  This class contains the main method which creates an instance of the DatabaseLoginGUI class which constructs the GUI
 *  for the login menu. Once the correct login details are entered a new instance of the UIController class in created,
 *  which loads the main menu of the system. The software is a hike tracking system that allows a user to add (either
 *  manually or through uploading a .txt file), view, update, and delete hikes in the system. The system also calculates
 *  the total distance of all hikes in the system. Hike data includes a name, location, distance, duration, elevation
 *  and difficulty level. The program checks for correctly formatted and valid data before creating a new hike object.
 *  The distance cannot be less than or equal to 0 and the elevation cannot be less than 0. Each hike will be
 *  stored in a MySQL database and the main menu will be handled with a graphical user interface using Java Swing. The
 *  program will continue to run until the user chooses to exit by closing the program window or selecting the exit button.
 *  @author Gary Montero
 * */
public class HikeTrackingSystem {

    /**
     * Creates a new instance of the DatabaseLoginGUI class
     * @param args
     */
    public static void main(String[] args) {
        new DatabaseLoginGUI();
    }

}
