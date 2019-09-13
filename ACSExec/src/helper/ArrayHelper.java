package helper;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrayHelper {
    /*
    public static <T> Object resize (ArrayList<LinkedList<T>> oldArray, int newSize) {

        ArrayList<LinkedList<T>> list = null;
        int oldSize = oldArray.size(); // gets the exact number of elements I want

        list = new ArrayList<LinkedList<T>> (newSize);

        for (int i = 0; i < newSize; i++) {
            //list.add("String num: " + i);
            if ( i < oldSize){
                list.add(oldArray.get(i));
            }
        }

        return list;
    }
    */


    public static ArrayList<LinkedList<Integer>> resize (ArrayList<LinkedList<Integer>> oldArray, int newSize) {

        ArrayList<LinkedList<Integer>> list = null;
        int oldSize = oldArray.size(); // gets the exact number of elements I want

        list = new ArrayList<LinkedList<Integer>> (newSize);

        for (int i = 0; i < newSize; i++) {
            //list.add("String num: " + i);
            if ( i < oldSize){
                list.add(oldArray.get(i));
            } else {
                list.add(new LinkedList<Integer>());
            }
        }

        return list;
    }

    public static ArrayList<java.util.LinkedList<Pair<Integer, Integer>>> resizePair (ArrayList<java.util.LinkedList<Pair<Integer, Integer>>> oldArray, int newSize) {

        ArrayList<java.util.LinkedList<Pair<Integer, Integer>>> list = null;
        int oldSize = oldArray.size(); // gets the exact number of elements I want

        list = new ArrayList<java.util.LinkedList<Pair<Integer, Integer>>> (newSize);

        for (int i = 0; i < newSize; i++) {
            //list.add("String num: " + i);
            if ( i < oldSize){
                list.add(oldArray.get(i));
            } else {
                list.add(new LinkedList<Pair<Integer, Integer>>());
            }
        }

        return list;
    }

    public static ArrayList<Integer> resizeArray(ArrayList<Integer> oldArray, int newSize) {
        ArrayList<Integer> list = null;
        int oldSize = oldArray.size(); // gets the exact number of elements I want

        list = new ArrayList<Integer> (newSize);

        for (int i = 0; i < newSize; i++) {
            //list.add("String num: " + i);
            if ( i < oldSize){
                list.add(oldArray.get(i));
            } else {
                list.add(0);
            }
        }

        return list;
    }
}
