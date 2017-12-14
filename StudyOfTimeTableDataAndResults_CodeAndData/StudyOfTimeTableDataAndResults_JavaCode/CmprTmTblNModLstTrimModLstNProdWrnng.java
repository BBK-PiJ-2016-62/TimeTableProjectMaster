import java.util.ArrayList;

/**
 * Created by Eric's laptop on 13/12/2017.
 */
public interface CmprTmTblNModLstTrimModLstNProdWrnng {

    /**
     * This method is for comparing two arraylists (format: ArrayList<ArrayList<String>>) and picking up those items at
     * index 0 of the arraylists of the first arraylist which are not the same as any of the items at index 1 of the
     * arrayLists of the second arraylist and return an arraylist (format: ArrayList<String>) of such items.
     * @param modStudNumList
     * @param firstList
     * @return
     */
    public ArrayList<String> modulesNotInTimeTable
            (ArrayList<ArrayList<String>> modStudNumList, ArrayList<ArrayList<String>> firstList);

    public ArrayList<ArrayList<String>> trimModListAllInTmTable
            (ArrayList<ArrayList<String>> modStudNumList, ArrayList<String> modulesNotInTimeTable );

    public ArrayList<String> modulesNotInModuleList
            (ArrayList<ArrayList<String>> modStudNumList, ArrayList<ArrayList<String>> firstList);
}
