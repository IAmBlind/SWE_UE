package Main;

import Server.*;

public class main {
    public static void main(String[] args) {
        Server _server;
        int port = 8080;
        try{
            System.out.println("Server starting");
            _server = new Server();
            _server.runServer(port);
           // _server.runServer(port, fileInput2);
            System.out.println("Server started");
        }catch (Exception e){
            System.out.println("Error: Server couldn't start" + e.getMessage());
        }

    }
}
