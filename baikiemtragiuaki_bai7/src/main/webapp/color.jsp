<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chọn màu nền</title>
</head>
<body style="background-color: <%= session.getAttribute("color") != null ? session.getAttribute("color") : "white" %>;">
    <h2>Chọn màu nền cho trang</h2>
    <form action="ColorServlet" method="post">
        <select name="color">
            <option value="white">White</option>
            <option value="red">Red</option>
            <option value="blue">Blue</option>
            <option value="green">Green</option>
        </select>
        <input type="submit" value="Chọn">
    </form>

    <% if (request.getAttribute("changed") != null) { %>
        <p>Màu nền đã được thay đổi thành: <strong><%= session.getAttribute("color") %></strong></p>
    <% } else if (request.getAttribute("error") != null) { %>
        <p style="color:red"><%= request.getAttribute("error") %></p>
    <% } %>
</body>