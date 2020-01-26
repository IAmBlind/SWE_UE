package Test.unitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;

import Client.initInterfaces.Request;
import Test.unitTest.helpers.makeRequest;

class RequestTest {
    private Request request;
    private String requestUrlTest = "https://test.at/root/desktop/test.html?a=1&b=2&c=3#aa";

    /** Test validity **/
    @Test
    void validTest() throws Exception{
        request = new Request(makeRequest.getValidRequestStream(requestUrlTest));
        assertTrue(request.isValid());
    }

    /** Test getMethod **/
    @Test
    void methodTest() throws Exception{
        request = new Request(makeRequest.getValidRequestStream(requestUrlTest));
        assertEquals("GET", request.getMethod());
    }

    /** Test Url **/
    @Test
    void urlTest() throws Exception {
        request = new Request(makeRequest.getValidRequestStream(requestUrlTest));
        assertEquals(requestUrlTest, request.getUrl().getRawUrl());
    }

    void contentBytesTest() throws Exception{
       InputStream input = makeRequest.getValidRequestStream(requestUrlTest, "GET", "hello world");
       request = new Request(input);
       assertEquals(IOUtils.toByteArray(input), request.getContentBytes());
    }
}