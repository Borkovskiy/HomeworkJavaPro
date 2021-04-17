package com.example.questionary;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StatisticServlet", value = "/StatisticServlet")
public class StatisticServlet extends HttpServlet {
    private Map<String, Integer> statistic= new HashMap<>();
    {
        statistic.put("moodGood",0);
        statistic.put("moodSad",0);
        statistic.put("jsYes",0);
        statistic.put("jsNo",0);
        statistic.put("javaYes",0);
        statistic.put("javaNo",0);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        String mood= request.getParameter("mood");
        statistic.put(mood,statistic.get(mood)+1);
        String js= request.getParameter("js");
        statistic.put(js,statistic.get(js)+1);
        String java= request.getParameter("java");
        statistic.put(java,statistic.get(java)+1);
        PrintWriter pw = response.getWriter();
        pw.println("<html><head><title>Table</title></head>");
        pw.println("<body><table width=\"100%\" border=\"1\" cellpadding=\"4\" cellspacing=\"0\">");
        pw.println("<caption><h3>Statistic</h3></caption>");
        pw.println("<tr><th>&nbsp;</th><th colspan=\"3\">Number of responses</th></tr>");
        pw.println("<tr><th>&nbsp;</th> <th>Wanna learning Java</th><th>knows the JS</th><th>Good Mood</th></tr>");
        pw.println("<tr><th>YES</th> <th>"+statistic.get("javaYes")+"<th>"+statistic.get("jsYes")+"</th><th>"+statistic.get("moodGood")+"</th></tr>");
        pw.println("<tr><th>NO</th> <th>"+statistic.get("javaNo")+"<th>"+statistic.get("jsNo")+"</th><th>"+statistic.get("moodSad")+"</th></tr></table></body></html>");
    }
}