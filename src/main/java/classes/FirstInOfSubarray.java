package classes;

import java.util.Arrays;

/**
 * Created by Maksym_Petrenko on 8/29/2017.
 */
public class FirstInOfSubarray {
    public static void main(String[] args) {

        /*int [] array={5,1,10,7,13,2};

        for (int i = 0; i <array.length ; i++) {
            for (int j = 0; j < array.length-1; j++) {
                if(array[j]>array[j+1]){
                    int temp= array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));

        for (int i = 0; i <array.length ; i++) {
            int minElement=array[i];
            int minIndex=i;
            for (int j = i; j <array.length-1 ; j++) {
                if(array[j]>array[j+1]){

                }
            }
        }*/
        /*Integer[] longArray = {6,6,2,5,6, 2, 6,5,7,6, 2, 5,7, 1, 15, 3, 20, 1, 2, 9, 3, 6,2,5, 6, 11, 2, 4, 5, 4, 6, 11,6, 2, 5,7,10};
        Integer[] subArray = {6, 2, 6};
        int index = findIndexOfEntranceSubArray(longArray, subArray);
        System.out.println(index);*/

        /*int[] array={1,2,4,6,8,11,13,15,16,18};
        int el= 5;
        System.out.println(binarySearch(array,el,0,array.length-1));*/

        int [] arraySort={1,1,2,2,5,5,6,6,6,8};
        int uniqueNumber= getIndexUniqueNumberFromArray(arraySort);
        System.out.println("unique number "+uniqueNumber);
    }

    public static int getIndexUniqueNumberFromArray(int[] arraySort) {

        for (int i = 0; i < arraySort.length; i++) {
            int uniqueNumber=arraySort[i];
            for (int j = 0; j < arraySort.length-1; j++) {
                if(i==j){
                    continue;
                }
                if ((uniqueNumber^arraySort[j])==0){
                    break;
                }
                if(j==arraySort.length-2){
                    return i;
                }
            }
        }

        return -1;
    }

    public static int binarySearch(int[] array,int numberToFind,int startIndex, int endIndex){
        if (array.length==0){
            return -1;
        }
        int midEl= (endIndex+startIndex)/2;
        if(array[midEl]==numberToFind){
            return midEl;
        }
        if (startIndex>=endIndex){
            return -1;
        }
        if(numberToFind>array[midEl]){
            return binarySearch(array,numberToFind,++midEl,endIndex);
        }
        else{
            return binarySearch(array,numberToFind,0,--midEl);
        }
    }

    public static<T> int findIndexOfEntranceSubArray(T[] longArray, T[] subArray) {
        int indexEntrance = -1;
        for (int i = 0; i < longArray.length; i++) {
            for (int j = 0; j <subArray.length ; j++) {
                if(i+j>=longArray.length){
                   return indexEntrance;
                }
                if(!longArray[i+j].equals(subArray[j])){
                    break;
                }
                if(j==subArray.length-1){
                    return i;
                }
            }
        }
        return indexEntrance;
    }

    /*public static<T> int findIndexOfEntranceSubArray(T[] longArray, T[] subArray) {

        int indexEntrance = -1;
        if (subArray.length > longArray.length||subArray.length==0) {
            return indexEntrance;
        }
        int indexSubArray = 0;
        boolean allNumbersSame = false;
        int posStart=0;
        for (int i = 0; i < longArray.length; i++) {
            if (allNumbersSame &&indexSubArray == subArray.length - 1&&longArray[i].equals(subArray[indexSubArray])) {
                indexEntrance = i - (subArray.length - 1);
                break;
            }
            if (longArray[i].equals(subArray[indexSubArray])&&indexSubArray<subArray.length-1 ) {
                if(!allNumbersSame){
                    posStart=i;
                }
                indexSubArray++;
                allNumbersSame = true;
            } else if(longArray[i].equals(subArray[0])) {
                indexSubArray=1;
                allNumbersSame = true;
            }
            else{
                indexSubArray = 0;
                allNumbersSame = false;

            }
        }
        return indexEntrance;
    }*/
}
