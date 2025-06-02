package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


public class MainView extends JFrame {
    
    // Các thành phần giao diện chính
    private JPanel contentPane;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Các view con
    private JPanel nguoiDungView;
    private JPanel nhanVienView;
    private JPanel sanPhamView;
    private JPanel banHangView;
    private JPanel hoaDonView;
    private JPanel thongKeView;
    private JPanel voucherView;
    
    // Vai trò và ID người dùng
    private String vaiTro;
    private int userId;
    
    // Label hiển thị thời gian
    private JLabel lblClock;
    
    /**
     * Khởi tạo giao diện chính
     * @param vaiTro Vai trò của người dùng (admin hoặc nhân viên)
     * @param userId ID của người dùng
     */
    public MainView(String vaiTro, int userId) {
        this.vaiTro = vaiTro;
        this.userId = userId;
        
        // Thiết lập giao diện
        initializeUI();
        
        // Khởi tạo đồng hồ
        initializeClock();
    }
    
    /**
     * Thiết lập các thành phần giao diện
     */
    private void initializeUI() {
        setTitle("Quản Lý Quán Cà Phê");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        // Tạo panel cho header
        JPanel headerPanel = createHeaderPanel();
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        // Tạo panel cho menu bên trái
        menuPanel = createMenuPanel();
        contentPane.add(menuPanel, BorderLayout.WEST);
        
        // Tạo panel chính để hiển thị nội dung
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.YELLOW);
        contentPane.add(contentPanel, BorderLayout.CENTER);
        
        // Khởi tạo các view con
        initializeViews();
        
