package URL;

import java.util.Map;
import java.util.HashMap;

public class Interface implements url {
    // Variable
    private String url;
    // Testing Constructor
    public Interface(String s) {
        this.url = s;
    }

    public static void main(String[] args) {

    }

    @Override
    public String getRawUrl() {
        return url;
    }

    @Override
    public String getPath() {
        //Variable
        int x = url.indexOf("/");
        int y = url.indexOf("//");
        int z = url.indexOf("?");

        if(x < 0){      // check if x in Array
            return "";  // no path available
        }else if(y > 0 && z > 0){
            String[] m_parameter = url.split("\\?");            // cut parameter
            String[] m_https = m_parameter[0].split("//");      // cut "//"
            String[] m_path = m_https[1].split("/", 2);    // cut domain
            return "/" + m_path[1];                                     // return the path
        }else {
            return url;
        }
    }

    @Override
    public Map<String, String> getParameter() {
        Map<String, String> _parameter = new HashMap<>();
        String parameters = url.substring(url.lastIndexOf("?") + 1);    // get parameter
        if(parameters.length() > 1){
            for(String para : parameters.split("&")){
                String[] pair = para.split("=");                      // identifier
                String key = pair[0];
                String value = "";
                if(pair.length > 1) value = pair[1];
                _parameter.put(key,value);                                   // input to hashmap
            }
        }
        return _parameter;
    }

    @Override
    public int getParameterCount() {
        int p_count;
        String[] _url = url.split("\\?");                               // search for ?
        if(_url.length >= 2){
            String [] parameter = _url[1].split("=");
            p_count = parameter.length - 1;
            return p_count;
        }else {
            return 0;
        }
    }

    @Override
    public String[] getSegments() {
        //String[] part = URL.url.split("/");
        //String URL.url = part[part.length -1];
        String[] _segments = url.split("/");                // get segments of URL.url
        return _segments;                                          // return the segments
    }

    @Override
    public String getFileName() {
        //Variable
        int i;
        String file;
        String[] x;
        String _extension = url.substring(url.lastIndexOf('/'));    //get file
        i = _extension.indexOf(".");
        if(i <= 0){
            return "";      //return nothing
        }else {
            if(_extension.contains("?")){
                file = _extension.substring(0, _extension.indexOf("?"));    // cut segments
                x = file.split("/");
                return x[1];                // return file name
            }
            x = _extension.split("/");
            return x[1];                    // return file name
        }
    }

    @Override
    public String getExtension() {
        String temp = url.substring(url.lastIndexOf('/'));
        int i = temp.indexOf(".");                                          // identifier
        if(i > 0){
            String _extension = url.substring(url.lastIndexOf('.'));    // cut the file name
            if(_extension.contains("?")){
                return _extension.substring(0, _extension.indexOf('?'));
            }
            return _extension;                                              // return extension
        } else {
            return "";                                                      // return nothing
        }
    }

    @Override
    public String getFragment(){
        int i = url.indexOf("#");           // search #
        if(i > 0){
            String[] fragment = url.split("#");
            return fragment[1];             //return fragment
        }else {
            return "";                      // return nothing
        }
    }
}
