package view;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.SwingUtilities;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.NguoiDungController;
import model.NguoiDung;

/**
 * Giao diện quản lý người dùng hệ thống
 */
public class NguoiDungView extends JPanel {
    private NguoiDungController controller;
    
    // Khai báo các thành phần giao diện
    private JPanel mainPanel, formPanel, tablePanel, buttonPanel;
    private JTextField txtId, txtTenDangNhap, txtSearch;
    private JPasswordField txtMatKhau, txtXacNhanMatKhau;
    private JComboBox<String> cboVaiTro;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
    private JTable tableNguoiDung;
    private DefaultTableModel tableModel;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    // Thiết lập màu sắc chủ đạo cho ứng dụng
    private Color primaryColor = new Color(121, 85, 72); // Màu nâu theo theme cà phê
    private Color secondaryColor = new Color(188, 170, 164); // Màu nâu nhạt
    private Color accentColor = new Color(62, 39, 35); // Màu nâu đậm
    private Color backgroundColor = new Color(245, 245, 245); // Màu nền nhẹ
    private Color successColor = new Color(76, 175, 80); // Màu xanh lá
    private Color warningColor = new Color(255, 152, 0); // Màu cam
    private Color dangerColor = new Color(244, 67, 54); // Màu đỏ
    
    public NguoiDungView() {
        // Khởi tạo controller
        controller = new NguoiDungController();
        
        // Thiết lập layout cho panel chính
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        
        // Tạo panel chính có border layout
        createMainPanel();
        
        // Load dữ liệu ban đầu
        loadNguoiDungData();
    }
    
    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(backgroundColor);
        
        // Tạo tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ NGƯỜI DÙNG", SwingConstants.CENTER);
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
                "Thông tin người dùng",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        formPanel.setPreferredSize(new Dimension(350, 400));
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

        // Tên đăng nhập
        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblTenDangNhap.setFont(labelFont);
        GridBagConstraints gbcLblTenDangNhap = new GridBagConstraints();
        gbcLblTenDangNhap.gridx = 0;
        gbcLblTenDangNhap.gridy = 1;
        gbcLblTenDangNhap.insets = new Insets(8, 8, 8, 8);
        gbcLblTenDangNhap.anchor = GridBagConstraints.WEST;
        formPanel.add(lblTenDangNhap, gbcLblTenDangNhap);

        txtTenDangNhap = new JTextField();
        txtTenDangNhap.setFont(fieldFont);
        GridBagConstraints gbcTxtTenDangNhap = new GridBagConstraints();
        gbcTxtTenDangNhap.gridx = 1;
        gbcTxtTenDangNhap.gridy = 1;
        gbcTxtTenDangNhap.insets = new Insets(8, 8, 8, 8);
        gbcTxtTenDangNhap.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtTenDangNhap.weightx = 1.0;
        formPanel.add(txtTenDangNhap, gbcTxtTenDangNhap);

        // Mật khẩu
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(labelFont);
        GridBagConstraints gbcLblMatKhau = new GridBagConstraints();
        gbcLblMatKhau.gridx = 0;
        gbcLblMatKhau.gridy = 2;
        gbcLblMatKhau.insets = new Insets(8, 8, 8, 8);
        gbcLblMatKhau.anchor = GridBagConstraints.WEST;
        formPanel.add(lblMatKhau, gbcLblMatKhau);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(fieldFont);
        GridBagConstraints gbcTxtMatKhau = new GridBagConstraints();
        gbcTxtMatKhau.gridx = 1;
        gbcTxtMatKhau.gridy = 2;
        gbcTxtMatKhau.insets = new Insets(8, 8, 8, 8);
        gbcTxtMatKhau.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtMatKhau.weightx = 1.0;
        formPanel.add(txtMatKhau, gbcTxtMatKhau);
        
        // Xác nhận mật khẩu
        JLabel lblXacNhanMatKhau = new JLabel("Xác nhận MK:");
        lblXacNhanMatKhau.setFont(labelFont);
        GridBagConstraints gbcLblXacNhanMatKhau = new GridBagConstraints();
        gbcLblXacNhanMatKhau.gridx = 0;
        gbcLblXacNhanMatKhau.gridy = 3;
        gbcLblXacNhanMatKhau.insets = new Insets(8, 8, 8, 8);
        gbcLblXacNhanMatKhau.anchor = GridBagConstraints.WEST;
        formPanel.add(lblXacNhanMatKhau, gbcLblXacNhanMatKhau);

