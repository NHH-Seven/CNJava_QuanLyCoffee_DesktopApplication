package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ColorFilter implements Filter {
    private final List<String> validColors = Arrays.asList("red", "blue", "green", "white");

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String color = req.getParameter("color");

        if (req.getMethod().equalsIgnoreCase("POST") && (color == null || !validColors.contains(color))) {
            request.setAttribute("error", "Màu không hợp lệ!");
            request.getRequestDispatcher("color.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
