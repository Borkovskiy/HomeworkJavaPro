package ua.kiev.prog.user;

import ua.kiev.prog.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SetStatus {
    public static void setStatus(String login, String status) throws IOException {
        URL obj = new URL(Utils.getURL() + "/setStatus");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write((login + ";" + status).getBytes(StandardCharsets.UTF_8));
            System.out.println("ur status change to " + status);
            int res = conn.getResponseCode();
            if (res != 200) {
                System.out.println("HTTP error occured: " + res);
                return;
            }

        }
    }
}
