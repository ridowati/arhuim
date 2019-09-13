package lib;

import helper.ArrayHelper;
import org.javatuples.*;
import org.apache.commons.math3.distribution.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Node {
    /* ====================  DATA MEMBERS  ======================================= */
    private static Node lStartNode = null;
    public static java.util.ArrayList<Integer> twoTWU = new java.util.ArrayList<Integer>();
    public static java.util.ArrayList<Transaction.oneTWU> lOneTWU;
    public static java.util.ArrayList<java.util.LinkedList<Pair<Integer, Integer>>> indexTable =
            new java.util.ArrayList<java.util.LinkedList<Pair<Integer, Integer>>>();
    public static final int sizeIndexTable = 1024;

    public int name;
    public int maxCheckPTable;
    public double pheromone;
    public Node lParentNode;
    public java.util.LinkedList<Integer> followingNodes = new java.util.LinkedList<Integer>();
    public java.util.LinkedList<Pair<Integer, Node>> remainNodes = new java.util.LinkedList<Pair<Integer, Node>>();
    public java.util.LinkedList<Integer> relatedTransactions = new java.util.LinkedList<Integer>();
    public boolean calculate;

    public Node(Node iLParentNode, int iName, java.util.LinkedList<Integer> iFollowingNodes, double initPheromone)
    {
        //System.out.println("Node constructor....");
        lParentNode = iLParentNode;
        name = iName;
        followingNodes = iFollowingNodes;
        pheromone = initPheromone;
        maxCheckPTable = 0;
        calculate = false;
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        for (Integer i : iFollowingNodes)
        {
            remainNodes.addLast(new Pair<Integer,Node>(i, (Node)null));
        }

        //System.out.println("size remain node in constructor : " + remainNodes.size());
    }

    public static void deleteStartNode() {
        if (Node.lStartNode != null)
        {
            if (Node.lStartNode != null)
                Node.lStartNode.dispose();
        }
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  ~lib.Nodeg
     * Description:  destructor
     *--------------------------------------------------------------------------------------
     */
    public void     dispose()
    {
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
        //System.out.println("Node destructor....");
        for (int index = 0; index < remainNodes.size(); index++)
        {
            if (remainNodes.get(index).getValue1() != null)
            {
                //remainNodes.get(index).setAt1(null);
                remainNodes.set(index, Pair.with(remainNodes.get(index).getValue0(),null));
            }

        }
    } // -----  end of method lib.Node::~lib.Node  (destructor)  -----

    public final boolean finish()
    {
        return (remainNodes.size() == 0);
    }

    public final void positivePrue(java.util.ArrayList<Integer> itemset, PTable pTable)
    {
        if (maxCheckPTable < pTable.maxVersion())
        {
            java.util.LinkedList<Pair<Integer, Node>> locationRecord =
                    new java.util.LinkedList<Pair<Integer, Node>>();
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
            for (var i : remainNodes)
            {
                if (i.getValue1() == null)
                {
                    java.util.ArrayList<Integer> tempItemset = new java.util.ArrayList<Integer>(itemset);
                    tempItemset.add(i.getValue0());
                    if (pTable.checkTable(tempItemset, maxCheckPTable))
                    {
                        locationRecord.addLast(i);
                    }
                }
            }
            maxCheckPTable = pTable.maxVersion();

            //C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
            for (var i : locationRecord)
            {
                //C++ TO JAVA CONVERTER TODO TASK: There is no direct equivalent to the STL list 'erase' method in Java:
                //remainNodes.erase(i);
                remainNodes.remove(i);
            }

            //System.out.println("size remain node after remove : " + remainNodes.size());
        }
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: inputTwoTWU
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public static void inputTwoTWU(int name1, int name2, int utility)
    {
        int index1 = getCandidateIndex(name1);
        int index2 = getCandidateIndex(name2);
        twoTWU.set(index1 * lOneTWU.size() + index2 - (index1 + 2) * (index1 + 1) / 2, utility);
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: localUpdate
     * Description:
     *
     *--------------------------------------------------------------------------------------
     */
    public final void localUpdate(double rho, double initPheromone)
    {
        pheromone = pheromone * (1.0 - rho) + initPheromone * rho;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: calculated
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final boolean calculated()
    {
        return calculate;
    }

    public static int getCandidateIndex(int name)
    {
        int lot = name;
        for (Pair<Integer, Integer> i : indexTable.get(lot))
        {
            if (i.getValue0() == name)
            {
                return i.getValue1();
            }
        }
        return (int)lOneTWU.size();
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: setCalculated
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final void setCalculated()
    {
        calculate = true;
    }

    public final Node selectNext(java.util.ArrayList<Integer> cItemset, double alpha, double beta, double q0, PTable pTable, double initPheromone) throws ParseException {
        if (cItemset.size() > 1)
        {
            positivePrue(cItemset, pTable);
        }

        if (finish())
        {
            return this;
        }

        java.util.ArrayList<Quartet<Pair<Integer, Node>, Double, Double, Double>> weightTable =
                new java.util.ArrayList<Quartet<Pair<Integer, Node>, Double, Double, Double>>(); //double: heuristic, double: pheromone, double: weight
        double pheromone;
        double totalWeight = 0.0;
        double maxWeight = 0.0;
        Pair<Integer, Node> iSelectedNode = null;

        //septian
        //System.out.println("Initialization.....");

        if (cItemset.size() == 0)
        {
            int remainNodesIndex = 0;
            for (var i : remainNodes)
            {
                int index = getCandidateIndex(i.getValue0());
                if (i.getValue1() != null)
                {
                    pheromone = i.getValue1().pheromone;
                }
                else
                {
                    pheromone = initPheromone;
                }

                //System.out.println("Pheromone : " + pheromone);

                weightTable.add(Quartet.with(remainNodes.get(remainNodesIndex), lOneTWU.get(index).twUtility, pheromone, 0.0));
                ++remainNodesIndex;
            }
        }
        else
        {
            int t1TWU = 0;
            int t2TWU = 0;
            java.util.LinkedList<Integer> no2TWU = new java.util.LinkedList<Integer>();

            int remainNodesIndex = 0;
            for (var i : remainNodes)
            {
                if (i.getValue1() != null)
                {
                    pheromone = i.getValue1().pheromone;
                }
                else
                {
                    pheromone = initPheromone;
                }
                int index1 = getCandidateIndex(name);
                int index2 = getCandidateIndex(i.getValue0());
                int oTWU = (int)lOneTWU.get(index2).twUtility;
                int indextTWU = index1 * lOneTWU.size() + index2 - (index1 + 2) * (index1 + 1) / 2;
                int tTWU = twoTWU.get(indextTWU);
                if (tTWU != 0)
                {
                    t1TWU += oTWU;
                    t2TWU += tTWU;
                    weightTable.add(Quartet.with(remainNodes.get(remainNodesIndex), (double)tTWU, pheromone, 0.0));
                }
                else
                {
                    no2TWU.addLast((int)weightTable.size());
                    weightTable.add(Quartet.with(remainNodes.get(remainNodesIndex), (double)oTWU, pheromone, 0.0));
                }
                ++remainNodesIndex;
            }

            if (no2TWU.size() != 0 && no2TWU.size() < remainNodes.size())
            {
                double a1TWU = (double)t1TWU / (double)(remainNodes.size() - no2TWU.size());
                double a2TWU = (double)t2TWU / (double)(remainNodes.size() - no2TWU.size());

                for (var i : no2TWU)
                {
                    //get<1>(weightTable.get(i)) = (get<1>(weightTable.get(i)) / a1TWU) * a2TWU;
                    //weightTable.get(i).getValue(1) = ((double)weightTable.get(i).getValue(1) / a1TWU) * a2TWU;
                    //weightTable.get(i).setAt1(((double)weightTable.get(i).getValue(1) / a1TWU) * a2TWU);
                    var newWeightTable = Quartet.with(weightTable.get(i).getValue0(),
                            ((double)weightTable.get(i).getValue(1) / a1TWU) * a2TWU,
                            weightTable.get(i).getValue2(), weightTable.get(i).getValue3());
                    //weightTable.get(i).setAt1(((double)weightTable.get(i).getValue(1) / a1TWU) * a2TWU);
                    weightTable.set(i, newWeightTable);
                }
            }
        }

        for (int indexweight = 0; indexweight < weightTable.size(); indexweight++)
        {
            //get<3>(i) = Math.pow(get<1>(i), alpha) * Math.pow(get<2>(i), beta);
            var i3 = Math.pow(weightTable.get(indexweight).getValue1(), alpha);
            var i32 = Math.pow(weightTable.get(indexweight).getValue2(), beta);
            var i3result = i3 * i32;
            //i.setAt3(i3result);
            //weightTable.setAt3(i3result);
            weightTable.set(indexweight, Quartet.with(weightTable.get(indexweight).getValue0(),
                    weightTable.get(indexweight).getValue1(), weightTable.get(indexweight).getValue2(),
                    i3result));
            //totalWeight += i.getValue3();
            totalWeight += weightTable.get(indexweight).getValue3();
            //System.out.println("weighTable "+ indexweight + " : " + weightTable.get(indexweight).getValue3());
            if (weightTable.get(indexweight).getValue3() > maxWeight)
            {
                maxWeight = weightTable.get(indexweight).getValue3();
                iSelectedNode = weightTable.get(indexweight).getValue0();
            }
        }
        //default_random_engine random = new default_random_engine((int)time(null));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String myDate = dtf.format(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(myDate);
        long millis = date.getTime();

        UniformRealDistribution dis = new UniformRealDistribution(0.0, 1.0);
        dis.reseedRandomGenerator(millis);

        //var test = dis.sample();

        if (dis.sample() >= q0)
        {
            //roulette wheel selection
            UniformRealDistribution dis2 = new UniformRealDistribution(0.0, totalWeight);
            dis2.reseedRandomGenerator(millis);
            double length = dis2.sample();
            //java.util.Iterator<Quartet<Pair<Integer, Node>, Double, Double, Double>> ball = weightTable.iterator();
            var ball =0;

            do{
                length -= weightTable.get(ball).getValue3();

                if (length <= 0.0) {
                    iSelectedNode = weightTable.get(ball).getValue0();
                } else {
                    ++ball;
                }
            } while (length > 0.0);

            /*
            int index = 0;
            var currentball = ball.next();
            do
            {
                //length -= get<3>(*ball);

                length -= currentball.getValue3();
                if (length <= 0.0)
                {
                    iSelectedNode = currentball.getValue0();
                }
                else
                {
                    currentball= ball.next();
                }

            } while (length > 0.0);
             */
        }

        if (iSelectedNode.getValue1() == null)
        {
            java.util.LinkedList<Integer> sFollowingNodes = new java.util.LinkedList<Integer>();
            var it = followingNodes.indexOf(iSelectedNode.getValue0());
            ++it;

            for (var i = it; i < followingNodes.size(); ++i)
            {
                sFollowingNodes.addLast(followingNodes.get(i));
            }

            //iSelectedNode.setAt1(new Node(this, iSelectedNode.getValue0(), sFollowingNodes, initPheromone));

            iSelectedNode = Pair.with(iSelectedNode.getValue0(),
                    new Node(this, iSelectedNode.getValue0(), sFollowingNodes, initPheromone));
        }

        cItemset.add(iSelectedNode.getValue0());
        setRemainNodes(iSelectedNode);
        return iSelectedNode.getValue1();
    }

    public void setRemainNodes(Pair<Integer,Node> iSelectedNode)
    {
        for (int i = 0; i < remainNodes.size(); i++)
        {
            if (remainNodes.get(i).getValue0() == iSelectedNode.getValue0())
            {
                remainNodes.set(i, iSelectedNode);
            }
        }
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: setRelatedTransactions
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final void setRelatedTransactions(java.util.LinkedList<Integer> transactions)
    {
        relatedTransactions = new java.util.LinkedList<Integer>(transactions);
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: getRelatedTransactions
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final java.util.LinkedList<Integer> getRelatedTransactions()
    {
        return relatedTransactions;
    }



    public static void recurisivePrune(Node node)
    {
        Node lPreviousNode = node.getLParentNode();
        lPreviousNode.deleteFollowingNode(node);
        if (node != null)
            node.dispose();
        if (lPreviousNode.getName() != -1 && lPreviousNode.finish())
        {
            recurisivePrune(lPreviousNode);
        }
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: initStartNode
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public static Node initStartNode(java.util.LinkedList<Integer> iFollowingNodes, double initPheromone)
    {
        Node.lStartNode = new Node(null, -1, iFollowingNodes, initPheromone);
        return Node.lStartNode;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: initTwoTWU
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public static void initTwoTWU(int nCandidate)
    {
        //due to positive prunning rule, we can set 0u for pair itemset which is never arrived
        //twoTWU.ensureCapacity((nCandidate * (nCandidate - 1)) / 2);
        twoTWU = ArrayHelper.resizeArray(twoTWU, (nCandidate * (nCandidate - 1)) / 2);
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: setLOneTWU
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public static void setLOneTWU(java.util.ArrayList<Transaction.oneTWU> ILOneTWU)
    {
        lOneTWU = ILOneTWU;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: setCandidateIndex
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public static void setCandidateIndex()
    {
        indexTable = ArrayHelper.resizePair(indexTable, sizeIndexTable);

        for (int i = 0; i < lOneTWU.size(); ++i)
        {
            int name = lOneTWU.get(i).name;
            indexTable.get(name % sizeIndexTable).addLast(Pair.with(name, i));
        }
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: globalUpdate
     * Description:
     *
     *--------------------------------------------------------------------------------------
     */
    public static void globalUpdate(java.util.ArrayList<Integer> itemset, int utility, double threshold)
    {
        //System.out.println("MASUK DONG GLOBAL UPDATE");
        Node lNode = Node.lStartNode;
        boolean find;

        for (int i : itemset)
        {
            find = false;
            for (var j : lNode.remainNodes)
            {
                Node lFNode = j.getValue1();
                if (lFNode != null)
                {
                    if (i == lFNode.getName())
                    {
                        find = true;
                        lNode = lFNode;
                        lNode.addPheromone(utility, threshold);
                        break;
                    }
                }
            }
            if (!find)
                break;
        }
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: getName
     * Description:
     *--------------------------------------------------------------------------------------
     */
    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
    //ORIGINAL LINE: int getName() const
    public final int getName()
    {
        return name;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: getPheromone
     * Description:
     *--------------------------------------------------------------------------------------
     */
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: double getPheromone() const
    public final double getPheromone()
    {
        return pheromone;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: clearFollowing
     * Description:
     *--------------------------------------------------------------------------------------
     */
    public final void clearRemainNodes()
    {
        remainNodes.clear();
        //System.out.println("size remain node after clear : " + remainNodes.size());
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: deleteFollowingNode
     * Description:
     *--------------------------------------------------------------------------------------
     */
    private void deleteFollowingNode(Node lNode)
    {
        //C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:

        /*
        System.out.println("remain Nodes before delete following ");

        for (var i : remainNodes) {
            if (i.getValue1() != null) {
                System.out.print(i.getValue0() + "," + i.getValue1() + "\t");
            } else {
                System.out.print(i.getValue0() + "," + "null" + "\t");
            }
            //System.out.println(" remainNodes->first : " + i.getValue0());
            //System.out.println(" remainNodes->second : " + i.getValue1());
            //cout << " remainNodes->first : " << i.first << endl;//System.out.println("size remain node after delete following : " + remainNodes.size());
        }
        System.out.println();
        */

        for (var i : remainNodes)
        {
            if (i.getValue0() == lNode.getName())
            {
                //C++ TO JAVA CONVERTER TODO TASK: There is no direct equivalent to the STL list 'erase' method in Java:
                remainNodes.remove(i);
                break;
            }
        }

        /*
        System.out.println("remain Nodes after delete following ");
        for (var i : remainNodes) {
            System.out.print(i.getValue0() + "," + i.getValue1() + "\t");
            //System.out.println(" remainNodes->first : " + i.getValue0());
            //System.out.println(" remainNodes->second : " + i.getValue1());
            //cout << " remainNodes->first : " << i.first << endl;//System.out.println("size remain node after delete following : " + remainNodes.size());
        }
        System.out.println();

         */

    }
    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.Node
     *      Method:  lib.Node :: getLParentNode
     * Description:
     *--------------------------------------------------------------------------------------
     */
    private Node    getLParentNode()
    {
        return lParentNode;
    }

    /*
     *--------------------------------------------------------------------------------------
     *       Class:  lib.PTable
     *      Method:  lib.PTable :: addPheromone
     * Description:
     *--------------------------------------------------------------------------------------
     */
    private void addPheromone(int utility, double threshold)
    {
        pheromone += (double)utility / threshold;
    }
}
