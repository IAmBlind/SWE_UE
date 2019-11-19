package Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import Server.*;

public class main {
    public static void main(String[] args) {
        Server _server;
        int port = 8080;
        String fileInput = System.getProperty("user.dir") + "/src/DataFiles/index.html";
        String fileInput2 = System.getProperty("user.dir") + "/src/DataFiles/test.gif";
        //String fileInput = System.getProperty("user.dir") + "";

        try{
            System.out.println("Server starting");
            _server = new Server();
            _server.runServer(port, fileInput);
            System.out.println("Server started");
        }catch (Exception e){
            System.out.println("Error: Server couldn't start" + e.getMessage());
        }

    }
}