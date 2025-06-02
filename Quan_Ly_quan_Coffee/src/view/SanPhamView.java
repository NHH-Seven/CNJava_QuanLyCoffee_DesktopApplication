package view;
import controller.SanPhamController;
import model.SanPham;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

/**
 * Giao diện quản lý sản phẩm
 */
public class SanPhamView extends JPanel {
    private SanPhamController controller;
    
    // Khai báo các thành phần giao diện
    private JPanel mainPanel, formPanel, tablePanel, buttonPanel, imagePanel;
    private JTextField txtId, txtTenSanPham, txtDonGia, txtSearch, txtSoLuong;
    private JTextArea txtMoTa;
    private JComboBox<String> cboLoai, cboTrangThai;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem, btnChonAnh;
    private JTable tableSanPham;
    private DefaultTableModel tableModel;
    private JLabel lblImagePreview;
    private DatePicker dateNgayNhapHang, dateNgayHetHan;
    
    private String currentImagePath = "";
    private NumberFormat currencyFormat = new DecimalFormat("#,###.##");
    
    // Thiết lập màu sắc chủ đạo cho ứng dụng
    private Color primaryColor = new Color(121, 85, 72); // Màu nâu theo theme cà phê
    private Color secondaryColor = new Color(188, 170, 164); // Màu nâu nhạt
    private Color accentColor = new Color(62, 39, 35); // Màu nâu đậm
    private Color backgroundColor = new Color(245, 245, 245); // Màu nền nhẹ
    private Color successColor = new Color(76, 175, 80); // Màu xanh lá
    private Color warningColor = new Color(255, 152, 0); // Màu cam
    private Color dangerColor = new Color(244, 67, 54); // Màu đỏ
    
    public SanPhamView() {
        // Khởi tạo controller
        controller = new SanPhamController();
        
        // Thiết lập layout cho panel chính
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        
        // Tạo panel chính có border layout
        createMainPanel();
        
        // Load dữ liệu ban đầu
        loadSanPhamData();
    }
    
    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(backgroundColor);
        
        // Tạo tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(primaryColor);
        lblTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        // Tạo các panel con
        createFormPanel();
        createTablePanel();
        createButtonPanel();
        
        // Thêm vào main panel
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Tạo panel chứa form và table để xếp cạnh nhau
        JPanel contentPanel = new JPanel(new BorderLayout(15, 0));
        contentPanel.setBackground(backgroundColor);
        contentPanel.add(formPanel, BorderLayout.WEST);
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Thêm mainPanel vào this (JPanel)
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void createFormPanel() {
        formPanel = new JPanel(new BorderLayout(0, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                "Thông tin sản phẩm",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        formPanel.setPreferredSize(new Dimension(400, 500));
        formPanel.setBackground(backgroundColor);

        // Panel cho các trường input
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(backgroundColor);
        
        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font fieldFont = new Font("Arial", Font.PLAIN, 13);

        // ID
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(labelFont);
        GridBagConstraints gbcLblId = new GridBagConstraints();
        gbcLblId.gridx = 0;
        gbcLblId.gridy = 0;
        gbcLblId.insets = new Insets(8, 8, 8, 8);
        gbcLblId.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblId, gbcLblId);

        txtId = new JTextField();
        txtId.setFont(fieldFont);
        txtId.setEditable(false);
        txtId.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbcTxtId = new GridBagConstraints();
        gbcTxtId.gridx = 1;
        gbcTxtId.gridy = 0;
        gbcTxtId.insets = new Insets(8, 8, 8, 8);
        gbcTxtId.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtId.weightx = 1.0;
        inputPanel.add(txtId, gbcTxtId);

        // Tên sản phẩm
        JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
        lblTenSanPham.setFont(labelFont);
        GridBagConstraints gbcLblTenSP = new GridBagConstraints();
        gbcLblTenSP.gridx = 0;
        gbcLblTenSP.gridy = 1;
        gbcLblTenSP.insets = new Insets(8, 8, 8, 8);
        gbcLblTenSP.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblTenSanPham, gbcLblTenSP);

        txtTenSanPham = new JTextField();
        txtTenSanPham.setFont(fieldFont);
        GridBagConstraints gbcTxtTenSP = new GridBagConstraints();
        gbcTxtTenSP.gridx = 1;
        gbcTxtTenSP.gridy = 1;
        gbcTxtTenSP.insets = new Insets(8, 8, 8, 8);
        gbcTxtTenSP.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtTenSP.weightx = 1.0;
        inputPanel.add(txtTenSanPham, gbcTxtTenSP);

        // Loại sản phẩm
        JLabel lblLoai = new JLabel("Loại:");
        lblLoai.setFont(labelFont);
        GridBagConstraints gbcLblLoai = new GridBagConstraints();
        gbcLblLoai.gridx = 0;
        gbcLblLoai.gridy = 2;
        gbcLblLoai.insets = new Insets(8, 8, 8, 8);
        gbcLblLoai.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblLoai, gbcLblLoai);

