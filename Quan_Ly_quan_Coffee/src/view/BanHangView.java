package view;

import controller.BanHangController;
import model.SanPham;
import model.HoaDon;
import model.ChiTietHoaDon;
import model.Voucher;
import utils.DatabaseUtil;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * Giao diện bán hàng
 * 
 * @author zhieu
 */
public class BanHangView extends JPanel {
    
    private BanHangController controller;
    private int userId;  // ID của người dùng đang đăng nhập
    
    // Components
    private JPanel mainPanel, productPanel, cartPanel;
    private JTable tableSanPham, tableGioHang;
    private DefaultTableModel modelSanPham, modelGioHang;
    private JTextField txtSearch, txtSoLuong;
    private JComboBox<Voucher> cboVoucher;
    private JButton btnThem, btnXoa, btnThanhToan, btnTimKiem, btnApDungVoucher, btnBoApDungVoucher;
    private JLabel lblTongTien, lblGiamGia, lblThanhTien;
    private JComboBox<String> cboLoaiSanPham;
    
    // Data
    private List<SanPham> danhSachSanPham;
    private List<ChiTietHoaDon> gioHang;
    private Voucher voucherHienTai;
    private NumberFormat currencyFormat = new DecimalFormat("#,###.##");
    
    // Colors
    private Color primaryColor = new Color(121, 85, 72);
    private Color secondaryColor = new Color(188, 170, 164);
    private Color accentColor = new Color(62, 39, 35);
    private Color backgroundColor = new Color(245, 245, 245);
    private Color successColor = new Color(76, 175, 80);
    private Color warningColor = new Color(255, 152, 0);
    private Color dangerColor = new Color(244, 67, 54);
    
    /**
     * Constructor mặc định không có tham số
     */
    public BanHangView() {
        this(0); // Gọi constructor có tham số với giá trị mặc định là 0
    }
    
    /**
     * Constructor với tham số userId
     * 
     * @param userId ID của người dùng đang đăng nhập
     */
    public BanHangView(int userId) {
        this.userId = userId;
        this.controller = new BanHangController();
        this.gioHang = new ArrayList<>();
        initializeUI();
        loadSanPhamData();
    }
    
    /**
     * Khởi tạo giao diện
     */
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        
        // Create main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(backgroundColor);
        
        // Title
        JLabel lblTitle = new JLabel("QUẢN LÝ BÁN HÀNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(primaryColor);
        lblTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        // Create panels
        createProductPanel();
        createCartPanel();
        
        // Add panels to main panel
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(productPanel, BorderLayout.CENTER);
        mainPanel.add(cartPanel, BorderLayout.EAST);
        
        add(mainPanel);
    }
    
