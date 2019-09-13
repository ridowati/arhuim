import lib.ACS;
import lib.Node;
import lib.PTable;
import lib.Transaction;
import tester.ACSTest;
import tester.NodeTest;
import tester.PTableTest;
import tester.TransactionTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

public class Main {
    public static void main(String[] argv){
        System.out.println("test");

        //test02();
        //test03();
        //test04();
        //test05();
        //test07();
        //test08();
        //test09();
        //test10();
        /*
        try {
            testMain();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
        //testTransaction();
        //testPTable();
        //testNode();
        testACS();
    }

    static void testACS()
    {
        ACSTest acstest = new ACSTest();
        acstest.test01();
    }

    static void testNode(){
        var nodetest = new NodeTest();
        //nodetest.Test01();
        //nodetest.Test02();
        //nodetest.Test03();
        //nodetest.Test04();
        //nodetest.Test05();
        //nodetest.Test06();
        //nodetest.Test07();
        //nodetest.Test08();
        //nodetest.Test09();
        //nodetest.Test10();
        //nodetest.Test11();
        //nodetest.Test12();
        //nodetest.Test13();
        nodetest.Test14();
    }

    static void testPTable()
    {
        var ptest = new PTableTest();
        //ptest.Test01();
        //ptest.Test02();
        //ptest.Test03();
        ptest.Test04();
    }

    static void testTransaction()
    {
        TransactionTest test = new TransactionTest();
        //test.Test01();
        test.Test02();
    }



    static void testMain() throws IOException, ParseException {
        double threshold =0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        final int maxIteration = 10000;
        final int interval = 100;
        Transaction tran = new Transaction(threshold);
        //tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        //tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        tran.readData("D:\\data\\mysample_new.txt");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        ACS ants = new ACS(size, tran, sNode, pheromone, rho, alpha, beta, q0);

        while (!sNode.finish() && ants.getNIteration() <= maxIteration){
            ants.runIteration();

            if (ants.getNIteration() % interval == 0) {
                System.out.println("\rIteration:\t" + ants.getNIteration() + " / " + maxIteration + "\t\t " + "NUM of HUIs:\t" + ants.huis.size());
			    //*output << "\rIteration:\t" << ants.getNIteration() << " / " << maxIteration << "\t\t "<< "NUM of HUIs:\t" << ants.huis.size() << endl;
            }
        }

        if (sNode.finish())
            System.out.println("\nAlgorithm terminated on generation " + ants.getNIteration() + " (ALL HUIs discovered)");
        else
            System.out.println("\nAlgorithm terminated on maximum generation");

        var huis = ants.huis;
        if (huis.size() > 0) {
            System.out.println("\n" + huis.size() + " High-Utility Itemsets discovered");
            System.out.println( "Last HUI found on generation: " + ants.getLastFind() + "\n");

            for (var i : huis){
                System.out.print("[");
                var itemp = i.getValue0();
                Collections.sort(itemp);
                for (var j : itemp) System.out.print(j + " ");
                System.out.println("]");

                System.out.println("Utility : " + i.getValue1());
            }
        }
        Node.deleteStartNode();
    }

    static void test10() throws IOException, ParseException {
        double threshold =0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        ACS ants = new ACS(size, tran, sNode, pheromone, rho, alpha, beta, q0);
        //sNode.finish() && ants.getNIteration() <= maxIteration
        //System.out.println("sNode.finis : " + sNode.finish());
        //System.out.println("ants.getNIteration : " + ants.getNIteration());

        ants.runIteration();

        //System.out.println("sNode.finis : " + sNode.finish());
        //System.out.println("ants.getNIteration : " + ants.getNIteration());
    }

    static void test09() throws IOException {
        double threshold =0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        ACS ants = new ACS(size, tran, sNode, pheromone, rho, alpha, beta, q0);
        System.out.println("nIteration : " + ants.nIteration);
        System.out.println("populationSize : " + ants.populationSize);
        System.out.println("initPheromone : " + ants.initPheromone);
        System.out.println("rho : " + ants.rho);
        System.out.println("alpha : " + ants.beta);
        System.out.println("beta : " + ants.beta);
        System.out.println("q0 : " + ants.q0);
    }

    static void test08() throws IOException {
        double threshold =0;
        double pheromone = 1.0;


        Transaction tran = new Transaction(threshold);
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        int index = 0;
        for (var i : Node.indexTable)
        {
            System.out.println("indext table " + index + " : ");
            //cout << "indext table " << index << " : " << endl;
            for (var j : i)
            {
                System.out.print(j.getValue0() + ", " + j.getValue1());
                //cout << j.first << ", " << j.second;
            }
            System.out.println();
            index++;
        }
    }

    static void test07() throws IOException {
        double threshold = 0;
        Transaction tran = new Transaction(threshold);
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");

        for (var i : tran.oneTWUs){
            System.out.println("lOneTWU.name " + i.name);
            System.out.println("lOneTWU.twUtility " + i.twUtility);
        }

        /*
        int index = 0;
        for (var i : tran.invertTable)
        {
            //cout << "invert table " << index << " : " << endl;
            System.out.println("invert table " + index + " : ");
            for (var j : i)
            {
                //cout << j << ", ";
                System.out.print(j + ", ");
            }
            System.out.println();
            index ++;
        }
        */

        /*
        for (var i : tran.data){
            System.out.println("data.utility " + i.utility);
            System.out.println("list items : ");

            int index = 0;
            for (var j : i.items){
                System.out.println( "key " + index + " : "+ j.getValue0());
                System.out.println("value " + index  + " : "+ j.getValue1());
                index++;

            }

            System.out.println( "***********************************************");
        }
         */
    }

