/* Gary Montero
 *  CEN3024C - Software Development 1
 *  February 27, 2025
 *  DatabaseManager.java
 *  This class contains JUnit tests for legacy code to make sure the functionalities of the older versions of the system
 *  are working as intended. The tests make sure that each CRUD operations perform their intended task as well as the custom
 * calculation function.
 * */

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HikeManagerTest {

//    HikeManager testHikeManager;
//
//
//    //creates a new instance of the HikeManager Class
//    @org.junit.jupiter.api.BeforeEach
//    void setUp() {
//        testHikeManager = new HikeManager();
//    }
//
//    //tests whether a hike gets successfully added to the system, if it does, the test is passed
//    @org.junit.jupiter.api.Test
//    void addHike() {
//        Hike hike = new Hike("Example Hike", "Example Location", 26.6, "5 Hours", 500, "Intermediate");
//        assertTrue(testHikeManager.addHike(hike));
//        //makes sure that the hikeList is not null after adding a hike
//        assertNotNull(testHikeManager.hikeList);
//    }
//
//
//    //tests whether a hike can be successfully added to the system from a text file, if it does, the test is passed
//    @org.junit.jupiter.api.Test
//    void addHikeFromTextFile() {
//        assertTrue(testHikeManager.addHikeFromTextFile("/Users/garymontero/Documents/List_of_Hikes.txt"));
//    }
//
//    //tests whether an existing hike can be updated, if it can be updated and the update matches the expected output
//    //then the test is passed
//    @org.junit.jupiter.api.Test
//    void updateHike() {
//        Hike hike = new Hike("Example Hike", "Example Location", 26.6, "5 Hours", 500, "Intermediate");
//        testHikeManager.addHike(hike);
//        testHikeManager.scanner = new Scanner(new ByteArrayInputStream("Example Hike\n1\nExample Hike2\n".getBytes()));
//        assertTrue(testHikeManager.updateHike());
//        assertEquals("Example Hike2", testHikeManager.hikeList.get(testHikeManager.hikeList.size() - 1).getName());
//    }
//
//    //tests whether a hike gets successfully removed from the system, if it can be removed, the test is passed
//    @org.junit.jupiter.api.Test
//    void removeHike() {
//        //creates a new hike that will be removed
//        Hike hike = new Hike("Example Hike", "Example Location", 26.6, "5 Hours", 500, "Intermediate");
//        testHikeManager.addHike(hike);
//        assertTrue(testHikeManager.removeHike("Example Hike"));
//    }
//
//    //tests whether the distances of the hikes in the system are successfully summed to a total number, if they are, the test is passed
//    @org.junit.jupiter.api.Test
//    void calculateTotalDistance() {
//        Hike hike = new Hike("Example Hike", "Example Location", 20, "5 Hours", 500, "Intermediate");
//        Hike hike2 = new Hike("Example Hike", "Example Location", 25, "5 Hours", 500, "Intermediate");
//        testHikeManager.addHike(hike);
//        testHikeManager.addHike(hike2);
//        assertEquals(45, testHikeManager.calculateTotalDistance());
//
//    }

}