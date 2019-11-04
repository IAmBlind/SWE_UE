package URL;

import java.util.Map;

public interface url {
    /**
     * @return Returns the raw URL.url.
     */
    String getRawUrl();

    /**
     * @return Returns the path of the URL.url, without parameter.
     */
    String getPath();

    /**
     * @return Returns a dictionary with the parameter of the URL.url. Never returns
     * null.
     */
    Map<String, String> getParameter();


    /**
     * @return Returns the number of parameter of the URL.url. Returns 0 if there are no parameter.
     */
    int getParameterCount();

    /**
     * @return Returns the segments of the URL.url path. A segment is divided by '/'
     * chars. Never returns null.
     */
    String[] getSegments();

    /**
     * @return Returns the filename (with extension) of the URL.url path. If the URL.url
     * contains no filename, a empty string is returned. Never returns
     * null. A filename is present in the URL.url, if the last segment
     * contains a name with at least one dot.
     */
    String getFileName();

    /**
     * @return Returns the extension of the URL.url filename, including the leading
     * dot. If the URL.url contains no filename, a empty string is returned.
     * Never returns null.
     */
    String getExtension();

    /**
     * @return Returns the URL.url fragment. A fragment is the part after a '#' char
     * at the end of the URL.url. If the URL.url contains no fragment, a empty
     * string is returned. Never returns null.
     */
    String getFragment();
}
