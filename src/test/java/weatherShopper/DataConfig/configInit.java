package weatherShopper.DataConfig;

import java.io.*;
import java.util.Properties;

public class configInit {

    Properties config = new Properties();

    public String readConfigData (String key) throws IOException {
        InputStream input = new FileInputStream("src/test/java/weatherShopper/DataConfig/config.properties");
        config.load(input);
        return config.getProperty(key);
    }

    public void writeConfigData() throws IOException {
        OutputStream output = new FileOutputStream("config.properties");
        config.setProperty("key", "value");
    }
}
