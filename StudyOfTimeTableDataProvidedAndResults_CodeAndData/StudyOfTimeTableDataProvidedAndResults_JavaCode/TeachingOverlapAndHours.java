import java.util.ArrayList;
import java.util.Comparator;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public interface TeachingOverlapAndHours {

    public ArrayList<String> teachersHavingTimeClashes(ArrayList<ArrayList<String>> firstList);

    public ArrayList<String> teachesTeachMoreThanSixHoursADay(ArrayList<ArrayList<String>> firstList);

    public ArrayList<ArrayList<String>> teachingOverlapAndHours(ArrayList<ArrayList<String>> firstList);

    public ArrayList<ArrayList<String>> splitArrayLists(ArrayList<String> arrayList, int itemNo);

    public ArrayList<ArrayList<String>> pick(ArrayList<ArrayList<String>> stringAArrayList);

    public ArrayList<ArrayList<String>> teachingTimeExceedSixHoursADay
            (ArrayList<ArrayList<String>> stringAArrayList);

    /**
     * This method is for splitting an arraylist with item(5) of it in the form of "Mon/Wed/Fri" to three lines with
     * item(5) of them "Monday", "Wednesday" and "Friday" respectively, or in the form "Tue-Thu" to three lines with
     * item(5) of them "Tuesday", "Wednesday" and "Thursday" respectively.
     * @param arrayList
     * @return
     */
    public ArrayList<ArrayList<String>> splittingWeekdays(ArrayList<String> arrayList);
}
/**
 * This subclass is for comparing and sorting input arraylist (Format:
 * ArrayList<ArrayList<String>>) according to
 * multiple conditions of each member (Format: ArrayList<String>) of the aforesaid arraylist:
 * first: item index 11 (String), second: item index 4 (paste to int), third: item index 5
 * (paste to double) and forth: item index 6 (paste to double).
 */

interface CompareItemsInTheRowsOfTheTimetable extends Comparator<ArrayList<String>> {


    public int compare(ArrayList<String> arrayList1, ArrayList<String> arrayList2);
}
