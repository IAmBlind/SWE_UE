package SQLDatenbank;

// libraries
import java.sql.*;
import java.util.*;

public class DBAccess {
    public static void main (String[] args){
        try{
            // Connect to database
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
            // Create a statement
            Statement statement = connection.createStatement();
            // Execute SQL query
            ResultSet selectDB = statement.executeQuery("use tempdb");
            ResultSet resultset = statement.executeQuery("select * from temperaturdb");
            // Process the result set
            while (resultset.next()){
                System.out.println(resultset.getString(
                        "ID") + "," + resultset.getString("Temperatur")
                        + ", "+resultset.getString("Date"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void runDB(){
        try{
            // Connect to database
            //Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
            // Create a statement
            Statement statement = connection.createStatement();
            // Execute SQL query
            ResultSet selectDB = statement.executeQuery("use tempdb");
            ResultSet resultset = statement.executeQuery("select * from temperaturdb");
            // Process the result set
            while (resultset.next()){
                System.out.println(resultset.getString(
                        "ID") + "," + resultset.getString("Temperatur")
                        + ", "+resultset.getString("Date"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
