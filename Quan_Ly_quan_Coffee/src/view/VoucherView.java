package view;
import controller.VoucherController;
import model.Voucher;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
/**
 * Giao diện quản lý voucher
 */
public class VoucherView extends JPanel {
    private VoucherController controller;
    
    // Khai báo các thành phần giao diện
    private JPanel mainPanel, formPanel, tablePanel, buttonPanel;
    private JTextField txtId, txtMaVoucher, txtPhanTramGiam, txtSearch;
    private DatePicker dateNgayBatDau, dateNgayKetThuc;
    private JComboBox<String> cboTrangThai;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
    private JTable tableVoucher;
    private DefaultTableModel tableModel;
    
    // Thiết lập màu sắc chủ đạo cho ứng dụng
    private Color primaryColor = new Color(121, 85, 72); // Màu nâu theo theme cà phê
    private Color secondaryColor = new Color(188, 170, 164); // Màu nâu nhạt
    private Color accentColor = new Color(62, 39, 35); // Màu nâu đậm
    private Color backgroundColor = new Color(245, 245, 245); // Màu nền nhẹ
    private Color successColor = new Color(76, 175, 80); // Màu xanh lá
    private Color warningColor = new Color(255, 152, 0); // Màu cam
    private Color dangerColor = new Color(244, 67, 54); // Màu đỏ
    
    // Format cho date
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public VoucherView() {
        // Khởi tạo controller
        controller = new VoucherController();
        
        // Thiết lập layout cho panel chính
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        
        // Tạo panel chính có border layout
        createMainPanel();
        
        // Load dữ liệu ban đầu
        loadVoucherData();
    }
    
    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(backgroundColor);
        
        // Tạo tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ VOUCHER", SwingConstants.CENTER);
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
                "Thông tin voucher",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        formPanel.setPreferredSize(new Dimension(400, 300));
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

        // Mã voucher
        JLabel lblMaVoucher = new JLabel("Mã voucher:");
        lblMaVoucher.setFont(labelFont);
        GridBagConstraints gbcLblMaVoucher = new GridBagConstraints();
        gbcLblMaVoucher.gridx = 0;
        gbcLblMaVoucher.gridy = 1;
        gbcLblMaVoucher.insets = new Insets(8, 8, 8, 8);
        gbcLblMaVoucher.anchor = GridBagConstraints.WEST;
        formPanel.add(lblMaVoucher, gbcLblMaVoucher);

        txtMaVoucher = new JTextField();
        txtMaVoucher.setFont(fieldFont);
        GridBagConstraints gbcTxtMaVoucher = new GridBagConstraints();
        gbcTxtMaVoucher.gridx = 1;
        gbcTxtMaVoucher.gridy = 1;
        gbcTxtMaVoucher.insets = new Insets(8, 8, 8, 8);
        gbcTxtMaVoucher.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtMaVoucher.weightx = 1.0;
        formPanel.add(txtMaVoucher, gbcTxtMaVoucher);

        // Phần trăm giảm
        JLabel lblPhanTramGiam = new JLabel("Phần trăm giảm (%):");
        lblPhanTramGiam.setFont(labelFont);
        GridBagConstraints gbcLblPhanTramGiam = new GridBagConstraints();
        gbcLblPhanTramGiam.gridx = 0;
        gbcLblPhanTramGiam.gridy = 2;
        gbcLblPhanTramGiam.insets = new Insets(8, 8, 8, 8);
        gbcLblPhanTramGiam.anchor = GridBagConstraints.WEST;
        formPanel.add(lblPhanTramGiam, gbcLblPhanTramGiam);

        txtPhanTramGiam = new JTextField();
        txtPhanTramGiam.setFont(fieldFont);
        GridBagConstraints gbcTxtPhanTramGiam = new GridBagConstraints();
        gbcTxtPhanTramGiam.gridx = 1;
        gbcTxtPhanTramGiam.gridy = 2;
        gbcTxtPhanTramGiam.insets = new Insets(8, 8, 8, 8);
        gbcTxtPhanTramGiam.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtPhanTramGiam.weightx = 1.0;
        formPanel.add(txtPhanTramGiam, gbcTxtPhanTramGiam);

