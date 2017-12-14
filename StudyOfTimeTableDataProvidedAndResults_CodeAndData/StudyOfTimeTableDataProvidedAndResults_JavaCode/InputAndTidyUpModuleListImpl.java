import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Eric's laptop on 13/12/2017.
 */
public class InputAndTidyUpModuleListImpl implements InputAndTidyUpModuleList {

    @Override
    public ArrayList<ArrayList<String>> modStudNumList() {
        //obtain 1st module list from Launcher.java
        String moduleListPath1=Launcher.moduleListPath1;
        ArrayList<ArrayList<String>> modStudNumList =
                moduleNumOfStudentList(inputDataFromTable(moduleListPath1));
        //obtain 1st module list from Launcher.java
        String moduleListPath2=Launcher.moduleListPath2;
        ArrayList<ArrayList<String>> modStudNumList1 =
                moduleNumOfStudentList(inputDataFromTable(moduleListPath2));
        ArrayList<ArrayList<String>> tempModStudNumList = new ArrayList<>(modStudNumList);
        //ArrayList<ArrayList<String>> tempModStudNumList1 = new ArrayList<>(modStudNumList1);
        int count;
        abcd:
        for(ArrayList<String> alStr:modStudNumList1) {
            count = 0;
            for (ArrayList<String> alStr1 : tempModStudNumList) {
                if (alStr.get(0).trim().toLowerCase().equals(alStr1.get(0).trim().toLowerCase())) continue abcd;
                else if(count==tempModStudNumList.size()-1)modStudNumList.add(alStr);
                count++;
            }
        }
        return modStudNumList;
    }
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
