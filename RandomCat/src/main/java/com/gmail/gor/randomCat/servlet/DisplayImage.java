package com.gmail.gor.randomCat.servlet;

import com.gmail.gor.randomCat.entity.Cat;
import com.gmail.gor.randomCat.servise.CatServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@WebServlet(name = "RandomCat", value = "/RandomCat")
public class DisplayImage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            CatServise catServise = CatServise.getInstance();
            if (catServise.isFullness()) {
                Cat cat = catServise.catFromDB();
                byte[] catBytes = cat.getCatImage();
                String base64Encoded = Base64.getEncoder().encodeToString(catBytes);
                cat.setBase64Image(base64Encoded);
                request.setAttribute("catDB", cat);
                getServletContext().getRequestDispatcher("/catDB.jsp").forward(request, response);
            } else {
                String cat = catServise.catFromApi();
                request.setAttribute("cat", cat);
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }


        } catch (ServletException | InterruptedException | IOException ex) {
            ex.printStackTrace();
        }

    }
}
