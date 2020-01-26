package Client.initInterfaces.helpers;

//libraries
import java.io.*;

import Client.Interface.Plugin;
import Client.initInterfaces.Request;
import Client.initInterfaces.Response;

public class toLowerPlugin implements Plugin {
    @Override
    public float canHandle(Request req) {
        if(req.isValid()){
            if(req.getUrl().getRawUrl().startsWith("/toLower")){
                return 1.0f;
            }
        }
        return 0.0f;
    }

    @Override
    public Response handle(Request req) throws IOException {
        Response response = new Response();
        if(response.getContentLength() > 0){
            String[] in = req.getContentString().split("=");
            response.setContent(buildHTML(in[1]));
        }
        else{
            response.setContent(buildHTML(null));
        }
        return response;
        /*Response response = new Response();
        if(response.getContentLength() > 0) {
            String input[] = req.getContentString().split("=");
            //response.setStatusCode(200);
            //response.setContentType("text/plain");
            response.setContent(buildHTML(input[1]));
        }
        return response;*/
    }

    /**
     * Make a website with the text in lower case
     * @param in
     * @return the website with the input text in lower case
     */
    private String buildHTML(String in) {
        return "<html>"
                + "<head>"
                + "</head>"
                + "<body>"
                + "<form <action='tolower' method='post'>"
                + "Text: <br><br><textarea rows='4' cols='50' name='text'> </textarea><br><br>"
                + "<input type='submit' value='Submit'>"
                + "</form>"
                + "<br><pre>"
                + (in != null ? in.toLowerCase() : "")
                + "</pre>"
                + "</body>"
                + "</html>";
    }

    @Override
    public String toString(){
        return "toLowerPlugin{}";
    }
}



