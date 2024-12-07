package utils;

import java.io.FileReader;
import java.util.Properties;

public class CredentialLoader {
    public final static Properties properties = new Properties();
    public static void LoadPropertiesFile(){
        try(FileReader fileReader =
                            new FileReader("application.properties");)
        {
              properties.load(fileReader);

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