        txtXacNhanMatKhau = new JPasswordField();
        txtXacNhanMatKhau.setFont(fieldFont);
        GridBagConstraints gbcTxtXacNhanMatKhau = new GridBagConstraints();
        gbcTxtXacNhanMatKhau.gridx = 1;
        gbcTxtXacNhanMatKhau.gridy = 3;
        gbcTxtXacNhanMatKhau.insets = new Insets(8, 8, 8, 8);
        gbcTxtXacNhanMatKhau.fill = GridBagConstraints.HORIZONTAL;
        gbcTxtXacNhanMatKhau.weightx = 1.0;
        formPanel.add(txtXacNhanMatKhau, gbcTxtXacNhanMatKhau);

        // Vai trò
        JLabel lblVaiTro = new JLabel("Vai trò:");
        lblVaiTro.setFont(labelFont);
        GridBagConstraints gbcLblVaiTro = new GridBagConstraints();
        gbcLblVaiTro.gridx = 0;
        gbcLblVaiTro.gridy = 4;
        gbcLblVaiTro.insets = new Insets(8, 8, 8, 8);
        gbcLblVaiTro.anchor = GridBagConstraints.WEST;
        formPanel.add(lblVaiTro, gbcLblVaiTro);

        cboVaiTro = new JComboBox<>(new String[]{"admin", "nhanvien"});
        cboVaiTro.setFont(fieldFont);
        GridBagConstraints gbcCboVaiTro = new GridBagConstraints();
        gbcCboVaiTro.gridx = 1;
        gbcCboVaiTro.gridy = 4;
        gbcCboVaiTro.insets = new Insets(8, 8, 8, 8);
        gbcCboVaiTro.fill = GridBagConstraints.HORIZONTAL;
        gbcCboVaiTro.weightx = 1.0;
        formPanel.add(cboVaiTro, gbcCboVaiTro);
    }
    
    private void createTablePanel() {
        // Tạo panel chứa bảng hiển thị dữ liệu
        tablePanel = new JPanel(new BorderLayout(5, 10));
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(primaryColor, 1),
                "Danh sách người dùng", 
                TitledBorder.LEFT, 
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                primaryColor));
        tablePanel.setBackground(backgroundColor);
        
        // Tạo model cho table
        String[] columnNames = {"ID", "Tên đăng nhập", "Vai trò", "Ngày tạo"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép edit trực tiếp trên bảng
            }
        };
        
        // Tạo table
        tableNguoiDung = new JTable(tableModel);
        tableNguoiDung.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableNguoiDung.setRowHeight(28);
        tableNguoiDung.setGridColor(new Color(139, 69, 19));
        tableNguoiDung.setFont(new Font("Arial", Font.PLAIN, 13));
        tableNguoiDung.setBackground(new Color(255, 255, 255));
        tableNguoiDung.setSelectionBackground(secondaryColor);
        tableNguoiDung.setSelectionForeground(Color.BLACK);
        
        // Header style
        tableNguoiDung.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tableNguoiDung.getTableHeader().setBackground(primaryColor);
        tableNguoiDung.getTableHeader().setForeground(Color.BLACK);
        tableNguoiDung.getTableHeader().setPreferredSize(new Dimension(0, 35));
        
        // Thêm sự kiện khi click vào một dòng
        tableNguoiDung.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableNguoiDung.getSelectedRow();
                if (selectedRow >= 0) {
                    displaySelectedNguoiDung(selectedRow);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableNguoiDung);
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
                timKiemNguoiDung();
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
                themNguoiDung();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaNguoiDung();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaNguoiDung();
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
    private void themNguoiDung() {
        if (!validateInput()) {
            return;
        }
        
        // Kiểm tra tên đăng nhập đã tồn tại chưa
        String tenDangNhap = txtTenDangNhap.getText().trim();
        if (controller.kiemTraTenDangNhapTonTai(tenDangNhap, 0)) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtTenDangNhap.requestFocus();
            return;
        }
        
        NguoiDung nguoiDung = getNguoiDungFromForm();
        
        boolean result = controller.themNguoiDung(nguoiDung);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm người dùng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadNguoiDungData();
            lamMoiForm();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm người dùng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaNguoiDung() {
        int selectedRow = tableNguoiDung.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateInput()) {
            return;
        }
        
        int id = Integer.parseInt(txtId.getText());
        String tenDangNhap = txtTenDangNhap.getText().trim();
        
        // Kiểm tra tên đăng nhập đã tồn tại chưa (trừ ID hiện tại)
        if (controller.kiemTraTenDangNhapTonTai(tenDangNhap, id)) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtTenDangNhap.requestFocus();
            return;
        }
        
        NguoiDung nguoiDung = getNguoiDungFromForm();
        boolean result = controller.suaNguoiDung(nguoiDung);
        
        if (result) {
            JOptionPane.showMessageDialog(this, "Cập nhật người dùng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadNguoiDungData();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật người dùng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaNguoiDung() {
        int selectedRow = tableNguoiDung.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenNguoiDung = tableModel.getValueAt(selectedRow, 1).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa người dùng \"" + tenNguoiDung + "\"?", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
        if (confirm == JOptionPane.YES_OPTION) {
            boolean result = controller.xoaNguoiDung(id);
            if (result) {
                JOptionPane.showMessageDialog(this, "Xóa người dùng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadNguoiDungData();
                lamMoiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa người dùng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void timKiemNguoiDung() {
        String tuKhoa = txtSearch.getText().trim();
        if (tuKhoa.isEmpty()) {
            loadNguoiDungData(); // Nếu từ khóa trống, load lại tất cả
            return;
        }
        
        // Tìm kiếm người dùng theo tên đăng nhập
        List<NguoiDung> ketQua = controller.timKiemNguoiDungTheoTen(tuKhoa);
        
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // Thêm kết quả tìm kiếm vào bảng
        for (NguoiDung nd : ketQua) {
            Object[] row = {
                nd.getId(),
                nd.getTenDangNhap(),
                nd.getVaiTro(),
                nd.getNgayTao() != null ? dateFormat.format(nd.getNgayTao()) : ""
            };
            tableModel.addRow(row);
        }
        
        if (ketQua.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy người dùng nào phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void lamMoiForm() {
        txtId.setText("");
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        txtXacNhanMatKhau.setText("");
        cboVaiTro.setSelectedIndex(0);
        tableNguoiDung.clearSelection();
    }
    
    private void loadNguoiDungData() {
        try {
            // Xóa dữ liệu cũ
            tableModel.setRowCount(0);
            
            // Lấy danh sách người dùng từ controller
            List<NguoiDung> danhSachNguoiDung = controller.layDanhSachNguoiDung();
            
            // Thêm dữ liệu vào table model
            for (NguoiDung nd : danhSachNguoiDung) {
                Object[] row = {
                    nd.getId(),
                    nd.getTenDangNhap(),
                    nd.getVaiTro(),
                    nd.getNgayTao() != null ? dateFormat.format(nd.getNgayTao()) : ""
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ trong quá trình load dữ liệu
            System.err.println("Lỗi khi load dữ liệu người dùng: " + e.getMessage());
            e.printStackTrace();
            
            // Thông báo lỗi nhưng không dừng ứng dụng
            JOptionPane.showMessageDialog(this, 
                "Đã xảy ra lỗi khi tải dữ liệu người dùng. Chi tiết: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displaySelectedNguoiDung(int selectedRow) {
        txtId.setText(tableModel.getValueAt(selectedRow, 0).toString());
        txtTenDangNhap.setText((String) tableModel.getValueAt(selectedRow, 1));
        
        // Reset mật khẩu fields
        txtMatKhau.setText("");
        txtXacNhanMatKhau.setText("");
        
        String vaiTro = (String) tableModel.getValueAt(selectedRow, 2);
        if ("admin".equals(vaiTro)) {
            cboVaiTro.setSelectedIndex(0);
        } else if ("nhanvien".equals(vaiTro)) {
            cboVaiTro.setSelectedIndex(1);
        }
    }
    
    private NguoiDung getNguoiDungFromForm() {
        NguoiDung nguoiDung = new NguoiDung();
        
        if (!txtId.getText().isEmpty()) {
            nguoiDung.setId(Integer.parseInt(txtId.getText()));
        }
        
        nguoiDung.setTenDangNhap(txtTenDangNhap.getText().trim());
        nguoiDung.setMatKhau(new String(txtMatKhau.getPassword()));
        nguoiDung.setVaiTro((String) cboVaiTro.getSelectedItem());
        
        return nguoiDung;
    }
    
    private boolean validateInput() {
        if (txtTenDangNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtTenDangNhap.requestFocus();
            return false;
        }
        
        // Validate tên đăng nhập theo định dạng (chỉ cho phép chữ, số và dấu gạch dưới)
        if (!txtTenDangNhap.getText().trim().matches("^[a-zA-Z0-9_]+$")) {
        	JOptionPane.showMessageDialog(this, 
                    "Tên đăng nhập chỉ được chứa chữ cái, số và dấu gạch dưới!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                txtTenDangNhap.requestFocus();
                return false;
            }
            
            // Kiểm tra mật khẩu khi thêm mới hoặc khi muốn thay đổi mật khẩu
            String matKhau = new String(txtMatKhau.getPassword());
            String xacNhanMatKhau = new String(txtXacNhanMatKhau.getPassword());
            
            // Nếu đang thêm mới (không có ID) hoặc có nhập mật khẩu khi sửa
            if (txtId.getText().isEmpty() || !matKhau.isEmpty()) {
                if (matKhau.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMatKhau.requestFocus();
                    return false;
                }
                
                if (matKhau.length() < 6) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtMatKhau.requestFocus();
                    return false;
                }
                
                if (!matKhau.equals(xacNhanMatKhau)) {
                    JOptionPane.showMessageDialog(this, "Xác nhận mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtXacNhanMatKhau.requestFocus();
                    return false;
                }
            }
            
            return true;
        }
        
        /**
         * Phương thức trả về panel để nhúng vào các màn hình khác
         * @return JPanel của màn hình quản lý người dùng
         */
        public JPanel getMainPanel() {
            return mainPanel;
        }
        
        /**
         * Làm mới dữ liệu trên giao diện
         */
        public void refreshData() {
            loadNguoiDungData();
            lamMoiForm();
        }
        
        /**
         * Cài đặt quyền truy cập cho người dùng
         * @param isAdmin true nếu người dùng là admin, false nếu là nhân viên
         */
        public void setPermission(boolean isAdmin) {
            // Chỉ admin mới có quyền thêm, sửa, xóa người dùng
            btnThem.setEnabled(isAdmin);
            btnSua.setEnabled(isAdmin);
            btnXoa.setEnabled(isAdmin);
            
            // Disable các input nếu không phải admin
            txtTenDangNhap.setEditable(isAdmin);
            txtMatKhau.setEditable(isAdmin);
            txtXacNhanMatKhau.setEditable(isAdmin);
            cboVaiTro.setEnabled(isAdmin);
            
            // Nhân viên vẫn có thể xem và tìm kiếm
            txtSearch.setEditable(true);
            btnTimKiem.setEnabled(true);
            tableNguoiDung.setEnabled(true);
        }
        
        /**
         * Cài đặt màu sắc tùy chỉnh cho giao diện
         * @param primary Màu chủ đạo
         * @param secondary Màu phụ
         * @param accent Màu nhấn mạnh
         */
        public void setCustomColors(Color primary, Color secondary, Color accent) {
            this.primaryColor = primary;
            this.secondaryColor = secondary;
            this.accentColor = accent;
            
            // Cập nhật lại màu sắc cho các thành phần
            updateComponentColors();
        }
        
        private void updateComponentColors() {
            // Cập nhật màu cho các border
            ((TitledBorder) formPanel.getBorder()).setTitleColor(primaryColor);
            ((TitledBorder) tablePanel.getBorder()).setTitleColor(primaryColor);
            
            // Cập nhật màu cho bảng
            tableNguoiDung.getTableHeader().setBackground(primaryColor);
            tableNguoiDung.setSelectionBackground(secondaryColor);
            
            // Cập nhật màu cho các nút (giữ nguyên màu của button theo chức năng)
            btnTimKiem.setBackground(primaryColor);
            
            // Yêu cầu cập nhật giao diện
            SwingUtilities.updateComponentTreeUI(this);
        }
        
        /**
         * Phương thức main để test giao diện
         */
        public static void main(String[] args) {
            try {
                // Thiết lập look and feel
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Tạo và hiển thị frame
            javax.swing.JFrame frame = new javax.swing.JFrame("Quản lý người dùng - Hệ thống quản lý quán cà phê");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            
            NguoiDungView view = new NguoiDungView();
            frame.getContentPane().add(view);
            
            frame.setVisible(true);
        }
    }
