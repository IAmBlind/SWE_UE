package Client.initInterfaces.helpers;

import Client.Interface.Plugin;
import Client.initInterfaces.Request;
import Client.initInterfaces.Response;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class staticPlugin implements Plugin {

    /**
     * @param req
     * @return handle
     */
    @Override
    public float canHandle(Request req) {
        float handle = 0f;
        try {
            FileReader _test = new FileReader(System.getProperty("user.dir") + "/src/Datafiles/"+req.getUrl().getPath().replaceAll("/", ""));
            _test.close();
        } catch (IOException e) {
            return handle;
        }
        handle = 1f;
        return handle;
    }

    /**
     * looks for an existing file
     * @param req
     * @return
     * @throws IOException
     */
    @Override
    public Response handle(Request req) throws IOException {
        Response response = new Response();
        File file = new File(System.getProperty("user.dir") + "/src/Client.DataFiles/" + req.getUrl().getFileName());

        if (file.exists()) {
            response.setStatusCode(200);
            response.setContent(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/Client.DataFiles/" + req.getUrl().getFileName())));
            try {
                response.setContent(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/Client.DataFiles/" + req.getUrl().getFileName())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @Override
    public String toString(){
        return "staticPlugin{}";
    }
}