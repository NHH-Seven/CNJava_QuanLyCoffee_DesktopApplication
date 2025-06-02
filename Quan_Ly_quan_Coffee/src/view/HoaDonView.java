package view;

import controller.HoaDonController;
import model.HoaDon;
import model.ChiTietHoaDon;
import utils.DatabaseUtil;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HoaDonView extends JPanel {
	private HoaDonController controller;
	private JTable tableHoaDon;
	private DefaultTableModel modelHoaDon;
	private JTable tableChiTiet;
	private DefaultTableModel modelChiTiet;
	private JSpinner dateFrom, dateTo;
	private JButton btnTimKiem, btnXemChiTiet, btnInHoaDon, btnLamMoi;
	private JLabel lblTongTien, lblGiamGia, lblThanhTien;
	private NumberFormat currencyFormat = new DecimalFormat("#,###.##");
	
	// Colors
	private Color primaryColor = new Color(121, 85, 72);
	private Color secondaryColor = new Color(188, 170, 164);
	private Color accentColor = new Color(62, 39, 35);
	private Color backgroundColor = new Color(245, 245, 245);
	
	private boolean isRefreshing = false;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Quản Lý Hóa Đơn");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setContentPane(new HoaDonView());
			frame.pack();
			frame.setSize(1200, 700);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
	
	public HoaDonView() {
		controller = new HoaDonController();
		initializeUI();
		loadHoaDonData();
	}
	
	private void initializeUI() {
		setLayout(new BorderLayout(10, 10));
		setBackground(backgroundColor);
		
		// Title
		JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setForeground(primaryColor);
		lblTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
		
		// Search Panel
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		searchPanel.setBackground(backgroundColor);
		
		SpinnerDateModel dateModel = new SpinnerDateModel();
		dateFrom = new JSpinner(dateModel);
		dateFrom.setEditor(new JSpinner.DateEditor(dateFrom, "dd/MM/yyyy"));
		dateFrom.setPreferredSize(new Dimension(150, 30));
		
		dateModel = new SpinnerDateModel();
		dateTo = new JSpinner(dateModel);
		dateTo.setEditor(new JSpinner.DateEditor(dateTo, "dd/MM/yyyy"));
		dateTo.setPreferredSize(new Dimension(150, 30));
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBackground(primaryColor);
		btnTimKiem.setForeground(Color.BLACK);
		btnTimKiem.setFocusPainted(false);
		
		searchPanel.add(new JLabel("Từ ngày:"));
		searchPanel.add(dateFrom);
		searchPanel.add(new JLabel("Đến ngày:"));
		searchPanel.add(dateTo);
		searchPanel.add(btnTimKiem);
		
		// Hóa đơn table
		String[] columns = {"ID", "Ngày lập", "Tổng tiền", "Giảm giá", "Thành tiền", "Trạng thái"};
		modelHoaDon = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableHoaDon = new JTable(modelHoaDon);
		tableHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHoaDon.setRowHeight(25);
		
		JScrollPane scrollPaneHoaDon = new JScrollPane(tableHoaDon);
		
		// Chi tiết hóa đơn table
		String[] columnsChiTiet = {"ID", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
		modelChiTiet = new DefaultTableModel(columnsChiTiet, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tableChiTiet = new JTable(modelChiTiet);
		tableChiTiet.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableChiTiet.setRowHeight(25);
		
		JScrollPane scrollPaneChiTiet = new JScrollPane(tableChiTiet);
		
		// Summary Panel
		JPanel summaryPanel = new JPanel(new GridLayout(3, 2, 5, 5));
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
		
		// Button Panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		buttonPanel.setBackground(backgroundColor);
		
		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setBackground(primaryColor);
		btnXemChiTiet.setForeground(Color.BLACK);
		btnXemChiTiet.setFocusPainted(false);
		
		btnInHoaDon = new JButton("In hóa đơn");
		btnInHoaDon.setBackground(primaryColor);
		btnInHoaDon.setForeground(Color.BLACK);
		btnInHoaDon.setFocusPainted(false);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(primaryColor);
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setFocusPainted(false);
		
		buttonPanel.add(btnXemChiTiet);
		buttonPanel.add(btnInHoaDon);
		buttonPanel.add(btnLamMoi);
		
		// Layout
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(backgroundColor);
		topPanel.add(lblTitle, BorderLayout.NORTH);
		topPanel.add(searchPanel, BorderLayout.CENTER);
		
		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel.setBackground(backgroundColor);
		centerPanel.add(scrollPaneHoaDon, BorderLayout.CENTER);
		centerPanel.add(summaryPanel, BorderLayout.SOUTH);
		
		JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
		bottomPanel.setBackground(backgroundColor);
		bottomPanel.add(scrollPaneChiTiet, BorderLayout.CENTER);
		bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		// Add event listeners
		btnTimKiem.addActionListener(e -> timKiemHoaDon());
		btnXemChiTiet.addActionListener(e -> xemChiTietHoaDon());
		btnInHoaDon.addActionListener(e -> inHoaDon());
		btnLamMoi.addActionListener(e -> lamMoiDuLieu());
		tableHoaDon.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				xemChiTietHoaDon();
			}
		});
	}
	
	private void loadHoaDonData() {
		List<HoaDon> danhSach = controller.getAllHoaDon();
		updateHoaDonTable(danhSach);
	}
	
	private void updateHoaDonTable(List<HoaDon> danhSach) {
		modelHoaDon.setRowCount(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		for (HoaDon hd : danhSach) {
			Object[] row = {
				hd.getId(),
				dateFormat.format(hd.getNgayLap()),
				currencyFormat.format(hd.getTongTien()) + " VND",
				currencyFormat.format(hd.getGiamGia()) + " VND",
				currencyFormat.format(hd.getThanhTien()) + " VND",
				hd.isTrangThai() ? "Đã thanh toán" : "Chưa thanh toán"
			};
			modelHoaDon.addRow(row);
		}
	}
	
	private void timKiemHoaDon() {
		Date ngayBatDau = (Date) dateFrom.getValue();
		Date ngayKetThuc = (Date) dateTo.getValue();
		
		if (ngayBatDau == null || ngayKetThuc == null) {
			JOptionPane.showMessageDialog(this,
				"Vui lòng chọn khoảng thời gian tìm kiếm!",
				"Thông báo",
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Convert java.util.Date to java.sql.Date
		java.sql.Date sqlNgayBatDau = new java.sql.Date(ngayBatDau.getTime());
		java.sql.Date sqlNgayKetThuc = new java.sql.Date(ngayKetThuc.getTime());
		
		List<HoaDon> danhSach = controller.timKiemHoaDonTheoNgay(sqlNgayBatDau, sqlNgayKetThuc);
		updateHoaDonTable(danhSach);
	}
	
	private void xemChiTietHoaDon() {
		if (isRefreshing) return;
		int selectedRow = tableHoaDon.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this,
				"Vui lòng chọn hóa đơn cần xem chi tiết!",
				"Thông báo",
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			int hoaDonId = Integer.parseInt(modelHoaDon.getValueAt(selectedRow, 0).toString());
			System.out.println("Đang tìm hóa đơn với ID: " + hoaDonId); // Debug log
			
			HoaDon hoaDon = controller.getHoaDonById(hoaDonId);
			
			if (hoaDon != null) {
				System.out.println("Đã tìm thấy hóa đơn. Số chi tiết: " + hoaDon.getChiTietList().size()); // Debug log
				updateChiTietTable(hoaDon.getChiTietList());
				updateSummary(hoaDon);
			} else {
				JOptionPane.showMessageDialog(this,
					"Không tìm thấy thông tin chi tiết hóa đơn!",
					"Lỗi",
					JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
				"Lỗi khi xử lý ID hóa đơn: " + e.getMessage(),
				"Lỗi",
				JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				"Có lỗi xảy ra khi xem chi tiết hóa đơn: " + e.getMessage(),
				"Lỗi",
				JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void updateChiTietTable(List<ChiTietHoaDon> chiTietList) {
		try {
			modelChiTiet.setRowCount(0);
			
			if (chiTietList == null || chiTietList.isEmpty()) {
				System.out.println("Danh sách chi tiết hóa đơn trống"); // Debug log
				return;
			}
			
			System.out.println("Đang cập nhật bảng chi tiết với " + chiTietList.size() + " mục"); // Debug log
			
			for (ChiTietHoaDon cthd : chiTietList) {
				if (cthd.getSanPham() == null) {
					System.out.println("Cảnh báo: Sản phẩm null cho chi tiết ID: " + cthd.getId()); // Debug log
					continue;
				}
				
				Object[] row = {
					cthd.getId(),
					cthd.getSanPham().getTenSanPham(),
					cthd.getSoLuong(),
					currencyFormat.format(cthd.getDonGia()) + " VND",
					currencyFormat.format(cthd.getThanhTien()) + " VND"
				};
				modelChiTiet.addRow(row);
			}
			
			System.out.println("Đã cập nhật xong bảng chi tiết"); // Debug log
		} catch (Exception e) {
			System.err.println("Lỗi khi cập nhật bảng chi tiết: " + e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
				"Có lỗi xảy ra khi hiển thị chi tiết hóa đơn: " + e.getMessage(),
				"Lỗi",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateSummary(HoaDon hoaDon) {
		lblTongTien.setText(currencyFormat.format(hoaDon.getTongTien()) + " VND");
		lblGiamGia.setText(currencyFormat.format(hoaDon.getGiamGia()) + " VND");
		lblThanhTien.setText(currencyFormat.format(hoaDon.getThanhTien()) + " VND");
	}
	
	private void inHoaDon() {
		int selectedRow = tableHoaDon.getSelectedRow();
		if (selectedRow < 0) {
			JOptionPane.showMessageDialog(this,
				"Vui lòng chọn hóa đơn cần in!",
				"Thông báo",
				JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int hoaDonId = Integer.parseInt(modelHoaDon.getValueAt(selectedRow, 0).toString());
		HoaDon hoaDon = controller.getHoaDonById(hoaDonId);
		
		if (hoaDon != null) {
			try {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new HoaDonPrintable(hoaDon));
				
				if (job.printDialog()) {
					job.print();
				}
			} catch (PrinterException e) {
				JOptionPane.showMessageDialog(this,
					"Lỗi khi in hóa đơn: " + e.getMessage(),
					"Lỗi",
					JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Làm mới dữ liệu
	 */
	private void lamMoiDuLieu() {
		try {
			isRefreshing = true;
			// Xóa dữ liệu cũ
			modelHoaDon.setRowCount(0);
			modelChiTiet.setRowCount(0);
			// Reset các label tổng tiền
			lblTongTien.setText("0 VND");
			lblGiamGia.setText("0 VND");
			lblThanhTien.setText("0 VND");
			// Load lại dữ liệu mới
			loadHoaDonData();
			isRefreshing = false;
			JOptionPane.showMessageDialog(this,
				"Đã làm mới dữ liệu thành công!",
				"Thông báo",
				JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			isRefreshing = false;
			JOptionPane.showMessageDialog(this,
				"Có lỗi xảy ra khi làm mới dữ liệu: " + e.getMessage(),
				"Lỗi",
				JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	// Inner class for printing
	private class HoaDonPrintable implements Printable {
		private HoaDon hoaDon;
		private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		public HoaDonPrintable(HoaDon hoaDon) {
			this.hoaDon = hoaDon;
		}
		
		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			if (pageIndex > 0) {
				return NO_SUCH_PAGE;
			}
			
			Graphics2D g2d = (Graphics2D) graphics;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			
			// Set font
			Font titleFont = new Font("Arial", Font.BOLD, 18);
			Font headerFont = new Font("Arial", Font.BOLD, 12);
			Font normalFont = new Font("Arial", Font.PLAIN, 12);
			
			int y = 50;
			int lineHeight = 20;
			
			// Title
			g2d.setFont(titleFont);
			String title = "HÓA ĐƠN BÁN HÀNG";
			int titleWidth = g2d.getFontMetrics().stringWidth(title);
			g2d.drawString(title, (int) (pageFormat.getImageableWidth() - titleWidth) / 2, y);
			
			// Header info
			y += lineHeight * 2;
			g2d.setFont(normalFont);
			g2d.drawString("Mã hóa đơn: " + hoaDon.getId(), 50, y);
			y += lineHeight;
			g2d.drawString("Ngày lập: " + dateFormat.format(hoaDon.getNgayLap()), 50, y);
			
			// Table header
			y += lineHeight * 2;
			g2d.setFont(headerFont);
			g2d.drawString("Tên sản phẩm", 50, y);
			g2d.drawString("Số lượng", 300, y);
			g2d.drawString("Đơn giá", 400, y);
			g2d.drawString("Thành tiền", 500, y);
			
			// Table content
			y += lineHeight;
			g2d.setFont(normalFont);
			for (ChiTietHoaDon cthd : hoaDon.getChiTietList()) {
				g2d.drawString(cthd.getSanPham().getTenSanPham(), 50, y);
				g2d.drawString(String.valueOf(cthd.getSoLuong()), 300, y);
				g2d.drawString(currencyFormat.format(cthd.getDonGia()) + " VND", 400, y);
				g2d.drawString(currencyFormat.format(cthd.getThanhTien()) + " VND", 500, y);
				y += lineHeight;
			}
			
			// Summary
			y += lineHeight;
			g2d.drawString("Tổng tiền: " + currencyFormat.format(hoaDon.getTongTien()) + " VND", 400, y);
			y += lineHeight;
			g2d.drawString("Giảm giá: " + currencyFormat.format(hoaDon.getGiamGia()) + " VND", 400, y);
			y += lineHeight;
			g2d.setFont(headerFont);
			g2d.drawString("Thành tiền: " + currencyFormat.format(hoaDon.getThanhTien()) + " VND", 400, y);
			
			return PAGE_EXISTS;
		}
	}
}
