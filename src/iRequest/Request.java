package iRequest;

// Libraries
import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.*;

// class
import URL.*;
import iResponse.*;


public class Request implements iRequest{
    //Variables
    private InputStream input;
    private Map<String, String> header = new HashMap<>();
    private Interface url = new Interface("/");
    private String instruction;


    // constructor
    public Request(InputStream inputStream) throws IOException{
        this.input = inputStream;
        headerIn(); // get header
    }

    private void headerIn() throws IOException{
        // Variable
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.input, StandardCharsets.UTF_8));
        String text;
        String _text;
        String[] headerParts;

        // get the header and place them in an array
        text = reader.readLine();
        headerParts = text.split("/", 3);
        url = new Interface(headerParts[1]);
        instruction = headerParts[0].toUpperCase();     // save the http instruction for validation
        // get the real header
        while((_text = reader.readLine()) != null) {
            String[] _header = _text.split(":",2);
            header.put(_header[0], _header[1]);
        }

    }

    @Override
    public boolean isValid() {
       // check if the http instruction is valid
        if(instruction.length() != 3){
            return false;
        } else if(instruction.length() > 2){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getMethod() {
        // Variable

        return null;   // return the upperCase
    }

    /*@Override
    public URL getUrl(){
        /*URL _url = new URL("www.test.at");

        return _url;
        return null;
    }*/

    @Override
    public Map<String, String> getHeaders() {
        return this.header;
    }

    @Override
    public int getHeaderCount() {
        // Variable
        int count;

        count = this.header.size();     // count header
        return count;
    }

    @Override
    public String getUserAgent() {
        return this.header.get("User Agent");
    }

    @Override
    public int getContentLength() {
        return 0;
    }

    @Override
    public String getContentType() {
        return this.header.get("Content Type");
    }

    @Override
    public InputStream getContentStream() {
        if(this.input == null){
            return null;
        }else {
            return this.input;
        }
    }

    @Override
    public String getContentString() {
        // Variable
        String content;

        if(this.input == null){
            return null;
        } else {
            content = this.input.toString();    // convert the input to a string
            return content;
        }
    }

    @Override
    public byte[] getContentBytes() {
        /*HttpResponse response;

        if (response != null) {
            ByteArrayOutputStream body = new ByteArrayOutputStream();
            response.getEntity().writeTo(body);
            byte[] bytes = body.toByteArray();
            return bytes;
        }else {
            return null;        // return null if no content was received
        }*/
        return null;
    }
}
