package tester;

import helper.ArrayHelper;
import lib.Node;
import lib.PTable;
import lib.Transaction;

import java.io.IOException;
import java.sql.Array;
import java.text.ParseException;
import java.util.ArrayList;

public class NodeTest {
    public void Test01()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        int iname = -1;
        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = new Node(null, iname, tran.oneTWUsName, pheromone);

        if (node.lParentNode == null)
        {
            System.out.println("lParent Node is null : " + node.lParentNode);
        }

        System.out.println("node.Name : " + node.name);
        System.out.print("node.followingNodes : ");
        for (var followingNode : node.followingNodes)
        {
            System.out.print(followingNode + ", ");
        }
        System.out.println();

        System.out.println("pheromone : " + node.pheromone);
        System.out.println("maxCheckPTable : " + node.maxCheckPTable);
        System.out.println("calculate : " + node.calculate);

        System.out.print("node.remainNodes : ");
        for (var remainNode : node.remainNodes)
        {
            System.out.print(remainNode.getValue0() + "," + remainNode.getValue1() + "\t");
        }
        System.out.println();

        node.name = 3;
        System.out.println("iName : " + iname);
        iname = 0;
        System.out.println("node.Name : " + node.name);
    }

    public void Test02()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = Node.initStartNode(tran.oneTWUsName, pheromone);

        if (node.lParentNode == null)
        {
            System.out.println("lParent Node is null : " + node.lParentNode);
        }

        System.out.println("node.Name : " + node.name);
        System.out.print("node.followingNodes : ");
        for (var followingNode : node.followingNodes)
        {
            System.out.print(followingNode + ", ");
        }
        System.out.println();

        System.out.println("pheromone : " + node.pheromone);
        System.out.println("maxCheckPTable : " + node.maxCheckPTable);
        System.out.println("calculate : " + node.calculate);

        System.out.print("node.remainNodes : ");
        for (var remainNode : node.remainNodes)
        {
            System.out.print(remainNode.getValue0() + "," + remainNode.getValue1() + "\t");
        }
        System.out.println();

    }

    public void Test03()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());

        System.out.println( "\n***** twoTWU ***** ");
        for (var tTWU : Node.twoTWU)
        {
            System.out.print(tTWU + ",");
        }
        System.out.println();

        System.out.println("size twoTWU : " + Node.twoTWU.size());
    }

    public void Test04()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);

        System.out.println("\n***** ONETWUS ***** ");
        for(var oneTWU : tran.oneTWUs){
            System.out.println("oneTWU " + oneTWU.name + " : " + oneTWU.twUtility);
        }

        System.out.println("\n***** LOneTWUS ***** ");
        for(var loneTWU : Node.lOneTWU){
            System.out.println("loneTWU " + loneTWU.name + " : " + loneTWU.twUtility);
        }

        tran.oneTWUs.get(0).name = 333;
        Transaction.oneTWU temptwu = new Transaction.oneTWU();
        temptwu.name = 555;
        temptwu.twUtility = 55;

        Transaction.oneTWU temptwu2 = new Transaction.oneTWU();
        temptwu2.name = 444;
        temptwu2.twUtility = 44;
        Node.lOneTWU.add(temptwu);
        tran.oneTWUs.add(temptwu2);

        System.out.println("\n***** ONETWUS ***** ");
        for(var oneTWU : tran.oneTWUs){
            System.out.println("oneTWU " + oneTWU.name + " : " + oneTWU.twUtility );
        }

        System.out.println("\n***** LOneTWUS ***** ");
        for(var loneTWU : Node.lOneTWU){
            System.out.println("loneTWU " + loneTWU.name + " : " + loneTWU.twUtility);
        }
    }

    public void Test05()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        int index = 0;
        System.out.println("index Table size : " + Node.indexTable.size());
        for (var i : Node.indexTable)
        {
            System.out.println("indext table " + index + " : " );
            for (var j : i)
            {
                System.out.print(j.getValue0() + "," + j.getValue1());
            }
            System.out.println();
            index++;
        }
    }

    public void Test06()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);

        System.out.print("node.remainNodes : ");
        for (var remainNode : sNode.remainNodes)
        {
            System.out.print(remainNode.getValue0() + "," + remainNode.getValue1() + "\t");
        }
        System.out.println();

        System.out.println("is finish : " + sNode.finish());

        sNode.remainNodes.clear();
        System.out.println("is finish : " + sNode.finish());
    }

    public void Test07()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        int iname = -1;
        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = new Node(null, iname, tran.oneTWUsName, pheromone);


        System.out.println("node.Name : " + node.name);
        System.out.println("node.Name : " + node.getName());
    }

    public void Test08()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        int iname = -1;
        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = new Node(null, iname, tran.oneTWUsName, pheromone);


        System.out.println("node.pheromone : " + node.pheromone);
        System.out.println("node.pheromone : " + node.getPheromone());
    }

    public void Test09()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        int iname = -1;
        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = new Node(null, iname, tran.oneTWUsName, pheromone);

        PTable ptable = new PTable();
        ArrayList<Integer> itemset1 = new ArrayList<>();
        ArrayList<Integer> itemset2 = new ArrayList<>();
        itemset1.add(5);
        itemset1.add(3);
        itemset1.add(10);
        ptable.insertRecord(itemset1);
        itemset2.add(15);
        itemset2.add(10);
        itemset2.add(17);
        itemset2.add(16);
        ptable.insertRecord(itemset2);

        ArrayList<Integer> itemtest1 = new ArrayList<>();
        ArrayList<Integer> itemtest2 = new ArrayList<>();
        ArrayList<Integer> itemtest3 = new ArrayList<>();

        itemtest1.add(15);
        itemtest1.add(10);
        itemtest1.add(17);

        itemtest2.add(15);
        itemtest2.add(10);
        itemtest2.add(17);
        itemtest2.add(16);
        itemtest2.add(21);

        itemtest3.add(15);
        itemtest3.add(10);
        itemtest3.add(18);

        System.out.println("maxCheckPTable : " + node.maxCheckPTable);
        System.out.print("remainNodes : ");
        for (var remainNode : node.remainNodes)
        {
            System.out.print(remainNode.getValue0() + "," + remainNode.getValue1() + "\t");
        }
        System.out.println();

        node.positivePrue(itemtest1, ptable);
        System.out.println("maxCheckPTable : " + node.maxCheckPTable);
        System.out.print("remainNodes : ");
        for (var remainNode : node.remainNodes)
        {
            System.out.print(remainNode.getValue0() + "," + remainNode.getValue1() + "\t");
        }
        System.out.println();

        ptable.insertRecord(itemset2);
        node.positivePrue(itemtest2, ptable);
        System.out.println("maxCheckPTable : " + node.maxCheckPTable);
        System.out.print("remainNodes : ");
        for (var remainNode : node.remainNodes)
        {
            System.out.print(remainNode.getValue0() + "," + remainNode.getValue1() + "\t");
        }
        System.out.println();

        node.positivePrue(itemtest3, ptable);
        System.out.println("maxCheckPTable : " + node.maxCheckPTable);
        System.out.print("remainNodes : ");
        for (var remainNode : node.remainNodes)
        {
            System.out.print(remainNode.getValue0() + "," + remainNode.getValue1() + "\t");
        }
        System.out.println();
    }

    public void Test10()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node node = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        System.out.println("candidate index 0 : " + Node.getCandidateIndex(0) );
        System.out.println("candidate index 1 : " + Node.getCandidateIndex(1) );
        System.out.println("candidate index 2 : " + Node.getCandidateIndex(2) );
        System.out.println("candidate index 10 : " + Node.getCandidateIndex(10) );
    }

    public void Test11()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        PTable pTable = new PTable();
        Node lSNode;
        Node lCNode;
        Node lParentNode;
        ArrayList<Integer> cItemset = new ArrayList<>();
        cItemset.clear();

        lSNode = sNode;
        lCNode = lSNode;

        double initPheromone = pheromone;
        while (!lCNode.finish()) {
            System.out.print("Citemset : ");
            for (var citem : cItemset)
            {
                System.out.print(citem + ", ");
            }
            System.out.println();

            System.out.println("name : " + lCNode.name );
            System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
            System.out.println("pheromone : " + lCNode.pheromone);
            //lCNode->lParentNode;
            if ((lCNode.lParentNode) != null){
                System.out.println("Parent Node : " + (lCNode.lParentNode).name);
            } else {
                System.out.println("Parent Node : null");
            }

            System.out.print("followingNodes : ");
            for (var followingNode : lCNode.followingNodes){
                System.out.print(followingNode + ", ");
            }
            System.out.println();

            System.out.print("remainNodes : ");
            for (var remainNode : lCNode.remainNodes)
            {
                if (remainNode.getValue1() == null){
                    System.out.print(remainNode.getValue0() + ",null\t");
                } else
                {
                    System.out.print(remainNode.getValue0() + ",object\t");
                }
            }
            System.out.println();

            System.out.print("relatedTransactions : ");
            for (var relatedTransaction : lCNode.relatedTransactions){
                System.out.print(relatedTransaction + ", ");
            }
            System.out.println();

            System.out.println("calculate : " + lCNode.calculate + "\n");

            try {
                lCNode = lCNode.selectNext(cItemset, alpha, beta, q0, pTable, initPheromone);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Citemset : ");
        for (var citem : cItemset)
        {
            System.out.print(citem + ", ");
        }
        System.out.println();

        System.out.println("name : " + lCNode.name );
        System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
        System.out.println("pheromone : " + lCNode.pheromone);
        //lCNode->lParentNode;
        if ((lCNode.lParentNode) != null){
            System.out.println("Parent Node : " + (lCNode.lParentNode).name);
        } else {
            System.out.println("Parent Node : null");
        }

        System.out.print("followingNodes : ");
        for (var followingNode : lCNode.followingNodes){
            System.out.print(followingNode + ", ");
        }
        System.out.println();

        System.out.print("remainNodes : ");
        for (var remainNode : lCNode.remainNodes)
        {
            if (remainNode.getValue1() == null){
                System.out.print(remainNode.getValue0() + ",null\t");
            } else
            {
                System.out.print(remainNode.getValue0() + ",object\t");
            }
        }
        System.out.println();

        System.out.print("relatedTransactions : ");
        for (var relatedTransaction : lCNode.relatedTransactions){
            System.out.print(relatedTransaction + ", ");
        }
        System.out.println();

        System.out.println("calculate : " + lCNode.calculate + "\n");
    }

    public void Test12()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        PTable pTable = new PTable();
        Node lSNode;
        Node lCNode;
        Node lParentNode;
        ArrayList<Integer> cItemset = new ArrayList<>();
        cItemset.clear();

        lSNode = sNode;
        lCNode = lSNode;

        double initPheromone = pheromone;
        int populationSize = size;
        for (int i = 0; i < populationSize; ++i) {
            System.out.println("*************** Population " + i + " ****************");
            while (!lCNode.finish()) {
                System.out.print("Citemset : ");
                for (var citem : cItemset) {
                    System.out.print(citem + ", ");
                }
                System.out.println();

                System.out.println("name : " + lCNode.name);
                System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
                System.out.println("pheromone : " + lCNode.pheromone);
                //lCNode->lParentNode;
                if ((lCNode.lParentNode) != null) {
                    System.out.println("Parent Node : " + (lCNode.lParentNode).name);
                } else {
                    System.out.println("Parent Node : null");
                }

                System.out.print("followingNodes : ");
                for (var followingNode : lCNode.followingNodes) {
                    System.out.print(followingNode + ", ");
                }
                System.out.println();

                System.out.print("remainNodes : ");
                for (var remainNode : lCNode.remainNodes) {
                    if (remainNode.getValue1() == null) {
                        System.out.print(remainNode.getValue0() + ",null\t");
                    } else {
                        System.out.print(remainNode.getValue0() + ",object\t");
                    }
                }
                System.out.println();

                System.out.print("relatedTransactions : ");
                for (var relatedTransaction : lCNode.relatedTransactions) {
                    System.out.print(relatedTransaction + ", ");
                }
                System.out.println();

                System.out.println("calculate : " + lCNode.calculate + "\n");

                try {
                    lCNode = lCNode.selectNext(cItemset, alpha, beta, q0, pTable, initPheromone);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            System.out.print("Citemset : ");
            for (var citem : cItemset) {
                System.out.print(citem + ", ");
            }
            System.out.println();

            System.out.println("name : " + lCNode.name);
            System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
            System.out.println("pheromone : " + lCNode.pheromone);
            //lCNode->lParentNode;
            if ((lCNode.lParentNode) != null) {
                System.out.println("Parent Node : " + (lCNode.lParentNode).name);
            } else {
                System.out.println("Parent Node : null");
            }

            System.out.print("followingNodes : ");
            for (var followingNode : lCNode.followingNodes) {
                System.out.print(followingNode + ", ");
            }
            System.out.println();

            System.out.print("remainNodes : ");
            for (var remainNode : lCNode.remainNodes) {
                if (remainNode.getValue1() == null) {
                    System.out.print(remainNode.getValue0() + ",null\t");
                } else {
                    System.out.print(remainNode.getValue0() + ",object\t");
                }
            }
            System.out.println();

            System.out.print("relatedTransactions : ");
            for (var relatedTransaction : lCNode.relatedTransactions) {
                System.out.print(relatedTransaction + ", ");
            }
            System.out.println();

            System.out.println("calculate : " + lCNode.calculate + "\n");
        }
    }

    public void Test13()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        PTable pTable = new PTable();
        Node lSNode;
        Node lCNode;
        Node lParentNode;
        ArrayList<Integer> cItemset = new ArrayList<>();
        cItemset.clear();

        lSNode = sNode;
        lCNode = lSNode;

        double initPheromone = pheromone;
        int populationSize = size;
        for (int i = 0; i < populationSize; ++i) {
            System.out.println("*************** Population " + i + " ****************");
            while (!lCNode.finish()) {
                System.out.print("Citemset : ");
                for (var citem : cItemset) {
                    System.out.print(citem + ", ");
                }
                System.out.println();

                System.out.println("name : " + lCNode.name);
                System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
                System.out.println("pheromone : " + lCNode.pheromone);
                //lCNode->lParentNode;
                if ((lCNode.lParentNode) != null) {
                    System.out.println("Parent Node : " + (lCNode.lParentNode).name);
                } else {
                    System.out.println("Parent Node : null");
                }

                System.out.print("followingNodes : ");
                for (var followingNode : lCNode.followingNodes) {
                    System.out.print(followingNode + ", ");
                }
                System.out.println();

                System.out.print("remainNodes : ");
                for (var remainNode : lCNode.remainNodes) {
                    if (remainNode.getValue1() == null) {
                        System.out.print(remainNode.getValue0() + ",null\t");
                    } else {
                        System.out.print(remainNode.getValue0() + ",object\t");
                    }
                }
                System.out.println();

                System.out.print("relatedTransactions : ");
                for (var relatedTransaction : lCNode.relatedTransactions) {
                    System.out.print(relatedTransaction + ", ");
                }
                System.out.println();

                System.out.println("calculate : " + lCNode.calculate + "\n");

                try {
                    lCNode = lCNode.selectNext(cItemset, alpha, beta, q0, pTable, initPheromone);
                    lCNode.localUpdate(rho, initPheromone);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (lCNode.finish())
                {
                    System.out.print("Citemset : ");
                    for (var citem : cItemset) {
                        System.out.print(citem + ", ");
                    }
                    System.out.println();

                    System.out.println("name : " + lCNode.name);
                    System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
                    System.out.println("pheromone : " + lCNode.pheromone);
                    //lCNode->lParentNode;
                    if ((lCNode.lParentNode) != null) {
                        System.out.println("Parent Node : " + (lCNode.lParentNode).name);
                    } else {
                        System.out.println("Parent Node : null");
                    }

                    System.out.print("followingNodes : ");
                    for (var followingNode : lCNode.followingNodes) {
                        System.out.print(followingNode + ", ");
                    }
                    System.out.println();

                    System.out.print("remainNodes : ");
                    for (var remainNode : lCNode.remainNodes) {
                        if (remainNode.getValue1() == null) {
                            System.out.print(remainNode.getValue0() + ",null\t");
                        } else {
                            System.out.print(remainNode.getValue0() + ",object\t");
                        }
                    }
                    System.out.println();

                    System.out.print("relatedTransactions : ");
                    for (var relatedTransaction : lCNode.relatedTransactions) {
                        System.out.print(relatedTransaction + ", ");
                    }
                    System.out.println();

                    System.out.println("calculate : " + lCNode.calculate + "\n");
                }
            }


        }
    }

    public void Test14()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        PTable pTable = new PTable();
        Node lSNode;
        Node lCNode;
        Node lParentNode;
        ArrayList<Integer> cItemset = new ArrayList<>();
        java.util.LinkedList<Integer> relatedTransactions = new java.util.LinkedList<Integer>();
        lSNode = sNode;


        double initPheromone = pheromone;
        int populationSize = size;
        for (int i = 0; i < populationSize; ++i) {
            lCNode = lSNode;
            cItemset.clear();
            relatedTransactions.clear();
            System.out.println("*************** Population " + i + " ****************");
            while (!lCNode.finish()) {
                System.out.print("Citemset : ");
                for (var citem : cItemset) {
                    System.out.print(citem + ", ");
                }
                System.out.println();

                System.out.println("name : " + lCNode.name);
                System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
                System.out.println("pheromone : " + lCNode.pheromone);
                //lCNode->lParentNode;
                if ((lCNode.lParentNode) != null) {
                    System.out.println("Parent Node : " + (lCNode.lParentNode).name);
                } else {
                    System.out.println("Parent Node : null");
                }

                System.out.print("followingNodes : ");
                for (var followingNode : lCNode.followingNodes) {
                    System.out.print(followingNode + ", ");
                }
                System.out.println();

                System.out.print("remainNodes : ");
                for (var remainNode : lCNode.remainNodes) {
                    if (remainNode.getValue1() == null) {
                        System.out.print(remainNode.getValue0() + ",null\t");
                    } else {
                        System.out.print(remainNode.getValue0() + ",object\t");
                    }
                }
                System.out.println();

                System.out.print("relatedTransactions : ");
                for (var relatedTransaction : lCNode.relatedTransactions) {
                    System.out.print(relatedTransaction + ", ");
                }
                System.out.println();

                System.out.println("calculate : " + lCNode.calculate + "\n");

                try {
                    lCNode = lCNode.selectNext(cItemset, alpha, beta, q0, pTable, initPheromone);
                    //lCNode.localUpdate(rho, initPheromone);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*
                if (!lCNode.calculated())
                {

                } else {
                    relatedTransactions = new java.util.LinkedList<Integer>(lCNode.getRelatedTransactions());
                }
                 */

                if (lCNode.finish())
                {
                    System.out.print("Citemset : ");
                    for (var citem : cItemset) {
                        System.out.print(citem + ", ");
                    }
                    System.out.println();

                    System.out.println("name : " + lCNode.name);
                    System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
                    System.out.println("pheromone : " + lCNode.pheromone);
                    //lCNode->lParentNode;
                    if ((lCNode.lParentNode) != null) {
                        System.out.println("Parent Node : " + (lCNode.lParentNode).name);
                    } else {
                        System.out.println("Parent Node : null");
                    }

                    System.out.print("followingNodes : ");
                    for (var followingNode : lCNode.followingNodes) {
                        System.out.print(followingNode + ", ");
                    }
                    System.out.println();

                    System.out.print("remainNodes : ");
                    for (var remainNode : lCNode.remainNodes) {
                        if (remainNode.getValue1() == null) {
                            System.out.print(remainNode.getValue0() + ",null\t");
                        } else {
                            System.out.print(remainNode.getValue0() + ",object\t");
                        }
                    }
                    System.out.println();

                    System.out.print("relatedTransactions : ");
                    for (var relatedTransaction : lCNode.relatedTransactions) {
                        System.out.print(relatedTransaction + ", ");
                    }
                    System.out.println();

                    System.out.println("calculate : " + lCNode.calculate + "\n");
                }
            } // end while

            if (lCNode.getName() == -1) break;
            Node.recurisivePrune(lCNode);

        } //end for
    }

    public void Test15()
    {
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        try {
            tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        PTable pTable = new PTable();
        Node lSNode;
        Node lCNode;
        Node lParentNode;
        ArrayList<Integer> cItemset = new ArrayList<>();
        cItemset.clear();

        lSNode = sNode;
        lCNode = lSNode;

        double initPheromone = pheromone;
        int populationSize = size;
        for (int i = 0; i < populationSize; ++i) {
            System.out.println("*************** Population " + i + " ****************");
            while (!lCNode.finish()) {
                try {
                    lCNode = lCNode.selectNext(cItemset, alpha, beta, q0, pTable, initPheromone);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            System.out.print("Citemset : ");
            for (var citem : cItemset) {
                System.out.print(citem + ", ");
            }
            System.out.println();

            System.out.println("name : " + lCNode.name);
            System.out.println("maxCheckPTable : " + lCNode.maxCheckPTable);
            System.out.println("pheromone : " + lCNode.pheromone);
            //lCNode->lParentNode;
            if ((lCNode.lParentNode) != null) {
                System.out.println("Parent Node : " + (lCNode.lParentNode).name);
            } else {
                System.out.println("Parent Node : null");
            }

            System.out.print("followingNodes : ");
            for (var followingNode : lCNode.followingNodes) {
                System.out.print(followingNode + ", ");
            }
            System.out.println();

            System.out.print("remainNodes : ");
            for (var remainNode : lCNode.remainNodes) {
                if (remainNode.getValue1() == null) {
                    System.out.print(remainNode.getValue0() + ",null\t");
                } else {
                    System.out.print(remainNode.getValue0() + ",object\t");
                }
            }
            System.out.println();

            System.out.print("relatedTransactions : ");
            for (var relatedTransaction : lCNode.relatedTransactions) {
                System.out.print(relatedTransaction + ", ");
            }
            System.out.println();

            System.out.println("calculate : " + lCNode.calculate + "\n");
        }
    }
}
