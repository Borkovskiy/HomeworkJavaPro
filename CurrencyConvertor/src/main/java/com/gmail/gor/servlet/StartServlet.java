package com.gmail.gor.servlet;

import com.gmail.gor.json.Currency;
import com.gmail.gor.json.CurrencyJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "Convertor", value = "")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Currency currency = new CurrencyJson().getCurrency();
        Map<String, Double> allCurrency = currency.getRates();
        try {

            request.setAttribute("allCurrency", allCurrency);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
