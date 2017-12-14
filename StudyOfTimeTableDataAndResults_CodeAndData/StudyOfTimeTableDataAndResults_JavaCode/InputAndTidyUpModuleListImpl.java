import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public class InputAndTidyUpModuleListImpl implements InputAndTidyUpModuleList {

    @Override
    public ArrayList<ArrayList<String>> modStudNumList() {
        //obtain module list path from Launcher.java
        String moduleListPath=Launcher.moduleListPath;
        ArrayList<ArrayList<String>> modStudNumList =
                moduleNumOfStudentList(inputDataFromTable(moduleListPath));
        return modStudNumList;
    }
    //static helper class
    public static ArrayList<ArrayList<String>> inputDataFromTable(String moduleInforPath) {
        ArrayList<ArrayList<String>> firstList = new ArrayList<>();
        Path path = Paths.get(moduleInforPath);
        try {
            Files.lines(path).forEach(s ->firstList.add(new ArrayList<>
                    (Arrays.asList(s.split(",")))));
        } catch (IOException e) {
            System.out.println(e);
        }
        return firstList;
    }
    //static helper class
    public static ArrayList<ArrayList<String>> moduleNumOfStudentList(ArrayList<ArrayList<String>> firstList){

        ArrayList<ArrayList<String>> modStudNumList = new ArrayList<>();
        ArrayList<String> tempString=new ArrayList<>();
        String aStr=""; int i=0; boolean firstEncounterModuleName=true;
        int countIterations=0;
        for(ArrayList<String> row:firstList){

            if(row.get(0).matches("[A-Za-z]{2}[0-9]{4}")){
                if(firstEncounterModuleName==true){
                    aStr=row.get(0);
                    firstEncounterModuleName=false;
                }else {
                    tempString.add(aStr);
                    tempString.add(Integer.toString(i));
                    modStudNumList.add(tempString);
                    tempString = new ArrayList<>();
                    aStr = row.get(0);
                    i = 0;
                }
            }
            if(row.get(0).toLowerCase().equals("count")){
                i=Math.max(i,Integer.parseInt(row.get(1)));
            }
            if(countIterations==firstList.size()-1&&i!=0){
                tempString.add(aStr);
                tempString.add(Integer.toString(i));
                modStudNumList.add(tempString);
            }
            countIterations++;
        }
        return modStudNumList;
    }
}
