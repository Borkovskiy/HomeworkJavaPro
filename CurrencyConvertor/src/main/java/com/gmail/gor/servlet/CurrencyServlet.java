package com.gmail.gor.servlet;

import com.gmail.gor.servise.Convertor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CurrencyServlet", value = "/CurrencyServlet")
public class CurrencyServlet extends HttpServlet {
    static final String TEMPLATE = "<html>" +
            "<head><title>Convertor</title></head>" +
            "<body><h1>%s</h1></body></html>";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String currency = request.getParameter("currency");
        String currencyValue = request.getParameter("value");
        Convertor convertor = new Convertor(currency, currencyValue);
        Double result = convertor.convert();
        if (result == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(String.format(TEMPLATE, "ERROR"));
        } else {
            response.getWriter().println(String.format(TEMPLATE, result));
        }
    }


}
