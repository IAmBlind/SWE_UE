package Client.initInterfaces.helpers;

import Client.Interface.Plugin;
import Client.initInterfaces.Interface;
import Client.initInterfaces.Request;
import Client.initInterfaces.Response;

public class implPlugin implements Plugin {
    private Interface url;

    /**
     * Returns a score between 0 and 1 to indicate that the plugin is willing to
     * handle the request. The plugin with the highest score will execute the
     * request.
     *
     * @param req
     * @return A score between 0 and 1
     */
    @Override
    public float canHandle(Request req) {
        if(req.getUrl().getRawUrl().startsWith("/")){
            return (float) 1;
        }else {
            return 0;
        }
    }

    /**
     * Called by the server when the plugin should handle the request.
     *
     * @param req
     * @return A new response object.
     */
    @Override
    public Response handle(Request req) {
        Response response = new Response();

        String content = "<html>"
                + "<body>"
                + "<h1>Welcome to your demise!\n"
                + "</h1>"
                + "<h2>Plugins"
                +"</h2>"
                + "<li><a href=\"localhost:8080/temp\"> Temperatur"
                +"</a></li>"
                + "<li><a href=\"localhost:8080/toLower\"> ToLower"
                +"</a></li>"
                + "<h2>Files"
                +"</h2>"
                + "<li><a href=\"localhost:8080/index.html\"> index"
                +"</a></li>"
                + "<li><a href=\"localhost:8080/nyan.gif\"> Katzengott"
                +"</a></li>"
                + "<li><a href=\"localhost:8080/test.jpg\"> test1"
                +"</a></li>"
                + "<li><a href=\"localhost:8080/test.png\"> test2"
                +"</a></li>"
                + "<li><a href=\"localhost:8080/test.txt\"> text"
                +"</a></li>";
        response.setContent(content);
        return response;
    }
}
