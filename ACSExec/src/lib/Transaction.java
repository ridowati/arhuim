package lib;

import helper.*;
import org.javatuples.*;

import java.io.*;
import java.util.*;

public class Transaction {
    /*-----------------------------------------------------------------------------
     *  struct for 1-HTWUIs
     *-----------------------------------------------------------------------------*/
    public static class oneTWU implements Comparable<oneTWU>
    {
        public int name;
        public double twUtility;

        public int compareTo(oneTWU compareFruit) {

            double comparetwUtility = compareFruit.twUtility;

            //ascending order
            return (int)(this.twUtility - comparetwUtility);

            //descending order
            //return compareQuantity - this.quantity;

        }
    }

    public static class record
    {
        public int utility;
        public java.util.LinkedList<Pair<Integer, Integer>> items = new java.util.LinkedList<Pair<Integer, Integer>>(); //first: name, second: item utility
    }

    public java.util.ArrayList<oneTWU> oneTWUs = new java.util.ArrayList<oneTWU>();
    public java.util.LinkedList<Integer> oneTWUsName = new java.util.LinkedList<Integer>();

    public java.util.ArrayList<record> data = new java.util.ArrayList<record>();
    public java.util.ArrayList<java.util.LinkedList<Integer>> invertTable =
            new java.util.ArrayList<java.util.LinkedList<Integer>>();
    public int totalUtility; // change to private
    public double thresholdRatio; //change to private
    public double threshold;

    public Transaction(double iThresholdRatio)
    {
        //System.out.println("Transaction constructor......");
        totalUtility = 0;
        thresholdRatio = iThresholdRatio;
    }

