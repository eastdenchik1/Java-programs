package WorkWithFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class EditFILES {


    public static ArrayList beatifyFiles(File file) {
        ArrayList<String> list = new ArrayList<String>();
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                int j = 0;
                reader.close();
                fr.close();

                for (int i = 0; i < line.length(); i += 2) {
                    if (j != 20) {
                        String tmp = line.substring(i, i + 2);
                        list.add(tmp);
//                        System.out.print(tmp + " ");
                        j ++;
                    } else {
                        j = 0;
//                        System.out.println();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }



}
