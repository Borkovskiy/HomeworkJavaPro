package ua.kiev.prog;

import ua.kiev.prog.user.SetStatus;
import ua.kiev.prog.user.SingUp;
import ua.kiev.prog.user.User;
import ua.kiev.prog.user.UsersStatus;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String chatRoom = "all";
        Scanner scanner = new Scanner(System.in);
        try {
            User user = new SingUp(scanner).enter();
            String login = user.getLogin();

            Thread th = new Thread(new GetThread(login, chatRoom));
            th.setDaemon(true);
            th.start();
            UsersStatus st = new UsersStatus();
            st.usersStatus();
            System.out.println("Add character \"/w\" and nickname after text for private message (massage/wNickname): ");
            System.out.println("Enter \"/setting\"  to open the setting");
            System.out.println("Enter your message");
            while (true) {
                String text = scanner.nextLine();
                String to = "all";


                if (text.isEmpty()) {
                    SetStatus.setStatus(login, "Offline");
                    break;
                }
                if (text.equalsIgnoreCase("/setting")) {
                    Setting s = new Setting(user, scanner);
                    s.setting();

                    chatRoom = s.getChatroom();


                    System.out.println("Enter your message");
                    continue;
                }
                if (text.contains("/w")) {
                    String[] part = text.split("/w");
                    if (part.length == 2) {
                        text = part[0];
                        to = part[1];
                    }


                }

                Message m = new Message(login, to, text, chatRoom);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
