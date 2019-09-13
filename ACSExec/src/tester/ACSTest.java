package tester;

import lib.ACS;
import lib.Node;
import lib.Transaction;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

public class ACSTest {
    public void test01()
    {
        double threshold = 0.14;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        final int maxIteration = 10000;
        final int interval = 100;

        Transaction tran = new Transaction(threshold);
        try {
            //tran.readData("D:\\Latihan\\ACSTry\\mysample_new.txt");
            tran.readData("D:\\data\\mushroom_utility_15_9.txt");
            //tran.readData("E:\\Project\\acscppjava\\mushroom_utility_15_9.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Node sNode = Node.initStartNode(tran.oneTWUsName, pheromone);
        Node.initTwoTWU(tran.oneTWUsName.size());
        Node.setLOneTWU(tran.oneTWUs);
        Node.setCandidateIndex();

        ACS ants = new ACS(size, tran, sNode, pheromone, rho, alpha, beta, q0);

        while (!sNode.finish() && ants.getNIteration() <= maxIteration){
            try {
                ants.runIteration();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.print("\rIteration:\t" + ants.getNIteration() + " / " + maxIteration + "\t\t " + "NUM of HUIs:\t" + ants.huis.size());

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
}
