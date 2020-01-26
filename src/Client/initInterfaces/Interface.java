package Client.initInterfaces;

import Client.Interface.url;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Interface implements url {
    // Variable
    private String url;
    String retPath = "";

    // Testing Constructor
    public Interface(String s) {
        this.url = s;
    }

    /**
     * @return Returns the raw Client.Interface.url.
     */
    @Override
    public String getRawUrl() {
        return url;
    }

    /**
     * @return Returns the path of the Client.Interface.url, without parameter.
     */
    @Override
    public String getPath() {
        if (url != null) {
            if (url.contains("//") && url.contains("?")) {
                String[] withoutHttps = url.split("\\?")[0].split("//");
                retPath = withoutHttps[1].split("/", 2)[1];
            } else if (url.contains("//")) {
                retPath = url.split("//")[1].split("/", 2)[1];
            } else if (url.contains("?")) {
                retPath = url.split("\\?")[0].split("/", 2)[1];
            } else {
                retPath = url;
            }
        }
        return retPath;
    }

    /**
     * @return Returns a dictionary with the parameter of the Client.Interface.url. Never returns
     * null.
     */
    @Override
    public Map<String, String> getParameter() {
        Map<String, String> _parameters = new HashMap<>();
        String parameters = url.substring(url.lastIndexOf("?") + 1);    // get parameter

        if (parameters.length() > 1) {
            for (String param : parameters.split("&")) {
                String[] pair = param.split("=");
                _parameters.put(pair[0], pair[1]);
            }
        }
        return _parameters;
    }

    /**
     * @return Returns the number of parameter of the Client.Interface.url. Returns 0 if there are no parameter.
     */
    @Override
    public int getParameterCount() {
        int p_count = 0;
        String[] _url = url.split("\\?");                               // search for ?
        if(_url.length >= 2){
            p_count = _url[1].split("=").length -1;
            return p_count;
        }else {
            return p_count;
        }
    }

    /**
     * @return Returns the segments of the Client.Interface.url path. A segment is divided by '/'
     * chars. Never returns null.
     */
    @Override
    public String[] getSegments() {
        String[] _segments = url.split("/", 4);
        String rawURL = _segments[_segments.length - 1];
        String[] segments = rawURL.split("/");

        if (segments[segments.length - 1].contains("?")) {
            segments = Arrays.copyOf(segments, segments.length - 1);
        }
        return segments;
    }

    /**
     * @return Returns the filename (with extension) of the Client.Interface.url path. If the Client.Interface.url
     * contains no filename, a empty string is returned. Never returns
     * null. A filename is present in the Client.Interface.url, if the last segment
     * contains a name with at least one dot.
     */
    @Override
    public String getFileName() {
        String[] x;
        String extension = url.substring(url.lastIndexOf('/'));

        if (extension.contains(".")) {
            if (extension.contains("?")) {
                String file = extension.substring(0, extension.indexOf("?"));
                x = file.split("/");
            } else {
                x = extension.split("/");
            }
            retPath = x[1];
        }
        return retPath;
    }

    /**
     * @return Returns the extension of the Client.Interface.url filename, including the leading
     * dot. If the Client.Interface.url contains no filename, a empty string is returned.
     *  Never returns null.
     */
    @Override
    public String getExtension() {
        if (url.substring(url.lastIndexOf('/')).indexOf(".") > 0 ) {
            String ext = url.substring(url.lastIndexOf('.') + 1);
            retPath = ext.contains("?") ? ext.substring(0, ext.indexOf('?')) : ext;
        }
        return retPath;
    }

    /**
     * @return Returns the Client.Interface.url fragment. A fragment is the part after a '#' char
     * at the end of the Client.Interface.url. If the Client.Interface.url contains no fragment, a empty
     * string is returned. Never returns null.
     */
    @Override
    public String getFragment(){
        return url.contains("#") ? url.split("#")[1] : "";
    }

    /**
     * @return if file is real
     */
    public boolean realFile(){
        return !getFileName().isEmpty();
    }
}