    public void dispose()
    {
        System.out.println("Transaction desctructor....");
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Transaction
     *      Method:  lib.Transaction :: getThreshold
     * Description:
     *--------------------------------------------------------------------------------------
     */
    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
    //ORIGINAL LINE: const double& getThreshold() const
    public final double getThreshold()
    {
        return threshold;
    }

    public final boolean readData(String path) throws IOException {
        File file = new File(path);

        if (!file.exists())
        {
            return false;
        }

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while((line = br.readLine()) != null){
            //process the line
            int firstSemicolon = line.indexOf(":");
            int secondSemicolon = line.lastIndexOf(":");
            record aRecord = new record();

            StringBuilder ssName = new StringBuilder();
            StringBuilder ssTotalUtility = new StringBuilder();
            StringBuilder ssUtility = new StringBuilder();

            ssName.append(tangible.DotNetToJavaStringHelper.substring(line, 0, firstSemicolon));
            ssTotalUtility.append(tangible.DotNetToJavaStringHelper.substring(line, firstSemicolon + 1, secondSemicolon - firstSemicolon - 1));
            ssUtility.append(tangible.DotNetToJavaStringHelper.substring(line, secondSemicolon + 1, line.length() - secondSemicolon - 1));

            aRecord.utility = Integer.parseInt(ssTotalUtility.toString());
            totalUtility += aRecord.utility;

            //System.out.println("name : " + ssName);
            //System.out.println("utilities : " + ssUtility);
            String[] names = ssName.toString().trim().split(" ");
            String[] utilities = ssUtility.toString().trim().split(" ");
            int index=0;
            for (String sname : names) {
                int name;
                int utility;

                name = Integer.parseInt(sname);
                utility = Integer.parseInt(utilities[index]);

                //System.out.println("name : " + name);
                //System.out.println("utility : " + utility);
                //System.out.println("----------------------------------");
                aRecord.items.addLast(Pair.with(name, utility));
                index++;
            }

            if (names.length % 2 > 0){
                int name = Integer.parseInt(names[names.length - 1]);
                int utility = Integer.parseInt(utilities[utilities.length - 1]);
                aRecord.items.addLast(Pair.with(name, utility));
            }

            data.add(aRecord);
            //utility = Integer.parseInt(ssUtility.toString());
            //
        }
        br.close();

        //calculate the threshold
        threshold = (double)totalUtility * thresholdRatio;

        //fill invert table
        for (int i = 0; i < data.size(); ++i)
        {
            record aRecord = data.get(i);
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
            for (var j : aRecord.items)
            {
                if (invertTable.size() < j.getValue0() + 1)
                {
                    //invertTable.ensureCapacity(j.getValue0() + 1);
                    invertTable = (ArrayList<LinkedList<Integer>>) ArrayHelper.resize(invertTable, j.getValue0() + 1);
                }
                invertTable.get(j.getValue0()).addLast(i);
            }
        }

        //build 1-HTWUIs
        for (int i = 0; i < invertTable.size(); ++i)
        {
            oneTWU twu = new oneTWU();
            twu.name = i;
            twu.twUtility = 0;
            for (int j : invertTable.get(i))
            {
                twu.twUtility += data.get(j).utility;
            }
            if (twu.twUtility > threshold)
            {
                oneTWUs.add(twu);
            }
        }
        Collections.sort(oneTWUs, new Comparator<oneTWU>(){
            public int compare (oneTWU i, oneTWU j)
            {
                //return i.twUtility > j.twUtility;
                return i.compareTo(j);
            }
        });
        /*
        sort(oneTWUs.iterator(), oneTWUs.end(), (oneTWU i, oneTWU j) ->
        {
            return i.twUtility < j.twUtility;
        });
        */

        for (oneTWU i : oneTWUs)
        {
            oneTWUsName.addLast(i.name);
        }

        return true;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Transaction
     *      Method:  lib.Transaction :: calculateUtility
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final Quartet<Integer, Boolean, Boolean, Integer> calculateUtility(tangible.RefObject<java.util.LinkedList<Integer>> relatedTransaction, java.util.ArrayList<Integer> cItemset)
    {
        int name = cItemset.get(cItemset.size() - 1);
        //System.out.println("name of calculate : " + name);
        int utility = 0;
        int twUtility = 0;
        boolean isHUI = false;
        boolean passThreshold = true;
        final java.util.LinkedList<Integer> itemRelatedTransaction = invertTable.get(name);
        java.util.ArrayList<Integer> itemset = new java.util.ArrayList<Integer>(cItemset);
        if (cItemset.size() == 1)
        {
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
            for (var i : itemRelatedTransaction)
            {
                relatedTransaction.argValue.addLast(i);
            }
        }
        else
        {
            java.util.LinkedList<Integer> newRelatedTransaction = new java.util.LinkedList<Integer>();

            var index1= 0;
            var index2 = 0;

            while (index1 < relatedTransaction.argValue.size() && index2 < itemRelatedTransaction.size())
            {
                if (relatedTransaction.argValue.get(index1) < itemRelatedTransaction.get(index2))
                {
                    ++index1;
                }
                else if (itemRelatedTransaction.get(index2) < relatedTransaction.argValue.get(index1))
                {
                    ++index2;
                }
                else{
                    newRelatedTransaction.add(relatedTransaction.argValue.get(index1));
                    ++index1; ++index2;
                }
            }

            relatedTransaction.argValue = newRelatedTransaction;
        }
        Collections.sort(itemset);
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        for (var i : relatedTransaction.argValue)
        {
            //System.out.println("Related lib.Transaction : " + i);
            twUtility += data.get(i).utility;
            final java.util.LinkedList<Pair<Integer, Integer>> items = data.get(i).items;

            var index1 = 0;
            var index2 = 0;

            while (index1 < itemset.size())
            {
                if (items.get(index2).getValue0() < itemset.get(index1)) ++index2;
                else if (items.get(index2).getValue0() == itemset.get(index1)){
                    utility += items.get(index2).getValue1();
                    ++index1; ++index2;
                }
            }
        }
        if (utility >= threshold)
        {
            isHUI = true;
        }
        else if (twUtility < threshold)
        {
            passThreshold = false;
        }

        return Quartet.with(utility, isHUI, passThreshold, twUtility);
    }
}
