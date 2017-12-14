import java.util.ArrayList;

/**
 * Project: Time Table Project
 * Date: 18 December 2017
 * Version: 1.0
 * Programmers: ZAHID HUSSAIN, JOHN OLORUNSUYI, Eric Chiu
 * Course: MSc Computer Science (CN7021), University of East London
 */
public interface FormModuleList {

    /**
     * Forming a list of module objects according to the interface/class Module.java with information of them supplied
     * by CmprTmTblNModLstTrimModLstNProdWrnng.java, InputAndTidyUpModuleList.java and InputAndTidyUpTimeTable
     * which serve to input and tidy data
     * @return
     */

    public ArrayList<ModuleImpl> moduleCodes();

    public ModuleImpl formModuleCode(String s);

    public ArrayList<ModuleImpl> addingDataToModuleCodes(ArrayList<ArrayList<String>> firstList,
                                                         ArrayList<ModuleImpl> moduleCodes);

    public void addingData(ModuleImpl m, ArrayList<String> s);
}
