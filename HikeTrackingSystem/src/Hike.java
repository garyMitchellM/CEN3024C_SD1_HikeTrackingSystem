/*  Gary Montero
 *  CEN3024C - Software Development 1
 *  February 18, 2025
 *  Hike.java
 *  This class contains the attributes, and methods for assigning those attributes,
 * to a Hike object when the user enters one into the system.
 * */

import java.util.Scanner;

public class Hike {

    Scanner scanner = new Scanner(System.in);

    //Attributes
    private String name;
    private String location;
    private double distance;
    private String duration;
    private int elevation;
    private String difficultyLevel;

    //Constructor
    public Hike(String name, String location, double distance, String duration, int elevation, String difficultyLevel) {
        this.name = name;
        this.location = location;
        this.distance = distance;
        this.duration = duration;
        this.elevation = elevation;
        this.difficultyLevel = difficultyLevel;
    }

    /*  Method: Hike
     *  Parameters: none
     *  Return: none
     *  Purpose: overloads the Hike() constructor to create a Hike object by calling the setters
     * */
    public Hike() {
        setName();
        setLocation();
        setDistance();
        setDuration();
        setElevation();
        setDifficultyLevel();
    }


    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName() {

        System.out.println("Enter the name of the hike.");
        String hikeName = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeName == null || hikeName.isEmpty() || (hikeName.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the name of the hike.");
            hikeName = scanner.nextLine().trim();
        }
        this.name = hikeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLocation() {
        System.out.println("Enter the location of the hike.");
        String hikeLocation = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeLocation == null || hikeLocation.isEmpty() || (hikeLocation.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the location of the hike.");
            hikeLocation = scanner.nextLine().trim();
        }
        this.location = hikeLocation;
    }

    public double getDistance() {
        return distance;
    }


    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setDistance() {
        while (true) {
            try {
                System.out.println("Enter how many miles the hike was.");
                double hikeDistance = scanner.nextDouble();

                //Makes sure the user can't enter a distance less than or equal to 0
                while (hikeDistance <= 0) {
                    System.out.println("You must enter a positive number.\n" + "Enter the hike's distance");
                    hikeDistance = scanner.nextDouble();
                }
                this.distance = hikeDistance;
                break;
            } catch (Exception e) {
                System.out.println("You must enter a valid positive number!");
                scanner.nextLine(); //takes care of the extra new line character that nextDouble misses
            }
        }
        scanner.nextLine();
    }

    public String getDuration() {
        return duration;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDuration() {
        System.out.println("Enter the duration of the hike.");
        String hikeDuration = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeDuration == null || hikeDuration.isEmpty() || (hikeDuration.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the duration of the hike.");
            hikeDuration = scanner.nextLine().trim();
        }
        this.duration = hikeDuration;
    }

    public int getElevation() {
        return elevation;
    }


    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setElevation() {
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
                this.elevation = hikeElevation;
                break;
            } catch (Exception e) {
                System.out.println("You must enter a valid number!");
                scanner.nextLine(); //takes care of the extra new line character that nextInt misses
            }
        }
        scanner.nextLine(); //takes care of the extra new line character that nextInt misses
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }


    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setDifficultyLevel() {
        System.out.println("Enter the difficulty level of the hike.");
        String hikeDiffLvl = scanner.nextLine().trim();
        //Loop makes sure that the user can't enter an empty string or just a space
        while (hikeDiffLvl == null || hikeDiffLvl.isEmpty() || (hikeDiffLvl.charAt(0) + "").equals(" ")) {
            System.out.println("Invalid input!\n" + "Enter the difficulty level of the hike.");
            hikeDiffLvl = scanner.nextLine().trim();
        }
        this.difficultyLevel = hikeDiffLvl;
    }


    /* Method: toString
     *  Parameters: none
     *  Return: String
     *  Purpose: returns a string containing the listed values separated by a comma
     * */
    @Override
    public String toString() {
        return "[" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", distance=" + distance +
                ", duration='" + duration + '\'' +
                ", elevation=" + elevation +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ']' +
                "\n";
    }

}

