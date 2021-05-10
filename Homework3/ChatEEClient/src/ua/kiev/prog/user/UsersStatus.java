package ua.kiev.prog.user;

import ua.kiev.prog.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UsersStatus {
    public void usersStatus() throws IOException {
        URL url = null;
        try {
            url = new URL(Utils.getURL() + "//checkUser");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream is = http.getInputStream();

            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            System.out.println(strBuf);
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
    }

    private byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
