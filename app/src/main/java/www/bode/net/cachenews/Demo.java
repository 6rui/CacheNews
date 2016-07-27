package www.bode.net.cachenews;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by Liu on 2016-07-21.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        ClassLoader.getSystemClassLoader();
        FileReader fileReader = new FileReader("data.bat");
        
        fileReader.read();
        InputStreamReader reader =
                                 new InputStreamReader(new FileInputStream("e:\\workspace\\file.txt"));
        reader.read();
    }
    static class Inner1{}
    static class Inner2{}
}
