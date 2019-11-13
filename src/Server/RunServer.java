package Server;

//libraries
import java.io.*;
import java.net.*;

//classes
import iRequest.Request;
import iResponse.Response;
import ReadFile.Read;

public class RunServer {
    private Socket socket;

    //constructor
    RunServer(Socket _socket){
        this.socket = _socket;
    }

    public void run(){
        try{
            Request _request = new Request(socket.getInputStream());
            Response _response = new Response();

            // close server
            if(!_request.isValid()){
                socket.close();
            }else{                                  // successful connection
                _response.setContent("Test");
                //_response.setContent(Reader());
            }
            _response.send(this.socket.getOutputStream());
            //Response _response = new Response();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
