package view;
import controller.NhanVienController;
import model.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

/**
 * Giao diện quản lý nhân viên
 * 
 * @author zhieu
 */
public class NhanVienView extends JPanel {
    private NhanVienController controller;
    
    // Khai báo các thành phần giao diện
    private JPanel mainPanel, formPanel, tablePanel, buttonPanel;
    private JTextField txtId, txtHoTen, txtSoDienThoai, txtDiaChi, txtSearch, txtSoCCCD;
    private JRadioButton radNam, radNu, radKhac;
    private DatePicker dateNgaySinh, dateNgayVaoLam;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
    private JTable tableNhanVien;
    private DefaultTableModel tableModel;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    // Thiết lập màu sắc chủ đạo cho ứng dụng
    private Color primaryColor = new Color(121, 85, 72); // Màu nâu theo theme cà phê
    private Color secondaryColor = new Color(188, 170, 164); // Màu nâu nhạt
    private Color accentColor = new Color(62, 39, 35); // Màu nâu đậm
    private Color backgroundColor = new Color(245, 245, 245); // Màu nền nhẹ
    private Color successColor = new Color(76, 175, 80); // Màu xanh lá
    private Color warningColor = new Color(255, 152, 0); // Màu cam
    private Color dangerColor = new Color(244, 67, 54); // Màu đỏ
    
    public NhanVienView() {
        // Khởi tạo controller
        controller = new NhanVienController();
        
        // Thiết lập layout cho panel chính
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        
        // Tạo panel chính có border layout
        createMainPanel();
        
        // Load dữ liệu ban đầu
        loadNhanVienData();
    }
    
    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(backgroundColor);
        
        // Tạo tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
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
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                "Thông tin nhân viên",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        formPanel.setPreferredSize(new Dimension(350, 500));
        formPanel.setBackground(backgroundColor);

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
        formPanel.add(lblId, gbcLblId);

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
        formPanel.add(txtId, gbcTxtId);

        // Họ tên
        JLabel lblHoTen = new JLabel("Họ tên:");
        lblHoTen.setFont(labelFont);
        GridBagConstraints gbcLblHoTen = new GridBagConstraints();
        gbcLblHoTen.gridx = 0;
        gbcLblHoTen.gridy = 1;
        gbcLblHoTen.insets = new Insets(8, 8, 8, 8);
        gbcLblHoTen.anchor = GridBagConstraints.WEST;
        formPanel.add(lblHoTen, gbcLblHoTen);

        txtHoTen = new JTextField();
        txtHoTen.setFont(fieldFont);
        GridBagConstraints gbcTxtHoTen = new GridBagConstraints();
        gbcTxtHoTen.gridx = 1;
        gbcTxtHoTen.gridy = 1;
        gbcTxtHoTen.insets = new Insets(8, 8, 8, 8);
        gbcTxtHoTen.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtHoTen.weightx = 1.0;
        formPanel.add(txtHoTen, gbcTxtHoTen);

        // Giới tính
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(labelFont);
        GridBagConstraints gbcLblGioiTinh = new GridBagConstraints();
        gbcLblGioiTinh.gridx = 0;
        gbcLblGioiTinh.gridy = 2;
        gbcLblGioiTinh.insets = new Insets(8, 8, 8, 8);
        gbcLblGioiTinh.anchor = GridBagConstraints.WEST;
        formPanel.add(lblGioiTinh, gbcLblGioiTinh);

        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        radKhac = new JRadioButton("Khác");
        radNam.setFont(fieldFont);
        radNu.setFont(fieldFont);
        radKhac.setFont(fieldFont);
        radNam.setBackground(backgroundColor);
        radNu.setBackground(backgroundColor);
        radKhac.setBackground(backgroundColor);

        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(radNam);
        groupGioiTinh.add(radNu);
        groupGioiTinh.add(radKhac);
        radNam.setSelected(true);

        JPanel panelGioiTinh = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelGioiTinh.setBackground(backgroundColor);
        panelGioiTinh.add(radNam);
        panelGioiTinh.add(radNu);
        panelGioiTinh.add(radKhac);

