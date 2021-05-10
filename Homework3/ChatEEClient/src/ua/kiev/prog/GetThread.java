package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private String login;
    private String chatRoom = "all";




    public GetThread(String login, String chatRoom) {
        this.login = login;
        this.chatRoom = chatRoom;
        gson = new GsonBuilder().create();
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                URL url = new URL(Utils.getURL() + "/get?from=" + n);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream is = http.getInputStream();
                try {
                    byte[] buf = responseBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        if ((!chatRoom.equalsIgnoreCase("all"))) {

                            for (Message m : list.getList()) {
                                if ((m.getTo().equalsIgnoreCase(login) || m.getTo().equalsIgnoreCase("all") || m.getFrom().equalsIgnoreCase(login)) && m.getChatRoom().equalsIgnoreCase(chatRoom)) {
                                    System.out.println(m);
                                }
                                n++;
                            }
                        } else if (chatRoom.equalsIgnoreCase("all")) {
                            for (Message m : list.getList()) {
                                if ((m.getTo().equalsIgnoreCase(login) || m.getTo().equalsIgnoreCase("all") || m.getFrom().equalsIgnoreCase(login)) && m.getChatRoom().equalsIgnoreCase(chatRoom)) {
                                    System.out.println(m);
                                }
                                n++;
                            }
                        }
                    }

                } finally {
                    is.close();
                }

                Thread.sleep(500);
            }
        }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            } catch (Exception ex) {

            ex.printStackTrace();

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
