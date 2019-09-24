package Manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manager {


    public Properties init_properties(String PROPERTIES) throws IOException {
        Properties props = new Properties();
        FileInputStream fileInputStream = new FileInputStream(PROPERTIES);
        props.load(fileInputStream);
        return props;
    }

}
