package Client.initInterfaces.helpers;

import Client.Interface.Plugin;
import Client.initInterfaces.Request;
import Client.initInterfaces.Response;

import java.io.IOException;

public class naviPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        float handle = 0f;
        if (req.getUrl().getRawUrl().startsWith("/static")) {
            handle = 1f;
            return handle;
        } else
            return handle;
    }

    @Override
    public Response handle(Request req) throws IOException {
        return null;
    }

    @Override
    public String toString(){
        return "naviPlugin{}";
    }
}
