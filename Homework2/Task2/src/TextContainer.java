import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@SaveTo(path = "file.txt")
class TextContainer {
    String text = "testingN3";

    @Saver
    public void save(String path) {
        File file = new File(path);
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

