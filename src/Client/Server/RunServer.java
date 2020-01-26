package Client.Server;

//libraries
import java.net.*;

//classes
import Client.initInterfaces.Request;
import Client.initInterfaces.Response;
import Client.initInterfaces.implPluginManager;
import Client.Interface.Plugin;

public class RunServer implements Runnable{
    private implPluginManager manager = null;
    private Socket socket;

    //constructor
    RunServer(Socket _socket, implPluginManager _manager){
        this.socket = _socket;
        this.manager = _manager;
    }

    /**
     * Implementing a instance
     */
    public void run(){
        try{
            Request _request = new Request(socket.getInputStream());
            Response response = new Response();
            Plugin plug = Server.manager.selectPlugin(_request);

            // close server
            if(!_request.isValid()){
                System.out.println("Request is not Valid");
                socket.close();
            }else if(_request.isValid()){                                  // successful connection
                plug = Server.manager.selectPlugin(_request);
               // response = plug.handle(_request);
                if(plug == null){
                    response = plug.handle(_request);
                }
                if(!(plug == null)){
                    response = plug.handle(_request);
                }else if(plug == null){
                    System.out.println("Error");
                    System.out.println("No Plugin");
                }

                if (response != null){
                    response.setStatusCode(200);
                } else if (response == null){
                    response = new Response();
                    response.setStatusCode(404);
                }
                response.send(socket.getOutputStream());
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