        // Ngày bắt đầu
        JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
        lblNgayBatDau.setFont(labelFont);
        GridBagConstraints gbcLblNgayBatDau = new GridBagConstraints();
        gbcLblNgayBatDau.gridx = 0;
        gbcLblNgayBatDau.gridy = 3;
        gbcLblNgayBatDau.insets = new Insets(8, 8, 8, 8);
        gbcLblNgayBatDau.anchor = GridBagConstraints.WEST;
        formPanel.add(lblNgayBatDau, gbcLblNgayBatDau);

        DatePickerSettings dateSettingsStart = new DatePickerSettings();
        dateSettingsStart.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettingsStart.setAllowEmptyDates(false);
        dateNgayBatDau = new DatePicker(dateSettingsStart);
        dateNgayBatDau.getComponentDateTextField().setFont(fieldFont);
        GridBagConstraints gbcDateNgayBatDau = new GridBagConstraints();
        gbcDateNgayBatDau.gridx = 1;
        gbcDateNgayBatDau.gridy = 3;
        gbcDateNgayBatDau.insets = new Insets(8, 8, 8, 8);
        gbcDateNgayBatDau.fill = GridBagConstraints.HORIZONTAL;
        gbcDateNgayBatDau.weightx = 1.0;
        formPanel.add(dateNgayBatDau, gbcDateNgayBatDau);

        // Ngày kết thúc
        JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
        lblNgayKetThuc.setFont(labelFont);
        GridBagConstraints gbcLblNgayKetThuc = new GridBagConstraints();
        gbcLblNgayKetThuc.gridx = 0;
        gbcLblNgayKetThuc.gridy = 4;
        gbcLblNgayKetThuc.insets = new Insets(8, 8, 8, 8);
        gbcLblNgayKetThuc.anchor = GridBagConstraints.WEST;
        formPanel.add(lblNgayKetThuc, gbcLblNgayKetThuc);

        DatePickerSettings dateSettingsEnd = new DatePickerSettings();
        dateSettingsEnd.setFormatForDatesCommonEra("dd/MM/yyyy");
        dateSettingsEnd.setAllowEmptyDates(false);
        dateNgayKetThuc = new DatePicker(dateSettingsEnd);
        dateNgayKetThuc.getComponentDateTextField().setFont(fieldFont);
        GridBagConstraints gbcDateNgayKetThuc = new GridBagConstraints();
        gbcDateNgayKetThuc.gridx = 1;
        gbcDateNgayKetThuc.gridy = 4;
        gbcDateNgayKetThuc.insets = new Insets(8, 8, 8, 8);
        gbcDateNgayKetThuc.fill = GridBagConstraints.HORIZONTAL;
        gbcDateNgayKetThuc.weightx = 1.0;
        formPanel.add(dateNgayKetThuc, gbcDateNgayKetThuc);

        // Trạng thái
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setFont(labelFont);
        GridBagConstraints gbcLblTrangThai = new GridBagConstraints();
        gbcLblTrangThai.gridx = 0;
        gbcLblTrangThai.gridy = 5;
        gbcLblTrangThai.insets = new Insets(8, 8, 8, 8);
        gbcLblTrangThai.anchor = GridBagConstraints.WEST;
        formPanel.add(lblTrangThai, gbcLblTrangThai);

