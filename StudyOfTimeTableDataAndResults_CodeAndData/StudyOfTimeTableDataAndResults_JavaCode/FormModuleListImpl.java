
import java.util.ArrayList;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public class FormModuleListImpl implements FormModuleList {

    //@Override
    public ArrayList<ModuleImpl> moduleCodes() {

        ArrayList<ModuleImpl> moduleCodes = new ArrayList<>();
        InputAndTidyUpModuleList emi=new InputAndTidyUpModuleListImpl();
        ArrayList<ArrayList<String>> modStudNumList= emi.modStudNumList();
        CmprTmTblNModLstTrimModLstNProdWrnng ttbles = new CmprTmTblNModLstTrimModLstNProdWrnngImpl();
        InputAndTidyUpTimeTable iattt=new InputAndTidyUpTimeTableImpl();
        ArrayList<ArrayList<String>> firstList = iattt.inputAndTidyTimeTable();
        ArrayList<String> modulesNotInTimeTable = ttbles.modulesNotInTimeTable(modStudNumList, firstList);
        ArrayList<ArrayList<String>> trimModListAllInTmTable=
                ttbles.trimModListAllInTmTable(emi.modStudNumList(), modulesNotInTimeTable);
        trimModListAllInTmTable.forEach(s -> moduleCodes.add(formModuleCode1(s)));
        return moduleCodes;
    }

    //@Override
    public ModuleImpl formModuleCode(String s) {

        String[] ss = s.split(",");
        ModuleImpl moduleCode = new ModuleImpl();
        moduleCode.setCourseCode(ss[0]);
        moduleCode.setStudentNum(Integer.parseInt(ss[1]));
        return moduleCode;
    }

    public ModuleImpl formModuleCode1(ArrayList<String> s){
        ModuleImpl moduleCode = new ModuleImpl();
        moduleCode.setCourseCode(s.get(0));
        moduleCode.setStudentNum(Integer.parseInt(s.get(1)));
        return moduleCode;
    }

    //@Override
    public ArrayList<ModuleImpl> addingDataToModuleCodes(
            ArrayList<ArrayList<String>> firstList, ArrayList<ModuleImpl> moduleCodes) {

        ArrayList<ModuleImpl> moduleCodes1 = new ArrayList<>(moduleCodes);
        for (ModuleImpl m : moduleCodes1) {
            firstList.forEach(s -> addingData(m, s));
        }
        return moduleCodes1;
    }

    //@Override
    public void addingData(ModuleImpl m, ArrayList<String> s) {

        if (s.size() >= 4 && s.get(0).length() != 0 && s.get(1).length() != 0 &&
                s.get(3).length() != 0)
            if (s.get(1).trim().toLowerCase().equals(m.getCourseCode().toLowerCase())) {
                ArrayList<String> al = new ArrayList<>();
                al.add(s.get(3));
                if (s.size() >= 14 && s.get(13).length() != 0) al.add(s.get(13));
                else al.add("No room assigned");
                m.addToTypeOfMeetingNRmNumList(al);
        }
    }
}

