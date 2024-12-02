import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class NumberOfSafeRecords {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> myListOfLists = importNumbers();
        System.out.println(getNumberOfSafeRecords(myListOfLists));

    }

    public static ArrayList<ArrayList<Integer>> importNumbers(){
        ArrayList<ArrayList<Integer>> myListOfLists = new ArrayList<ArrayList<Integer>>();


        try {
            File file = new File("./resources/input.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);


            ArrayList<Integer> listToAdd;
            String line;
            while((line = br.readLine()) != null) {
                String[] numbs = line.split(" ");
                listToAdd = convertStringArrayToIntegerArrayList(numbs);
                myListOfLists.add(listToAdd);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return myListOfLists;
    }

    public static int getNumberOfSafeRecords(ArrayList<ArrayList<Integer>> myArray) {
        boolean checkTrue;
        int total = 0;
        
        for (ArrayList<Integer> row : myArray) {
            checkTrue = checkDecreasingOrIncreasingAndWithinRange(row);
            if (checkTrue){
                total++;
            }
        }
        return total;
    }

    public static boolean checkDecreasingOrIncreasingAndWithinRange(ArrayList<Integer> myIntArrayList) {
        boolean increasingOrDecreasing;
        boolean betweenOneAndThree;
        increasingOrDecreasing = allDecreasingOrIncreasing(myIntArrayList);
        betweenOneAndThree = adjacentDifferenceBetweenOneAndThree(myIntArrayList);
        return increasingOrDecreasing && betweenOneAndThree;
    }

    public static boolean allDecreasingOrIncreasing(ArrayList<Integer> myArray) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        int index = 1;
        while(isIncreasing && index < myArray.size()){

            if (myArray.get(index) == myArray.get(index-1)) {
                isIncreasing = false;
                isDecreasing = false;
                break;
            }
            if (myArray.get(index) <= myArray.get(index-1)) {
                isIncreasing = false;
            }

            if (myArray.get(index) >= myArray.get(index-1)) {
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