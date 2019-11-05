package iResponse;

//libraries
import java.io.*;
import java.util.*;
import java.net.*;

//classes
import iRequest.*;

public class Response implements iResponse{
    // Variables
    private String statusServer;
    private int statusCode;
    private String _header;
    private byte[] contentB;
    private int contentLength;
    private Map<String, String> header = new HashMap<>();

    // constructor
    public Response(){

    }

    @Override
    public Map<String, String> getHeaders() {
        return this.header;
    }

    @Override
    public int getContentLength() {
        return this.contentLength;
    }

    @Override
    public String getContentType() {
        return header.get("Content-Type");
    }

    @Override
    public void setContentType(String contentType) {
        header.put("Content-Type", contentType);
    }

    @Override
    public int getStatusCode() {
        if(this.statusCode != 0){
            return this.statusCode;
        } else {
            return 0;
        }
    }

    @Override
    public void setStatusCode(int status) {
        this.statusCode = status;
        //check for status codes
        switch(status){
            case 200:
                this.statusServer = "Code 200: OK";
                break;
            case 201:
                this.statusServer = "Code 201: Created";
                break;
            case 202:
                this.statusServer = "Code 202: Accepted";
                break;
            case 302:
                this.statusServer = "Code 302: Moved Temporarily";
                break;
            case 400:
                this.statusServer = "Code 308: Permanent Redirect";
                break;
            case 401:
                this.statusServer = "Code 401: Unauthorized";
                break;
            case 403:
                this.statusServer = "Code 403: Forbidden";
                break;
            case 404:
                this.statusServer = "Code 404: Not Found";
                break;
            case 500:
                this.statusServer = "Code 500: Internal Server Error";
                break;
            case 502:
                this.statusServer = "Code 502: Bad Gateway";
                break;
            default:
                break;
        }
    }

    @Override
    public String getStatus() {
        if(statusCode != 0){
            return this.statusServer;
        }else {
            return null;
        }
    }

    @Override
    public void addHeader(String header, String value) {
        this.header.put(header, value);
    }

    @Override
    public String getServerHeader() {
        return this._header;
    }

    @Override
    public void setServerHeader(String server) {
        this.header.put("Server", server);
    }

    @Override
    public void setContent(String content) {
        this.contentB = content.getBytes();
    }

    @Override
    public void setContent(byte[] content) {
        this.contentB = content;
    }

    @Override
    public void setContent(InputStream stream){
        try {
            this.contentB = stream.readAllBytes();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void send(OutputStream network) throws Exception {
        try {
            StringBuilder response = new StringBuilder();
            response.append("HTTP/1.1 ").append(getStatus()).append("\n");
            // create the header
            for(Map.Entry<String, String> entry : header.entrySet()) {
                response.append(entry.getKey()).append((": ")).append(entry.getValue()).append("\n");
            }
            // send the header
            network.write(response.toString().getBytes());
            network.write(contentB);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
