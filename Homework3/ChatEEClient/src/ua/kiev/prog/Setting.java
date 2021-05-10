package ua.kiev.prog;

import ua.kiev.prog.user.User;
import ua.kiev.prog.user.SetStatus;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Setting {
    private User user;
    private Scanner scanner;
    private String text;
    private String chatRoom = "all";
    private Thread th;
    static final Map<Integer, String> statusMap = new HashMap<>();

    static {
        statusMap.put(1, "busy");
        statusMap.put(2, "afk");
        statusMap.put(3, "online");
    }

    public String getChatroom() {
        return chatRoom;
    }

    public void setChatroom(String chatroom) {
        this.chatRoom = chatroom;
    }

    public Setting(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;


    }

    public void setting() throws IOException {
        while (true) {
            System.out.println("Print \"/status\" for change status");

            System.out.println("Print \"enter/ChatroomName\" to enter or create the chatRoom ");
            System.out.println("if wanna return to chat print\"ok\"");
            System.out.println("if u wanna back to all chat print \"/toAll\" ");
            text = scanner.nextLine();
            if (text.equalsIgnoreCase("/status")) {
                choiseStatus();
            } else if (text.equalsIgnoreCase("/toAll")) {
                chatRoom = "all";

                th.interrupt();


            } else if (text.contains("enter/")) {

                enterRoom(text);
                th = new Thread(new GetThread(user.getLogin(), chatRoom));
                th.setDaemon(true);
                th.start();


            } else if (text.equalsIgnoreCase("ok")) {
                break;

            } else {
                System.out.println("error command ");
            }
        }
    }

    public void choiseStatus() throws IOException {
        System.out.println("print 1 for change status to \"busy\" ");
        System.out.println("print 2 for change status to \"afk\" ");
        System.out.println("print 3 for change status to \"online\" ");
        String s = scanner.nextLine();
        try {
            int number = Integer.parseInt(s);

            if (statusMap.containsKey(number)) {
                SetStatus status = new SetStatus();
                status.setStatus(user.getLogin(), statusMap.get(number));

            } else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("error number");


        }
    }

    public void enterRoom(String room) throws IOException {
        URL obj = new URL(Utils.getURL() + "/chatRoom");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        String[] name = room.split("/");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {

            os.write(room.getBytes(StandardCharsets.UTF_8));

            if (conn.getResponseCode() == 202) {
                System.out.println("u enter to Chat Room \"" + name[1] + "\"");
                chatRoom = name[1];

            }
            if (conn.getResponseCode() != 202) { // 200 OK
                System.out.println("HTTP error occured: " + conn.getResponseCode());

            }
        }

    }
}
