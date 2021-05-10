package ua.kiev.prog.user;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CheckUserServlet extends HttpServlet {
    private final UserList userList = UserList.getInstance();
    private final List<User> users = userList.getList();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User user = User.fromJSON(bufStr);
        if (user != null) {


            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getLogin().equals(user.getLogin()) && users.get(i).getPassword().equals(user.getPassword())) {
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                    users.get(i).setStatus("Online");
                    return;
                } else if (users.get(i).getLogin().equals(user.getLogin()) && (!users.get(i).getPassword().equals(user.getPassword()))) {
                    resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    return;
                }
            }
            userList.add(user);
            users.get(users.size() - 1).setStatus("Online");
            resp.setStatus(HttpServletResponse.SC_CREATED);


        } else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        resp.setContentType("text/plain");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            sb.append((i + 1) + ". " + users.get(i).getLogin() + " - " + users.get(i).getStatus() + ";" + System.lineSeparator());

        }
        PrintWriter pw = resp.getWriter();
        pw.print(sb);
    }

}
