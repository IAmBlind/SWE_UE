package Client.Interface;

import java.util.Map;

public interface url {
    /**
     * @return Returns the raw Client.Interface.url.
     */
    String getRawUrl();

    /**
     * @return Returns the path of the Client.Interface.url, without parameter.
     */
    String getPath();

    /**
     * @return Returns a dictionary with the parameter of the Client.Interface.url. Never returns
     * null.
     */
    Map<String, String> getParameter();


    /**
     * @return Returns the number of parameter of the Client.Interface.url. Returns 0 if there are no parameter.
     */
    int getParameterCount();

    /**
     * @return Returns the segments of the Client.Interface.url path. A segment is divided by '/'
     * chars. Never returns null.
     */
    String[] getSegments();

    /**
     * @return Returns the filename (with extension) of the Client.Interface.url path. If the Client.Interface.url
     * contains no filename, a empty string is returned. Never returns
     * null. A filename is present in the Client.Interface.url, if the last segment
     * contains a name with at least one dot.
     */
    String getFileName();

    /**
     * @return Returns the extension of the Client.Interface.url filename, including the leading
     * dot. If the Client.Interface.url contains no filename, a empty string is returned.
     * Never returns null.
     */
    String getExtension();

    /**
     * @return Returns the Client.Interface.url fragment. A fragment is the part after a '#' char
     * at the end of the Client.Interface.url. If the Client.Interface.url contains no fragment, a empty
     * string is returned. Never returns null.
     */
    String getFragment();
}