        GridBagConstraints gbcPanelGioiTinh = new GridBagConstraints();
        gbcPanelGioiTinh.gridx = 1;
        gbcPanelGioiTinh.gridy = 2;
        gbcPanelGioiTinh.insets = new Insets(8, 8, 8, 8);
        gbcPanelGioiTinh.anchor = GridBagConstraints.WEST;
        formPanel.add(panelGioiTinh, gbcPanelGioiTinh);

        // Ngày sinh
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setFont(labelFont);
        GridBagConstraints gbcLblNgaySinh = new GridBagConstraints();
        gbcLblNgaySinh.gridx = 0;
        gbcLblNgaySinh.gridy = 3;
        gbcLblNgaySinh.insets = new Insets(8, 8, 8, 8);
        gbcLblNgaySinh.anchor = GridBagConstraints.WEST;
        formPanel.add(lblNgaySinh, gbcLblNgaySinh);

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettings.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, primaryColor);
        dateSettings.setColor(DatePickerSettings.DateArea.TextMonthAndYearMenuLabels, Color.WHITE);
        dateNgaySinh = new DatePicker(dateSettings);
        dateNgaySinh.setFont(fieldFont);
        GridBagConstraints gbcDateNgaySinh = new GridBagConstraints();
        gbcDateNgaySinh.gridx = 1;
        gbcDateNgaySinh.gridy = 3;
        gbcDateNgaySinh.insets = new Insets(8, 8, 8, 8);
        gbcDateNgaySinh.fill = GridBagConstraints.HORIZONTAL;
        gbcDateNgaySinh.weightx = 1.0;
        formPanel.add(dateNgaySinh, gbcDateNgaySinh);

        // Số điện thoại
        JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
        lblSoDienThoai.setFont(labelFont);
        GridBagConstraints gbcLblSDT = new GridBagConstraints();
        gbcLblSDT.gridx = 0;
        gbcLblSDT.gridy = 4;
        gbcLblSDT.insets = new Insets(8, 8, 8, 8);
        gbcLblSDT.anchor = GridBagConstraints.WEST;
        formPanel.add(lblSoDienThoai, gbcLblSDT);

        txtSoDienThoai = new JTextField();
        txtSoDienThoai.setFont(fieldFont);
        GridBagConstraints gbcTxtSDT = new GridBagConstraints();
        gbcTxtSDT.gridx = 1;
        gbcTxtSDT.gridy = 4;
        gbcTxtSDT.insets = new Insets(8, 8, 8, 8);
        gbcTxtSDT.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtSDT.weightx = 1.0;
        formPanel.add(txtSoDienThoai, gbcTxtSDT);

        // Địa chỉ
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(labelFont);
        GridBagConstraints gbcLblDiaChi = new GridBagConstraints();
        gbcLblDiaChi.gridx = 0;
        gbcLblDiaChi.gridy = 5;
        gbcLblDiaChi.insets = new Insets(8, 8, 8, 8);
        gbcLblDiaChi.anchor = GridBagConstraints.WEST;
        formPanel.add(lblDiaChi, gbcLblDiaChi);

        txtDiaChi = new JTextField();
        txtDiaChi.setFont(fieldFont);
        GridBagConstraints gbcTxtDiaChi = new GridBagConstraints();
        gbcTxtDiaChi.gridx = 1;
        gbcTxtDiaChi.gridy = 5;
        gbcTxtDiaChi.insets = new Insets(8, 8, 8, 8);
        gbcTxtDiaChi.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtDiaChi.weightx = 1.0;
        formPanel.add(txtDiaChi, gbcTxtDiaChi);

        // Số CCCD
        JLabel lblSoCCCD = new JLabel("Số CCCD:");
        lblSoCCCD.setFont(labelFont);
        GridBagConstraints gbcLblSoCCCD = new GridBagConstraints();
        gbcLblSoCCCD.gridx = 0;
        gbcLblSoCCCD.gridy = 6;
        gbcLblSoCCCD.insets = new Insets(8, 8, 8, 8);
        gbcLblSoCCCD.anchor = GridBagConstraints.WEST;
        formPanel.add(lblSoCCCD, gbcLblSoCCCD);

        txtSoCCCD = new JTextField();
        txtSoCCCD.setFont(fieldFont);
        GridBagConstraints gbcTxtSoCCCD = new GridBagConstraints();
        gbcTxtSoCCCD.gridx = 1;
        gbcTxtSoCCCD.gridy = 6;
        gbcTxtSoCCCD.insets = new Insets(8, 8, 8, 8);
        gbcTxtSoCCCD.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtSoCCCD.weightx = 1.0;
        formPanel.add(txtSoCCCD, gbcTxtSoCCCD);

        // Ngày vào làm
        JLabel lblNgayVaoLam = new JLabel("Ngày vào làm:");
        lblNgayVaoLam.setFont(labelFont);
        GridBagConstraints gbcLblVaoLam = new GridBagConstraints();
        gbcLblVaoLam.gridx = 0;
        gbcLblVaoLam.gridy = 7;
        gbcLblVaoLam.insets = new Insets(8, 8, 8, 8);
        gbcLblVaoLam.anchor = GridBagConstraints.WEST;
        formPanel.add(lblNgayVaoLam, gbcLblVaoLam);

        DatePickerSettings dateSettingsVaoLam = new DatePickerSettings();
        dateSettingsVaoLam.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettingsVaoLam.setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, primaryColor);
        dateSettingsVaoLam.setColor(DatePickerSettings.DateArea.TextMonthAndYearMenuLabels, Color.WHITE);
        dateNgayVaoLam = new DatePicker(dateSettingsVaoLam);
        dateNgayVaoLam.setFont(fieldFont);
        dateNgayVaoLam.setDate(LocalDate.now());

        GridBagConstraints gbcDateVaoLam = new GridBagConstraints();
        gbcDateVaoLam.gridx = 1;
        gbcDateVaoLam.gridy = 7;
        gbcDateVaoLam.insets = new Insets(8, 8, 8, 8);
        gbcDateVaoLam.fill = GridBagConstraints.HORIZONTAL;
        gbcDateVaoLam.weightx = 1.0;
        formPanel.add(dateNgayVaoLam, gbcDateVaoLam);
    }
    
    private void createTablePanel() {
        // Tạo panel chứa bảng hiển thị dữ liệu
        tablePanel = new JPanel(new BorderLayout(5, 10));
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                "Danh sách nhân viên", 
                TitledBorder.LEFT, 
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        tablePanel.setBackground(backgroundColor);
        
        // Tạo model cho table
        String[] columnNames = {"ID", "Họ tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Số CCCD", "Ngày vào làm"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép edit trực tiếp trên bảng
            }
        };
        
        // Tạo table
        tableNhanVien = new JTable(tableModel);
        tableNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableNhanVien.setRowHeight(28);
        tableNhanVien.setGridColor(new Color(139, 69, 19));
        tableNhanVien.setFont(new Font("Arial", Font.PLAIN, 13));
        tableNhanVien.setBackground(new Color(255, 255, 255));
        tableNhanVien.setSelectionBackground(secondaryColor);
        tableNhanVien.setSelectionForeground(Color.BLACK);
        
        // Header style
        tableNhanVien.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableNhanVien.getTableHeader().setBackground(primaryColor);
        tableNhanVien.getTableHeader().setForeground(Color.BLACK);
        tableNhanVien.getTableHeader().setPreferredSize(new Dimension(0, 35));
        
        // Thêm sự kiện khi click vào một dòng
        tableNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableNhanVien.getSelectedRow();
                if (selectedRow >= 0) {
                    displaySelectedNhanVien(selectedRow);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // Tạo panel tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(backgroundColor);
        
        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 13));
        
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 13));
        txtSearch.setPreferredSize(new Dimension(200, 28));
        
        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setFont(new Font("Arial", Font.BOLD, 12));
        btnTimKiem.setBackground(primaryColor);
        btnTimKiem.setForeground(new Color(139, 69, 19));
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setPreferredSize(new Dimension(100, 28));
        
        // Thêm sự kiện tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemNhanVien();
            }
        });
        
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
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
        
        btnThem.setForeground(new Color(139, 69, 19));
        btnSua.setForeground(new Color(139, 69, 19));
        btnXoa.setForeground(new Color(139, 69, 19));
        btnLamMoi.setForeground(new Color(139, 69, 19));
        
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
                themNhanVien();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaNhanVien();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaNhanVien();
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
    private void themNhanVien() {
        if (!validateInput()) {
            return;
        }
        
        // Kiểm tra số điện thoại đã tồn tại chưa
        String soDienThoai = txtSoDienThoai.getText().trim();
        if (controller.kiemTraSoDienThoaiTonTai(soDienThoai, 0)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã được sử dụng bởi nhân viên khác!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoDienThoai.requestFocus();
            return;
        }
        
        NhanVien nhanVien = getNhanVienFromForm();
        
        boolean result = controller.themNhanVien(nhanVien);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadNhanVienData();
            lamMoiForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaNhanVien() {
        int selectedRow = tableNhanVien.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateInput()) {
            return;
        }
        
        int id = Integer.parseInt(txtId.getText());
        String soDienThoai = txtSoDienThoai.getText().trim();
        
        // Kiểm tra số điện thoại đã tồn tại chưa (trừ ID hiện tại)
        if (controller.kiemTraSoDienThoaiTonTai(soDienThoai, id)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã được sử dụng bởi nhân viên khác!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoDienThoai.requestFocus();
            return;
        }
        
        NhanVien nhanVien = getNhanVienFromForm();
        boolean result = controller.suaNhanVien(nhanVien);
        
        if (result) {
            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadNhanVienData();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaNhanVien() {
        int selectedRow = tableNhanVien.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenNhanVien = tableModel.getValueAt(selectedRow, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa nhân viên \"" + tenNhanVien + "\"?", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
        if (confirm == JOptionPane.YES_OPTION) {
            boolean result = controller.xoaNhanVien(id);
            if (result) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadNhanVienData();
                lamMoiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void timKiemNhanVien() {
        String tuKhoa = txtSearch.getText().trim();
        if (tuKhoa.isEmpty()) {
            loadNhanVienData(); // Nếu từ khóa trống, load lại tất cả
            return;
        }
        
        // Tìm kiếm nhân viên theo tên
        List<NhanVien> ketQua = controller.timKiemNhanVienTheoTen(tuKhoa);
        
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // Thêm kết quả tìm kiếm vào bảng
        for (NhanVien nv : ketQua) {
            Object[] row = {
                nv.getId(),
                nv.getHoTen(),
                nv.getGioiTinh(),
                nv.getNgaySinh() != null ? dateFormat.format(nv.getNgaySinh()) : "",
                nv.getSoDienThoai(),
                nv.getDiaChi(),
                nv.getSoCCCD(),
                nv.getNgayVaoLam() != null ? dateFormat.format(nv.getNgayVaoLam()) : ""
            };
            tableModel.addRow(row);
        }
        
        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void lamMoiForm() {
        txtId.setText("");
        txtHoTen.setText("");
        radNam.setSelected(true);
        dateNgaySinh.setDate(null);
        txtSoDienThoai.setText("");
        txtDiaChi.setText("");
        txtSoCCCD.setText("");
        dateNgayVaoLam.setDate(LocalDate.now());
        tableNhanVien.clearSelection();
    }
    
    private void loadNhanVienData() {
        try {
            // Xóa dữ liệu cũ
            tableModel.setRowCount(0);
            
            // Lấy danh sách nhân viên từ controller
            List<NhanVien> danhSachNhanVien = controller.layDanhSachNhanVien();
            
            // Thêm dữ liệu vào table model
            for (NhanVien nv : danhSachNhanVien) {
                Object[] row = {
                    nv.getId(),
                    nv.getHoTen(),
                    nv.getGioiTinh(),
                    nv.getNgaySinh() != null ? dateFormat.format(nv.getNgaySinh()) : "",
                    nv.getSoDienThoai(),
                    nv.getDiaChi(),
                    nv.getSoCCCD(),
                    nv.getNgayVaoLam() != null ? dateFormat.format(nv.getNgayVaoLam()) : ""
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ trong quá trình load dữ liệu
            System.err.println("Lỗi khi load dữ liệu nhân viên: " + e.getMessage());
            e.printStackTrace();
            
            // Thông báo lỗi nhưng không dừng ứng dụng
            JOptionPane.showMessageDialog(this, 
                "Đã xảy ra lỗi khi tải dữ liệu nhân viên. Chi tiết: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displaySelectedNhanVien(int selectedRow) {
        txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtHoTen.setText((String) tableModel.getValueAt(selectedRow, 1));
        
        String gioiTinh = (String) tableModel.getValueAt(selectedRow, 2);
        if ("Nam".equals(gioiTinh)) {
            radNam.setSelected(true);
        } else if ("Nữ".equals(gioiTinh)) {
            radNu.setSelected(true);
        } else {
            radKhac.setSelected(true);
        }
        
        try {
            String ngaySinhStr = (String) tableModel.getValueAt(selectedRow, 3);
            if (ngaySinhStr != null && !ngaySinhStr.isEmpty()) {
                Date date = dateFormat.parse(ngaySinhStr);
                // Chuyển đổi java.util.Date sang LocalDate
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                dateNgaySinh.setDate(localDate);
            } else {
                dateNgaySinh.setDate(null);
            }
        } catch (ParseException e) {
            dateNgaySinh.setDate(null);
        }
        
        txtSoDienThoai.setText((String) tableModel.getValueAt(selectedRow, 4));
        txtDiaChi.setText((String) tableModel.getValueAt(selectedRow, 5));
        txtSoCCCD.setText((String) tableModel.getValueAt(selectedRow, 6));
        
        try {
            String ngayVaoLamStr = (String) tableModel.getValueAt(selectedRow, 7);
            if (ngayVaoLamStr != null && !ngayVaoLamStr.isEmpty()) {
                Date date = dateFormat.parse(ngayVaoLamStr);
                // Chuyển đổi java.util.Date sang LocalDate
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                dateNgayVaoLam.setDate(localDate);
            } else {
                dateNgayVaoLam.setDate(LocalDate.now());
            }
        } catch (ParseException e) {
            dateNgayVaoLam.setDate(LocalDate.now());
        }
    }
    
    private NhanVien getNhanVienFromForm() {
        NhanVien nhanVien = new NhanVien();
        
        if (!txtId.getText().isEmpty()) {
            nhanVien.setId(Integer.parseInt(txtId.getText()));
        }
        
        nhanVien.setHoTen(txtHoTen.getText());
        
        if (radNam.isSelected()) {
            nhanVien.setGioiTinh("Nam");
        } else if (radNu.isSelected()) {
            nhanVien.setGioiTinh("Nữ");
        } else {
            nhanVien.setGioiTinh("Khác");
        }
        
        // Chuyển đổi LocalDate sang java.util.Date
        LocalDate ngaySinhLocalDate = dateNgaySinh.getDate();
        if (ngaySinhLocalDate != null) {
            Date ngaySinhDate = Date.from(ngaySinhLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            nhanVien.setNgaySinh(ngaySinhDate);
        }
        
        nhanVien.setSoDienThoai(txtSoDienThoai.getText());
        nhanVien.setDiaChi(txtDiaChi.getText());
        nhanVien.setSoCCCD(txtSoCCCD.getText());
        
        // Chuyển đổi LocalDate sang java.util.Date
        LocalDate ngayVaoLamLocalDate = dateNgayVaoLam.getDate();
        if (ngayVaoLamLocalDate != null) {
            Date ngayVaoLamDate = Date.from(ngayVaoLamLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            nhanVien.setNgayVaoLam(ngayVaoLamDate);
        }
        
        return nhanVien;
    }
    
    private boolean validateInput() {
        if (txtHoTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtHoTen.requestFocus();
            return false;
        }
        
        if (dateNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgaySinh.requestFocus();
            return false;
        }
        
        String soDienThoai = txtSoDienThoai.getText().trim();
        if (soDienThoai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoDienThoai.requestFocus();
            return false;
        }
        
        // Kiểm tra định dạng số điện thoại
        if (!soDienThoai.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoDienThoai.requestFocus();
            return false;
        }
        
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtDiaChi.requestFocus();
            return false;
        }

        String soCCCD = txtSoCCCD.getText().trim();
        if (soCCCD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số CCCD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoCCCD.requestFocus();
            return false;
        }

        if (!soCCCD.matches("\\d{12}")) {
            JOptionPane.showMessageDialog(this, "Số CCCD phải có 12 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtSoCCCD.requestFocus();
            return false;
        }
        
        if (dateNgayVaoLam.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày vào làm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgayVaoLam.requestFocus();
            return false;
        }
        
        return true;
    }
}
