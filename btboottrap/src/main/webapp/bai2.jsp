<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Nhúng Bootstrap từ CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Tạo class CSS tùy chỉnh -->
    <style>
        .text-uppercase-custom {
            text-transform: uppercase;
        }

        .text-color-custom {
            color: #ff5722;
        }

        .bg-color-custom {
            background-color: rgba(0, 123, 255, 0.1);
        }

        .custom-table {
            color: #333; /* Màu chữ */
            background-color: #f9f9f9; /* Màu nền bảng */
            border: 2px solid #007bff; /* Viền bảng màu xanh */
        }

        .custom-table th,
        .custom-table td {
            border: 1px solid #007bff; /* Viền từng ô */
            padding: 10px;
        }

        .custom-table th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
<!-- Tiêu đề -->
    <h1 class="text-center text-uppercase-custom text-color-custom bg-color-custom p-3">
        Bảng thông tin sinh viên
    </h1>

    <!-- Bảng thông tin sinh viên -->
    <div class="container mt-4">
        <table class="table custom-table">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Mã SV</th>
                    <th>Họ và tên</th>
                    <th>Quê quán</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>SV001</td>
                    <td>Nguyễn Huy Hiếu</td>
                    <td>Vĩnh Phúc</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>SV002</td>
                    <td>Nguyễn văn bằng</td>
                    <td>Hưng Yên</td>
                </tr>
            </tbody>
        </table>
    </div>

</body>
</html>