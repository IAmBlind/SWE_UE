package Server;

//libraries
import java.io.*;
import java.net.*;

//classes
import iRequest.Request;
import iResponse.Response;

public class RunServer {
    private Socket socket;

    //constructor
    RunServer(Socket _socket){
        this.socket = _socket;
    }

    public void run(){
        try{
            Request _request = new Request(socket.getInputStream());
            // close server
            if(!_request.isValid()){
                socket.close();
            }
            Response _response = new Response();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
