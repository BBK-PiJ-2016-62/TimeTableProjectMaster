
import java.util.ArrayList;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public interface CheckTutorialGroupSufficiency {

    /**
     * group size is limited to 25 or the room's accommodation limit, whichever is the lower.
     * @param firstList
     * @return
     */

    public ArrayList<String> checkTutorialGroupSufficiency(ArrayList<ArrayList<String>> firstList);
}
