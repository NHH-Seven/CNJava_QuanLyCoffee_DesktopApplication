package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.Box;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.ui.TextAnchor;

import utils.DatabaseUtil;

public class ThongKeView extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cboThoiGian;
	private JTable tableThongKe;
	private DefaultTableModel modelThongKe;
	private JLabel lblTongDoanhThu;
	private JLabel lblSoHoaDon;
	private Connection conn;
	private JTabbedPane tabbedPane;
	
	// Các biểu đồ
	private ChartPanel doanhThuChartPanel;
	private ChartPanel sanPhamChartPanel;
	private ChartPanel voucherChartPanel;
	private ChartPanel nhanVienChartPanel;

	// Thêm các biến bảng và model cho các tab
	private DefaultTableModel modelSanPham, modelVoucher, modelNhanVien;
	private JTable tableSanPham, tableVoucher, tableNhanVien;

	public ThongKeView() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		try {
			conn = DatabaseUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Panel chọn thời gian
		JPanel panelThoiGian = createTimeSelectionPanel();
		
		// Panel tổng quan
		JPanel panelTongQuan = createOverviewPanel();
		
		// TabbedPane cho các loại thống kê
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		// Thêm các tab
		tabbedPane.addTab("Doanh Thu", createDoanhThuPanel());
		tabbedPane.addTab("Sản Phẩm", createSanPhamPanel());
		tabbedPane.addTab("Voucher", createVoucherPanel());
		tabbedPane.addTab("Nhân Viên", createNhanVienPanel());

		// Thêm các panel vào layout
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(panelThoiGian, BorderLayout.NORTH);
		topPanel.add(panelTongQuan, BorderLayout.CENTER);

		this.add(topPanel, BorderLayout.NORTH);
		this.add(tabbedPane, BorderLayout.CENTER);

		// Hiển thị thống kê mặc định
		capNhatThongKe();
	}

	private JPanel createTimeSelectionPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(BorderFactory.createTitledBorder("Chọn thời gian"));
		cboThoiGian = new JComboBox<>(new String[]{"Hôm nay", "Tuần này", "Tháng này", "Năm nay"});
		JButton btnXem = new JButton("Xem thống kê");
		btnXem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel.add(new JLabel("Thời gian:"));
		panel.add(cboThoiGian);
		panel.add(btnXem);

		btnXem.addActionListener(e -> capNhatThongKe());
		return panel;
	}

	private JPanel createOverviewPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		panel.setBorder(BorderFactory.createTitledBorder("Tổng quan"));
		lblTongDoanhThu = new JLabel("Tổng doanh thu: 0 VND");
		lblSoHoaDon = new JLabel("Số hóa đơn: 0");
		lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSoHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panel.add(lblTongDoanhThu);
		panel.add(lblSoHoaDon);
		return panel;
	}

	private JPanel createDoanhThuPanel() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));

		// Biểu đồ doanh thu
		doanhThuChartPanel = new ChartPanel(null);
		doanhThuChartPanel.setPreferredSize(new Dimension(900, 400));
		doanhThuChartPanel.setBorder(BorderFactory.createLineBorder(new Color(41, 128, 185), 2, true));

		// Bảng chi tiết doanh thu
		String[] columns = {"Ngày", "Doanh thu", "Số hóa đơn"};
		modelThongKe = new DefaultTableModel(columns, 0);
		tableThongKe = new JTable(modelThongKe);
		tableThongKe.setRowHeight(28);
		tableThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JScrollPane scrollPane = new JScrollPane(tableThongKe);
		scrollPane.setPreferredSize(new Dimension(900, 200));
		scrollPane.setBorder(BorderFactory.createTitledBorder("Chi tiết doanh thu"));

		panel.add(doanhThuChartPanel, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		return panel;
	}

	private JPanel createSanPhamPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));

		// Bar chart top sản phẩm
		sanPhamChartPanel = new ChartPanel(null);
		sanPhamChartPanel.setPreferredSize(new Dimension(900, 350));
		sanPhamChartPanel.setBorder(BorderFactory.createLineBorder(new Color(46, 204, 113), 2, true));
		panel.add(sanPhamChartPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Bảng chi tiết sản phẩm
		String[] columnsSP = {"Tên sản phẩm", "Số lượng bán"};
		modelSanPham = new DefaultTableModel(columnsSP, 0);
		tableSanPham = new JTable(modelSanPham);
		tableSanPham.setRowHeight(28);
		tableSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JScrollPane scrollSP = new JScrollPane(tableSanPham);
		scrollSP.setPreferredSize(new Dimension(900, 150));
		scrollSP.setBorder(BorderFactory.createTitledBorder("Chi tiết sản phẩm bán chạy"));
		panel.add(scrollSP);

		return panel;
	}

	private JPanel createVoucherPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));

		// Bar chart voucher sử dụng
		voucherChartPanel = new ChartPanel(null);
		voucherChartPanel.setPreferredSize(new Dimension(900, 350));
		voucherChartPanel.setBorder(BorderFactory.createLineBorder(new Color(155, 89, 182), 2, true));
		panel.add(voucherChartPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Bảng chi tiết voucher
		String[] columnsVC = {"Mã voucher", "Số lần sử dụng"};
		modelVoucher = new DefaultTableModel(columnsVC, 0);
		tableVoucher = new JTable(modelVoucher);
		tableVoucher.setRowHeight(28);
		tableVoucher.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JScrollPane scrollVC = new JScrollPane(tableVoucher);
		scrollVC.setPreferredSize(new Dimension(900, 150));
		scrollVC.setBorder(BorderFactory.createTitledBorder("Chi tiết voucher sử dụng"));
		panel.add(scrollVC);

		return panel;
	}

	private JPanel createNhanVienPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));

		// Bar chart doanh thu theo nhân viên
		nhanVienChartPanel = new ChartPanel(null);
		nhanVienChartPanel.setPreferredSize(new Dimension(900, 350));
		nhanVienChartPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 126, 34), 2, true));
		panel.add(nhanVienChartPanel);
		panel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Bảng chi tiết nhân viên
		String[] columnsNV = {"Tên nhân viên", "Doanh thu"};
		modelNhanVien = new DefaultTableModel(columnsNV, 0);
		tableNhanVien = new JTable(modelNhanVien);
		tableNhanVien.setRowHeight(28);
		tableNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JScrollPane scrollNV = new JScrollPane(tableNhanVien);
		scrollNV.setPreferredSize(new Dimension(900, 150));
		scrollNV.setBorder(BorderFactory.createTitledBorder("Chi tiết doanh thu nhân viên"));
		panel.add(scrollNV);

		return panel;
	}

	private void capNhatThongKe() {
		String thoiGian = cboThoiGian.getSelectedItem().toString();
		Calendar cal = Calendar.getInstance();
		Date ngayBatDau = null;
		Date ngayKetThuc = new Date();

		switch (thoiGian) {
			case "Hôm nay":
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				ngayBatDau = cal.getTime();
				break;
			case "Tuần này":
				cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				ngayBatDau = cal.getTime();
				break;
			case "Tháng này":
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				ngayBatDau = cal.getTime();
				break;
			case "Năm nay":
				cal.set(Calendar.DAY_OF_YEAR, 1);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				ngayBatDau = cal.getTime();
				break;
		}

		try {
			// Cập nhật doanh thu
			capNhatDoanhThu(ngayBatDau, ngayKetThuc);
			
			// Cập nhật thống kê sản phẩm
			capNhatThongKeSanPham(ngayBatDau, ngayKetThuc);
			
			// Cập nhật thống kê voucher
			capNhatThongKeVoucher(ngayBatDau, ngayKetThuc);
			
			// Cập nhật thống kê nhân viên
			capNhatThongKeNhanVien(ngayBatDau, ngayKetThuc);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void capNhatDoanhThu(Date ngayBatDau, Date ngayKetThuc) throws SQLException {
		String sql = "SELECT CAST(ngayLap AS DATE) as ngay, " +
					"SUM(thanhTien) as doanhThu, " +
					"COUNT(*) as soHoaDon " +
					"FROM HoaDon " +
					"WHERE ngayLap BETWEEN ? AND ? " +
					"GROUP BY CAST(ngayLap AS DATE) " +
					"ORDER BY ngay";
			
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setTimestamp(1, new java.sql.Timestamp(ngayBatDau.getTime()));
		pstmt.setTimestamp(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
		
		ResultSet rs = pstmt.executeQuery();
		
		// Cập nhật bảng thống kê
		modelThongKe.setRowCount(0);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		double tongDoanhThu = 0;
		int tongHoaDon = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat currencyFormat = new DecimalFormat("#,### VND");

		while (rs.next()) {
			Date ngay = rs.getDate("ngay");
			double doanhThu = rs.getDouble("doanhThu");
			int soHoaDon = rs.getInt("soHoaDon");
			
			modelThongKe.addRow(new Object[]{
				dateFormat.format(ngay),
				currencyFormat.format(doanhThu),
				soHoaDon
			});
			
			dataset.addValue(doanhThu, "Doanh thu", dateFormat.format(ngay));
			
			tongDoanhThu += doanhThu;
			tongHoaDon += soHoaDon;
		}

		// Cập nhật tổng quan
		lblTongDoanhThu.setText("Tổng doanh thu: " + currencyFormat.format(tongDoanhThu));
		lblSoHoaDon.setText("Số hóa đơn: " + tongHoaDon);

		// Cập nhật biểu đồ doanh thu
		JFreeChart chart = ChartFactory.createLineChart(
			"Biểu đồ doanh thu theo ngày",
			"Ngày",
			"Doanh thu (VND)",
			dataset,
			PlotOrientation.VERTICAL,
			true,
			true,
			false
		);

		// Tùy chỉnh biểu đồ
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(245, 245, 245));
		plot.setRangeGridlinePaint(new Color(200, 200, 200));
		plot.setDomainGridlinePaint(new Color(200, 200, 200));
		
		// Tùy chỉnh renderer
		LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		renderer.setSeriesPaint(0, new Color(41, 128, 185)); // Màu xanh dương đẹp
		renderer.setSeriesStroke(0, new java.awt.BasicStroke(3.0f));
		renderer.setSeriesShapesVisible(0, true);
		renderer.setSeriesShapesFilled(0, true);
		renderer.setUseFillPaint(true);
		renderer.setSeriesFillPaint(0, new Color(41, 128, 185, 50));
		
		// Hiển thị giá trị trên biểu đồ
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.BOLD, 12));
		renderer.setDefaultItemLabelPaint(Color.BLACK);
		renderer.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator(
			"{2}", new DecimalFormat("#,### VND")));
		renderer.setDefaultPositiveItemLabelPosition(
			new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0)
		);
		plot.setRenderer(renderer);
		plot.getRangeAxis().setUpperMargin(0.15);
		
		// Tùy chỉnh font và màu sắc
		chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 20));
		chart.getTitle().setPaint(new Color(44, 62, 80));
		
		// Tùy chỉnh trục
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
		rangeAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
		rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
		domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		// Thêm legend
		chart.getLegend().setItemFont(new Font("Segoe UI", Font.PLAIN, 12));
		chart.getLegend().setBackgroundPaint(new Color(245, 245, 245));
		chart.getLegend().setFrame(BlockBorder.NONE);

		doanhThuChartPanel.setChart(chart);
	}

	private void capNhatThongKeSanPham(Date ngayBatDau, Date ngayKetThuc) throws SQLException {
		String sql = "SELECT TOP 10 sp.tenSanPham, SUM(cthd.soLuong) as tongSoLuong " +
					"FROM ChiTietHoaDon cthd " +
					"JOIN SanPham sp ON cthd.sanPham_id = sp.id " +
					"JOIN HoaDon hd ON cthd.hoaDon_id = hd.id " +
					"WHERE hd.ngayLap BETWEEN ? AND ? " +
					"GROUP BY sp.tenSanPham " +
					"ORDER BY tongSoLuong DESC";
			
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setTimestamp(1, new java.sql.Timestamp(ngayBatDau.getTime()));
		pstmt.setTimestamp(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
		
		ResultSet rs = pstmt.executeQuery();
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		boolean hasData = false;
		modelSanPham.setRowCount(0); // Xóa dữ liệu cũ
		while (rs.next()) {
			String tenSanPham = rs.getString("tenSanPham");
			int tongSoLuong = rs.getInt("tongSoLuong");
			dataset.addValue(tongSoLuong, "Số lượng", tenSanPham);
			modelSanPham.addRow(new Object[]{tenSanPham, tongSoLuong});
			hasData = true;
		}
		if (!hasData) {
			sanPhamChartPanel.setChart(null);
			sanPhamChartPanel.removeAll();
			JLabel lbl = new JLabel("Không có dữ liệu", JLabel.CENTER);
			lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
			sanPhamChartPanel.add(lbl);
			sanPhamChartPanel.revalidate();
			sanPhamChartPanel.repaint();
			return;
		}
		JFreeChart chart = ChartFactory.createBarChart(
			"Top 10 sản phẩm bán chạy",
			"Sản phẩm",
			"Số lượng",
			dataset,
			PlotOrientation.VERTICAL,
			false,
			true,
			false
		);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);
		plot.setRangeGridlinePaint(new Color(200, 200, 200));
		plot.setDomainGridlinePaint(new Color(200, 200, 200));
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(46, 204, 113));
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.BOLD, 14));
		renderer.setDefaultItemLabelPaint(Color.BLACK);
		renderer.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator(
			"{2}", new DecimalFormat("#,###")));
		renderer.setDefaultPositiveItemLabelPosition(
			new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0)
		);
		renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
		renderer.setShadowVisible(false);
		plot.getRangeAxis().setUpperMargin(0.15);
		chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 22));
		chart.getTitle().setPaint(new Color(44, 62, 80));
		chart.getTitle().setHorizontalAlignment(org.jfree.chart.ui.HorizontalAlignment.CENTER);
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 15));
		rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 13));
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 15));
		domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 13));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		sanPhamChartPanel.setPreferredSize(new Dimension(700, 350));
		sanPhamChartPanel.setChart(chart);
	}

	private void capNhatThongKeVoucher(Date ngayBatDau, Date ngayKetThuc) throws SQLException {
		// Thống kê voucher được sử dụng
		String sql = "SELECT v.maVoucher, COUNT(hd.id) as soLanSuDung " +
					"FROM Voucher v " +
					"LEFT JOIN HoaDon hd ON v.id = hd.voucher_id " +
					"WHERE hd.ngayLap BETWEEN ? AND ? " +
					"GROUP BY v.maVoucher " +
					"ORDER BY soLanSuDung DESC";
			
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setTimestamp(1, new java.sql.Timestamp(ngayBatDau.getTime()));
		pstmt.setTimestamp(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
		
		ResultSet rs = pstmt.executeQuery();
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		modelVoucher.setRowCount(0);
		while (rs.next()) {
			String maVoucher = rs.getString("maVoucher");
			int soLanSuDung = rs.getInt("soLanSuDung");
			dataset.addValue(soLanSuDung, "Số lần sử dụng", maVoucher);
			modelVoucher.addRow(new Object[]{maVoucher, soLanSuDung});
		}

		JFreeChart chart = ChartFactory.createBarChart(
			"Thống kê sử dụng voucher",
			"Mã voucher",
			"Số lần sử dụng",
			dataset,
			PlotOrientation.VERTICAL,
			true,
			true,
			false
		);

		// Tùy chỉnh biểu đồ
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(245, 245, 245));
		plot.setRangeGridlinePaint(new Color(200, 200, 200));
		plot.setDomainGridlinePaint(new Color(200, 200, 200));
		
		// Tùy chỉnh renderer
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new java.awt.GradientPaint(
			0.0f, 0.0f, new Color(155, 89, 182),
			0.0f, 100.0f, new Color(142, 68, 173)
		));
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.BOLD, 14));
		renderer.setDefaultItemLabelPaint(Color.BLACK);
		renderer.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator(
			"{2}", new DecimalFormat("#,###")));
		renderer.setDefaultPositiveItemLabelPosition(
			new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0)
		);
		renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
		renderer.setShadowVisible(false);
		plot.getRangeAxis().setUpperMargin(0.15);
		
		// Tùy chỉnh font và màu sắc
		chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 20));
		chart.getTitle().setPaint(new Color(44, 62, 80));
		
		// Tùy chỉnh trục
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
		rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
		domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		// Thêm legend
		chart.getLegend().setItemFont(new Font("Segoe UI", Font.PLAIN, 12));
		chart.getLegend().setBackgroundPaint(new Color(245, 245, 245));
		chart.getLegend().setFrame(BlockBorder.NONE);

		voucherChartPanel.setChart(chart);
	}

	private void capNhatThongKeNhanVien(Date ngayBatDau, Date ngayKetThuc) throws SQLException {
		// Thống kê doanh thu theo nhân viên
		String sql = "SELECT nv.hoTen, SUM(hd.thanhTien) as tongDoanhThu " +
					"FROM NhanVien nv " +
					"JOIN HoaDon hd ON nv.id = hd.nhanVien_id " +
					"WHERE hd.ngayLap BETWEEN ? AND ? " +
					"GROUP BY nv.hoTen " +
					"ORDER BY tongDoanhThu DESC";
			
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setTimestamp(1, new java.sql.Timestamp(ngayBatDau.getTime()));
		pstmt.setTimestamp(2, new java.sql.Timestamp(ngayKetThuc.getTime()));
		
		ResultSet rs = pstmt.executeQuery();
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		modelNhanVien.setRowCount(0);
		while (rs.next()) {
			String hoTen = rs.getString("hoTen");
			double tongDoanhThu = rs.getDouble("tongDoanhThu");
			dataset.addValue(tongDoanhThu, "Doanh thu", hoTen);
			modelNhanVien.addRow(new Object[]{hoTen, new DecimalFormat("#,### VND").format(tongDoanhThu)});
		}

		JFreeChart chart = ChartFactory.createBarChart(
			"Doanh thu theo nhân viên",
			"Nhân viên",
			"Doanh thu (VND)",
			dataset,
			PlotOrientation.VERTICAL,
			true,
			true,
			false
		);

		// Tùy chỉnh biểu đồ
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(new Color(245, 245, 245));
		plot.setRangeGridlinePaint(new Color(200, 200, 200));
		plot.setDomainGridlinePaint(new Color(200, 200, 200));
		
		// Tùy chỉnh renderer
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new java.awt.GradientPaint(
			0.0f, 0.0f, new Color(230, 126, 34),
			0.0f, 100.0f, new Color(211, 84, 0)
		));
		renderer.setDefaultItemLabelsVisible(true);
		renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.BOLD, 14));
		renderer.setDefaultItemLabelPaint(Color.BLACK);
		renderer.setDefaultItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator(
			"{2}", new DecimalFormat("#,### VND")));
		renderer.setDefaultPositiveItemLabelPosition(
			new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0)
		);
		renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());
		renderer.setShadowVisible(false);
		plot.getRangeAxis().setUpperMargin(0.15);
		
		// Tùy chỉnh font và màu sắc
		chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 20));
		chart.getTitle().setPaint(new Color(44, 62, 80));
		
		// Tùy chỉnh trục
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));
		rangeAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
		rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
		domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		// Thêm legend
		chart.getLegend().setItemFont(new Font("Segoe UI", Font.PLAIN, 12));
		chart.getLegend().setBackgroundPaint(new Color(245, 245, 245));
		chart.getLegend().setFrame(BlockBorder.NONE);

		nhanVienChartPanel.setChart(chart);
	}
}
