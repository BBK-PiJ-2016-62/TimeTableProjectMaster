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
public class TeachingOverlapAndHoursImpl implements TeachingOverlapAndHours {

    @Override
    public ArrayList<String> teachersHavingTimeClashes(ArrayList<ArrayList<String>> firstList){

        ArrayList<ArrayList<String>> fifthList= new ArrayList<>(teachingOverlapAndHours(firstList));
        ArrayList<String> teachersTimeCrashesResult=new ArrayList<>();
        String firstLine = "Find clashes in teacher same time in different classes:";
        System.out.println(firstLine);
        System.out.println();
        teachersTimeCrashesResult.add(firstLine);
        teachersTimeCrashesResult.add(" ");
        ArrayList<ArrayList<String>> picked = pick(fifthList);
        ArrayList<String> csvFileHeader = new ArrayList<>(Arrays.asList(
                "Module Code/ Category/ Term/ Day/ Start/ End/ Teacher(s)/ Room type".split("/")));
        String csvFileHeaderStr=csvFileHeader.toString().replace("[","").replace("]","");
        teachersTimeCrashesResult.add(csvFileHeaderStr);
        System.out.println(csvFileHeaderStr);
        for (ArrayList<String> pk: picked){
            ArrayList<String> condensedList = new ArrayList<>();
            condensedList.add(pk.get(1)); condensedList.add(pk.get(3)); condensedList.add(pk.get(4));
            condensedList.add(pk.get(5)); condensedList.add(pk.get(6)); condensedList.add(pk.get(7));
            condensedList.add(pk.get(11)); condensedList.add(pk.get(13));
            String pk1=condensedList.toString().replace("[","").replace("]","");
            teachersTimeCrashesResult.add(pk1);
            System.out.println(pk1);
        }
        return teachersTimeCrashesResult;
    }

    @Override
    public ArrayList<String> teachesTeachMoreThanSixHoursADay(ArrayList<ArrayList<String>> firstList){

        ArrayList<ArrayList<String>> fifthList= new ArrayList<>(teachingOverlapAndHours(firstList));
        ArrayList<String> teachersMoreThanSixHoursADayResult=new ArrayList<>();
        System.out.println();
        String firstLine = "Find teachers who teach more than six hours a day:";
        System.out.println(firstLine);
        teachersMoreThanSixHoursADayResult.add(firstLine);
        System.out.println(" ");
        teachersMoreThanSixHoursADayResult.add(" ");
        String csvFileHeader= "Teacher Name, Term, Day, Hours";
        System.out.println(csvFileHeader);
        teachersMoreThanSixHoursADayResult.add(csvFileHeader);
        ArrayList<ArrayList<String>> tTimeEx6ADay = teachingTimeExceedSixHoursADay(fifthList);
        for (ArrayList<String> ttex6: tTimeEx6ADay){
            String ttex61=ttex6.toString().replace("[","").replace("]","");
            teachersMoreThanSixHoursADayResult.add(ttex61);
            System.out.println(ttex61);
        }
        return teachersMoreThanSixHoursADayResult;
    }

    @Override
    public ArrayList<ArrayList<String>> teachingOverlapAndHours(ArrayList<ArrayList<String>> firstList){
                ArrayList<ArrayList<String>> secondList = new ArrayList<>();
                ArrayList<String> tempArrayList1 = new ArrayList<>();
                for (ArrayList<String> tempArrayList : firstList) {
                    if (tempArrayList.size() >= 7) {
                        if (tempArrayList.get(0).length() != 0) {
                            if (tempArrayList.get(6).length() != 0) {
                                if (tempArrayList.get(11).length() != 0) {

                                    if (tempArrayList.size() >= 14)
                                        tempArrayList1 = new ArrayList<String>(
                                                tempArrayList.subList(0, 14));
                                    if (tempArrayList.size() == 13)
                                        tempArrayList1 = new ArrayList<String>(
                                                tempArrayList.subList(0, 13));
                                    if (tempArrayList.size() == 12)
                                        tempArrayList1 = new ArrayList<String>(
                                                tempArrayList.subList(0, 12));
                                    secondList.add(tempArrayList1);
                                }
                            }
                        }
                    }
                }
        ArrayList<ArrayList<String>> thirdList = new ArrayList<>();
        for (ArrayList<String> s : secondList) {

            if (s.get(11).matches("[/a-zA-Z\\s]+")) {
                for (ArrayList<String> arrayList : splitArrayLists(s, 11))
                    thirdList.add(arrayList);
            } else thirdList.add(s);
        }

        ArrayList<ArrayList<String>> forthList = new ArrayList<>();
        for (ArrayList<String> s : thirdList) {
            if (s.get(4).matches("[&0-9\\s]+")) {
                for (ArrayList<String> arrayList : splitArrayLists(s, 4))
                    forthList.add(arrayList);
            } else forthList.add(s);
        }
        ArrayList<ArrayList<String>> fifthList = new ArrayList<>();
        for (ArrayList<String> s : forthList) {
            if (s.get(5).trim().matches("\\w{3}\\/\\w+.*")||
                    s.get(5).trim().matches("[a-zA-Z]{3}\\s*-\\s*[a-zA-Z]{3}")) {
                for (ArrayList<String> arrayList : splitArrayLists(s, 5))
                    fifthList.add(arrayList);
            } else {fifthList.add(s);}
        }
        Collections.sort(fifthList, new CompareItemsInTheRowsOfTheTimetableImpl());

        return fifthList;
    }

