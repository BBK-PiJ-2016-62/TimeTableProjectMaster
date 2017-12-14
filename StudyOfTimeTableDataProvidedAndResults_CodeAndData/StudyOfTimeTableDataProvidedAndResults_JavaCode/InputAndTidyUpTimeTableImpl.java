
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public class InputAndTidyUpTimeTableImpl implements InputAndTidyUpTimeTable {

    @Override
    public ArrayList<ArrayList<String>> inputAndTidyTimeTable() {
        ArrayList<ArrayList<String>> firstList = new ArrayList<>();
        String DraftTimeTablePath = Launcher.draftTimeTablePath;
        Path path = Paths.get(DraftTimeTablePath);
        try {
            Files.lines(path).forEach(s ->firstList.add(
                    new ArrayList<>(Arrays.asList(s.split(",")))));
        } catch (IOException e) {
            System.out.println(e);
        }
        for (ArrayList<String> tempArrayList : firstList) {
            if (tempArrayList.size() >= 7) {
                combinTheFirstFewItems(tempArrayList);
            }
        }
        return firstList;
    }
    @Override
    public ArrayList<String> combinTheFirstFewItems (ArrayList<String> stringArrayList){

        ArrayList<Integer> tempToBeDeletedItemIndex = new ArrayList<>();
        String s=stringArrayList.get(0);
        for (int i = 1; i < 5; i++) {
            if (stringArrayList.get(i).matches("\\s*[a-zA-Z]{2}[0-9]{4}\\s*")) {
                break;
            } else {
                String s1 = stringArrayList.get(i);
                s = s+","+s1;
                tempToBeDeletedItemIndex.add(i);
            }
        }
        //loop the list in reverse order so that removal of items would not affect
        //index number of those not yet reached
        Collections.reverse(tempToBeDeletedItemIndex);
        for (int i : tempToBeDeletedItemIndex) stringArrayList.remove(i);
        stringArrayList.set(0, s);
        return stringArrayList;

    }
}
