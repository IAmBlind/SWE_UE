package unitTest;

import URL.Interface;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

// Function testing
public class InterfaceTest {

    // Test of getRawUrl
    @Test
    public void t_rawUrl(){
        Interface object = new Interface("www.testing.at/home/user/file.txt");
        String out = object.getRawUrl();
        Assertions.assertEquals("www.testing.at/home/user/file.txt", out);
    }
    @Test
    public void t_rawUrl2(){
        Interface object = new Interface("https://testing.at/home/user/file.txt?x=6&y=9&z=42#xx");
        String out = object.getRawUrl();
        Assertions.assertEquals("https://testing.at/home/user/file.txt?x=6&y=9&z=42#xx", out);
    }

    // Test of getPath
    /*@Test
    public void t_Path(){
        URL.Interface object = new URL.Interface("www.testing.at/home/user/file.txt");
        String out = object.getPath();
        Assertions.assertEquals("/home/user/file.txt", out);
    }*/

    @Test
    public void t_Path(){
        Interface object = new Interface("https://testing.at/home/user/file.txt?x=6&y=9&z=42#xx");
        String out = object.getPath();
        Assertions.assertEquals("/home/user/file.txt", out);
    }

    /*@Test
    public void t_Path3(){
        URL.Interface object = new URL.Interface("");
        String out = object.getPath();
        Assertions.assertEquals("", out);
    }*/


    // Test of getParameterCount
    @Test
    public void t_PCount(){
        Interface object = new Interface("www.testing.at/home/user/file?x=6&y=9&z=42#xx");
        int out = object.getParameterCount();
        Assertions.assertEquals(3, out);
    }

    @Test
    public void t_PCount2(){
        Interface object = new Interface("www.testing.at/home/user/file");
        int out = object.getParameterCount();
        Assertions.assertEquals(0, out);
    }

    /*@Test
    public void t_PCount3(){
        URL.Interface object = new URL.Interface("");
        int out = object.getParameterCount();
        Assertions.assertEquals("", out);
    }*/

    // Test of getParameter
    @Test
    public void t_Parameter(){
        Interface object = new Interface("www.testing.at/home/user/file?x=6&y=9&z=42");
        String out = object.getParameter().get("x");
        String out2 = object.getParameter().get("y");
        String out3 = object.getParameter().get("z");

        Assertions.assertEquals("6", out);
        Assertions.assertEquals("9", out2);
        Assertions.assertEquals("42", out3);
    }

    // Test of getFileName
    @Test
    public void t_FileName(){
        Interface object = new Interface("https://testing.at/home/user/file.txt");
        String out = object.getFileName();
        Assertions.assertEquals("file.txt", out);
    }

    @Test
    public void t_FileName2(){
        Interface object = new Interface("www.testing.at/home/user/file.txt");
        String out = object.getFileName();
        Assertions.assertEquals("file.txt", out);
    }

    @Test
    public void t_FileName3(){
        Interface object = new Interface("www.testing.at/home/user/");
        String out = object.getFileName();
        Assertions.assertEquals("", out);
    }

    // Test of getExtension
    @Test
    public void t_Extension(){
        Interface object = new Interface("www.testing.at/home/user/file?x=6&y=9&z=42");
        String out = object.getExtension();
        Assertions.assertEquals("", out);
    }

    @Test
    public void t_Extension2(){
        Interface object = new Interface("https://testing.at/home/user/file");
        String out = object.getExtension();
        Assertions.assertEquals("", out);
    }

    @Test
    public void t_Extension3(){
        Interface object = new Interface("https://testing.at/home/user/file.txt");
        String out = object.getExtension();
        Assertions.assertEquals(".txt", out);
    }

    // Test of getFragment
    @Test
    public void t_Fragment(){
        Interface object = new Interface("https://testing.at/home/user/file?x=6&y=9&z=42#xx");
        String out = object.getFragment();
        Assertions.assertEquals("xx", out);
    }

    @Test
    public void t_Fragment2(){
        Interface object = new Interface("https://testing.at/home/user/file");
        String out = object.getFragment();
        Assertions.assertEquals("", out);
    }

    // Test of getSegment
    /*@Test
    public void t_Segment(){
        URL.Interface object = new URL.Interface("");
        String[] out = object.getSegments();
        Assertions.assertEquals("", out);
    }*/
}

// Request Tests


// Response Tests