    @Override
    public ArrayList<ArrayList<String>> splitArrayLists(ArrayList<String> arrayList, int itemNo) {

        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        //ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        if (itemNo == 4 || itemNo == 11) {
            if (itemNo == 4 && arrayList.get(4).matches("[&0-9\\s]+"))
                strings = new ArrayList<String>(Arrays.asList(arrayList.get(4).split("[\\s]*&[\\s]*")));
            if (itemNo == 11 && arrayList.get(11).matches("[/a-zA-Z\\s]+"))
                strings = new ArrayList<String>(Arrays.asList(arrayList.get(11).split("/")));
            for (String s : strings) {
                ArrayList<String> arrayList2 = new ArrayList<>(arrayList);
                arrayList2.set(itemNo, s);
                arrayLists.add(arrayList2);

            }
        }
        if (itemNo == 5){
            arrayLists= splittingWeekdays(arrayList);
        }
        return arrayLists;
    }

    @Override
    public ArrayList<ArrayList<String>> pick(ArrayList<ArrayList<String>> stringAArrayList) {
        ArrayList<ArrayList<String>> pickedAArrayList = new ArrayList<>();
        for (int i = 0; i < stringAArrayList.size() - 1; i++) {
            if (stringAArrayList.get(i).get(11).trim().equals(stringAArrayList.get(i + 1).get(11).trim()) &&
                    stringAArrayList.get(i).get(4).trim().equals(stringAArrayList.get(i + 1).get(4).trim()) &&
                    stringAArrayList.get(i).get(5).trim().equals(stringAArrayList.get(i + 1).get(5).trim())) {
                if (Double.parseDouble(stringAArrayList.get(i).get(7).trim().replace(":", ".")) >
                        Double.parseDouble(stringAArrayList.get(i + 1).get(6).trim().replace(":", "."))) {
                    if (i == 0) pickedAArrayList.add(stringAArrayList.get(i));
                    else if (!stringAArrayList.get(i).equals(stringAArrayList.get(i - 1)))
                        pickedAArrayList.add(stringAArrayList.get(i));
                    pickedAArrayList.add(stringAArrayList.get(i + 1));
                }
            }
        }
        return pickedAArrayList;
    }

    @Override
    public ArrayList<ArrayList<String>> teachingTimeExceedSixHoursADay (ArrayList<ArrayList<String>> stringAArrayList){

        ArrayList<ArrayList<String>> teachingTimeExceededList=new ArrayList<>();
        Double accumulatedTeachingTime = 0.0;
        for(int i = 0; i<stringAArrayList.size(); i++){
            accumulatedTeachingTime =
                    accumulatedTeachingTime+
                            Double.parseDouble(stringAArrayList.get(i).get(7).trim().replace(":","."))-
                            Double.parseDouble(stringAArrayList.get(i).get(6).trim().replace(":","."));
            if(i==stringAArrayList.size()-1){
                if (accumulatedTeachingTime>6.0) {
                    teachingTimeExceededList.add(new ArrayList<String>
                            (Arrays.asList(stringAArrayList.get(i).get(11), stringAArrayList.get(i).get(4),
                                    stringAArrayList.get(i).get(5), String.valueOf(accumulatedTeachingTime))));
                    return teachingTimeExceededList;
                }
                else return teachingTimeExceededList;
            }
            else if((!stringAArrayList.get(i).get(11).trim().equals(stringAArrayList.get(i+1).get(11).trim())||
                    !stringAArrayList.get(i).get(4).trim().equals(stringAArrayList.get(i+1).get(4).trim())||
                    !stringAArrayList.get(i).get(5).trim().equals(stringAArrayList.get(i+1).get(5).trim()))){
                if (accumulatedTeachingTime>6.0) {
                    teachingTimeExceededList.add(new ArrayList<String>
                            (Arrays.asList(stringAArrayList.get(i).get(11), stringAArrayList.get(i).get(4),
                                    stringAArrayList.get(i).get(5), String.valueOf(accumulatedTeachingTime))));
                    accumulatedTeachingTime = 0.0;
                }else accumulatedTeachingTime = 0.0;
            }
        }
        return teachingTimeExceededList;
    }

    @Override
    public ArrayList<ArrayList<String>> splittingWeekdays(ArrayList<String> arrayList) {

        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

        if (arrayList.get(5).trim().matches("[[A-Za-z]{3}/]+[A-Za-z]{3}")) {

            String[] weekdays = arrayList.get(5).trim().split("/");
            for (String s : weekdays) {
                ArrayList<String> arrayList2 = new ArrayList<>(arrayList);
                if (s.trim().toLowerCase().equals("mon")) {
                    arrayList2.set(5, "Monday");
                    arrayLists.add(arrayList2);
                }
                if (s.trim().toLowerCase().equals("tue")) {
                    arrayList2.set(5, "Tuesday");
                    arrayLists.add(arrayList2);
                }
                if (s.trim().toLowerCase().equals("wed")) {
                    arrayList2.set(5, "Wednesday");
                    arrayLists.add(arrayList2);
                }
                if (s.trim().toLowerCase().equals("thu")) {
                    arrayList2.set(5, "Thursday");
                    arrayLists.add(arrayList2);
                }
                if (s.trim().toLowerCase().equals("fri")) {
                    arrayList2.set(5, "Friday");
                    arrayLists.add(arrayList2);
                }
                if (s.trim().toLowerCase().equals("sat")) {
                    arrayList2.set(5, "Saturday");
                    arrayLists.add(arrayList2);
                }
                if (s.trim().toLowerCase().equals("sun")) {
                    arrayList2.set(5, "Sunday");
                    arrayLists.add(arrayList2);
                }
            }
        }
        if (arrayList.get(5).trim().matches("[a-zA-Z]{3}\\s*-\\s*[a-zA-Z]{3}")) {

            String[] weekdays = arrayList.get(5).trim().split("-");

            String s1 = weekdays[0].trim().toLowerCase();
            String s2 = weekdays[1].trim().toLowerCase();
            switch (s1) {
                case "mon":
                    ArrayList<String> arrayList21 = new ArrayList<>(arrayList);
                    arrayList21.set(5, "Monday");
                    arrayLists.add(arrayList21);
                    if (s2.equals("mon")) break;
                case "tue":
                    ArrayList<String> arrayList22 = new ArrayList<>(arrayList);
                    arrayList22.set(5, "Tuesday");
                    arrayLists.add(arrayList22);
                    if (s2.equals("tue")) break;
                case "wed":
                    ArrayList<String> arrayList23 = new ArrayList<>(arrayList);
                    arrayList23.set(5, "Wednesday");
                    arrayLists.add(arrayList23);
                    if (s2.equals("wed")) break;
                case "thu":
                    ArrayList<String> arrayList24 = new ArrayList<>(arrayList);
                    arrayList24.set(5, "Thursday");
                    arrayLists.add(arrayList24);
                    if (s2.equals("thu")) break;
                case "fri":
                    ArrayList<String> arrayList25 = new ArrayList<>(arrayList);
                    arrayList25.set(5, "Friday");
                    arrayLists.add(arrayList25);
                    if (s2.equals("fri")) break;
                case "sat":
                    ArrayList<String> arrayList26 = new ArrayList<>(arrayList);
                    arrayList26.set(5, "Saturday");
                    arrayLists.add(arrayList26);
                    if (s2.equals("sat")) break;
                case "sun":
                    ArrayList<String> arrayList27 = new ArrayList<>(arrayList);
                    arrayList27.set(5, "Sunday");
                    arrayLists.add(arrayList27);
                    if (s2.equals("sun")) break;
            }
        }
        return arrayLists;
    }

}
class CompareItemsInTheRowsOfTheTimetableImpl implements CompareItemsInTheRowsOfTheTimetable {

    @Override
    public int compare(ArrayList<String> arrayList1, ArrayList<String> arrayList2) {

        int result = arrayList1.get(11).trim().compareTo(arrayList2.get(11).trim());
        if (result != 0) return result;
        if (Integer.parseInt(arrayList1.get(4).trim()) > Integer.parseInt(arrayList2.get(4).trim())) return 1;
        else if (Integer.parseInt(arrayList1.get(4).trim()) < Integer.parseInt(arrayList2.get(4).trim())) return -1;
        result = arrayList1.get(5).trim().compareTo(arrayList2.get(5).trim());
        if (result != 0) return result;
        if (Double.parseDouble(arrayList1.get(6).trim().replace(":", "."))
                > Double.parseDouble(arrayList2.get(6).trim().replace(":", "."))) return 1;
        else if (Double.parseDouble(arrayList1.get(6).trim().replace(":", "."))
                < Double.parseDouble(arrayList2.get(6).trim().replace(":", "."))) return -1;
        return 1;
    }
}
