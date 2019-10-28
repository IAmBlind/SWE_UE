package Server;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket _server = new ServerSocket(8080);
        Socket _client = _server.accept();

        System.out.println("client connected");
    }
}
