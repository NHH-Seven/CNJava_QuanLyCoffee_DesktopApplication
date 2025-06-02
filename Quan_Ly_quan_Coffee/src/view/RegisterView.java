package view;

import controller.RegisterController;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Giao diện đăng ký tài khoản mới
 * 
 * @author zhieu
 */
public class RegisterView extends JFrame implements ActionListener {
    
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtHoTen;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    private JComboBox<String> cboVaiTro;
    private JButton btnRegister;
    private JButton btnCancel;
    private RegisterController controller;
    
    /**
     * Khởi tạo giao diện đăng ký
     */
    public RegisterView() {
        // Khởi tạo controller
        controller = new RegisterController(this);
        
        // Thiết lập giao diện
        initializeUI();
    }
    
    /**
     * Thiết lập các thành phần giao diện
     */
    private void initializeUI() {
        setTitle("Đăng Ký Tài Khoản - Quản Lý Quán Cà Phê");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 672);
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
        logoPanel.setBounds(0, 0, 250, 635);
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
        
        // Panel đăng ký
        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(new Color(250, 248, 240));
        registerPanel.setBounds(250, 0, 400, 600);
        contentPane.add(registerPanel);
        registerPanel.setLayout(null);
        
        // Tiêu đề đăng ký
        JLabel lblTitle = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(139, 69, 19));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(25, 30, 350, 30);
        registerPanel.add(lblTitle);
        
        // Thông tin tài khoản
        JLabel lblAccountInfo = new JLabel("THÔNG TIN TÀI KHOẢN");
        lblAccountInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAccountInfo.setForeground(new Color(139, 69, 19));
        lblAccountInfo.setBounds(50, 80, 300, 20);
        registerPanel.add(lblAccountInfo);
        
        // Label tên đăng nhập
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setBounds(50, 110, 300, 20);
        registerPanel.add(lblUsername);
        
        // Textfield tên đăng nhập
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBounds(50, 135, 300, 35);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        registerPanel.add(txtUsername);
        
        // Label mật khẩu
        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setBounds(50, 180, 300, 20);
        registerPanel.add(lblPassword);
        
        // Password field
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBounds(50, 205, 300, 35);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        registerPanel.add(txtPassword);
        
        // Label xác nhận mật khẩu
        JLabel lblConfirmPassword = new JLabel("Xác nhận mật khẩu:");
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblConfirmPassword.setBounds(50, 250, 300, 20);
        registerPanel.add(lblConfirmPassword);
        
        // Password field xác nhận
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtConfirmPassword.setBounds(50, 275, 300, 35);
        txtConfirmPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        registerPanel.add(txtConfirmPassword);
        
        // Thông tin cá nhân
        JLabel lblPersonalInfo = new JLabel("THÔNG TIN CÁ NHÂN");
        lblPersonalInfo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPersonalInfo.setForeground(new Color(139, 69, 19));
        lblPersonalInfo.setBounds(50, 320, 300, 20);
        registerPanel.add(lblPersonalInfo);
        
        // Label họ tên
        JLabel lblHoTen = new JLabel("Họ và tên:");
        lblHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblHoTen.setBounds(50, 350, 300, 20);
        registerPanel.add(lblHoTen);
        
        // Textfield họ tên
        txtHoTen = new JTextField();
        txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtHoTen.setBounds(50, 375, 300, 35);
        txtHoTen.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        registerPanel.add(txtHoTen);
        
        // Label giới tính
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblGioiTinh.setBounds(50, 420, 300, 20);
        registerPanel.add(lblGioiTinh);
        
        // Radio button Nam
        rdoNam = new JRadioButton("Nam");
        rdoNam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rdoNam.setBounds(50, 445, 100, 30);
        rdoNam.setBackground(new Color(250, 248, 240));
        rdoNam.setSelected(true);
        registerPanel.add(rdoNam);
        
        // Radio button Nữ
        rdoNu = new JRadioButton("Nữ");
        rdoNu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rdoNu.setBounds(160, 445, 100, 30);
        rdoNu.setBackground(new Color(250, 248, 240));
        registerPanel.add(rdoNu);
        
        // Button group cho giới tính
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdoNam);
        genderGroup.add(rdoNu);
        
        // Label vai trò
        JLabel lblVaiTro = new JLabel("Vai trò:");
        lblVaiTro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblVaiTro.setBounds(50, 485, 300, 20);
        registerPanel.add(lblVaiTro);
        
        // Combobox vai trò
        cboVaiTro = new JComboBox<>(new String[]{"nhanvien", "admin"});
        cboVaiTro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboVaiTro.setBounds(50, 510, 300, 35);
        registerPanel.add(cboVaiTro);
        
        // Nút đăng ký
        btnRegister = new JButton("Đăng Ký");
        btnRegister.setBackground(new Color(121, 85, 72));
        btnRegister.setForeground(new Color(139, 69, 19));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setBounds(50, 560, 140, 40);
        btnRegister.setFocusPainted(false);
        btnRegister.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnRegister.addActionListener(this);
        registerPanel.add(btnRegister);
        
        // Nút hủy
        btnCancel = new JButton("Hủy");
        btnCancel.setBackground(new Color(120, 120, 120));
        btnCancel.setForeground(new Color(139, 69, 19));
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancel.setBounds(210, 560, 140, 40);
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnCancel.addActionListener(this);
        registerPanel.add(btnCancel);
    }
    
    /**
     * Xử lý sự kiện từ các nút
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            // Xử lý đăng ký
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());
            String hoTen = txtHoTen.getText();
            String gioiTinh = rdoNam.isSelected() ? "Nam" : "Nữ";
            String vaiTro = cboVaiTro.getSelectedItem().toString();
            
            // Kiểm tra thông tin đăng ký trống
            if (username.trim().isEmpty() || password.trim().isEmpty() || 
                confirmPassword.trim().isEmpty() || hoTen.trim().isEmpty()) {
                showError("Vui lòng nhập đầy đủ thông tin đăng ký!");
                return;
            }
            
            // Kiểm tra mật khẩu trùng khớp
            if (!password.equals(confirmPassword)) {
                showError("Mật khẩu và xác nhận mật khẩu không khớp!");
                return;
            }
            
            // Gọi controller để xử lý đăng ký
            controller.register(username, password, hoTen, gioiTinh, vaiTro);
            
        } else if (e.getSource() == btnCancel) {
            // Xử lý hủy đăng ký
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Bạn có chắc chắn muốn hủy đăng ký?", 
                    "Xác nhận hủy", 
                    JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginView().setVisible(true);
            }
        }
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
     * Đóng giao diện đăng ký và chuyển về đăng nhập
     */
    public void navigateToLogin() {
        dispose();
        new LoginView().setVisible(true);
    }
}
