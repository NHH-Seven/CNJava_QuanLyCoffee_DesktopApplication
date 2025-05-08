<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- CSS tùy chỉnh -->
    <style>
        body {
            background-color: #f8f9fa;
        }

        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="form-container">
        <h2>Form Đăng Ký</h2>
        <form action="registerServlet" method="post" class="needs-validation" novalidate>
            <!-- Email -->
            <div class="mb-3">
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail" name="email" required>
                <div class="invalid-feedback">Vui lòng nhập email hợp lệ.</div>
            </div>

            <!-- Mật khẩu -->
            <div class="mb-3">
                <label for="inputPassword" class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" id="inputPassword" name="password" required pattern=".{6,}" title="Mật khẩu ít nhất 6 ký tự">
                <div class="invalid-feedback">Mật khẩu phải có ít nhất 6 ký tự.</div>
            </div>

            <!-- Địa chỉ -->
            <div class="mb-3">
                <label for="inputAddress" class="form-label">Địa chỉ</label>
                <input type="text" class="form-control" id="inputAddress" name="address" required>
                <div class="invalid-feedback">Vui lòng nhập địa chỉ.</div>
            </div>

            <!-- Địa chỉ 2 -->
            <div class="mb-3">
                <label for="inputAddress2" class="form-label">Địa chỉ 2</label>
                <input type="text" class="form-control" id="inputAddress2" name="address2">
            </div>

            <!-- Thành phố, tỉnh, zip -->
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="inputCity" class="form-label">Thành phố</label>
                    <input type="text" class="form-control" id="inputCity" name="city" required>
                    <div class="invalid-feedback">Vui lòng nhập thành phố.</div>
                </div>

                <div class="col-md-4 mb-3">
                    <label for="inputState" class="form-label">Tỉnh/Thành</label>
                    <select id="inputState" class="form-select" name="state" required>
                        <option value="" selected disabled>Chọn...</option>
                        <option>Hà Nội</option>
                        <option>TP.HCM</option>
                        <option>Đà Nẵng</option>
                        <option>Huế</option>
                    </select>
                    <div class="invalid-feedback">Vui lòng chọn tỉnh/thành.</div>
                </div>

                <div class="col-md-2 mb-3">
                    <label for="inputZip" class="form-label">Zip</label>
                    <input type="text" class="form-control" id="inputZip" name="zip" pattern="[0-9]{5}" required>
                    <div class="invalid-feedback">Mã Zip phải gồm 5 chữ số.</div>
                </div>
            </div>

            <!-- Checkbox -->
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="checkMeOut" name="check">
                <label class="form-check-label" for="checkMeOut">Check me out</label>
            </div>

            <!-- Nút submit -->
            <button type="submit" class="btn btn-primary w-100">Sign in</button>
        </form>
    </div>

    <!-- Bootstrap JS + JavaScript kiểm tra -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Bootstrap validation
        (() => {
            'use strict';
            const forms = document.querySelectorAll('.needs-validation');
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();
    </script>
</body>
</html>