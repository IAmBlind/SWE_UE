package iResponse;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.net.*;

public class Response implements iResponse{

    private static void listen() throws IOException {
        ServerSocket listener = new ServerSocket(8080);
        Socket s = listener.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line;
        while ((line=in.readLine()) != null){
            System.out.println(line);
        }
    }

    public static void main(String[] args) throws IOException {
        listen();
    }

    @Override
    public Map<String, String> getHeaders() {

        return null;
    }

    @Override
    public int getContentLength() {
        /*List<String> contentLength = map.get("Content-Length");
        if (contentLength == null) {
            System.out.println("'Content-Length' doesn't present in Header!");
        } else {
            for (String header : contentLength) {
                System.out.println("Content-Lenght: " + header);
            }
        }
        return 0;*/
        return 0;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void setContentType(String contentType) {

    }

    @Override
    public int getStatusCode() {
        // Variable
        int _scode = 0;

        // connection

        // get Status code
        _scode = 200;

        return _scode;
    }

    @Override
    public void setStatusCode(int status) {

    }

    @Override
    public String getStatus() {
        // Variable
        int status = getStatusCode();
        String Status = "";

        return Status;
    }

    @Override
    public void addHeader(String header, String value) {

    }

    @Override
    public String getServerHeader() {
        return null;
    }

    @Override
    public void setServerHeader(String server) {

    }

    @Override
    public void setContent(String content) {

    }

    @Override
    public void setContent(byte[] content) {

    }

    @Override
    public void setContent(InputStream stream) {

    }

    @Override
    public void send(OutputStream network) throws Exception {
        /*Response _response = new Response();
        _response.getHeaders();*/
    }
}
