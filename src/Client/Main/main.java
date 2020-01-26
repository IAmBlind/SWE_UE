package Client.Main;

import Client.Server.*;

public class main {
    public static void main(String[] args) {
        Server server;
        int port = 8080;

        try{
            System.out.println("Client.Server starting...");
            server = new Server();
            server.runServer(port);
            System.out.println("The Client.Server is good to go at port: " + port);
        }catch (Exception e){
            System.out.println("An Error shines brigth like a diamond: " + e.getMessage());
        }
    }
}
