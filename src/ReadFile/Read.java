package ReadFile;

//libraries
import java.io.*;

public class Read {
    public static void main(String[] args) throws Exception{
        File file = new File("C:\\Users\\johnd\\Desktop\\FH Technikum\\Semester 3\\Programmieren\\Text_Files\\test.txt");
        BufferedReader bReader = new BufferedReader(new FileReader(file));

        String text;
        while((text = bReader.readLine()) != null){
        System.out.println(text);
        }
    }

    /* string Reader(){

     */
}
