<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Hãy Nhập vào 3 cạnh a, b, c:</h2>
    <form method="post">
        a: <input type="number" name="a" required><br><br>
        b: <input type="number" name="b" required><br><br>
        c: <input type="number" name="c" required><br><br>
        <input type="submit" value="Kiểm tra">
    </form>

    <%
        String sa = request.getParameter("a");
        String sb = request.getParameter("b");
        String sc = request.getParameter("c");

        if (sa != null && sb != null && sc != null) {
            try {
                int a = Integer.parseInt(sa);
                int b = Integer.parseInt(sb);
                int c = Integer.parseInt(sc);

                if (a > 0 && b > 0 && c > 0) {
                    if (a + b > c && a + c > b && b + c > a) {
                        out.println("<h3 style='color:green;'>Ba cạnh a = " + a + ", b = " + b + ", c = " + c + " có thể tạo thành một tam giác.</h3>");
                    } else {
                        out.println("<h3 style='color:red;'>Ba cạnh a = " + a + ", b = " + b + ", c = " + c + " không tạo thành tam giác.</h3>");
                    }
                } else {
                    out.println("<h3 style='color:red;'>Các cạnh phải là số nguyên dương.</h3>");
                }
            } catch (NumberFormatException e) {
                out.println("<h3 style='color:red;'>Vui lòng nhập đúng định dạng số nguyên.</h3>");
            }
        }
    %>

</body>
</html>