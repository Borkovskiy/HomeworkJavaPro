import java.io.*;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        ClassFields cF = new ClassFields();
        File file = new File("text.txt");
        Serialization s = new Serialization(cF, file);
        s.deSerialization();
        System.out.println(cF);
    }

}