        cboLoai = new JComboBox<>(new String[]{"Cà phê", "Trà", "Nước ép", "Bánh ngọt", "Thức ăn nhanh", "Khác"});
        cboLoai.setFont(fieldFont);
        GridBagConstraints gbcCboLoai = new GridBagConstraints();
        gbcCboLoai.gridx = 1;
        gbcCboLoai.gridy = 2;
        gbcCboLoai.insets = new Insets(8, 8, 8, 8);
        gbcCboLoai.fill = GridBagConstraints.HORIZONTAL;
        gbcCboLoai.weightx = 1.0;
        inputPanel.add(cboLoai, gbcCboLoai);
        
        // Đơn giá
        JLabel lblDonGia = new JLabel("Đơn giá:");
        lblDonGia.setFont(labelFont);
        GridBagConstraints gbcLblDonGia = new GridBagConstraints();
        gbcLblDonGia.gridx = 0;
        gbcLblDonGia.gridy = 3;
        gbcLblDonGia.insets = new Insets(8, 8, 8, 8);
        gbcLblDonGia.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblDonGia, gbcLblDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setFont(fieldFont);
        GridBagConstraints gbcTxtDonGia = new GridBagConstraints();
        gbcTxtDonGia.gridx = 1;
        gbcTxtDonGia.gridy = 3;
        gbcTxtDonGia.insets = new Insets(8, 8, 8, 8);
        gbcTxtDonGia.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtDonGia.weightx = 1.0;
        inputPanel.add(txtDonGia, gbcTxtDonGia);
        
