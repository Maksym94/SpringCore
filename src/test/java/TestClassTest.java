
import classes.FirstInOfSubarray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class TestClassTest {
    private FirstInOfSubarray testObj;

    @Before
    public void setUp() throws Exception {
        testObj = new FirstInOfSubarray();
    }

    @Test
    public void findIndexOfEntranceSubarrayForInteger() throws Exception {
        Integer[] arr1 = {3, 5, 6, 6, 4, 2, 6, 3, 3, 6};
        Integer[] arr2 = {6, 4, 2};
        int result = testObj.findIndexOfEntranceSubArray(arr1, arr2);

        Assert.assertThat(result, is(3));
    }

    @Test
    public void findIndexOfEntranceSubarrayForString() {
        String[] arr1 = {"sd", "vf", "rt", "bg", "lm", "dsi", ""};
        String[] arr2 = {"rt", "bg"};
        int result = testObj.findIndexOfEntranceSubArray(arr1, arr2);
        Assert.assertThat(result, is(2));
    }

    @Test
    public void ifSubArrayBiggerReturnNegativeOne() {
        Integer[] arr1 = {3, 5, 6, 4, 2, 6, 3, 3, 6};
        Integer[] arr2 = {6, 4, 2};
        int result = testObj.findIndexOfEntranceSubArray(arr2, arr1);
        Assert.assertThat(result, is(-1));
    }

    @Test
    public void ifNotEntranceReturnNegativeOne() {
        Integer[] arr1 = {3, 5, 6, 4, 2, 6, 3, 3, 6};
        Integer[] arr2 = {5, 4, 2};
        int result = testObj.findIndexOfEntranceSubArray(arr1, arr2);
        Assert.assertThat(result, is(-1));
    }

    @Test
    public void differentTypeOfArrayReturnNegativeOne() {
        Integer[] arr1 = {3, 5, 6, 4, 2, 6, 3, 3, 6};
        String[] arr2 = {"5", "4", "2"};
        int result = testObj.findIndexOfEntranceSubArray(arr1, arr2);
        Assert.assertThat(result, is(-1));
    }

    @Test
    public void differentNumberTypeOfArrayReturnNegativeOne() {
        Integer[] arr1 = {3, 5, 6, 4, 2, 6, 3, 3, 6};
        Long[] arr2 = {5L, 4L, 2L};
        int result = testObj.findIndexOfEntranceSubArray(arr1, arr2);
        Assert.assertThat(result, is(-1));
    }

    @Test
    public void entranceNumbersInbigArray(){
        Integer[] arr = new Integer[1000000];
        Integer [] same= {4,8,7,4,8};
        Random rand = new Random();
        for (int i = 0; i <arr.length ; i++) {
            arr[i]= rand.nextInt(10);
        }
        int result= FirstInOfSubarray.findIndexOfEntranceSubArray(arr,same);
        System.out.println(result);
        Assert.assertThat(result,not(-1));
    }
    @Test
    public void checkCloseNumberArray(){
    Integer[] longArray = {0,0,0,1};
    Integer[] subArray = {0, 0, 1};
        int result= FirstInOfSubarray.findIndexOfEntranceSubArray(longArray,subArray );
        Assert.assertThat(result,is(1));
    }
}