/*  Gary Montero
 *  CEN3024C - Software Development 1
 *  February 18, 2025
 *  HikeManager.java
 *  This class contains the ArrayList that will store the Hike objects as well as
 * all the methods for manipulating that ArrayList that the user will initiate from
 * the main menu
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HikeManager {

    //ArrayList that will hold all the Hike objects
    public ArrayList<Hike> hikeList;

    public HikeManager() {
        this.hikeList = new ArrayList<>();
    }

    Scanner scanner = new Scanner(System.in);

    /* Method: addHike
     *  Parameters: none
     *  Return: boolean
     *  Purpose: Allows the user to manually enter hike data. It then creates a new Hike
     * object and adds it to the hikeList ArrayList
     * */
    public boolean addHike() {
        //Creating a new Hike object
        Hike hike = new Hike();
        System.out.println("Enter the name of the hike.");
        String hikeName = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeName == null || hikeName.isEmpty() || (hikeName.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the name of the hike.");
            hikeName = scanner.nextLine().trim();
        }
        hike.setName(hikeName);

        System.out.println("Enter the location of the hike.");
        String hikeLocation = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeLocation == null || hikeLocation.isEmpty() || (hikeLocation.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the location of the hike.");
            hikeLocation = scanner.nextLine().trim();
        }
        hike.setLocation(hikeLocation);

        //Loop will always run if the user enters incorrect data
        while (true) {
            try {
                System.out.println("Enter how many miles the hike was.");
                double hikeDistance = scanner.nextDouble();

                //Makes sure the user can't enter a distance less than or equal to 0
                while (hikeDistance <= 0) {
                    System.out.println("You must enter a positive number.\n" + "Enter the hike's distance");
                    hikeDistance = scanner.nextDouble();
                }
                hike.setDistance(hikeDistance);
                break;
            } catch (Exception e) {
                System.out.println("You must enter a valid positive number!");
                scanner.nextLine(); //takes care of the extra new line character that nextDouble misses
            }
        }
        scanner.nextLine(); //takes care of the extra new line character that nextDouble misses

        System.out.println("Enter the duration of the hike.");
        String hikeDuration = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeDuration == null || hikeDuration.isEmpty() || (hikeDuration.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the duration of the hike.");
            hikeDuration = scanner.nextLine().trim();
        }
        hike.setDuration(hikeDuration);

        //Loop will always run if the user enters incorrect data
        while (true) {
            try {
                System.out.println("Enter the elevation of the hike.");
                int hikeElevation = scanner.nextInt();

                //Makes sure the user can't enter an elevation less than 0
                while (hikeElevation < 0) {
                    System.out.println("Elevation cannot be negative.\n" + "Enter the hike's elevation");
                    hikeElevation = scanner.nextInt();
                }
                hike.setElevation(hikeElevation);
                break;
            } catch (Exception e) {
                System.out.println("You must enter a valid number!");
                scanner.nextLine(); //takes care of the extra new line character that nextInt misses
            }
        }
        scanner.nextLine(); //takes care of the extra new line character that nextInt misses


        System.out.println("Enter the difficulty level of the hike.");
        String hikeDiffLvl = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeDiffLvl == null || hikeDiffLvl.isEmpty() || (hikeDiffLvl.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the difficulty level of the hike.");
            hikeDiffLvl = scanner.nextLine().trim();
        }
        hike.setDifficultyLevel(hikeDiffLvl);

        System.out.println("\n'" + hike.getName() + "' was added to the system.");
        System.out.println(hike);
        // returns true if the hike is added to 'hikeList'
        return hikeList.add(hike);
    }


    /* Method: addHikeFromTextFile
     *  Parameters: String filePath
     *  Return: boolean
     *  Purpose: Allows the user to add hikes from a text file. If there are any incorrect
     * hike details it tells the user what the problem was and then does not add the incorrect
     * hike.
     * */
    public boolean addHikeFromTextFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = "";
            System.out.println("\nHere is the contents of the text file: \n");

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
                if (name == null || name.isEmpty() || (name.charAt(0) + "").equals(" ") || (name.charAt(0) + "").equals(",")) {
                    System.out.println("--Unable to enter hike because there was no name.");
                    continue;
                } else if (location == null || location.isEmpty() || (location.charAt(0) + "").equals(" ")
                        || (location.charAt(0) + "").equals(",")) {
                    System.out.println("--Unable to enter hike because there was no location.");
                    continue;
                } else if (distance <= 0) {
                    System.out.println("--Unable to enter hike because distance was not a positive number.");
                    continue;
                } else if (duration == null || duration.isEmpty() || (duration.charAt(0) + "").equals(" ")
                        || (duration.charAt(0) + "").equals(",")) {
                    System.out.println("--Unable to enter hike because there was no duration.");
                    continue;
                } else if (elevation < 0) {
                    System.out.println("--Unable to enter hike because elevation was not a valid number.");
                    continue;
                } else if (diffLvl == null || diffLvl.isEmpty() || (diffLvl.charAt(0) + "").equals(" ")
                        || (diffLvl.charAt(0) + "").equals(",")) {
                    System.out.println("--Unable to enter hike because there was no difficulty level.");
                    continue;
                }
                hikeList.add(new Hike(name, location, distance, duration, elevation, diffLvl));
            }
            //Returns true when the new Hike object is added to 'hikeList'
            return true;
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (NumberFormatException nfe) {
            System.out.println("Error: distance or elevation fields should be a number.");
        }
        //Returns false if an exception is caught. The incorrectly formatted hike is not created
        return false;
    }


    /* Method: updateHike
     *  Parameters: none
     *  Return: boolean
     *  Purpose: Allows the user to update hikes using the hike's name. First is prints out all the hikes.
     * Then it checks if the hike exists and then asks the user which detail about the hike they want to update.
     * It validates what the user entered and then updates the hike with the new data.
     * */
    public boolean updateHike() {

        if (!hikeList.isEmpty()) {
            System.out.println(getAllHikes(hikeList));
            System.out.println("Enter the name of the hike you want to update:");
            String hikeToUpdate = scanner.nextLine().toLowerCase();

            //Creates a new blank hike named 'update'
            Hike update = new Hike();

            //Acts as a flag to indicate whether the hike exists.
            boolean exists = false;

            for (Hike hike : hikeList) {
                if (hike.getName().toLowerCase().equals(hikeToUpdate)) {
                    //If the hike exists then the flag is changed and the hike is assigned to the new 'update' object
                    exists = true;
                    update = hike;
                    break;
                }
            }


            //If the hike exists then the update menu is displayed
            if (exists) {
                System.out.println("\nHere are the details of the hike. " + update);
                System.out.println("What about hike: '" + hikeToUpdate + "' would you like to update?");
                System.out.println(
                        "[1. Name]\n" +
                                "[2. Location.]\n" +
                                "[3. Distance]\n" +
                                "[4. Duration]\n" +
                                "[5. Elevation]\n" +
                                "[6. Difficulty level]\n" +
                                "[7. Back to Main Menu]");

                String input = scanner.nextLine();

                //The update menu handles the validation to update the hike in a similar way to the addHike method
                switch (input) {
                    case "1":
                        System.out.println("Enter the new name: ");
                        String newName = scanner.nextLine();
                        while (newName == null || newName.isEmpty() || (newName.charAt(0) + "").equals(" ")) {
                            System.out.println("Invalid input!\n" + "Enter the new name of the hike.");
                            newName = scanner.nextLine().trim();
                        }
                        update.setName(newName);
                        System.out.println("\nThe hike '" + newName + "' was updated.");
                        break;

                    case "2":
                        System.out.println("Enter the new location: ");
                        String newLocation = scanner.nextLine();
                        while (newLocation == null || newLocation.isEmpty() || (newLocation.charAt(0) + "").equals(" ")) {
                            System.out.println("Invalid input!\n" + "Enter the new location of the hike.");
                            newLocation = scanner.nextLine().trim();
                        }
                        update.setLocation(newLocation);
                        System.out.println("\nThe hike '" + hikeToUpdate + "' was updated.");
                        break;

                    case "3":

                        while (true) {
                            try {
                                System.out.println("Enter the new distance: ");
                                double newDistance = scanner.nextDouble();

                                while (newDistance <= 0) {
                                    System.out.println("You must enter a positive number.\n" + "Enter the hike's new distance");
                                    newDistance = scanner.nextDouble();
                                }

                                update.setDistance(newDistance);
                                System.out.println("\nThe hike '" + hikeToUpdate + "' was updated.");
                                break;

                            } catch (Exception e) {
                                System.out.println("You must enter a valid positive number!");
                                scanner.nextLine();
                            }
                        }
                        scanner.nextLine();
                        break;

                    case "4":
                        System.out.println("Enter the new duration: ");
                        String newDuration = scanner.nextLine();
                        while (newDuration == null || newDuration.isEmpty() || (newDuration.charAt(0) + "").equals(" ")) {
                            System.out.println("Invalid input!\n" + "Enter the new duration of the hike.");
                            newDuration = scanner.nextLine().trim();
                        }
                        update.setDuration(newDuration);
                        System.out.println("\nThe hike '" + hikeToUpdate + "' was updated.");
                        break;

                    case "5":
                        while (true) {
                            try {

                                System.out.println("Enter the elevation of the hike.");
                                int newElevation = scanner.nextInt();

                                while (newElevation < 0) {
                                    System.out.println("Elevation cannot be negative.\n" + "Enter the hike's new elevation");
                                    newElevation = scanner.nextInt();

                                }
                                update.setElevation(newElevation);
                                System.out.println("\nThe hike '" + hikeToUpdate + "' was updated.");
                                break;
                            } catch (Exception e) {
                                System.out.println("You must enter a valid number!");
                                scanner.nextLine();
                            }
                        }
                        scanner.nextLine();
                        break;

                    case "6":
                        System.out.println("Enter the new difficultly level: ");
                        String newDiffLvl = scanner.nextLine();
                        while (newDiffLvl == null || newDiffLvl.isEmpty() || (newDiffLvl.charAt(0) + "").equals(" ")) {
                            System.out.println("Invalid input!\n" + "Enter the difficulty level of the hike.");
                            newDiffLvl = scanner.nextLine().trim();
                        }
                        update.setDifficultyLevel(newDiffLvl);
                        System.out.println("\nThe hike '" + hikeToUpdate + "' was updated.");
                        break;

                    case "7":
                        break;
                }
            } else {
                System.out.println("Could not update hike because it does not exist in the system.");
                //If no hike is found with the entered name then the method returns false;
                return false;
            }
            System.out.println(update);
        } else {
            System.out.println("\nThere are no hikes currently in the system.\n");
            return false;
        }
        //Returns true when a hike is updated
        return true;
    }


    /* Method: removeHike
     *  Parameters: none
     *  Return: boolean
     *  Purpose: Allows the user to remove hikes using the hike's name. First is prints out all the hikes.
     * Then it checks if the hike exists and if it does exist it removes it from 'hikeList'
     * */
    public boolean removeHike() {
        if (hikeList.isEmpty()) {
            System.out.println("\nThere are no hikes currently in the system.\n");
            return false;
        }
        //Acts as a flag to indicate whether the hike exists.
        boolean exists = false;
        System.out.println(getAllHikes(hikeList) + "\n");
        System.out.println("Enter the name of the hike you want to remove");
        String hikeToRemove = scanner.nextLine().toLowerCase();
        //Creates a blank Hike object called 'remove'
        Hike remove = new Hike();

        for (Hike hike : hikeList) {
            if (hike.getName().toLowerCase().equals(hikeToRemove)) {
                //If the hike exists then the flag is changed and the hike is assigned to the new 'remove' object
                exists = true;
                remove = hike;
            }
        }

        //Removes the found hike from 'hikeList' and returns true. If hike does not exist, the method returns false
        if (exists) {
            hikeList.remove(remove);
            System.out.println("\nThe hike: '" + hikeToRemove + "' was removed from the system");
            return true;
        } else {
            System.out.println("\nCould not remove hike because it does not exist in the system.");
            return false;
        }
    }


    /* Method: calculateTotalDistance
     *  Parameters: none
     *  Return: double
     *  Purpose: Allows the user to see the total distance of every hike in the system. If there are no hikes in
     * the system, the user is notified and the value of 0.0 is returned.
     * */
    public double calculateTotalDistance() {

        double totalDistance = 0;

        if (!hikeList.isEmpty()) {
            System.out.print("The total number of miles you have hiked is: ");
            for (Hike hike : hikeList) {
                totalDistance += hike.getDistance();
            }
        } else {
            System.out.println("\nThere are no hikes currently in the system.\n");
        }
        return totalDistance;

    }

    /* Method: getAllHikes
     *  Parameters: ArrayList<Hike> hikeList
     *  Return: String
     *  Purpose: Allows the user to see the every hike in the system. The hikes currently in the system are numbered
     * so the user can see how many there are.
     * */
    public String getAllHikes(ArrayList<Hike> hikeList) {
        String printedList = "";
        int counter = 1;
        for (Hike hike : hikeList) {
            printedList += '\n' + "Hike #" + counter + ": " + hike.toString();
            counter++;
        }
        if (hikeList.isEmpty()) {
            return "There are no hikes currently in the system.\n";
        }
        return printedList;
    }
}