        // Số lượng
        JLabel lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setFont(labelFont);
        GridBagConstraints gbcLblSoLuong = new GridBagConstraints();
        gbcLblSoLuong.gridx = 0;
        gbcLblSoLuong.gridy = 4;
        gbcLblSoLuong.insets = new Insets(8, 8, 8, 8);
        gbcLblSoLuong.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblSoLuong, gbcLblSoLuong);

        txtSoLuong = new JTextField();
        txtSoLuong.setFont(fieldFont);
        GridBagConstraints gbcTxtSoLuong = new GridBagConstraints();
        gbcTxtSoLuong.gridx = 1;
        gbcTxtSoLuong.gridy = 4;
        gbcTxtSoLuong.insets = new Insets(8, 8, 8, 8);
        gbcTxtSoLuong.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtSoLuong.weightx = 1.0;
        inputPanel.add(txtSoLuong, gbcTxtSoLuong);

        // Ngày nhập hàng
        JLabel lblNgayNhapHang = new JLabel("Ngày nhập hàng:");
        lblNgayNhapHang.setFont(labelFont);
        GridBagConstraints gbcLblNgayNhapHang = new GridBagConstraints();
        gbcLblNgayNhapHang.gridx = 0;
        gbcLblNgayNhapHang.gridy = 5;
        gbcLblNgayNhapHang.insets = new Insets(8, 8, 8, 8);
        gbcLblNgayNhapHang.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblNgayNhapHang, gbcLblNgayNhapHang);

        DatePickerSettings dateSettingsNhapHang = new DatePickerSettings();
        dateSettingsNhapHang.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettingsNhapHang.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");
        
        dateNgayNhapHang = new DatePicker(dateSettingsNhapHang);
        dateNgayNhapHang.setFont(fieldFont);
        GridBagConstraints gbcDateNgayNhapHang = new GridBagConstraints();
        gbcDateNgayNhapHang.gridx = 1;
        gbcDateNgayNhapHang.gridy = 5;
        gbcDateNgayNhapHang.insets = new Insets(8, 8, 8, 8);
        gbcDateNgayNhapHang.fill = GridBagConstraints.HORIZONTAL;
        gbcDateNgayNhapHang.weightx = 1.0;
        inputPanel.add(dateNgayNhapHang, gbcDateNgayNhapHang);

        // Ngày hết hạn
        JLabel lblNgayHetHan = new JLabel("Ngày hết hạn:");
        lblNgayHetHan.setFont(labelFont);
        GridBagConstraints gbcLblNgayHetHan = new GridBagConstraints();
        gbcLblNgayHetHan.gridx = 0;
        gbcLblNgayHetHan.gridy = 6;
        gbcLblNgayHetHan.insets = new Insets(8, 8, 8, 8);
        gbcLblNgayHetHan.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblNgayHetHan, gbcLblNgayHetHan);

        DatePickerSettings dateSettingsHetHan = new DatePickerSettings();
        dateSettingsHetHan.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettingsHetHan.setFormatForDatesBeforeCommonEra("dd/MM/yyyy");
        
        dateNgayHetHan = new DatePicker(dateSettingsHetHan);
        dateNgayHetHan.setFont(fieldFont);
        GridBagConstraints gbcDateNgayHetHan = new GridBagConstraints();
        gbcDateNgayHetHan.gridx = 1;
        gbcDateNgayHetHan.gridy = 6;
        gbcDateNgayHetHan.insets = new Insets(8, 8, 8, 8);
        gbcDateNgayHetHan.fill = GridBagConstraints.HORIZONTAL;
        gbcDateNgayHetHan.weightx = 1.0;
        inputPanel.add(dateNgayHetHan, gbcDateNgayHetHan);

        // Trạng thái
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setFont(labelFont);
        GridBagConstraints gbcLblTrangThai = new GridBagConstraints();
        gbcLblTrangThai.gridx = 0;
        gbcLblTrangThai.gridy = 7;
        gbcLblTrangThai.insets = new Insets(8, 8, 8, 8);
        gbcLblTrangThai.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblTrangThai, gbcLblTrangThai);

        String[] trangThaiOptions = {"Còn hàng", "Hết hàng"};
        cboTrangThai = new JComboBox<>(trangThaiOptions);
        cboTrangThai.setFont(fieldFont);
        GridBagConstraints gbcCboTrangThai = new GridBagConstraints();
        gbcCboTrangThai.gridx = 1;
        gbcCboTrangThai.gridy = 7;
        gbcCboTrangThai.insets = new Insets(8, 8, 8, 8);
        gbcCboTrangThai.fill = GridBagConstraints.HORIZONTAL;
        gbcCboTrangThai.weightx = 1.0;
        inputPanel.add(cboTrangThai, gbcCboTrangThai);
        
        // Mô tả
        JLabel lblMoTa = new JLabel("Mô tả:");
        lblMoTa.setFont(labelFont);
        GridBagConstraints gbcLblMoTa = new GridBagConstraints();
        gbcLblMoTa.gridx = 0;
        gbcLblMoTa.gridy = 8;
        gbcLblMoTa.insets = new Insets(8, 8, 8, 8);
        gbcLblMoTa.anchor = GridBagConstraints.WEST;
        inputPanel.add(lblMoTa, gbcLblMoTa);

        txtMoTa = new JTextArea();
        txtMoTa.setFont(fieldFont);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        JScrollPane scrollMoTa = new JScrollPane(txtMoTa);
        scrollMoTa.setPreferredSize(new Dimension(0, 80));
        
        GridBagConstraints gbcScrollMoTa = new GridBagConstraints();
        gbcScrollMoTa.gridx = 1;
        gbcScrollMoTa.gridy = 8;
        gbcScrollMoTa.insets = new Insets(8, 8, 8, 8);
        gbcScrollMoTa.fill = GridBagConstraints.BOTH;
        gbcScrollMoTa.weightx = 1.0;
        gbcScrollMoTa.weighty = 1.0;
        inputPanel.add(scrollMoTa, gbcScrollMoTa);
        
        // Hình ảnh
        JLabel lblHinhAnh = new JLabel("Hình ảnh:");
        lblHinhAnh.setFont(labelFont);
        GridBagConstraints gbcLblHinhAnh = new GridBagConstraints();
        gbcLblHinhAnh.gridx = 0;
        gbcLblHinhAnh.gridy = 9;
        gbcLblHinhAnh.insets = new Insets(8, 8, 8, 8);
        gbcLblHinhAnh.anchor = GridBagConstraints.NORTHWEST;
        inputPanel.add(lblHinhAnh, gbcLblHinhAnh);

        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setFont(new Font("Arial", Font.PLAIN, 12));
        btnChonAnh.setFocusPainted(false);
        btnChonAnh.setBackground(secondaryColor);
        btnChonAnh.setForeground(Color.WHITE);
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chonAnh();
            }
        });
        
        // Panel hiển thị ảnh
        imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        imagePanel.setPreferredSize(new Dimension(150, 150));
        
        lblImagePreview = new JLabel();
        lblImagePreview.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(lblImagePreview, BorderLayout.CENTER);
        
        GridBagConstraints gbcBtnChonAnh = new GridBagConstraints();
        gbcBtnChonAnh.gridx = 1;
        gbcBtnChonAnh.gridy = 9;
        gbcBtnChonAnh.insets = new Insets(8, 8, 8, 8);
        gbcBtnChonAnh.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnChonAnh.anchor = GridBagConstraints.NORTH;
        inputPanel.add(btnChonAnh, gbcBtnChonAnh);
        
        GridBagConstraints gbcImagePanel = new GridBagConstraints();
        gbcImagePanel.gridx = 1;
        gbcImagePanel.gridy = 10;
        gbcImagePanel.insets = new Insets(0, 8, 8, 8);
        gbcImagePanel.fill = GridBagConstraints.BOTH;
        gbcImagePanel.weightx = 1.0;
        gbcImagePanel.weighty = 2.0;
        inputPanel.add(imagePanel, gbcImagePanel);
        
        formPanel.add(inputPanel, BorderLayout.CENTER);
    }
    
    private void createTablePanel() {
        // Tạo panel chứa bảng hiển thị dữ liệu
        tablePanel = new JPanel(new BorderLayout(5, 10));
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                "Danh sách sản phẩm", 
                TitledBorder.LEFT, 
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        tablePanel.setBackground(backgroundColor);
        
        // Tạo model cho table
        String[] columnNames = {"ID", "Tên sản phẩm", "Loại", "Đơn giá", "Số lượng", "Ngày nhập", "Ngày hết hạn", "Trạng thái", "Mô tả"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép edit trực tiếp trên bảng
            }
        };
        
        // Tạo table
        tableSanPham = new JTable(tableModel);
        tableSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSanPham.setRowHeight(28);
        tableSanPham.setGridColor(new Color(240, 240, 240));
        tableSanPham.setFont(new Font("Arial", Font.PLAIN, 13));
        tableSanPham.setBackground(Color.WHITE);
        tableSanPham.setSelectionBackground(secondaryColor);
        tableSanPham.setSelectionForeground(Color.WHITE);
        
        // Header style
        tableSanPham.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableSanPham.getTableHeader().setBackground(primaryColor);
        tableSanPham.getTableHeader().setForeground(Color.BLACK);
        tableSanPham.getTableHeader().setPreferredSize(new Dimension(0, 35));
        
        // Thêm sự kiện khi click vào một dòng
        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableSanPham.getSelectedRow();
                if (selectedRow >= 0) {
                    displaySelectedSanPham(selectedRow);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableSanPham);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // Tạo panel tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(backgroundColor);
        
        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 13));
        
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 13));
        txtSearch.setPreferredSize(new Dimension(200, 28));
        
        JComboBox<String> cboSearchType = new JComboBox<>(new String[]{"Tên sản phẩm", "Loại sản phẩm"});
        cboSearchType.setFont(new Font("Arial", Font.PLAIN, 13));
        cboSearchType.setPreferredSize(new Dimension(150, 28));
        
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setFont(new Font("Arial", Font.BOLD, 12));
        btnTimKiem.setBackground(primaryColor);
        btnTimKiem.setForeground(new Color(0, 0, 0));
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setPreferredSize(new Dimension(100, 28));
        
        // Thêm sự kiện tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemSanPham(txtSearch.getText().trim(), 
                        cboSearchType.getSelectedItem().toString());
            }
        });
        
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(cboSearchType);
        searchPanel.add(btnTimKiem);
        
        // Thêm vào panel
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void createButtonPanel() {
        // Tạo panel chứa các nút chức năng
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(backgroundColor);
        
        // Tạo các nút
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        
        // Thiết lập style cho nút
        Font buttonFont = new Font("Arial", Font.BOLD, 13);
        
        btnThem.setFont(buttonFont);
        btnSua.setFont(buttonFont);
        btnXoa.setFont(buttonFont);
        btnLamMoi.setFont(buttonFont);
        
        btnThem.setBackground(successColor);
        btnSua.setBackground(warningColor);
        btnXoa.setBackground(dangerColor);
        btnLamMoi.setBackground(new Color(158, 158, 158));
        
        btnThem.setForeground(new Color(0, 0, 0));
        btnSua.setForeground(new Color(0, 0, 0));
        btnXoa.setForeground(new Color(0, 0, 0));
        btnLamMoi.setForeground(new Color(0, 0, 0));
        
        btnThem.setFocusPainted(false);
        btnSua.setFocusPainted(false);
        btnXoa.setFocusPainted(false);
        btnLamMoi.setFocusPainted(false);
        
        Dimension buttonSize = new Dimension(120, 35);
        btnThem.setPreferredSize(buttonSize);
        btnSua.setPreferredSize(buttonSize);
        btnXoa.setPreferredSize(buttonSize);
        btnLamMoi.setPreferredSize(buttonSize);
        
        // Thêm sự kiện cho nút
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPham();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaSanPham();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPham();
            }
        });
        
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lamMoiForm();
            }
        });
        
        // Thêm vào panel
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
    }
    
    // Các phương thức xử lý sự kiện
    private void themSanPham() {
        if (!validateInput()) {
            return;
        }
        
        SanPham sanPham = getSanPhamFromForm();
        System.out.println("Thông tin sản phẩm trước khi thêm:");
        System.out.println("Tên: " + sanPham.getTenSanPham());
        System.out.println("Loại: " + sanPham.getLoai());
        System.out.println("Đơn giá: " + sanPham.getDonGia());
        System.out.println("Trạng thái: " + sanPham.isTrangThai());
        
        boolean result = controller.themSanPham(sanPham);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadSanPhamData();
            lamMoiForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaSanPham() {
        int selectedRow = tableSanPham.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateInput()) {
            return;
        }
        
        SanPham sanPham = getSanPhamFromForm();
        boolean result = controller.suaSanPham(sanPham);
        
        if (result) {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadSanPhamData();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaSanPham() {
        int selectedRow = tableSanPham.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenSanPham = tableModel.getValueAt(selectedRow, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa sản phẩm \"" + tenSanPham + "\"?", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
        if (confirm == JOptionPane.YES_OPTION) {
            boolean result = controller.xoaSanPham(id);
            if (result) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadSanPhamData();
                lamMoiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void timKiemSanPham(String tuKhoa, String loaiTimKiem) {
        if (tuKhoa.isEmpty()) {
            loadSanPhamData(); // Nếu từ khóa trống, load lại tất cả
            return;
        }
        
        // Tìm kiếm sản phẩm theo tiêu chí
        List<SanPham> ketQua;
        if (loaiTimKiem.equals("Tên sản phẩm")) {
            ketQua = controller.timKiemSanPhamTheoTen(tuKhoa);
        } else {
            ketQua = controller.timKiemSanPhamTheoLoai(tuKhoa);
        }
        
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // Thêm kết quả tìm kiếm vào bảng
        for (SanPham sp : ketQua) {
            Object[] row = {
                sp.getId(),
                sp.getTenSanPham(),
                sp.getLoai(),
                currencyFormat.format(sp.getDonGia()) + " VND",
                sp.getSoLuong(),
                sp.getNgayNhapHang(),
                sp.getNgayHetHan(),
                sp.isTrangThai() ? "Còn hàng" : "Hết hàng",
                sp.getMoTa()
            };
            tableModel.addRow(row);
        }
        
        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm nào phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn hình ảnh");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            currentImagePath = selectedFile.getAbsolutePath();
            
            // Hiển thị hình ảnh được chọn lên ImagePreview
            try {
                ImageIcon imageIcon = new ImageIcon(currentImagePath);
                // Scale ảnh để vừa với kích thước hiển thị
                Image img = imageIcon.getImage().getScaledInstance(
                        imagePanel.getWidth() - 10, 
                        imagePanel.getHeight() - 10, 
                        Image.SCALE_SMOOTH);
                lblImagePreview.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                        "Không thể hiển thị hình ảnh: " + e.getMessage(), 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadSanPhamData() {
        // Lấy danh sách sản phẩm từ controller
        List<SanPham> danhSachSanPham = controller.getAllSanPham();
        
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // Thêm dữ liệu mới
        for (SanPham sp : danhSachSanPham) {
            Object[] row = {
                sp.getId(),
                sp.getTenSanPham(),
                sp.getLoai(),
                currencyFormat.format(sp.getDonGia()) + " VND",
                sp.getSoLuong(),
                sp.getNgayNhapHang(),
                sp.getNgayHetHan(),
                sp.isTrangThai() ? "Còn hàng" : "Hết hàng",
                sp.getMoTa()
            };
            tableModel.addRow(row);
        }
    }
    
    private void lamMoiForm() {
        txtId.setText("");
        txtTenSanPham.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        txtMoTa.setText("");
        dateNgayNhapHang.clear();
        dateNgayHetHan.clear();
        cboLoai.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);
        currentImagePath = "";
        lblImagePreview.setIcon(null);
        tableSanPham.clearSelection();
        txtTenSanPham.requestFocus();
    }
    
    private void displaySelectedSanPham(int row) {
        int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        SanPham sp = controller.getSanPhamById(id);
        if (sp != null) {
            txtId.setText(String.valueOf(sp.getId()));
            txtTenSanPham.setText(sp.getTenSanPham());
            cboLoai.setSelectedItem(sp.getLoai());
            txtDonGia.setText(String.valueOf(sp.getDonGia()));
            txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
            if (sp.getNgayNhapHang() != null) {
                dateNgayNhapHang.setDate(sp.getNgayNhapHang().toLocalDate());
            }
            if (sp.getNgayHetHan() != null) {
                dateNgayHetHan.setDate(sp.getNgayHetHan().toLocalDate());
            }
            cboTrangThai.setSelectedItem(sp.isTrangThai() ? "Còn hàng" : "Hết hàng");
            txtMoTa.setText(sp.getMoTa());
            
            if (sp.getHinhAnh() != null && !sp.getHinhAnh().isEmpty()) {
                currentImagePath = sp.getHinhAnh();
                try {
                    ImageIcon imageIcon = new ImageIcon(currentImagePath);
                    Image img = imageIcon.getImage().getScaledInstance(
                            imagePanel.getWidth() - 10, 
                            imagePanel.getHeight() - 10, 
                            Image.SCALE_SMOOTH);
                    lblImagePreview.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    lblImagePreview.setIcon(null);
                }
            } else {
                lblImagePreview.setIcon(null);
                currentImagePath = "";
            }
        }
    }
    
    private SanPham getSanPhamFromForm() {
        SanPham sp = new SanPham();
        
        if (!txtId.getText().isEmpty()) {
            sp.setId(Integer.parseInt(txtId.getText()));
        }
        
        sp.setTenSanPham(txtTenSanPham.getText().trim());
        sp.setLoai(cboLoai.getSelectedItem().toString().trim());
        
        try {
            String donGiaStr = txtDonGia.getText().trim().replace(",", "");
            BigDecimal donGia = new BigDecimal(donGiaStr);
            sp.setDonGia(donGia);
        } catch (NumberFormatException e) {
            sp.setDonGia(BigDecimal.ZERO);
        }
        
        try {
            sp.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
        } catch (NumberFormatException e) {
            sp.setSoLuong(0);
        }
        
        LocalDate ngayNhap = dateNgayNhapHang.getDate();
        if (ngayNhap != null) {
            sp.setNgayNhapHang(java.sql.Date.valueOf(ngayNhap));
        }
        
        LocalDate ngayHetHan = dateNgayHetHan.getDate();
        if (ngayHetHan != null) {
            sp.setNgayHetHan(java.sql.Date.valueOf(ngayHetHan));
        }
        
        String trangThai = cboTrangThai.getSelectedItem().toString();
        sp.setTrangThai(trangThai.equals("Còn hàng"));
        
        sp.setMoTa(txtMoTa.getText().trim());
        sp.setHinhAnh(currentImagePath);
        
        return sp;
    }
    
    private boolean validateInput() {
        if (txtTenSanPham.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập tên sản phẩm!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtTenSanPham.requestFocus();
            return false;
        }
        
        if (txtDonGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập đơn giá!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtDonGia.requestFocus();
            return false;
        }
        
        try {
            String donGiaStr = txtDonGia.getText().trim().replace(",", "");
            BigDecimal donGia = new BigDecimal(donGiaStr);
            
            if (donGia.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, 
                        "Đơn giá không được âm!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtDonGia.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "Đơn giá không hợp lệ!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtDonGia.requestFocus();
            return false;
        }
        
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập số lượng!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoLuong.requestFocus();
            return false;
        }
        
        try {
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, 
                        "Số lượng không được âm!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtSoLuong.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "Số lượng không hợp lệ!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoLuong.requestFocus();
            return false;
        }
        
        if (dateNgayNhapHang.getDate() == null) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn ngày nhập hàng!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgayNhapHang.requestFocus();
            return false;
        }
        
        if (dateNgayHetHan.getDate() == null) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn ngày hết hạn!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgayHetHan.requestFocus();
            return false;
        }
        
        if (dateNgayHetHan.getDate().isBefore(dateNgayNhapHang.getDate())) {
            JOptionPane.showMessageDialog(this, 
                    "Ngày hết hạn không được trước ngày nhập hàng!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgayHetHan.requestFocus();
            return false;
        }
        
        return true;
    }
    
    // Phương thức chính để kiểm tra giao diện
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Quản Lý Sản Phẩm");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new SanPhamView());
                frame.pack();
                frame.setSize(1200, 700);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}