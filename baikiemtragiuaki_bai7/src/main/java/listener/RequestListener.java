package listener;

import jakarta.servlet.*;

public class RequestListener implements ServletRequestListener {
    private static int requestCount = 0;

    public void requestInitialized(ServletRequestEvent sre) {
        requestCount++;
        System.out.println("Yêu cầu mới. Tổng số yêu cầu: " + requestCount);
    }

    public void requestDestroyed(ServletRequestEvent sre) {}
}
