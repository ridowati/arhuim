package tester;

import lib.PTable;

import java.util.ArrayList;

public class PTableTest {

    public void Test01() {
        PTable ptable = new PTable();
    }

    public void  Test02()
    {
        PTable ptable = new PTable();
        java.util.ArrayList<Integer> itemset1 = new java.util.ArrayList<Integer>();
        java.util.ArrayList<Integer> itemset2 = new java.util.ArrayList<Integer>();
        itemset1.add(5);
        itemset1.add(3);
        itemset1.add(10);
        ptable.insertRecord(itemset1);
        System.out.print("\nItemset 1 : ");
        for (var itemset : itemset1)
        {
            System.out.print(itemset + ", ");
        }

        int index = 0;
        for (var tables : ptable.table)
        {
            System.out.print("\nTables " + index++ + " : ");
            for (var table : tables)
            {
                System.out.print(table + ", ");
            }
        }

        itemset2.add(15);
        itemset2.add(10);
        itemset2.add(17);
        itemset2.add(16);
        ptable.insertRecord(itemset2);
        System.out.print("\n\nItemset 2 : ");
        for (var itemset : itemset2)
        {
            System.out.print(itemset + ", ");
        }
        index = 0;
        for (var tables : ptable.table)
        {
            System.out.print("\nTables " + index++ + " : ");
            for (var table : tables)
            {
                System.out.print(table + ", ");
            }
        }
    }

    public void Test03()
    {
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

        System.out.println("table size : " + ptable.table.size());
        System.out.println("check 1 : " + ptable.checkTable(itemtest1,1));
        System.out.println("check 2 : " + ptable.checkTable(itemtest2,1));
        System.out.println("check 3 : " + ptable.checkTable(itemtest3,1));

        System.out.print("\n\nItemtest 1 : ");

        for (var item : itemtest1)
        {
            System.out.print(item + ", ");
        }

        System.out.print("\n\nItemtest 2 : ");
        for (var item : itemtest2)
        {
            System.out.print(item + ", ");
        }

        System.out.print("\n\nItemtest 3 : ");
        for (var item : itemtest3)
        {
            System.out.print(item + ", ");
        }
    }

    public void Test04()
    {
        PTable ptable = new PTable();
        System.out.println("max version : " + ptable.maxVersion());

        ArrayList<Integer> itemset1 = new ArrayList<>();
        ArrayList<Integer> itemset2 = new ArrayList<>();
        itemset1.add(5);
        itemset1.add(3);
        itemset1.add(10);
        ptable.insertRecord(itemset1);
        System.out.println("max version : " + ptable.maxVersion());

        itemset2.add(15);
        itemset2.add(10);
        itemset2.add(17);
        itemset2.add(16);
        ptable.insertRecord(itemset2);
        System.out.println("max version : " + ptable.maxVersion());

        ptable.insertRecord(itemset2);
        System.out.println("max version : " + ptable.maxVersion());
    }
}
