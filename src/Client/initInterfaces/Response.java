package Client.initInterfaces;

//libraries
import java.io.*;
import java.util.*;

//classes
import Client.Interface.iResponse;

public class Response implements iResponse {
    // Variables
    private byte[] contentByte;
    public String contentType;
    private String serverHead;
    private int contentLength;
    public int statusCode;
    private String status;
    private Map<String, String> header = new HashMap<>();

    // constructor
    public Response(){
        header.put("Client/Server", "BIF-SWE1-Client.Server");
    }

    /**
     * @return Returns a writable map of the response headers. Never returns
     * null.
     */
    @Override
    public Map<String, String> getHeaders() {
        return header;
    }

    /**
     * @return Returns the content length or 0 if no content is set yet.
     */
    @Override
    public int getContentLength() {
        return this.contentLength;
    }

    /**
     * @return Gets the content type of the response.
     */
    @Override
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType Sets the content type of the response.
     */
    @Override
    public void setContentType(String contentType) {
        header.put("Content-Type", contentType);
        this.contentType = contentType;
    }

    /**
     * @return Gets the current status code. An Exceptions is thrown, if no status code was set.
     */
    @Override
    public int getStatusCode() {
        if(statusCode == 0){
            throw new IllegalArgumentException("null");
        }else {
            return statusCode;
        }
    }

    /**
     * @param status Sets the current status code.
     */
    @Override
    public void setStatusCode(int status) {
        switch(status){
            case 200:
                this.status = "Code 200: OK";
                break;
            case 201:
                this.status = "Code 201: Created";
                break;
            case 202:
                this.status = "Code 202: Accepted";
                break;
            case 302:
                this.status = "Code 302: Moved Temporarily";
                break;
            case 400:
                this.status = "Code 400: Bad Request";
                break;
            case 401:
                this.status = "Code 401: Unauthorized";
                break;
            case 403:
                this.status = "Code 403: Forbidden";
                break;
            case 404:
                this.status = "Code 404: Not Found";
                break;
            case 500:
                this.status = "Code 500: Internal Client.Server Error";
                break;
            case 502:
                this.status = "Code 502: Bad Gateway";
                break;
            default:
                this.status = "400 Bad Request";
        }
        statusCode = status;
    }

    /**
     * @return Returns the status code as string. (200 OK)
     */
    @Override
    public String getStatus() {
        if(statusCode != 0){
            return "(" + status +")";
        } else{
            throw new IllegalArgumentException("null");
        }
    }

    /**
     * @param header
     * @param value
     */
    @Override
    public void addHeader(String header, String value) {
        this.header.put(header, value);
    }

    /**
     * @return Returns the Client.Server response header. Defaults to "BIF-SWE1-Client.Server".
     */
    @Override
    public String getServerHeader() {
        return serverHead;
    }

    /**
     * @param server
     */
    @Override
    public void setServerHeader(String server) {
        header.put("Client/Server", server);
    }

    /**
     * @param content Sets a string content. The content will be encoded in UTF-8.
     */
    @Override
    public void setContent(String content) {
        contentByte = content.getBytes();
        contentLength = contentByte.length;
    }

    /**
     * @param content Sets a byte[] as content.
     */
    @Override
    public void setContent(byte[] content) {
        contentByte = content;
        contentLength = contentByte.length;
    }

    /**
     * @param stream Sets the stream as content.
     */
    @Override
    public void setContent(InputStream stream){
        try {
            contentByte = stream.readAllBytes();
            contentLength = contentByte.length;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @param network Sends the response to the network stream.
     */
    @Override
    public void send(OutputStream network) throws IOException {
        try {
            if (status == null || contentByte.length == 0) {
                throw new IllegalStateException("No status code or content set.");
            }

            StringBuilder respondHeader = new StringBuilder();
            respondHeader.append("HTTP/1.1 ").append(getStatus()).append("\n");
            respondHeader.append("Content-Length: ").append(getContentLength()).append("\n");

            for (Map.Entry<String, String> entry : header.entrySet()) {
                respondHeader.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            respondHeader.append("\n");

            network.write(respondHeader.toString().getBytes());
            network.write(contentByte);
            network.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}