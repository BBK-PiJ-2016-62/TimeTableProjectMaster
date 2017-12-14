import java.util.ArrayList;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public interface InputAndTidyUpTimeTable {

    /**
     * input and tidy draft time table
     * @return
     */

    public ArrayList<ArrayList<String>> inputAndTidyTimeTable();

    /**
     * This method is for combining the first few items of an arrayList which are ended
     * with a comma until reaching an item in the pattern of "[a-zA-Z]{2}[0-9]{4}" (That
     * is, the pattern of a module number such as CN7021). It is for solving the problem
     * of inputting data from the .csv format timetable file with the first item of a row
     * of it itself consists of commas.
     * @param stringArrayList
     * @return
     */
    public ArrayList<String> combinTheFirstFewItems (ArrayList<String> stringArrayList);
}
