package tester;

import lib.Transaction;

import javax.sound.midi.SysexMessage;
import java.io.IOException;

public class TransactionTest {
    public void Test01(){
        double threshold = 0;
        double pheromone = 1.0;
        int size = 20;
        double rho = 0.4;
        double alpha = 1.0;
        double beta = 3.0;
        double q0 = 0.8;

        Transaction tran = new Transaction(threshold);
        System.out.println("Total Utility : " + tran.totalUtility);
        System.out.println("Threshold Ratio : " + tran.thresholdRatio);

        System.out.println("===================================================");

        Transaction tran2 = new Transaction(1);
        System.out.println("Total Utility 2 : " + tran2.totalUtility);
        System.out.println("Threshold Ratio 2 : " + tran2.thresholdRatio);
    }

    public void Test02(){
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
            System.out.println("Total Utility : " + tran.totalUtility);
            System.out.println("\n***** data *****");
            for(var data : tran.data){
                System.out.println("data.utility : " + data.utility);
                System.out.println("data.items : ");

                for (var item : data.items)
                {
                    System.out.println("\t item->first : " + item.getValue0());
                    System.out.println("\t item->second : " + item.getValue1());
                }
            }

            System.out.println("\nThreshold : " + tran.threshold );

            System.out.println("\n***** Invert Table ***** ");
            int index = 0;
            for(var invertTables : tran.invertTable){
                System.out.println("invert Tables " + index);

                for (var invertTable : invertTables)
                {
                    System.out.print(invertTable + ",");
                }
                System.out.println();
                index++;
            }

            System.out.println("\n***** ONETWUS ***** ");
            for(var oneTWU : tran.oneTWUs){
                System.out.println("oneTWU " + oneTWU.name + " : " + oneTWU.twUtility);
            }

            System.out.println("\n***** oneTWUsName ***** ");
            for(var oneTWUName : tran.oneTWUsName){
                System.out.println(oneTWUName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