    private void createProductPanel() {
        productPanel = new JPanel(new BorderLayout(5, 5));
        productPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(primaryColor, 1),
            "Danh sách sản phẩm",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            primaryColor));
        productPanel.setBackground(backgroundColor);
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        searchPanel.setBackground(backgroundColor);
        
        txtSearch = new JTextField(20);
        txtSearch.setPreferredSize(new Dimension(200, 30));
        
        cboLoaiSanPham = new JComboBox<>(new String[]{"Tất cả", "Cà phê", "Trà", "Nước ép", "Bánh ngọt", "Thức ăn nhanh", "Khác"});
        cboLoaiSanPham.setPreferredSize(new Dimension(150, 30));
        
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(primaryColor);
        btnTimKiem.setForeground(Color.BLACK);
        btnTimKiem.setFocusPainted(false);
        
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(txtSearch);
        searchPanel.add(new JLabel("Loại:"));
        searchPanel.add(cboLoaiSanPham);
        searchPanel.add(btnTimKiem);
        
        // Product table
        String[] columns = {"ID", "Tên sản phẩm", "Loại", "Đơn giá", "Số lượng", "Trạng thái"};
        modelSanPham = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableSanPham = new JTable(modelSanPham);
        tableSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSanPham.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(tableSanPham);
        
        // Add to cart panel
        JPanel addToCartPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        addToCartPanel.setBackground(backgroundColor);
        
        txtSoLuong = new JTextField(5);
        txtSoLuong.setPreferredSize(new Dimension(60, 30));
        
        btnThem = new JButton("Thêm vào giỏ");
        btnThem.setBackground(successColor);
        btnThem.setForeground(Color.BLACK);
        btnThem.setFocusPainted(false);
        
        addToCartPanel.add(new JLabel("Số lượng:"));
        addToCartPanel.add(txtSoLuong);
        addToCartPanel.add(btnThem);
        
        // Add components to product panel
        productPanel.add(searchPanel, BorderLayout.NORTH);
        productPanel.add(scrollPane, BorderLayout.CENTER);
        productPanel.add(addToCartPanel, BorderLayout.SOUTH);
        
        // Add event listeners
        btnTimKiem.addActionListener(e -> timKiemSanPham());
        btnThem.addActionListener(e -> themVaoGioHang());
        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    themVaoGioHang();
                }
            }
        });
    }
    
    private void createCartPanel() {
        cartPanel = new JPanel(new BorderLayout(5, 5));
        cartPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(primaryColor, 1),
            "Giỏ hàng",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            primaryColor));
        cartPanel.setPreferredSize(new Dimension(400, 0));
        cartPanel.setBackground(backgroundColor);
        
        // Cart table
        String[] columns = {"ID", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        modelGioHang = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableGioHang = new JTable(modelGioHang);
        tableGioHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableGioHang.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(tableGioHang);
        
        // Voucher panel
        JPanel voucherPanel = new JPanel();
        voucherPanel.setLayout(new BoxLayout(voucherPanel, BoxLayout.Y_AXIS));
        voucherPanel.setBackground(backgroundColor);
        
        // Tạo combobox voucher
        cboVoucher = new JComboBox<>();
        cboVoucher.setPreferredSize(new Dimension(200, 30));
        cboVoucher.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Voucher) {
                    Voucher voucher = (Voucher) value;
                    setText(voucher.getMaVoucher() + " - Giảm " + voucher.getPhanTramGiamGia() + "%");
                }
                return this;
            }
        });
        
        // Thêm item mặc định
        cboVoucher.addItem(null);
        
        // Load danh sách voucher
        List<Voucher> vouchers = controller.getVouchersConHan();
        for (Voucher v : vouchers) {
            cboVoucher.addItem(v);
        }
        
        btnApDungVoucher = new JButton("Áp dụng");
        btnApDungVoucher.setHorizontalAlignment(SwingConstants.LEFT);
        btnApDungVoucher.setBackground(primaryColor);
        btnApDungVoucher.setForeground(Color.BLACK);
        btnApDungVoucher.setFocusPainted(false);
        
        btnBoApDungVoucher = new JButton("Bỏ áp dụng");
        btnBoApDungVoucher.setHorizontalAlignment(SwingConstants.LEFT);
        btnBoApDungVoucher.setBackground(warningColor);
        btnBoApDungVoucher.setForeground(Color.BLACK);
        btnBoApDungVoucher.setFocusPainted(false);
        btnBoApDungVoucher.setEnabled(false);
        
        voucherPanel.add(new JLabel("Chọn voucher:"));
        voucherPanel.add(cboVoucher);
        voucherPanel.add(btnApDungVoucher);
        voucherPanel.add(btnBoApDungVoucher);
        
        // Summary panel
        JPanel summaryPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        summaryPanel.setBackground(backgroundColor);
        
        lblTongTien = new JLabel("0 VND");
        lblGiamGia = new JLabel("0 VND");
        lblThanhTien = new JLabel("0 VND");
        
        summaryPanel.add(new JLabel("Tổng tiền:"));
        summaryPanel.add(lblTongTien);
        summaryPanel.add(new JLabel("Giảm giá:"));
        summaryPanel.add(lblGiamGia);
        summaryPanel.add(new JLabel("Thành tiền:"));
        summaryPanel.add(lblThanhTien);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBackground(backgroundColor);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setBackground(dangerColor);
        btnXoa.setForeground(Color.BLACK);
        btnXoa.setFocusPainted(false);
        
        btnThanhToan = new JButton("Thanh toán");
        btnThanhToan.setBackground(successColor);
        btnThanhToan.setForeground(Color.BLACK);
        btnThanhToan.setFocusPainted(false);
        
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnThanhToan);
        
        // Combine summaryPanel and buttonPanel into a southPanel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBackground(backgroundColor);
        southPanel.add(summaryPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add components to cart panel
        cartPanel.add(voucherPanel, BorderLayout.NORTH);
        cartPanel.add(scrollPane, BorderLayout.CENTER);
        cartPanel.add(southPanel, BorderLayout.SOUTH);
        
        // Add event listeners
        btnXoa.addActionListener(e -> xoaKhoiGioHang());
        btnThanhToan.addActionListener(e -> thanhToan());
        btnApDungVoucher.addActionListener(e -> apDungVoucher());
        btnBoApDungVoucher.addActionListener(e -> boApDungVoucher());
    }
    
    private void loadSanPhamData() {
        try {
            danhSachSanPham = controller.getAllSanPham();
            if (danhSachSanPham.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Không có sản phẩm nào trong kho!", 
                    "Thông báo", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            updateSanPhamTable(danhSachSanPham);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Lỗi khi tải dữ liệu sản phẩm: " + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void updateSanPhamTable(List<SanPham> danhSach) {
        modelSanPham.setRowCount(0);
        for (SanPham sp : danhSach) {
            Object[] row = {
                sp.getId(),
                sp.getTenSanPham(),
                sp.getLoai(),
                currencyFormat.format(sp.getDonGia()) + " VND",
                sp.getSoLuong(),
                sp.isTrangThai() ? "Còn hàng" : "Hết hàng"
            };
            modelSanPham.addRow(row);
        }
    }
    
    private void timKiemSanPham() {
        String tuKhoa = txtSearch.getText().trim();
        String loai = cboLoaiSanPham.getSelectedItem().toString();
        
        List<SanPham> ketQua;
        if (loai.equals("Tất cả")) {
            if (tuKhoa.isEmpty()) {
                ketQua = controller.getAllSanPham();
            } else {
                ketQua = controller.timKiemSanPhamTheoTen(tuKhoa);
            }
        } else {
            if (!tuKhoa.isEmpty()) {
                // Nếu có cả từ khóa và loại, tìm theo tên trước
                ketQua = controller.timKiemSanPhamTheoTen(tuKhoa);
                // Lọc thêm theo loại
                ketQua.removeIf(sp -> !sp.getLoai().equals(loai));
            } else {
                // Nếu chỉ có loại, tìm trực tiếp theo loại
                ketQua = controller.timKiemSanPhamTheoLoai(loai);
            }
        }
        
        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Không tìm thấy sản phẩm nào phù hợp!", 
                "Thông báo", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        updateSanPhamTable(ketQua);
    }
    
    private void themVaoGioHang() {
        int selectedRow = tableSanPham.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(modelSanPham.getValueAt(selectedRow, 0).toString());
        String tenSanPham = modelSanPham.getValueAt(selectedRow, 1).toString();
        BigDecimal donGia = new BigDecimal(modelSanPham.getValueAt(selectedRow, 3).toString().replace(" VND", "").replace(",", ""));
        int soLuongTon = Integer.parseInt(modelSanPham.getValueAt(selectedRow, 4).toString());
        
        int soLuong;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (soLuong > soLuongTon) {
                JOptionPane.showMessageDialog(this, "Số lượng vượt quá số lượng tồn kho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
        for (ChiTietHoaDon cthd : gioHang) {
            if (cthd.getSanPham_id() == id) {
                int tongSoLuong = cthd.getSoLuong() + soLuong;
                if (tongSoLuong > soLuongTon) {
                    JOptionPane.showMessageDialog(this, "Tổng số lượng vượt quá số lượng tồn kho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cthd.setSoLuong(tongSoLuong);
                capNhatGioHang();
                return;
            }
        }
        
        // Thêm sản phẩm mới vào giỏ hàng
        ChiTietHoaDon cthd = new ChiTietHoaDon();
        cthd.setSanPham_id(id);
        cthd.setSoLuong(soLuong);
        cthd.setDonGia(donGia);
        gioHang.add(cthd);
        
        capNhatGioHang();
        txtSoLuong.setText("1");
    }
    
    private void capNhatGioHang() {
        modelGioHang.setRowCount(0);
        BigDecimal tongTien = BigDecimal.ZERO;
        
        for (ChiTietHoaDon cthd : gioHang) {
            SanPham sp = controller.getSanPhamById(cthd.getSanPham_id());
            BigDecimal thanhTien = cthd.getDonGia().multiply(new BigDecimal(cthd.getSoLuong()));
            tongTien = tongTien.add(thanhTien);
            
            Object[] row = {
                sp.getId(),
                sp.getTenSanPham(),
                cthd.getSoLuong(),
                currencyFormat.format(cthd.getDonGia()) + " VND",
                currencyFormat.format(thanhTien) + " VND"
            };
            modelGioHang.addRow(row);
        }
        
        // Cập nhật tổng tiền và giảm giá
        BigDecimal giamGia = BigDecimal.ZERO;
        if (voucherHienTai != null) {
            giamGia = tongTien.multiply(voucherHienTai.getPhanTramGiamGia())
                             .divide(new BigDecimal(100));
        }
        
        lblTongTien.setText(currencyFormat.format(tongTien) + " VND");
        lblGiamGia.setText(currencyFormat.format(giamGia) + " VND");
        lblThanhTien.setText(currencyFormat.format(tongTien.subtract(giamGia)) + " VND");
    }
    
    private void xoaKhoiGioHang() {
        int selectedRow = tableGioHang.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        gioHang.remove(selectedRow);
        capNhatGioHang();
    }
    
    private void apDungVoucher() {
        Voucher selectedVoucher = (Voucher) cboVoucher.getSelectedItem();
        if (selectedVoucher == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn voucher!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        voucherHienTai = selectedVoucher;
        capNhatGioHang();
        btnBoApDungVoucher.setEnabled(true);
        JOptionPane.showMessageDialog(this, 
            "Áp dụng voucher thành công!\nGiảm giá: " + voucherHienTai.getPhanTramGiamGia() + "%", 
            "Thông báo", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void boApDungVoucher() {
        voucherHienTai = null;
        cboVoucher.setSelectedIndex(0);
        btnBoApDungVoucher.setEnabled(false);
        capNhatGioHang();
        JOptionPane.showMessageDialog(this, "Đã bỏ áp dụng voucher!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void thanhToan() {
        if (gioHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Tạo hóa đơn mới
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNhanVien_id(userId);
        if (voucherHienTai != null) {
            hoaDon.setVoucher_id(voucherHienTai.getId());
        }
        
        // Tính tổng tiền
        BigDecimal tongTien = BigDecimal.ZERO;
        for (ChiTietHoaDon cthd : gioHang) {
            tongTien = tongTien.add(cthd.getDonGia().multiply(new BigDecimal(cthd.getSoLuong())));
        }
        
        // Áp dụng giảm giá nếu có voucher
        BigDecimal giamGia = BigDecimal.ZERO;
        if (voucherHienTai != null) {
            giamGia = tongTien.multiply(voucherHienTai.getPhanTramGiamGia())
                             .divide(new BigDecimal(100));
        }
        
        hoaDon.setTongTien(tongTien);
        hoaDon.setGiamGia(giamGia);
        hoaDon.setThanhTien(tongTien.subtract(giamGia));
        
        // Lưu hóa đơn và chi tiết hóa đơn
        boolean result = controller.taoHoaDon(hoaDon, gioHang);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            // Reset giỏ hàng và form
            gioHang.clear();
            voucherHienTai = null;
            cboVoucher.setSelectedIndex(0);
            capNhatGioHang();
        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Bán Hàng");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new BanHangView());
            frame.pack();
            frame.setSize(1200, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}