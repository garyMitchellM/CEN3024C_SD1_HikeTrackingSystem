/* Gary Montero
 *  CEN3024C - Software Development 1
 *  February 18, 2025
 *  HikeTrackingSystem.java
 *  This class contains the main method which only calls the static handleUserInput method from the UIController class.
 *  The software is a hike tracking system that allows a user to add (either manually or through uploading
 *  a .txt file), view, update, and delete hikes in the system. The system also calculates the total distance of all
 *  hikes in the system. Hike data includes a name, location, distance, duration, elevation and difficulty level.
 *  The program checks for correctly formatted and valid data before creating a new hike object. The distance cannot
 *  be less than or equal to 0 and the elevation cannot be less than 0. Each hike object will be stored in an ArrayList
 *  and the main menu will be handled using a switch statement.The program will continue to run until the user chooses
 *  to quit the program.
 * */

public class HikeTrackingSystem {

    public static void main(String[] args){

        UIController.handleUserInput();

    }

}
