package ua.kiev.prog.chatRoom;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatRoomServlet extends HttpServlet {
    private final ChatRoomList chatRoomList = ChatRoomList.getInstance();
    private List<ChatRoom> chatRoomrs = chatRoomList.getList();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        String[] part = bufStr.split("/");
        for (int i = 0; i < chatRoomrs.size(); i++) {
            if (chatRoomrs.get(i).getName().equalsIgnoreCase(part[1])) {
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }

        }
        chatRoomrs.add(new ChatRoom(part[1]));
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);


    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
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