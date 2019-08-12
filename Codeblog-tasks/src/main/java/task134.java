import ru.sviridoff.packages.train;

import java.util.ArrayList;
import java.util.Date;


/**
 * Author: Sviridov Denis
 * Date: 2/08/2019
 * Site: https://sviridovden.ru/
 * Task 134 from codeblog (http://shwanoff.ru/).
 *
 */

public class task134 {

    public static void main(String[] args) {
        ArrayList<train> list = new ArrayList<train>();
        list.add(new train("Moscow", 1, new Date().toString() ));
        list.add(new train("Vladivostok", 2, new Date().toString() ));
        list.add(new train("Petropalvsk", 3, new Date().toString() ));
        list.add(new train("Khabarovs", 4, new Date().toString() ));
        list.add(new train("St. Petersburgh", 5, new Date().toString() ));

        for (train el: list) {
            if (el.getNumTrain() == 1) {
                System.out.println(el.getByTrain());
            }
        }

        System.out.println("*************************");
        for (train el: list){
            System.out.println(el.getByTrain());
        }


    }

}
