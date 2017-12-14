
import java.util.ArrayList;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public interface CheckRoomSizeSufficiency {

    public ArrayList<String> tutGroupAndRoomSize(ArrayList<ArrayList<String>> firstList);

    /**
     * tutorial/laboratory group size is limited to 25 students or the room's accommodation limit,
     * whichever is the lower, a module may need to divide into more than one group and one group may allocate to
     * more than one room. In this most complicated situation, warning will be given if there is in existence of
     * the sum of total group sizes which is less than the student number of the module
     *
     * @param moduleCodes
     * @return
     */
    public ArrayList<String> checkRoomSizeSufficiency(ArrayList<ModuleImpl> moduleCodes);
}
