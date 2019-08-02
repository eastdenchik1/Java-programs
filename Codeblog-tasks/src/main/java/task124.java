import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Sviridov Denis
 * Date: 2/08/2019
 * Site: https://sviridovden.ru/
 * Task 124 from codeblog.
 *
 * TODO:
 *  1. Enter number of the first sunday of January
 *  2. Enter number of the month
 *  3. Result is number of all sundays of required month
 */



public class task124 {

    private List<Integer> months = new ArrayList<Integer>(Arrays.asList(
            31, 28, 31,
            30, 31, 30,
            31, 31, 30,
            31, 30, 31
    ));

    private List<Integer> result = new ArrayList<Integer>();


    public void getSundays(int nS, int nM) {

        nM--;

        for (int i = 0; i < nM ; i++) {
            nS = 7 - ((months.get(i) - nS) % 7);
        }
        result.add(nS);
        for (int i = 0; i < 4 ; i++) {
            nS += 7;
            result.add(nS);
        }

    }

    public List<Integer> getResult() {
        return result;
    }

    @Test
    public void test() {

        getSundays(6, 8);

        System.out.println("All sundays of that months: ");
        for (Integer elem: getResult()) {
            System.out.print(elem + " ");
        }

    }



}