        // Hiển thị giao diện bán hàng đầu tiên
        cardLayout.show(contentPanel, "BanHang");
    }
    
    /**
     * Tạo panel header
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(121, 85, 72));
        headerPanel.setPreferredSize(new Dimension(1200, 60));
        headerPanel.setLayout(new BorderLayout());
        
        // Logo và tên quán
        JPanel brandPanel = new JPanel();
        brandPanel.setBackground(new Color(121, 85, 72));
        brandPanel.setLayout(new BoxLayout(brandPanel, BoxLayout.X_AXIS));
        brandPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        // Logo
        JLabel lblLogo = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/coffee_logo_small.png"));
            Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Không làm gì nếu không tìm thấy logo
        }
        brandPanel.add(lblLogo);
        brandPanel.add(Box.createHorizontalStrut(10));
        
        // Tên quán
        JLabel lblBrand = new JLabel("COFFEE SHOP");
        lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBrand.setForeground(Color.WHITE);
        brandPanel.add(lblBrand);
        
        headerPanel.add(brandPanel, BorderLayout.WEST);
        
        // Thông tin người dùng và đồng hồ
        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(121, 85, 72));
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        
        // Đồng hồ
        lblClock = new JLabel("00:00:00");
        lblClock.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblClock.setForeground(Color.WHITE);
        userPanel.add(lblClock);
        userPanel.add(Box.createHorizontalStrut(20));
        
        // Icon người dùng
        JLabel lblUserIcon = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/user_icon.png"));
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            lblUserIcon.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            lblUserIcon.setText("👤");
            lblUserIcon.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            lblUserIcon.setForeground(Color.WHITE);
        }
        userPanel.add(lblUserIcon);
        userPanel.add(Box.createHorizontalStrut(10));
        
        // Thông tin người dùng và vai trò
        String vaiTroText = vaiTro.equals("admin") ? "Quản trị viên" : "Nhân viên";
        JLabel lblUser = new JLabel("(" + vaiTroText + ")");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setForeground(Color.WHITE);
        userPanel.add(lblUser);
        userPanel.add(Box.createHorizontalStrut(15));
        
        // Nút đăng xuất
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        userPanel.add(btnLogout);
        
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    /**
     * Tạo panel menu bên trái
     */
    private JPanel createMenuPanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(62, 39, 35));
        sidePanel.setPreferredSize(new Dimension(220, 700));
        sidePanel.setLayout(new BorderLayout());
        
        // Panel chứa các mục menu
        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setBackground(new Color(62, 39, 35));
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Thêm các mục menu
        menuItemsPanel.add(createMenuItem("Bán Hàng", "/images/sale_icon.png", "BanHang"));
        menuItemsPanel.add(createMenuItem("Sản Phẩm", "/images/product_icon.png", "SanPham"));
        menuItemsPanel.add(createMenuItem("Hóa Đơn", "/images/invoice_icon.png", "HoaDon"));
        menuItemsPanel.add(createMenuItem("Voucher", "/images/voucher_icon.png", "Voucher"));
        
        // Thêm các mục menu chỉ dành cho admin
        if ("admin".equals(vaiTro)) {
            menuItemsPanel.add(createMenuItem("Nhân Viên", "/images/staff_icon.png", "NhanVien"));
            menuItemsPanel.add(createMenuItem("Người Dùng", "/images/user_icon.png", "NguoiDung"));
            menuItemsPanel.add(createMenuItem("Thống Kê", "/images/report_icon.png", "ThongKe"));
        }
        
        // Thêm panel menu vào side panel
        JScrollPane scrollPane = new JScrollPane(menuItemsPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sidePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel chân trang cho menu
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(51, 33, 29));
        footerPanel.setPreferredSize(new Dimension(220, 40));
        footerPanel.setLayout(new BorderLayout());
        
        JLabel lblVersion = new JLabel("v1.0.0");
        lblVersion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblVersion.setForeground(new Color(200, 200, 200));
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        footerPanel.add(lblVersion, BorderLayout.CENTER);
        
        sidePanel.add(footerPanel, BorderLayout.SOUTH);
        
        return sidePanel;
    }
    
    /**
     * Tạo một mục menu
     */
    private JPanel createMenuItem(String text, String iconPath, final String cardName) {
        JPanel menuItem = new JPanel();
        menuItem.setLayout(new BoxLayout(menuItem, BoxLayout.X_AXIS));
        menuItem.setBackground(new Color(62, 39, 35));
        menuItem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        menuItem.setMaximumSize(new Dimension(220, 50));
        menuItem.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Icon
        JLabel lblIcon = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblIcon.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Không làm gì nếu không tìm thấy icon
        }
        lblIcon.setPreferredSize(new Dimension(30, 30));
        menuItem.add(lblIcon);
        menuItem.add(Box.createHorizontalStrut(15));
        
        // Text
        JLabel lblText = new JLabel(text);
        lblText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblText.setForeground(Color.WHITE);
        menuItem.add(lblText);
        
        // Thêm sự kiện click
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentPanel, cardName);
                highlightMenuItem(menuItem);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(new Color(86, 54, 49));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!menuItem.getBackground().equals(new Color(121, 85, 72))) {
                    menuItem.setBackground(new Color(62, 39, 35));
                }
            }
        });
        
        return menuItem;
    }
    
    /**
     * Đánh dấu menu item đang được chọn
     */
    private void highlightMenuItem(JPanel selected) {
        // Đặt tất cả menu về màu mặc định
        for (Component comp : menuPanel.getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                JPanel menuItemsPanel = (JPanel) scrollPane.getViewport().getView();
                
                for (Component menuComp : menuItemsPanel.getComponents()) {
                    if (menuComp instanceof JPanel) {
                        menuComp.setBackground(new Color(62, 39, 35));
                    }
                }
            }
        }
        
        // Đánh dấu menu đang chọn
        selected.setBackground(new Color(121, 85, 72));
    }
    
    /**
     * Khởi tạo các view con
     */
    private void initializeViews() {
        // Khởi tạo các view - thay vì tạo các JFrame, tạo các JPanel
        banHangView = new BanHangView(userId);
        sanPhamView = new SanPhamView();
        hoaDonView = new HoaDonView();
        voucherView = new VoucherView();
        
        // Thêm các view vào contentPanel
        contentPanel.add(banHangView, "BanHang");
        contentPanel.add(sanPhamView, "SanPham");
        contentPanel.add(hoaDonView, "HoaDon");
        contentPanel.add(voucherView, "Voucher");
        
        // Các view chỉ dành cho admin
        if ("admin".equals(vaiTro)) {
            nhanVienView = new NhanVienView();
            nguoiDungView = new NguoiDungView();
            thongKeView = new ThongKeView();
            
            contentPanel.add(nhanVienView, "NhanVien");
            contentPanel.add(nguoiDungView, "NguoiDung");
            contentPanel.add(thongKeView, "ThongKe");
        }
    }
    
    /**
     * Helper method to create view panels
     * This is a temporary solution - later you should integrate with your actual view classes
     */
    private JPanel createViewPanel(String name, int userId) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel(name);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(lblTitle, BorderLayout.NORTH);
        
        JPanel contentArea = new JPanel();
        contentArea.setLayout(new BorderLayout());
        contentArea.setBackground(Color.WHITE);
        
        // This is temporary placeholder content
        JLabel lblContent = new JLabel("Nội dung " + name + " sẽ được hiển thị ở đây.");
        lblContent.setHorizontalAlignment(SwingConstants.CENTER);
        contentArea.add(lblContent, BorderLayout.CENTER);
        
        panel.add(contentArea, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Khởi tạo đồng hồ
     */
    private void initializeClock() {
        // Tạo timer để cập nhật thời gian mỗi giây
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();
    }
    
    /**
     * Cập nhật đồng hồ
     */
    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        lblClock.setText(sdf.format(new Date()));
    }
    
    /**
     * Xử lý đăng xuất
     */
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", 
                "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Đóng cửa sổ hiện tại
            
            // Mở lại giao diện đăng nhập
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        }
    }
    
    /**
     * Để test giao diện này riêng lẻ
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView("admin", 1).setVisible(true);
            }
        });
    }
}
