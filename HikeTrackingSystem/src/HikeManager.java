/*  Gary Montero
 *  CEN3024C - Software Development 1
 *  March 28, 2025
 *  HikeManager.java
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

 /**
  *  This class contains legacy code. This class contains the ArrayList that was used to store the Hike objects as well as
  * all the methods for manipulating that ArrayList that the user will initiate from the main menu. Currently, hikes are
  * stored in a database so this class is no longer needed.
  * @author Gary Montero
  * */
public class HikeManager {

    //ArrayList that will hold all the Hike objects
    public ArrayList<Hike> hikeList;

    //Constructor
    public HikeManager() {
        this.hikeList = new ArrayList<>();
    }

    Scanner scanner = new Scanner(System.in);

    /* Method: addHike
     *  Parameters: hike with type of Hike
     *  Return: boolean
     *  Purpose: Allows the user to manually enter hike data. When a new hike is created it gets added
     * to the hikeList ArrayList
     * */
    public boolean addHike(Hike hike) {
        System.out.println("\n'" + hike.getName() + "' was added to the system.");
        System.out.println(hike);
        // returns true if the hike is added to 'hikeList'
        return hikeList.add(hike);
    }


    /* Method: addHikeFromTextFile
     *  Parameters: filePath with type String
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
//                hikeList.add(new Hike(name, location, distance, duration, elevation, diffLvl));
            }
            //Returns true if the new Hike object is added to 'hikeList'
            return !flag;

        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (NumberFormatException nfe) {
            System.out.println("Error: distance or elevation fields should be a number.");
        }
        //Returns false if an exception is caught. The incorrectly formatted hike is not created
        return false;
    }



    /* Method: updateHike
     *  Parameters: String name, String location, double distance, String duration, int elevation, String diffLvl, int index
     *  Return: boolean
     *  Purpose: Allows the user to update the hike's information. It uses the passed in index of the hike in the
     * ArrayList to select the hike the user wants to update, and it calls the setters to assign what is passed in to the
     * hike object attributes.
     * */
    public boolean updateHike(String name, String location, double distance, String duration, int elevation, String diffLvl, int index){
        Hike hike = hikeList.get(index);
        hike.setName(name);
        hike.setLocation(location);
        hike.setDistance(distance);
        hike.setDuration(duration);
        hike.setElevation(elevation);
        hike.setDifficultyLevel(diffLvl);
        return true;
    }


    /* Method: removeHike
     *  Parameters: hikeToRemove with type String
     *  Return: boolean
     *  Purpose: Allows the user to remove hikes using the hike's name. It checks if the hike exists and if
     * it does exist it removes it from 'hikeList'
     * */
    public boolean removeHike(String hikeToRemove) {

        //Creates a blank Hike object called 'remove'
        Hike remove = null;

        for (Hike hike : hikeList) {
            if (hike.getName().equalsIgnoreCase(hikeToRemove)) {
                //If the hike exists then the hike is assigned to the blank 'remove' object
                remove = hike;
            }
        }

        //Removes the found hike from 'hikeList' and returns true.
        hikeList.remove(remove);
        System.out.println("\nThe hike: '" + hikeToRemove + "' was removed from the system");
        return true;
    }


    /* Method: calculateTotalDistance
     *  Parameters: none
     *  Return: double
     *  Purpose: Allows the user to see the total distance of every hike in the system. If there are no hikes in
     * the system the value of 0.0 is returned.
     * */
    public double calculateTotalDistance() {

        double totalDistance = 0;

        if (!hikeList.isEmpty()) {
            for (Hike hike : hikeList) {
                totalDistance += hike.getDistance();
            }
            System.out.print("The total number of miles you have hiked is: ");
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