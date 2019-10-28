package Server;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket _client = new Socket("localhost",8080);
    }
}
