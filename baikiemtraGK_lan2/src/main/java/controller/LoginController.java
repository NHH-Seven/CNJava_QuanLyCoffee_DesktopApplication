package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
	@GetMapping("/Welcome")
	public String showWelcomePage() {
	    return "welcome"; // trả về file welcome.html
	}
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; // trả về login.html
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        if ("admin".equals(username) && "123".equals(password)) {
            model.addAttribute("message", "Đăng nhập thành công!");
        } else {
            model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        return "login";
    }
}
