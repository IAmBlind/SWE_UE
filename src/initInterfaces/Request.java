package initInterfaces;

// Libraries
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import initInterfaces.helpers.HTTPMethods;
import org.apache.commons.io.IOUtils;

// class
import URL.*;
import Interface.iRequest;

public class Request implements iRequest {
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
        headerParts = text.split(" ", 3);
        url = new Interface(headerParts[1]);
        instruction = headerParts[0].toUpperCase();     // save the http instruction for validation
        // get the real header
        while((_text = reader.readLine()) != null && !_text.isEmpty()) {
            String[] _header = _text.split(":",2);
            this.header.put(_header[0].toLowerCase(), _header[1]);
        }

    }

    @Override
    public boolean isValid() {
       // check if the http instruction is valid
        if(instruction.length() > 2){
            for(HTTPMethods m : HTTPMethods.values()){
                if(m.name().equals(instruction))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String getMethod() {
        //return instruction;
        return this.isValid() ? this.instruction : null;
    }

    @Override
    public Interface getUrl() {
        if(this.isValid()){
            return this.url;
        }
        return this.url;
    }

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
        //return this.header.get("User Agent");
        if(this.header.size() > 0){
            return this.header.getOrDefault("user-agent", null);
        }
        return null;
    }

    @Override
    public int getContentLength() {
        // Variable
        String length;

        length = this.header.get("Content-Length");
        return Integer.parseInt(length);
    }

    @Override
    public String getContentType() {
        //return this.header.get("Content Type");
        if(this.header.size() > 0){
            return this.header.getOrDefault("content-type", "");
        }
        return "";
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
    public String getContentString() throws IOException{
        // Variable
        /*String content;

        if(this.input == null){
            return null;
        } else {
            content = this.input.toString();    // convert the input to a string
            return content;
        }*/
        return this.input != null ? IOUtils.toString(this.input, String.valueOf(StandardCharsets.UTF_8)) : null;
    }

    @Override
    public byte[] getContentBytes() throws IOException{
        return IOUtils.toByteArray(this.input);
    }
}
