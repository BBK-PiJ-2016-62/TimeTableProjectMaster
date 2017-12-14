
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public class CheckRoomSizeSufficiencyImpl implements CheckRoomSizeSufficiency {

    @Override
    public ArrayList<String> tutGroupAndRoomSize(ArrayList<ArrayList<String>> firstList) {

        FormModuleList fml = new FormModuleListImpl();
        ArrayList<ModuleImpl> moduleCodes = new ArrayList<>(fml.moduleCodes());
        ArrayList<ModuleImpl> moduleCodes1 = fml.addingDataToModuleCodes(firstList, moduleCodes);
        CheckRoomSizeSufficiency crss=new CheckRoomSizeSufficiencyImpl();
        ArrayList<String> tutGpAndRmSizeResult = new ArrayList<String>
                (crss.checkRoomSizeSufficiency(moduleCodes));
        return tutGpAndRmSizeResult;
    }

    @Override
    public ArrayList<String> checkRoomSizeSufficiency(ArrayList<ModuleImpl> moduleCodes){

        ArrayList<ArrayList<String>> roomSizeList = new InputAndTidyUpRmDetailListImpl().
                formRoomSizeList();

        ArrayList<String> roomSizeSufficiencyResult=new ArrayList<>();
        String firstLine="Find whether size of lecture rooms or labs assigned to each module is ok with student numbers:";
        String warning ="  WARNING:  Not able to study these modules since " +
                "they contained only in either the timetable or the module list:";
        String list1= new CmprTmTblNModLstTrimModLstNProdWrnngImpl().modulesNotInModuleList
                (new InputAndTidyUpModuleListImpl().modStudNumList(),
                        new InputAndTidyUpTimeTableImpl().
                                inputAndTidyTimeTable())
                .toString().replace("[","").replace("]","");
        String list2= new CmprTmTblNModLstTrimModLstNProdWrnngImpl().modulesNotInTimeTable
                (new InputAndTidyUpModuleListImpl().modStudNumList(),
                        new InputAndTidyUpTimeTableImpl().
                                inputAndTidyTimeTable())
                .toString().replace("[","").replace("]","");
        System.out.println();
        System.out.println(firstLine);
        System.out.println(warning);
        System.out.println("  "+list1);
        System.out.println("  "+list2);
        roomSizeSufficiencyResult.add("");
        roomSizeSufficiencyResult.add(firstLine);
        roomSizeSufficiencyResult.add(warning);
        roomSizeSufficiencyResult.add("  "+list1);
        roomSizeSufficiencyResult.add("  "+list2);
        for (ModuleImpl m:moduleCodes){
            System.out.println(m.getCourseCode()+":");
            roomSizeSufficiencyResult.add(m.getCourseCode()+":");
            System.out.println("  Number of students: "+m.getStudentNum());
            roomSizeSufficiencyResult.add("  Number of students: "+m.getStudentNum());
            int count = 0;
            for (ArrayList<String> as : m.getTypeOfMeetingNRmNumList()) {
                if ((as.get(0).trim().toLowerCase().contains("tutorial") ||
                        as.get(0).trim().toLowerCase().contains("practical"))&&
                        !as.get(0).trim().toLowerCase().contains("lecture")) {
                    count++;
                }
                if (as.get(0).trim().toLowerCase().contains("lecture")){
                    /*int roomSize=0;
                    boolean rmFound=false;*/
                    ArrayList<String> asList = new ArrayList<>(Arrays.asList(as.get(1).
                            split("/")));
                    Iterator<String> itr=asList.iterator();
                    while(itr.hasNext()) {
                        boolean rmFound=false;
                        String itr1 =itr.next();
                    for(ArrayList<String> rs:roomSizeList) {
                        int roomSize=0;

                        if (itr1.trim().replace(".","").toLowerCase().
                                contains(rs.get(0).trim().replace(
                                        ".","").toLowerCase())) {

                            roomSize = Integer.parseInt(rs.get(1).trim());
                            String line1= "  The room "+rs.get(0).trim()+
                                    " assigned to this module for "+
                                    as.get(0).toLowerCase()+" can accommodate "+roomSize+
                                    " students.";
                            System.out.println(line1);
                            roomSizeSufficiencyResult.add(line1);
                            if (m.getStudentNum() <= roomSize) {
                                String line2 = "  The room can accommodate the " +
                                        m.getStudentNum() + " students";
                                System.out.println(line2);
                                roomSizeSufficiencyResult.add(line2);
                            } else {
                                String line3 = "  The room is too small for the " +
                                        m.getStudentNum() + " students";
                                System.out.println(line3);
                                roomSizeSufficiencyResult.add(line3);
                            }
                                rmFound = true;
                        }
                    }
                    if(rmFound==false) {
                        String line4="  Either the number of the room ("+itr1.trim()+") assigned " +
                                "to this module for "+as.get(0).trim()+" does not in the room list " +
                                "or no room has been assigned.";
                        System.out.println(line4);
                        roomSizeSufficiencyResult.add(line4);
                    }
                }
                }
            }
            if (count == 0) {
                String line4 ="  This module does not has tutorial/practical or " +
                        "lectures and tutorials/practicals are combined.";
                System.out.println(line4);
                roomSizeSufficiencyResult.add(line4);
            }
            else if (m.getStudentNum() <= 25 * count && m.getStudentNum() > 25 * (count - 1)) {
                String line4 = "  Number of tutorial/Practical group(s) ("+count+") " +
                        "set is adequate.";
                System.out.println(line4);
                roomSizeSufficiencyResult.add(line4);
                String line5="  The room(s) allocated to the tutorials/practicals are/is:";
                System.out.println(line5);
                roomSizeSufficiencyResult.add(line5);
                ArrayList<ArrayList<Integer>> studentNumArray = new ArrayList<>();
                //boolean foundRm = false;
                abcde:
                for (int i = 0; i < m.getTypeOfMeetingNRmNumList().size(); i++) {
                    studentNumArray.add(new ArrayList<Integer>());
                    if (!m.getTypeOfMeetingNRmNumList().get(i).get(0).trim().
                            toLowerCase().contains("lecture") &&
                            (m.getTypeOfMeetingNRmNumList().get(i).get(0).trim().
                                    toLowerCase().contains("tutorial") ||
                                    m.getTypeOfMeetingNRmNumList().get(i).get(0).trim().
                                            toLowerCase().contains("practical"))) {
                        System.out.println("  Group " + i + ":");
                        roomSizeSufficiencyResult.add("  Group " + i + ":");
                        boolean foundRm = false;
                        ArrayList<String> tutOrPraRmsPerGroup = new ArrayList<String>
                                (Arrays.asList(m.getTypeOfMeetingNRmNumList().get(i).get(1).
                                        split("/")));
                        Iterator<String> itr = tutOrPraRmsPerGroup.iterator();
                        while (itr.hasNext()) {
                            String itr1 = itr.next();
                            for (ArrayList<String> rs : roomSizeList) {
                                if (itr1.replace(".","").toLowerCase().
                                        equals(rs.get(0).replace(
                                                ".","").toLowerCase())) {
                                    String line6 = "  Room "+rs.get(0)+": Size: "+
                                            rs.get(1)+" person. Therefore, " +
                                            "maximum tutorial group size in this room: "+Integer.parseInt(rs.get(1));
                                    System.out.println(line6);
                                    roomSizeSufficiencyResult.add(line6);
                                    studentNumArray.get(i).add(Math.min(25, Integer.parseInt(rs.get(1))));
                                    foundRm = true;
                                }
                            }
                            if (foundRm == false) {
                                String line7 = "  Either room number ("+itr1+") is not in the room list or no room " +
                                        "has been assigned to this module, analysis for this module" +
                                        " cannot proceed further.";
                                System.out.println(line7);
                                roomSizeSufficiencyResult.add(line7);
                                break abcde;
                            }
                            foundRm = false;
                        }
                        boolean groupAssignedMoreThanOneRoom = false;
                        if (!m.getTypeOfMeetingNRmNumList().get(i).get(0).trim().toLowerCase().contains("lecture") &&
                                i == m.getTypeOfMeetingNRmNumList().size() - 1) {
                            int roomMinTotStudentNum = 0;
                            for (ArrayList<Integer> studentNumArray1 : studentNumArray) {
                                if (studentNumArray1.size() != 0) {
                                    roomMinTotStudentNum = roomMinTotStudentNum+Collections.min(studentNumArray1);
                                }
                                if (studentNumArray1.size() > 1) groupAssignedMoreThanOneRoom = true;
                            }
                            if (m.getStudentNum() > roomMinTotStudentNum) {
                                if (count > 1 && groupAssignedMoreThanOneRoom == true) {
                                    String line2 = "  Note: As there are more than one group and there are more than " +
                                            "one room assigned to a group and it is in existence of the sum " +
                                            "of permitted student numbers calculated from selecting one room each" +
                                            " from the groups which is less than the total number of students of the" +
                                            " module ("+roomMinTotStudentNum+"), please check carefully" +
                                            " and rectify if necessary.";
                                    System.out.println(line2);
                                    roomSizeSufficiencyResult.add(line2);
                                } else{
                                    String line = "  As room(s) assigned to the tutorial/practical group(s) can " +
                                            "only accommodate "+roomMinTotStudentNum+
                                            " students. Larger room(s) should be found to " +
                                            "replace the smaller one(s).";
                                    System.out.println(line);
                                    roomSizeSufficiencyResult.add(line);
                                }
                            } else {
                                String line = "  The size of the room(s) allocated to the " +
                                        "tutorial/practical group(s) is also sufficient.";
                                System.out.println(line);
                                roomSizeSufficiencyResult.add(line);

                            }
                        }
                    }
                }
            }
            else if (m.getStudentNum() > 25 * count) {
                int additionalGroupNum = (int) Math.ceil(m.getStudentNum() / 25d) - count;
                String line = "  As there are "+m.getStudentNum()+" students and there are/is only "+count+
                        " tutorial/practical group(s), " +
                        +additionalGroupNum+" tutorial/practical group(s) need to be added.\n" +
                        "  (Caveat: classroom size should be taken into consideration upon adding " +
                        "tutorial/practical group(s))";
                System.out.println(line);
                roomSizeSufficiencyResult.add(line);
            }
            else {
                int reducingGroupNum = count - (int) Math.ceil(m.getStudentNum() / 25d);
                String line = "  As there are "+m.getStudentNum()+" students and there are/is "+count+
                        " tutorial/practical group(s), "+reducingGroupNum+" tutorial/practical group(s) " +
                        "need to be withdrawn.\n" +
                        "  (Caveat: classroom size should be taken into consideration upon deduction " +
                        "of tutorial/practical group(s))";
                System.out.println(line);
                roomSizeSufficiencyResult.add(line);
            }
        }
        return roomSizeSufficiencyResult;
    }
}
