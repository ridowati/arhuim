package lib;

import java.util.Collections;
import java.util.Vector;

public class PTable {

    public java.util.ArrayList<java.util.ArrayList<Integer>> table = new java.util.ArrayList<java.util.ArrayList<Integer>>();

    public PTable()
    {
        //System.out.println("PTable Constructor......");
    }

    public final int maxVersion()
    {
        return (int)table.size();
    }

    public boolean checkTable (java.util.ArrayList<Integer> itemset, int version ) {
        boolean result = false;
        if (table.size() != 0) {
            boolean alreadySort = false;
            if (version < table.size()) {
                for (int i = version; i < table.size(); ++i) {
                    if (table.get(i).size() < itemset.size() && table.get(i).size() != 0) {
                        if (!alreadySort) {
                            Collections.sort(itemset);
                            alreadySort = true;
                        }

                        if (itemset.containsAll(table.get(i))){
                            result = true;
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.PTable
     *      Method:  lib.PTable :: insertRecord
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final void insertRecord(java.util.ArrayList<Integer> itemset)
    {
        java.util.LinkedList<java.util.Iterator<java.util.ArrayList<Integer>>> lErase = new java.util.LinkedList<java.util.Iterator<java.util.ArrayList<Integer>>>();
        Collections.sort(itemset);
        for (java.util.ArrayList<Integer> i : table)
        {
            if (itemset.size() < i.size())
            {
                if (i.equals(itemset))
                {
                    i.clear();
                }
            }
        }
        table.add(itemset);
    }
}
