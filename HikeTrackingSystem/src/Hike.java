/*  Gary Montero
 *  CEN3024C - Software Development 1
 *  March 11, 2025
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
    }


    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*  Method: invalidName
     *  Parameters: hikeName
     *  Return: boolean
     *  Purpose: Validates the hike name argument that is passed into it and if it is invalid the method returns true
     * */
    public static boolean invalidName(String hikeName) {
        return hikeName == null || hikeName.isEmpty() || (hikeName.charAt(0) + "").equals(" ")
                || (hikeName.charAt(0) + "").equals(",");
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /*  Method: invalidLocation
     *  Parameters: hikeLocation
     *  Return: boolean
     *  Purpose: Validates the hike location argument that is passed into it and if it is invalid the method returns true
     * */
    public static boolean invalidLocation(String hikeLocation) {
        return hikeLocation == null || hikeLocation.isEmpty() || (hikeLocation.charAt(0) + "").equals(" ")
                || (hikeLocation.charAt(0) + "").equals(",");
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    /*  Method: invalidDistance
     *  Parameters: hikeDistance
     *  Return: boolean
     *  Purpose: Validates the hike distance argument that is passed into it and if it is invalid the method returns true
     * */
    public static boolean invalidDistance(double hikeDistance) {
        try {
            if (hikeDistance <= 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    /*  Method: invalidDuration
     *  Parameters: hikeDuration
     *  Return: boolean
     *  Purpose: Validates the hike duration argument that is passed into it and if it is invalid the method returns true
     * */
    public static boolean invalidDuration(String hikeDuration) {
        return hikeDuration == null || hikeDuration.isEmpty() || (hikeDuration.charAt(0) + "").equals(" ")
                || (hikeDuration.charAt(0) + "").equals(",");
    }


    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    /*  Method: invalidElevation
     *  Parameters: hikeElevation
     *  Return: boolean
     *  Purpose: Validates the hike elevation argument that is passed into it and if it is invalid the method returns true
     * */
    public static boolean invalidElevation(double hikeElevation) {
        try {
            if (hikeElevation < 0) {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /*  Method: invalidDiffLvl
     *  Parameters: hikeDiffLvl
     *  Return: boolean
     *  Purpose: Validates the hike difficulty level argument that is passed into it and if it is invalid the method returns true
     * */
    public static boolean invalidDiffLvl(String hikeDiffLvl) {
        return hikeDiffLvl == null || hikeDiffLvl.isEmpty() || (hikeDiffLvl.charAt(0) + "").equals(" ")
                || (hikeDiffLvl.charAt(0) + "").equals(",");
    }


    /* Method: toString
     *  Parameters: none
     *  Return: String
     *  Purpose: returns a string containing the listed values separated by a comma
     * */
    @Override
    public String toString() {

        return name + ", " + location + ", " + distance + ", " + duration + ", " + elevation + ", " + difficultyLevel + "\n";

    }

}

