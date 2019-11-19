package Server;

//libraries
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

//classes
import iRequest.Request;
import iResponse.Response;

public class RunServer implements Runnable{
    private String fileInput;
    private Socket socket;

    //constructor
    RunServer(Socket _socket, String _fileInput){
        this.socket = _socket;
        this.fileInput = _fileInput;
    }

    public void run(){
        try{
            Request _request = new Request(socket.getInputStream());
            Response _response = new Response();

            // close server
            if(!_request.isValid()){
                socket.close();
            }else{                                  // successful connection
                //_response.setContent(Files);
               // _response.setContent(Files.readAllBytes(Paths.get(fileInput)));
            }
            _response.send(this.socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
