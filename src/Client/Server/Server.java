package Client.Server;

//libraries
import java.io.*;
import java.net.*;
import Client.initInterfaces.implPluginManager;

public class Server {
    static implPluginManager manager = new implPluginManager();

    public void runServer(int port) throws IOException{
        ServerSocket serverSocket = new ServerSocket(port);
        while(!serverSocket.isClosed()) {
            RunServer _Server = new RunServer(serverSocket.accept(), manager);
            Thread _thread = new Thread(_Server);
            _thread.start();    // start thread
        }
    }
}
