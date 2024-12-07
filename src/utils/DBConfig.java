package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
    public static Connection getDbConfig(){
        CredentialLoader.LoadPropertiesFile();
        try{
            return DriverManager.getConnection(
                    CredentialLoader.properties.getProperty("db_url"),
                    CredentialLoader.properties.getProperty("db_username"),
                    CredentialLoader.properties.getProperty("db_password")
            );
        }catch (Exception exception){
            System.out.println("Error during connect to database: " + exception.getMessage());
        }
        return null;
    }
}
