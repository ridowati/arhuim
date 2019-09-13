package lib;

import org.javatuples.*;

import java.text.ParseException;

public class ACS {
    public java.util.ArrayList<Pair<java.util.ArrayList<Integer>, Integer>> huis =
            new java.util.ArrayList<Pair<java.util.ArrayList<Integer>, Integer>>();
    /* ====================  DATA MEMBERS  ======================================= */

    public int nIteration;
    public int populationSize;
    public double initPheromone;
    public double rho;
    public double alpha;
    public double beta;
    public double q0;
    public Transaction lDataset;
    public PTable pTable = new PTable();
    public Node lSNode;
    public int lastFind;
    /* ====================  DATA MEMBERS  ======================================= */

    public ACS(int iSize, Transaction iLDataset, Node iLSNode, double iInitialPheromone, double iRho, double iAlpha, double iBeta, double iQ0)
    {
        nIteration = 1;
        populationSize = iSize;
        lDataset = iLDataset;
        lSNode = iLSNode;
        initPheromone = iInitialPheromone;
        rho = iRho;
        alpha = iAlpha;
        beta = iBeta;
        q0 = iQ0;
    }

    public final int getNIteration()
    {
        return nIteration;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.ACS
     *      Method:  lib.ACS :: getLastFind
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final int getLastFind()
    {
        return lastFind;
    }

    public final void runIteration() throws ParseException {
        Node lCNode;
        java.util.ArrayList<Integer> cItemset = new java.util.ArrayList<Integer>();
        //System.out.println("itemsetsize after declaration : " + cItemset.size());
        java.util.ArrayList<Integer> lBestItemset = null;
        int bestUtility = 0;
        java.util.LinkedList<Integer> relatedTransactions = new java.util.LinkedList<Integer>();

        for (int i = 0; i < populationSize; ++i)
        {
            //System.out.println("\n\nPopulation " + i + " : ");
            lCNode = lSNode;
            cItemset.clear();
            //System.out.println("itemsetsize after clearn : " + cItemset.size());
            relatedTransactions.clear();
            int index = 0;
            while (!lCNode.finish())
            {
                //System.out.println("loop " + index + " : ");
                index++;
                //System.out.println("itemsetsize : " + cItemset.size());
                lCNode = lCNode.selectNext(cItemset, alpha, beta, q0, pTable, initPheromone);
                //System.out.println("itemsetsize after selectnext : " + cItemset.size());
                lCNode.localUpdate(rho, initPheromone);
                if (!lCNode.calculated())
                {
                    tangible.RefObject<java.util.LinkedList<Integer>> tempRef_relatedTransactions =
                            new tangible.RefObject<java.util.LinkedList<Integer>>(relatedTransactions);
                    Quartet<Integer, Boolean, Boolean, Integer> result =
                            lDataset.calculateUtility(tempRef_relatedTransactions, cItemset);
                    //System.out.println("itemsetsize after calculate utility: " + cItemset.size());
                    relatedTransactions = tempRef_relatedTransactions.argValue;
//C++ TO JAVA CONVERTER WARNING: The following line was determined to be a copy constructor call - this should be verified and a copy constructor should be created if it does not yet exist:
//ORIGINAL LINE: lCNode->setRelatedTransactions(relatedTransactions);
                    lCNode.setRelatedTransactions(new java.util.LinkedList(relatedTransactions));
                    if (result.getValue1())
                    {
                        huis.add(Pair.with(new java.util.ArrayList<Integer> (cItemset), result.getValue0()));
                        //System.out.println("itemsetsize after huis add : " + cItemset.size());
                        if (bestUtility < result.getValue0())
                        {
                            bestUtility = result.getValue0();
                            if (lBestItemset != null)
                            {
                                if (lBestItemset != null)
                                    lBestItemset.clear();
                            }
                            lBestItemset = new java.util.ArrayList<Integer>(cItemset);
                            //System.out.println("itemsetsize after lbestitem : " + cItemset.size());

                        }
                    }
                    else if (!result.getValue2())
                    {
                        pTable.insertRecord(cItemset);
                        //System.out.println("itemsetsize after pTable insert record : " + cItemset.size());
                        lCNode.clearRemainNodes();
                    }

                    if (cItemset.size() == 2)
                    {
                        //System.out.println("masuk dong " + result.getValue3());
                        Node.inputTwoTWU(cItemset.get(0), cItemset.get(cItemset.size() - 1), result.getValue3());
                        //System.out.println("itemsetsize after input two TWU : " + cItemset.size());
                        /*
                        System.out.print("twoTWU : ");
                        for(var tt : lib.Node.twoTWU){
                            System.out.print(tt + ", ");
                        }
                        System.out.println();

                         */
                    }
                    lCNode.setCalculated();
                }
                else
                {
                    relatedTransactions = new java.util.LinkedList<Integer>(lCNode.getRelatedTransactions());
                }
            }
            if (lCNode.getName() == -1)
                break;
            Node.recurisivePrune(lCNode);
        }
        if (lBestItemset != null)
        {
            Node.globalUpdate(lBestItemset, bestUtility, lDataset.getThreshold());
            if (lBestItemset != null)
                lBestItemset.clear();
            lastFind = nIteration;
        }
        ++nIteration;
    }


}