    static void test05() throws IOException {
        double threshold = 0;
        double pheromone = 1.0;

        Transaction tran = new Transaction(threshold);
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);

        for (var i : Node.lOneTWU){
            System.out.println("lOneTWU.name " + i.name);
            System.out.println("lOneTWU.twUtility " + i.twUtility);
        }
    }

    static void test06() throws IOException {
        double threshold = 0;
        double pheromone = 1.0;

        Transaction tran = new Transaction(threshold);
        System.out.println("Before read data");
        for (var i : tran.oneTWUs){
            System.out.println("lOneTWU.name " + i.name);
            System.out.println("lOneTWU.twUtility " + i.twUtility);
        }
        System.out.println("**********************************************");
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        System.out.println("after read data");
        for (var i : tran.oneTWUs){
            System.out.println("lOneTWU.name " + i.name);
            System.out.println("lOneTWU.twUtility " + i.twUtility);
        }
        System.out.println("**********************************************");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        System.out.println("init start node");
        for (var i : tran.oneTWUs){
            System.out.println("lOneTWU.name " + i.name);
            System.out.println("lOneTWU.twUtility " + i.twUtility);
        }
        System.out.println("**********************************************");
        Node.initTwoTWU(tran.oneTWUsName.size());
        System.out.println("init two TWU");
        for (var i : tran.oneTWUs){
            System.out.println("lOneTWU.name " + i.name);
            System.out.println("lOneTWU.twUtility " + i.twUtility);
        }
        System.out.println("**********************************************");
        Node.setLOneTWU(tran.oneTWUs);
        System.out.println("setlonetwu");
        for (var i : tran.oneTWUs){
            System.out.println("lOneTWU.name " + i.name);
            System.out.println("lOneTWU.twUtility " + i.twUtility);
        }
        System.out.println("**********************************************");
    }



    static void test04() throws IOException {
        double threshold = 0;
        double pheromone = 1.0;
        Transaction tran = new Transaction(threshold);
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());

        System.out.println("size twoTWU : " + Node.twoTWU.size());
    }

    static void test03() throws IOException {
        double threshold = 0;
        double pheromone = 1.0;
        Transaction tran = new Transaction(threshold);
        tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);

        System.out.println("name : " + sNode.getName());
        System.out.println("pheromone : " + sNode.getPheromone());
    }

    static void test02() throws IOException {
        double threshold = 0;
        Transaction tran = new Transaction(threshold);
       // tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
        tran.readData("D:\\data\\mysample_new.txt");
        System.out.println("size oneTWUs : " + tran.oneTWUs.size());

        for (var i : tran.oneTWUs){
            System.out.println( "oneTwo.name " + i.name );
            System.out.println( "onwTwo.twUtility " + i.twUtility);
        }

        for(var g : tran.oneTWUsName){
            System.out.print("\t" + g);
        }
        System.out.println();

        System.out.println("threshold : " + tran.getThreshold());
    }

    static void test01()
    {
        double threshold = 1;
        Transaction tran = new Transaction(threshold);

        System.out.println("totalUtility : " + tran.totalUtility);
        System.out.println("thresholdRatio : " + tran.thresholdRatio);

        //cout << "totalUtility : " << tran.totalUtility << endl;
        //cout << "thresholdRatio : " << tran.thresholdRatio << endl;
    }

}
