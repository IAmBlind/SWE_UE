package Client.initInterfaces;

// Libraries
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.apache.commons.io.IOUtils;

import Client.Interface.iRequest;

public class Request implements iRequest {

    private InputStream input;
    private Map<String, String> header = new HashMap<>();
    private Interface url = new Interface("/");
    private String method;
    private String version;
    private byte[] contentByte;

    public enum HttpMethod{
        GET, POST, PUT, DELETE
    }

    public Request(InputStream in) throws IOException {
        BufferedReader brReader = new BufferedReader(new InputStreamReader(in));

        String reader =brReader.readLine();
        if(reader != null && !reader.isEmpty()){
            String[] segments = reader.split(" ");
            method = segments[0].toUpperCase();
            url = new Interface(segments[1]);
        }

        while (reader != null && !reader.isEmpty()){
            reader = brReader.readLine();
            StringBuilder str = new StringBuilder(reader);

            for (int i = 0; i < str.length(); i++){
                String parts = str.toString();
                String[] p = parts.split(": ");
                this.header.put(p[0].toLowerCase(), p[1]);
            }
        }

    }


    /**
     * @return Returns true if the request is valid. A request is valid, if
     *         method and Client.Interface.url could be parsed. A header is not necessary.
     */
    @Override
    public boolean isValid() {
       // check if the http instruction is valid
        if(method.length() >= 3){
            for(HttpMethod m : HttpMethod.values()){
                if(m.name().equals(method))
                    return true;
            }
        }
        return false;
    }

    /**
     * @return Returns the request method in UPPERCASE. get -> GET
     */
    @Override
    public String getMethod() {
        return this.isValid() ? method : null;
    }

    /**
     * @return Returns a URL object of the request. Never returns null.
     */
    @Override
    public Interface getUrl() {
        return url;
    }

    /**
     * @return Returns the request header. Never returns null. All keys must be
     *         lower case.
     */
    @Override
    public Map<String, String> getHeaders() {
        return header;
    }

    /**
     * @return Returns the number of header or 0, if no header where found.
     */
    @Override
    public int getHeaderCount() {
        return header.size();
    }

    /**
     * @return Returns the user agent from the request header
     */
    @Override
    public String getUserAgent() {
        return header.getOrDefault("user-agent", null);
    }

    /**
     * @return Returns the parsed content length request header. Never returns
     *         null.
     */
    @Override
    public int getContentLength() {
        return Integer.parseInt(header.getOrDefault("content-length", "0"));
    }

    /**
     * @return Returns the parsed content type request header. Never returns
     *         null.
     */
    @Override
    public String getContentType() {
        return header.getOrDefault("content-type", "");
    }

    /**
     * @return Returns the request content (body) stream or null if there is no
     *         content stream.
     */
    @Override
    public InputStream getContentStream() {
        if(this.input == null){
            return null;
        }else {
            return this.input;
        }
    }

    /**
     * @return Returns the request content (body) as string or null if there is
     *         no content.
     */
    @Override
    public String getContentString() throws IOException {
        return this.input != null ? IOUtils.toString(this.input, String.valueOf(StandardCharsets.UTF_8)) : null;
    }

    /**
     * @return Returns the request content (body) as byte[] or null if there is
     *         no content.
     */
    @Override
    public byte[] getContentBytes() throws IOException{
        return IOUtils.toByteArray(input);
    }
}
