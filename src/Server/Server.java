package Server;

//libraries
import java.io.*;
import java.net.*;

public class Server {

    //public void runServer(int port, String fileInput) throws IOException{
    public void runServer(int port) throws IOException{
        ServerSocket serverSocket = new ServerSocket(port);
        while(!serverSocket.isClosed()) {
            //RunServer rServer = new RunServer(serverSocket.accept(), fileInput);
            RunServer _Server = new RunServer(serverSocket.accept());
            //RunServer _Server = new RunServer(serverSocket.accept(), fileInput);
            Thread _thread = new Thread(_Server);
            _thread.start();    // start thread
        }
    }
}
