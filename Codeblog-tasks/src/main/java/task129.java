import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Author: Sviridov Denis
 * Date: 2/08/2019
 * Site: https://sviridovden.ru/
 * Task 129 from codeblog (http://shwanoff.ru/).
 *
 * TODO:
 *  1. Enter file with coordinates of strikes
 *  2. Result is the number of the min strikes
 */



public class task129 {

    private List<Integer> tang = new ArrayList<Integer>();
    private int number = 0;
    private int res = 0;
    private List<Integer> coords = new ArrayList<Integer>();

    private void checkAngle() {
        for (int i = 0; i < coords.size(); i += 2) {
            if ((i + 1) != coords.size()) {
                int y = coords.get(i + 1);
                int x = coords.get(i);
                int k = y / x;
                tang.add(k);
            }
        }

        Set<Integer> set = new HashSet<Integer>(tang);
        tang.clear();
        tang.addAll(set);
        res = tang.size();
    }

    private void writeFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(res + "\r\n");
        fw.close();
    }

    private void readFile(File file) throws IOException {
        FileReader fr = new FileReader(file);
        Scanner sc = new Scanner(fr);
        int Number = Integer.parseInt(sc.nextLine());
        this.number = Number;
        while (sc.hasNextLine()) {
            String tmp = sc.nextLine();
            coords.add(Integer.parseInt(tmp.split(" ")[0]));
            coords.add(Integer.parseInt(tmp.split(" ")[1]));
        }
        fr.close();
    }

    @Test
    public void test() throws IOException {

        readFile(new File("Files/input.txt"));
        checkAngle();
        writeFile(new File("Files/output.txt"));

    }



}
