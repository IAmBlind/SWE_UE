package initInterfaces.helpers;

import Interface.Plugin;
import SQLDatenbank.DBAccess;
import URL.Interface;
import initInterfaces.Request;
import initInterfaces.Response;
//import initInterfaces.helpers.Timer;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.sql.Connection;

public class temperaturePlugin implements Plugin{

    private Connection connection = null;
    private static DBAccess dbAccess;

    @Override
    public float canHandle(Request req) {
        return 0;
    }

    @Override
    public Response handle(Request req) throws IOException {
        //connect = sqlAccess.getConnection;

        Response response = new Response();
        String[] seg = req.getUrl().getSegments();
        Interface url = (Interface) req.getUrl();




        return null;
    }

    // get date
    private LocalDate getDate(String y, String m, String d) {
        LocalDate date;
        date = LocalDate.of(Integer.parseInt(y), Integer.parseInt(m), Integer.parseInt(d));
        return date;
    }

    @Override
    public String toString(){
        return "temperaturePlugin{}";
    }
}

// Information for Creating the database
// https://www.php-einfach.de/mysql-tutorial/phpmyadmin-datenbank-verwalten/
// https://blog.templatetoaster.com/xampp-phpmyadmin/