        cboTrangThai = new JComboBox<>(new String[] {"Kích hoạt", "Hết hạn"});
        cboTrangThai.setFont(fieldFont);
        GridBagConstraints gbcCboTrangThai = new GridBagConstraints();
        gbcCboTrangThai.gridx = 1;
        gbcCboTrangThai.gridy = 5;
        gbcCboTrangThai.insets = new Insets(8, 8, 8, 8);
        gbcCboTrangThai.fill = GridBagConstraints.HORIZONTAL;
        gbcCboTrangThai.weightx = 1.0;
        formPanel.add(cboTrangThai, gbcCboTrangThai);
    }
    
    private void createTablePanel() {
        // Tạo panel chứa bảng hiển thị dữ liệu
        tablePanel = new JPanel(new BorderLayout(5, 10));
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                "Danh sách voucher", 
                TitledBorder.LEFT, 
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        tablePanel.setBackground(backgroundColor);
        
        // Tạo model cho table
        String[] columnNames = {"ID", "Mã voucher", "Phần trăm giảm", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép edit trực tiếp trên bảng
            }
        };
        
        // Tạo table
        tableVoucher = new JTable(tableModel);
        tableVoucher.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableVoucher.setRowHeight(28);
        tableVoucher.setGridColor(new Color(139, 69, 19));
        tableVoucher.setFont(new Font("Arial", Font.PLAIN, 13));
        tableVoucher.setBackground(new Color(255, 255, 255));
        tableVoucher.setSelectionBackground(secondaryColor);
        tableVoucher.setSelectionForeground(Color.BLACK);
        
        // Header style
        tableVoucher.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableVoucher.getTableHeader().setBackground(primaryColor);
        tableVoucher.getTableHeader().setForeground(Color.BLACK);
        tableVoucher.getTableHeader().setPreferredSize(new Dimension(0, 35));
        
        // Thêm sự kiện khi click vào một dòng
        tableVoucher.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableVoucher.getSelectedRow();
                if (selectedRow >= 0) {
                    displaySelectedVoucher(selectedRow);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableVoucher);
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
        btnTimKiem.setForeground(Color.BLACK);
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setPreferredSize(new Dimension(100, 28));
        
        // Thêm sự kiện tìm kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiemVoucher();
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
        
        btnThem.setForeground(Color.BLACK);
        btnSua.setForeground(Color.BLACK);
        btnXoa.setForeground(Color.BLACK);
        btnLamMoi.setForeground(Color.BLACK);
        
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
                themVoucher();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaVoucher();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaVoucher();
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
    private void themVoucher() {
        if (!validateInput()) {
            return;
        }
        
        // Kiểm tra mã voucher đã tồn tại chưa
        String maVoucher = txtMaVoucher.getText().trim();
        if (controller.kiemTraMaVoucherTonTai(maVoucher, 0)) {
            JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtMaVoucher.requestFocus();
            return;
        }
        
        Voucher voucher = getVoucherFromForm();
        
        boolean result = controller.themVoucher(voucher);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm voucher thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadVoucherData();
            lamMoiForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm voucher thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaVoucher() {
        int selectedRow = tableVoucher.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn voucher cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateInput()) {
            return;
        }
        
        int id = Integer.parseInt(txtId.getText());
        String maVoucher = txtMaVoucher.getText().trim();
        
        // Kiểm tra mã voucher đã tồn tại chưa (trừ ID hiện tại)
        if (controller.kiemTraMaVoucherTonTai(maVoucher, id)) {
            JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtMaVoucher.requestFocus();
            return;
        }
        
        Voucher voucher = getVoucherFromForm();
        boolean result = controller.suaVoucher(voucher);
        
        if (result) {
            JOptionPane.showMessageDialog(this, "Cập nhật voucher thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadVoucherData();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật voucher thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaVoucher() {
        int selectedRow = tableVoucher.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn voucher cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String maVoucher = tableModel.getValueAt(selectedRow, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa voucher \"" + maVoucher + "\"?", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
        if (confirm == JOptionPane.YES_OPTION) {
            boolean result = controller.xoaVoucher(id);
            if (result) {
                JOptionPane.showMessageDialog(this, "Xóa voucher thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadVoucherData();
                lamMoiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa voucher thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lamMoiForm() {
        txtId.setText("");
        txtMaVoucher.setText("");
        txtPhanTramGiam.setText("");
        dateNgayBatDau.setDate(null);
        dateNgayKetThuc.setDate(null);
        cboTrangThai.setSelectedIndex(0);
        
        // Bỏ chọn dòng trên bảng
        tableVoucher.clearSelection();
        
        // Set focus về mã voucher
        txtMaVoucher.requestFocus();
    }
    
    private void timKiemVoucher() {
        String keyword = txtSearch.getText().trim();
        List<Voucher> results = controller.timKiemVoucher(keyword);
        
        displayVoucherList(results);
    }
    
    private void loadVoucherData() {
        List<Voucher> listVouchers = controller.getAllVouchers();
        displayVoucherList(listVouchers);
    }
    
    private void displayVoucherList(List<Voucher> listVouchers) {
        tableModel.setRowCount(0);
        for (Voucher voucher : listVouchers) {
            Object[] row = {
                voucher.getId(),
                voucher.getMaVoucher(),
                voucher.getPhanTramGiamGia(),
                formatDate(voucher.getNgayBatDau()),
                formatDate(voucher.getNgayKetThuc()),
                voucher.isTrangThai() ? "Kích hoạt" : "Hết hạn"
            };
            tableModel.addRow(row);
        }
    }
    
    private void displaySelectedVoucher(int selectedRow) {
        txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtMaVoucher.setText(tableModel.getValueAt(selectedRow, 1).toString());
        
        try {
            Date ngayBatDau = dateFormat.parse(tableModel.getValueAt(selectedRow, 2).toString());
            Date ngayKetThuc = dateFormat.parse(tableModel.getValueAt(selectedRow, 3).toString());
            dateNgayBatDau.setDate(ngayBatDau.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            dateNgayKetThuc.setDate(ngayKetThuc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        txtPhanTramGiam.setText(tableModel.getValueAt(selectedRow, 4).toString());
        
        String trangThai = tableModel.getValueAt(selectedRow, 5).toString();
        switch (trangThai) {
            case "Kích hoạt":
                cboTrangThai.setSelectedIndex(0);
                break;
            case "Hết hạn":
                cboTrangThai.setSelectedIndex(1);
                break;
            default:
                cboTrangThai.setSelectedIndex(0);
        }
    }
    
    private boolean validateInput() {
        // Validate mã voucher
        if (txtMaVoucher.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã voucher!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtMaVoucher.requestFocus();
            return false;
        }
        
        // Validate phần trăm giảm
        try {
            int phanTramGiam = Integer.parseInt(txtPhanTramGiam.getText().trim());
            if (phanTramGiam <= 0 || phanTramGiam > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm phải > 0 và <= 100!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtPhanTramGiam.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm giảm phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPhanTramGiam.requestFocus();
            return false;
        }
        
        // Validate ngày bắt đầu
        if (dateNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgayBatDau.requestFocus();
            return false;
        }
        
        // Validate ngày kết thúc
        if (dateNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgayKetThuc.requestFocus();
            return false;
        }
        
        // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
        LocalDate ngayBatDau = dateNgayBatDau.getDate();
        LocalDate ngayKetThuc = dateNgayKetThuc.getDate();
        if (ngayKetThuc.isBefore(ngayBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            dateNgayKetThuc.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private Voucher getVoucherFromForm() {
        Voucher voucher = new Voucher();
        if (!txtId.getText().isEmpty()) {
            voucher.setId(Integer.parseInt(txtId.getText()));
        }
        voucher.setMaVoucher(txtMaVoucher.getText().trim());
        voucher.setPhanTramGiamGia(new BigDecimal(txtPhanTramGiam.getText().trim()));
        LocalDate localNgayBatDau = dateNgayBatDau.getDate();
        LocalDate localNgayKetThuc = dateNgayKetThuc.getDate();
        Date ngayBatDau = Date.from(localNgayBatDau.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date ngayKetThuc = Date.from(localNgayKetThuc.atStartOfDay(ZoneId.systemDefault()).toInstant());
        voucher.setNgayBatDau(ngayBatDau);
        voucher.setNgayKetThuc(ngayKetThuc);
        voucher.setTrangThai(cboTrangThai.getSelectedIndex() == 0);
        return voucher;
    }
    
    private String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }
}