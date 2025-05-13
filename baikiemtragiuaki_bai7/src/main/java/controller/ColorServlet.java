package controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ColorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String color = request.getParameter("color");
        HttpSession session = request.getSession();
        session.setAttribute("color", color);
        request.setAttribute("changed", true);
        request.getRequestDispatcher("color.jsp").forward(request, response);
    }
}
