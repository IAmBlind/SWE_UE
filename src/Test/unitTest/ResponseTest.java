package Test.unitTest;

import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.assertEquals;
import Client.initInterfaces.Response;

class ResponseTest {
    private Response response = new Response();

    /** Testing of Status **/
    @Test
    void getStatusBad(){
        int code = 400;
        response.setStatusCode(code);
        assertEquals(code, response.getStatusCode());
    }
    @Test
    void getStatusOK(){
        int code = 200;
        response.setStatusCode(code);
        assertEquals(code, response.getStatusCode());
    }
    @Test
    void getStatusAccepted(){
        int code = 202;
        response.setStatusCode(code);
        assertEquals(code, response.getStatusCode());
    }
    @Test
    void getStatusMoved(){
        int code = 302;
        response.setStatusCode(code);
        assertEquals(code, response.getStatusCode());
    }
    @Test
    void getStatusUnauthorized(){
        int code = 401;
        response.setStatusCode(code);
        assertEquals(code, response.getStatusCode());
    }
    @Test
    void getStatusBadGateway(){
        int code = 502;
        response.setStatusCode(code);
        assertEquals(code, response.getStatusCode());
    }

}