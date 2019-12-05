package initInterfaces.helpers;

import Interface.Plugin;
import initInterfaces.Request;
import initInterfaces.Response;

import java.io.IOException;

public class naviPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        return 0;
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
