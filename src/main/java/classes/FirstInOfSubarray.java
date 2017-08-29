package classes;

/**
 * Created by Maksym_Petrenko on 8/29/2017.
 */
public class FirstInOfSubarray {
    public static void main(String[] args) {
        Integer[] longArray = {6,6,2,5,6, 2, 6,5,7,6, 2, 5,7, 1, 15, 3, 20, 1, 2, 9, 3, 6,2,5, 6, 11, 2, 4, 5, 4, 6, 11,6, 2, 5,7,10};
        Integer[] subArray = {6, 2, 6};
        int index = findIndexOfEntranceSubArray(longArray, subArray);
        System.out.println(index);
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
}
