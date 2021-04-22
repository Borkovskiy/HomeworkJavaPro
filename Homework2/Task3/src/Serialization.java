import java.io.*;
import java.lang.reflect.Field;

public class Serialization {
    private ClassFields cF;
    private File file;

    public Serialization() {
    }

    public Serialization(ClassFields cF, File file) {
        this.cF = cF;
        this.file = file;
    }

    public ClassFields getcF() {
        return cF;
    }

    public void setcF(ClassFields cF) {
        this.cF = cF;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void serialization() {

        Class<?> cls = cF.getClass();
        Field[] fields = cls.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {

            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                try {
                    sb.append(field.getName() + "=" + field.get(cF) + ";");

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
        saveToFile(sb.toString());
    }

    public void saveToFile(String text) {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deSerialization() {
        String text = loadFromFile();
        Class<?> cls = cF.getClass();
        String[] strings = text.split(";");
        for (String field : strings) {
            String[] part = field.split("=");
            if (part.length == 2) {

                try {
                    Field f = cls.getDeclaredField(part[0]);
                    f.setAccessible(true);
                    if (f.getType() == int.class) {

                        f.setInt(cF, Integer.parseInt(part[1]));
                    }
                    if (f.getType() == String.class) {
                        f.set(cF, part[1]);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        }

    }

    public String loadFromFile() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String text = "";
            for (; (text = br.readLine()) != null; ) {
                sb.append(text).append(System.lineSeparator());

            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return sb.toString();
    }

}
