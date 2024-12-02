import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class Reports {
    public static void main(String[] args) {
        int totalSafe = 0;
        ArrayList<ArrayList<Integer>> myListOfLists = new ArrayList<ArrayList<Integer>>();


        try {
            File file = new File("./resources/input.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);


            ArrayList<Integer> listToAdd = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null) {
                String[] numbs = line.split(" ");
                listToAdd = convertStringArrayToIntegerArrayList(numbs);
                myListOfLists.add(listToAdd);
                listToAdd.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (ArrayList<Integer> list : myListOfLists) {
            boolean incrOrDecr = true;
            boolean betwOneOrThree = true;
            incrOrDecr = allDecreasingOrIncreasing(list);
            betwOneOrThree = adjacentDifferenceBetweenOneAndThree(list);
            if (incrOrDecr && betwOneOrThree) {
                totalSafe++;
            }
        }


        System.out.println(totalSafe);


    }



    public static boolean allDecreasingOrIncreasing(ArrayList<Integer> myArray) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        int index = 1;
        while(isIncreasing && index < myArray.size()){
            if (myArray.get(index) < myArray.get(index-1)) {
                isIncreasing = false;
            }

            if (myArray.get(index) > myArray.get(index-1)) {
                isDecreasing = false;
            }
            index++;
        }
        return isIncreasing || isDecreasing;
    }

    public static boolean adjacentDifferenceBetweenOneAndThree (ArrayList<Integer> myArray) {
        boolean diffWithinRange = true;
        int difference;

        for (int i = 1; i < myArray.size(); i++) {
            difference = abs(myArray.get(i-1) - myArray.get(i));
            if (difference == 0 || difference > 3) {
                diffWithinRange = false;
                break;
            }
        }
        return diffWithinRange;

    }

    public static ArrayList<Integer> convertStringArrayToIntegerArrayList(String[] myStringArray) {
        ArrayList<Integer> myIntArrayList = new ArrayList<>();

        for (String number : myStringArray) {
            myIntArrayList.add(Integer.parseInt(number));
        }
        return myIntArrayList;
    }
}