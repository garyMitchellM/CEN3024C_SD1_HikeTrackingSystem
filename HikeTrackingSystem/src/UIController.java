/* Gary Montero
 *  CEN3024C - Software Development 1
 *  February 18, 2025
 *  UIController.java
 *  This class contains the logic for the main menu. It displays all the possible options that the user
 * can choose from the menu and accepts the input for what the user wants to do. If the user enters an invalid option
 * then the menu will be redisplayed. The user can only exit the program by selecting the 'Quit' option.
 * */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UIController {


    /* Method: displayMenu
     *  Parameters: none
     *  Return: void
     *  Purpose: Displays the main menu
     * */
    public static void displayMenu() {
        System.out.println("\nWhat would you like to do?\n" +
                "[1. Import hikes from a text file]\n" +
                "[2. Manually enter a hike.]\n" +
                "[3. Update an existing hike.]\n" +
                "[4. Remove a hike from the system]\n" +
                "[5. See all hikes] \n" +
                "[6. Calculate total distance hiked] \n" +
                "[7. Quit.]");
    }


    /* Method: handleUserInput
     *  Parameters: none
     *  Return: void
     *  Purpose: Handles the functionality of the main menu with the use of a switch statement
     * */
    public static void startMainMenu() {

        HikeManager hikeManager = new HikeManager();

        System.out.println("\nWelcome to the hike tracking system!");
        Scanner scanner = new Scanner(System.in);

        while (true) {

            displayMenu();

            String input = "0";
            input = scanner.nextLine();

            //Each case calls the appropriate method from the hikeManager class to carry out the desired task
            switch (input) {
                case "1":

                    System.out.println("Enter the path of the text file you want to import");
                    String path = scanner.nextLine();
                    hikeManager.addHikeFromTextFile(path);
                    break;

                case "2":
                    //Creates a new Hike object, and it gets passed into addHike()
                    Hike hike = new Hike();
                    hikeManager.addHike(hike);
                    break;

                case "3":

                    hikeManager.updateHike();
                    break;

                case "4":
                    System.out.println("\n" + hikeManager.getAllHikes(hikeManager.hikeList));
                    System.out.println("Enter the name of the hike you want to remove");
                    String hikeToRemove = scanner.nextLine().toLowerCase();
                    //the name of the hike the user wants to remove gets passed into removeHike()
                    hikeManager.removeHike(hikeToRemove);
                    break;

                case "5":

                    System.out.println("\n" + hikeManager.getAllHikes(hikeManager.hikeList));
                    break;

                case "6":

                    System.out.println(hikeManager.calculateTotalDistance());
                    break;

                case "7":

                    System.out.println("\n\"Nature is not a place to visit. It is home.\"\n -Gary Snyder");
                    System.out.println("\nGoodbye!\n");
                    return;
            }
        }
    }


}

