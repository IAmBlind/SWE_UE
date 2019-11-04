package Server;

import iRequest.iRequest;
import iResponse.iResponse;

import iRequest.*;
import iResponse.*;

import java.io.*;
import java.net.*;


public class Server {
    // Variable
    //protected int serverPort = 8080;
    protected Socket serverSocket = null;
    protected boolean stopped = false;
    protected Thread running = null;

    /*public static void main(String[] args) {

    }

    public Server(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized (this){
            this.running = Thread.currentThread();
        }
        openSocket();
        while(!isStopped()){
            Socket client = null;
            try {
                client = this.serverSocket.accept();
            } catch (IOException e){
                if(isStopped()){
                    System.out.println("Server stopped");
                    return;
                } else {
                    throw new RuntimeException("Error accepting client", e);
                }
            }
            new Thread((java.lang.Runnable) new Runnable(client, "Server")).start();
        }
        System.out.println("Server stopped");
    }

    private synchronized boolean isStopped(){
        return this.stopped;
    }

    public synchronized void stop(){
        this.stopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openSocket(){
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e){
            throw new RuntimeException("Can't open port 8080", e);
        }
    }*/

    Server(Socket _serverSocket) {
        this.serverSocket = _serverSocket;
    }

    public void run(){
        try{
            Request _request = new Request(serverSocket.getInputStream());
            // close server
            if(!_request.isValid()){
                serverSocket.close();
            }
            Response _response = new Response();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // start new server
    public void runServer(int port) throws IOException{
        ServerSocket serverSocket = new ServerSocket(port);
        while(!serverSocket.isClosed()) {
            Server rServer = new Server(serverSocket.accept());
            Thread _thread = new Thread((Runnable) rServer);
            _thread.start();    // start thread
        }
    }
}
