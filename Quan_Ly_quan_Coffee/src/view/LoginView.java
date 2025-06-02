package view;

import controller.LoginController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Giao diện đăng nhập hệ thống quản lý quán cà phê
 * 
 * @author zhieu
 */
public class LoginView extends JFrame implements ActionListener {
    
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnExit;
    private JButton btnRegister; // Thêm nút đăng ký
    private LoginController controller;
    
    /**
     * Khởi tạo giao diện đăng nhập
     */
    public LoginView() {
        // Khởi tạo controller
        controller = new LoginController(this);
        
        // Thiết lập giao diện
        initializeUI();
    }
    
    /**
     * Thiết lập các thành phần giao diện
     */
    private void initializeUI() {
        setTitle("Đăng Nhập - Quản Lý Quán Cà Phê");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(250, 248, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Panel cho logo và hình ảnh
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(121, 85, 72));
        logoPanel.setBounds(0, 0, 250, 400);
        contentPane.add(logoPanel);
        logoPanel.setLayout(null);
        
        // Logo quán cà phê
        JLabel lblLogo = new JLabel("");
        try {
            // Bạn cần thay thế đường dẫn đến file logo thực tế
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/coffee_logo.png"));
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Nếu không tìm thấy logo, sử dụng text thay thế
            lblLogo.setText("COFFEE SHOP");
            lblLogo.setForeground(Color.WHITE);
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 20));
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        }
        lblLogo.setBounds(25, 80, 200, 150);
        logoPanel.add(lblLogo);
        
        // Tên quán cà phê
        JLabel lblShopName = new JLabel("COFFEE SHOP");
        lblShopName.setForeground(Color.WHITE);
        lblShopName.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblShopName.setHorizontalAlignment(SwingConstants.CENTER);
        lblShopName.setBounds(25, 240, 200, 30);
        logoPanel.add(lblShopName);
        
        // Slogan
        JLabel lblSlogan = new JLabel("Hương vị đậm đà, trải nghiệm tuyệt vời");
        lblSlogan.setForeground(new Color(255, 235, 205));
        lblSlogan.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSlogan.setHorizontalAlignment(SwingConstants.CENTER);
        lblSlogan.setBounds(25, 270, 200, 20);
        logoPanel.add(lblSlogan);
        
        // Panel đăng nhập
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(new Color(250, 248, 240));
        loginPanel.setBounds(250, 0, 350, 400);
        contentPane.add(loginPanel);
        loginPanel.setLayout(null);
        
        // Tiêu đề đăng nhập
        JLabel lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(new Color(139, 69, 19));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(25, 50, 300, 30);
        loginPanel.add(lblTitle);
        
        // Label tên đăng nhập
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setBounds(50, 110, 250, 20);
        loginPanel.add(lblUsername);
        
        // Textfield tên đăng nhập
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBounds(50, 135, 250, 35);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        loginPanel.add(txtUsername);
        
        // Label mật khẩu
        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setBounds(50, 180, 250, 20);
        loginPanel.add(lblPassword);
        
        // Password field
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBounds(50, 205, 250, 35);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        loginPanel.add(txtPassword);
        
        // Nút đăng nhập
        btnLogin = new JButton("Đăng Nhập");
        btnLogin.setBackground(new Color(139, 69, 19));
        btnLogin.setForeground(new Color(139, 69, 19));
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBounds(50, 265, 120, 40);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnLogin.addActionListener(this);
        loginPanel.add(btnLogin);
        
        // Nút thoát
        btnExit = new JButton("X");
        btnExit.setBackground(new Color(250, 248, 240));
        btnExit.setForeground(new Color(139, 69, 19));
        btnExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExit.setBounds(288, 10, 30, 20);
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnExit.addActionListener(this);
        loginPanel.add(btnExit);
        
        // Nút đăng ký mới
        btnRegister = new JButton("Đăng Ký");
        btnRegister.setBackground(new Color(139, 69, 19));
        btnRegister.setForeground(new Color(139, 69, 19));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setBounds(180, 265, 120, 40);
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnRegister.addActionListener(this);
        loginPanel.add(btnRegister);
        
        // Thông tin phiên bản
        JLabel lblVersion = new JLabel("Phiên bản 1.0.0");
        lblVersion.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblVersion.setForeground(new Color(150, 150, 150));
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblVersion.setBounds(50, 365, 250, 20);
        loginPanel.add(lblVersion);
    }
    
    /**
     * Xử lý sự kiện từ các nút
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            // Xử lý đăng nhập
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            
            // Kiểm tra thông tin đăng nhập trống
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                showError("Vui lòng nhập đầy đủ thông tin đăng nhập!");
                return;
            }
            
            // Gọi controller để xử lý đăng nhập
            controller.login(username, password);
        } else if (e.getSource() == btnExit) {
            // Xử lý thoát
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Bạn có chắc chắn muốn thoát?", 
                    "Xác nhận thoát", 
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == btnRegister) {
            // Xử lý chuyển sang form đăng ký
            navigateToRegisterView();
        }
    }
    
    /**
     * Chuyển hướng đến giao diện đăng ký
     */
    public void navigateToRegisterView() {
        // Đóng giao diện đăng nhập
        dispose();
        
        // Mở giao diện đăng ký
        RegisterView registerView = new RegisterView();
        registerView.setVisible(true);
    }
    
    /**
     * Hiển thị thông báo lỗi
     * @param message Nội dung lỗi
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Hiển thị thông báo thành công
     * @param message Nội dung thông báo
     */
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Chuyển hướng đến giao diện chính
     * @param vaiTro Vai trò người dùng
     * @param userId ID người dùng
     */
    public void navigateToMainView(String vaiTro, int userId) {
        // Đóng giao diện đăng nhập
        dispose();
        
        // Mở giao diện chính
        MainView mainView = new MainView(vaiTro, userId);
        mainView.setVisible(true);
    }
    
    /**
     * Phương thức main để khởi chạy ứng dụng
     */
    public static void main(String[] args) {
        try {
            // Thiết lập look and feel
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Khởi tạo và hiển thị giao diện đăng nhập
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}