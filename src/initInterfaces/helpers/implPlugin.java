package initInterfaces.helpers;

import initInterfaces.Request;
import initInterfaces.Response;
import URL.Interface;
import Interface.Plugin;

public class implPlugin implements Plugin {

    private Request _request;
    private Response _response;
    private Interface url;

    @Override
    public float canHandle(Request req) {
        this.url = req.getUrl();
        System.out.println("Path: " + this.url.getPath());
        //TODO: Bewertung
        return 0;
    }

    @Override
    public Response handle(Request req) {
        this.url = req.getUrl();
        /*this._response = new Response();
        this._response.setStatusCode(200);
        this._response.setContent("Response");

        return this._response;*/
        return null;
    }
}
