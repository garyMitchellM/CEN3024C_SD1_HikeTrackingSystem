/*  Gary Montero
 *  CEN3024C - Software Development 1
 *  February 18, 2025
 *  Hike.java
 *  This class contains the attributes, and methods for assigning those attributes,
 * to a Hike object when the user enters one into the system.
 * */

public class Hike {

    //Attributes
    private String name;
    private String location;
    private double distance;
    private String duration;
    private int elevation;
    private String difficultyLevel;

    //Constructor
    public Hike(String name, String location, double distance, String duration, int elevation, String difficultyLevel){
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
     *  Purpose: overloads the Hike() constructor to create a Hike object
     * */
    public Hike(){
    }


    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
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

